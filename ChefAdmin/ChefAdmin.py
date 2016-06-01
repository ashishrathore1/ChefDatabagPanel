#!/usr/bin/python
import mysql.connector
import sys
#import myconfig
import ConfigParser
cf = ConfigParser.ConfigParser()
cf.read('/home/shubham/chef_script/db.properties')
keyVal=cf.items("dbSection")
user=None
password=None
host=None
port=None
database=None


for items in keyVal:
	if items[0]=='db.user':
		user=items[1]
	if items[0]=='db.password':
		password=items[1]
	if items[0]=='db.host':
		host=items[1]
	if items[0]=='db.port':
		port=items[1]
	if items[0]=='db.database':
		database=items[1]

config = {
    'user': user,
    'password': password,
    'host': host,
    'port': port,
    'database': database,
    'raise_on_warnings': True,
    'buffered': True
}


cnx = mysql.connector.connect(**config)

def fetch_stmt(query):
	result=None
	try:
		cursor = cnx.cursor()
		cursor.execute(query)
		result = cursor.fetchall()
	except mysql.connector.Error as err:
		print("Something went wrong: {}".format(err))
	cursor.close()
	return result



def modify_stmt(query):
	try:
		cursor = cnx.cursor()
		cursor.execute(query)
		cnx.commit()
	except mysql.connector.Error as err:
		cnx.rollback()
		print("Something went wrong: {}".format(err))
	cursor.close()

def get_allUsers():
	query="select username from user_detail"
	result=fetch_stmt(query)
	if result!=[]:
		for user in result:
			print "\n"+user[0]
	else:
		print "No user exist"


def get_userDetail(userDetails):
	query="select userid from user_detail where username={}".format(userDetails)
	result=fetch_stmt(query)
	if result!=[]:
		userid=result[0][0]
		query="select * from databag_detail where databag_userid={}".format(userid)
		result1=fetch_stmt(query)
		for row in result1:
			print "The databag is: "+row[1]
			databag_id=row[0]
			query="select * from item_detail where item_databag_id={}".format(databag_id)
			result2=fetch_stmt(query)
			if result2!=[]:
				print "And the corresponding items are: "
				for items in result2:
					print items[2]
				print "\n \n"
			else:
				print "User has no access on this databag"
	else:
		print"user not found"
		return




def user_insert(userDetails):
	username=None
	details=None
	userId=None
	result=None
	for key,value in userDetails.iteritems():
		if key=='username':
			username='\''+value+'\''
		if key=='item_details':
			details=value
	query="select 1 from user_detail where username={}".format(username)
	result=fetch_stmt(query)
	if result==[]:
		add_user="INSERT INTO user_detail (username) VALUES ({})".format(username)
		modify_stmt(add_user)	
		query="select userid from user_detail where username={}".format(username)
		result=fetch_stmt(query)
		for row in result:
			userId=row[0]
		for key,value in details.iteritems():
			key='\''+key+'\''
			add_databag="insert into databag_detail(databag_name,databag_userid) values({},{})".format(key,userId)	
			modify_stmt(add_databag)
			query="select databag_id from databag_detail where databag_name={} and databag_userid={}".format(key,userId)
			result1=fetch_stmt(query)
			for row in result1:
				databagId=row[0]	
			for item in value:
				item='\''+item+'\''
				add_item="insert into item_detail(item_databag_id,item_name) values({}, {})".format(databagId,item)
				modify_stmt(add_item)
	else:
		print "User exists"
		return
		
		
			
		
def user_delete(userDetails):
	user=None
	query="select 1 from user_detail where username={}".format(userDetails)
	result=fetch_stmt(query)
	for row in result:
		user=row[0]
	if user!=None:
		delete_user="delete from user_detail where username={}".format(userDetails);
		modify_stmt(delete_user)
    


def user_revokeAccess(userDetails):
	username=None
	details=None
	userId=None
	result=None
	result1=None
	result2=None
	result3=None
	for key,value in userDetails.iteritems():
		if key=='username':
			username='\''+value+'\''
		if key=='item_details':
			details=value
	query="select 1 from user_detail where username={}".format(username)
	result=fetch_stmt(query)
	if result!=[]:	
		query="select userid from user_detail where username={}".format(username)
		result2=fetch_stmt(query)
		for row in result2:
			userId=row[0]
		for key,value in details.iteritems():
			key='\''+key+'\''
			query="select databag_id from databag_detail where databag_name={} and databag_userid={}".format(key,userId)
			result1=fetch_stmt(query)
			if result1!=[]:
				for row in result1:
					databagId=row[0]
				for item in value:
					item1='\''+item+'\''
					query="select 1 from item_detail where item_databag_id={} and item_name={}".format(databagId,item1)
					result3=fetch_stmt(query)
					if result3!=[]:
						delete_item="delete from item_detail where item_databag_id={} and item_name={}".format(databagId,item1)
						modify_stmt(delete_item)
					else:
						continue
			else:
				continue
	else:
		print "User doesn't exist"
		return
	





