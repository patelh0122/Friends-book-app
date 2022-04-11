
public interface DataStorage {
	
	public void createUserAccount(String loginID, String password, String name, String school);
	public AccountUser Accountsignin(String loginID, String password);
	public void CreateNewpost(String loginID, String Content,String Date, String type,int parent);
	public void Newfriendrequest(String loginID, String frienduserid, String message, String Date, String type, String status, String requeststatus);
	public void NewMessage(String loginID,String fuserid,String textmessage,String Date,String type,String status);
	public void NewNotification(String loginID, String status, String id,String newstatus);
	public void myfriends(String loginID, String status);
	public void newname(String loginID,String newname,String newschool, String type,int postid);
	public void update_post(String loginID, String Date);
	public void update_hashtag(String hashtag);
}
	