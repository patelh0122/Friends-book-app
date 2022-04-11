import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class AccountUser {
	
	private DataStorage data;
	
	public void setDataStorage(DataStorage d) 
	{
		this.data = d;
	}
	
	private String loginID;
	
	private String password;
	
	public AccountUser(String loginID) 
	{
		this.loginID = loginID;
	}
	
	
	public void welcome() 
	{
		Scanner input = new Scanner(System.in);
		String feature="";
    	
		while (feature != "0") 
		{
    	System.out.println("1: Select an update and post");
    	System.out.println("2: Check Notification");
    	System.out.println("3: Create a new post");
    	System.out.println("4: Friends");
    	System.out.println("5: Update profile");
    	System.out.println("6: Send a message");
    	System.out.println("7: Send a friend request");
    	System.out.println("8: See Hashtag in trends");
    	System.out.println("0: To exit");
    	
    	feature = input.next();
    	
    	while(feature != "0") 
    	{
		
		if(feature.equals("1"))
		{
			//Update and post
			wall();
		}
		else if(feature.equals("2"))
		{
			//Notification
			notifications();
		}
		else if(feature.equals("3")) 
		{
			//New post
			Newpost();
		}
		else if(feature.equals("4"))
		{
			//Friends
			friends();
		}
		else if(feature.equals("5"))
		{
			//Update profile
			update_profile();
		}
		else if(feature.equals("6"))
		{
			//Send a message
			message();
		}
		else if(feature.equals("7"))
		{
			// Send Friend request
			friend_request();
		}
		else if(feature.equals("8"))
		{
			//Hashtag in trends
		}
		
    	}
    	}
    	
    	

	}
	
	public void Newpost() 
	{	
		System.out.println("Write what you want to share today:");
		Scanner input = new Scanner(System.in);
		String Content= input.nextLine();
		String type = "Post";
		String Date = LocalDateTime.now().toString();
		
		data.CreateNewpost(loginID, Content, Date, type, -1);
		welcome();
		
		
	}
	
	public void friend_request() 
	{
		
		System.out.println("Please enter the UserID you want to send Friend request to:");
		Scanner input = new Scanner(System.in);
		String frienduserid = input.nextLine();
		String Date = LocalDateTime.now().toString();
		String type = "Request";
		String status ="Unread";
		String requeststatus ="pending";
		System.out.println("Write something for your friend to reach out:");
		String message = input.nextLine();
		
		
		data.Newfriendrequest(loginID, frienduserid, message, Date, type, status, requeststatus);
		welcome();
		
		
		
	}

	public void message() 
	{
		
		
		System.out.println("Please enter the UserID you want to send message to:");
		Scanner input = new Scanner(System.in);
		String fuserid = input.nextLine();
		String Date = LocalDateTime.now().toString();
		String type = "Message";
		String status ="Unread";
		System.out.println("Write your message below for your friend you want to send message:");
		String textmessage = input.nextLine();
		
		data.NewMessage(loginID, fuserid, textmessage, Date, type, status);
		welcome();
		
		
	}
	
	public void notifications()
	{
		String status = "Unread";
		String newstatus = "Read";
		String id ="";
		data.NewNotification(loginID, status, id, newstatus);
		welcome();
		
		
	}
	
	public void friends() 
	{
		String status = "Approved";
		data.myfriends(loginID, status);
		welcome();
	}
	
	public void update_profile() 
	{
		String option ="";
		String newschool="";
		String pass ="";
		String type = "Profile";
		int postid = 1;
//		System.out.println("1: To change the name of the user");
//		System.out.println("2: To change the name of the school of the user");
//		System.out.println("0: To exit");
		
//		option = input.nextLine();
		System.out.println("Please enter the name to update in your account");
		Scanner input = new Scanner(System.in);
		String newname = input.nextLine();
		System.out.println("Please enter the School name to update in your account");
		newschool = input.nextLine();
		data.newname(loginID, newname,newschool, type, postid);
		welcome();
		
		
	}
	public void wall() 
	{
		String Date = LocalDateTime.now().toString();
		data.update_post(loginID, Date);
		welcome();
	}
	
	public void hastag()
	{
		Scanner input = new Scanner(System.in);
		String in = input.next();
		ArrayList<String> hashtags = new ArrayList<String>();
		int indexHash=in.indexOf("#");
		while(indexHash>=0)
		{
			int indexEnd=in.indexOf(" ", indexHash+1);
			if(indexEnd>0) 
			{
				hashtags.add(in.substring(indexHash, indexEnd).toUpperCase());
				indexHash=in.indexOf("#", indexEnd);
			}
			else {
				hashtags.add(in.substring(indexHash).toUpperCase());
				break;
			}
		}
		for(String hashtag:hashtags) 
		{
			System.out.println(hashtag);
			data.update_hashtag(hashtag);
		}
		
	}
		
	
	
	
}
