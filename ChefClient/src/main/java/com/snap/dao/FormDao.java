package com.snap.dao;

import java.util.List;

public interface FormDao {
	
	 public List<String> getAllDataBag(String user_id);
	 public List<String> getAllDataBagItems(String username,String databag);
	 public void activityLog(String username ,String databag, String item, String keyname, String prev_value, String new_value,String action);
	 public void provideAccess(String username,String dataBag,String item);
	
}
