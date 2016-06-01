package com.snap.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class FormDaoImpl implements FormDao{

	
	
	JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<String> getAllDataBag(String username) {
	
		String query = "select databag_name  from databag_detail ,user_detail  where databag_detail.databag_userid = user_detail.userid and user_detail.username = '"+username+"'";
		List<String> dataBag = new ArrayList<String>();
		dataBag = jdbcTemplate.queryForList(query,String.class);
		return dataBag;
	}

	@Override
	public List<String> getAllDataBagItems(String username,String databag) {
		
		String query = "select item_name from item_detail where item_databag_id=(select dd.databag_id from databag_detail  dd where dd.databag_name='"+ databag + "' and dd.databag_userid = (select userid from user_detail where username = '"+username+"') )";
		List<String> dataBagItems = new ArrayList<String>();
		dataBagItems = jdbcTemplate.queryForList(query,String.class);
		return dataBagItems;
		
	}

	@Override
	public void activityLog(String username, String databag, String item, String keyname, String prev_value,String new_value, String action) {
		String query = "insert into user_activity_logs values('"+username+"','"+databag+"','"+item+"','"+keyname+"','"+prev_value+"','"+new_value+"','"+action+"',now())";
		int row = jdbcTemplate.update(query);
		
	}

	
	@Override
	public void provideAccess(String username, String dataBag, String item) {
		
	String getdatabag_id="select databag_id from databag_detail,user_detail where user_detail.userid=databag_detail.databag_userid and user_detail.username='"+username+"' and databag_detail.databag_name='"+dataBag+"' " ;
	List<Integer> dataBag_id = new ArrayList<Integer>();
	   dataBag_id=jdbcTemplate.queryForList(getdatabag_id,Integer.class);
	   int id = dataBag_id.get(0);
	   System.out.println(id);
	   String query="INSERT INTO item_detail (item_name,item_databag_id) SELECT * FROM (SELECT '"+item+"', "+id+") AS tmp WHERE NOT EXISTS ( SELECT * FROM item_detail WHERE item_databag_id = "+id+" and item_name='"+item+"')";
	   int row =jdbcTemplate.update(query);

	}
	


}