def user_addAccess(userDetails):
	username=None
	details=None
	userId=None
	result=None
	result1=None
	result2=None
	result3=None
	for key,value in userDetails.iteritems():
		if key=='username':
			username='\''+value+'\''
		if key=='item_details':
			details=value
	query="select 1 from user_detail where username={}".format(username)
	result=fetch_stmt(query)
	if result!=[]:	
		query="select userid from user_detail where username={}".format(username)
		result2=fetch_stmt(query)
		for row in result2:
			userId=row[0]
		for key,value in details.iteritems():
			key='\''+key+'\''
			query="select databag_id from databag_detail where databag_name={} and databag_userid={}".format(key,userId)
			result1=fetch_stmt(query)
			
			if result1!=[]:
				for row in result1:
					databagId=row[0]
					for item in value:
						item1='\''+item+'\''
						query="select 1 from item_detail where item_databag_id={} and item_name={}".format(databagId,item1)
						result3=fetch_stmt(query)
						if result3!=[]:
							continue
						else:
							add_item="insert into item_detail(item_databag_id,item_name) values({}, {})".format(databagId,item1)
							modify_stmt(add_item)

			else:
				add_databag="insert into databag_detail(databag_name,databag_userid) values({},{})".format(key,userId)	
				modify_stmt(add_databag)
				query="select databag_id from databag_detail where databag_name={} and databag_userid={}".format(key,userId)
				result1=fetch_stmt(query)
				for row in result1:
					databagId=row[0]
					for item in value:
						item1='\''+item+'\''
						query="select 1 from item_detail where item_databag_id={} and item_name={}".format(databagId,item1)
						result3=fetch_stmt(query)
						if result3!=[]:
							continue

						else:
							add_item="insert into item_detail(item_databag_id,item_name) values({},{})".format(databagId,item1)
							modify_stmt(add_item)			
	else:
		print "User doesn't exist. Create a new user"
		return


    
if __name__ == "__main__":
	print "Enter \n 1 to Insert user \n 2 to delete user \n 3 to add user access \n 4 to revoke user access \n 5 to get userDetails \n 6 to get all users"
	try:
		option=int(raw_input("Your choice? "))


	#    {'username':'singh.ruchi','item_details':{'staging':['cams','upgradetesting'],'production':['ims','payments']}} format for user_input
		if option==1:
			try:
				userDetails=input("Provide user details as dictionary \n e.g. {'username':'singh.ruchi','item_details':{'staging':['cams'],'production':['ims','payments']}} \n")
				if type(userDetails)==dict:
					user_insert(userDetails)
				else:
					print "Wrong entry"
					sys.exit(0)
			except:
				print "Wrong entry"
				sys.exit(0)
		if option==2:
			try:
				userDetails=raw_input("Provide username you want to delete \n e.g. shubham.01 \n")
				userDetails='\''+userDetails+'\''
				user_delete(userDetails)
			except:
				print "Wrong entry"
				sys.exit(0)

		if option==3:
			try:
				userDetails=input("Provide additional user details as dcitionary \n e.g. {'username':'singh.ruchi','item_details':{'staging':['cams'],'production':['ims','payments']}} \n")
				if type(userDetails)==dict:
					user_addAccess(userDetails)
				else:
					print "Wrong entry"
					sys.exit(0)
			except:
				print "Wrong entry"
				sys.exit(0)
				
		if option==4:
			try:
				userDetails=input("Provide user details as dictionary you want to revoke access from \n e.g. {'username':'singh.ruchi','item_details':{'staging':['cams'],'production':['ims','payments']}} \n")
				if type(userDetails)==dict:
					user_revokeAccess(userDetails)
				else:
					print "Wrong entry"
					sys.exit(0)
			except:
				print "Wrong entry"
				sys.exit(0)

		if option==5:
			try:
				userDetails=raw_input("Provide username you want information about \n e.g. shubham.01 \n")
				userDetails='\''+userDetails+'\''
				get_userDetail(userDetails)
			except:
				print "Wrong entry"
				sys.exit(0)

		if option==6:
			try:
				get_allUsers()
			except:
				print "Something went wrong"

	except:
		print "Wrong choice"

	cnx.close()


#INSERT INTO user_detail (username,role) SELECT * FROM (SELECT 'shubham', 'user') AS tmp WHERE NOT EXISTS (   SELECT username FROM user_detail WHERE username = 'shubham') LIMIT 1;