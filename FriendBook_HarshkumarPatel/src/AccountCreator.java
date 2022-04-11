import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccountCreator {

	//private AccountCreator theLoginAccount;

    public DataStorage data; 
    
   // DataStorage data1 = new SQL_Database();

    public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	public AccountCreator(DataStorage d)
    {
        //when the system is created, no login account yet
    	this.data = d;
        //this.theLoginAccount = null;
    }
    	
    private String loginID="";
	private String password="";
	private String name="";
	private String school="";
	public void register() {
		// TODO Auto-generated method stub
		//declare the variables
				
				
				Scanner input = new Scanner(System.in);
				boolean loginReqLen = false;
				boolean loginReqLetter = false;
				boolean loginReqDigit = false;
				boolean loginReqSpecial = false;
				boolean passwordreqlen = false;
				boolean passwordsameasloginId = false;
				System.out.println("Please enter your new login id for registration");
				
				while(!loginReqLen||!loginReqLetter || !loginReqDigit||!loginReqSpecial || !passwordreqlen || !passwordsameasloginId)
				{
					System.out.println("Please enter your new login id(3-10 characters) for registration");
					loginID = input.next();
					if((loginID.length()>=3)&&(loginID.length()<=10))
					{
						loginReqLen= true;
					}
					for(int i=0; i<loginID.length();i++) 
					{
						char c = loginID.charAt(i);//get each character in the string
						if(c=='#'|| c=='?'|| c=='!'|| c=='*') {
							
							loginReqSpecial =true;
						}
						int ascii =(int)c; //convert the character into the ascii code
						if(ascii >=48 && ascii <=57)
						{
							loginReqDigit = true;		
							
						}
						if((ascii >=65 && ascii <=90)||(ascii>=97 && ascii<=122)) 
						{
							loginReqLetter =true;
						}
						
					}
					System.out.println("Please create your password for your user Id in (3-10 characters)"); 
					password = input.next();
					if((password.length()>=3)&&(password.length()<=10))
					{
						passwordreqlen = true;
					}
					if(!password.equals(loginID))
					{
						passwordsameasloginId = true;
					}
					System.out.println("Please enter your Name:");
					name = input.next();
					System.out.println("Please enter your school:");
					school = input.next();
					
					data.createUserAccount(loginID, password, name, school);
					
					input.close();
					
				}
				
	}
}
	
	/*public void signin() 
	{
		Scanner input = new Scanner(System.in);

		
		System.out.println("Please your your Username for Signin");
		loginID = input.next();
		System.out.println("Please enter your password");
		password = input.next();
	
		 
		if(data.Accountsignin(loginID, password)) {
			AccountCreator.welcome(loginID,password);
		}
		else
        {
            System.out.println("The login failed");
            System.out.println();
        }
		
		
		/*if(theLoginAccount != null)
        {
           // theLoginAccount.setData(data);
			AccountCreator.welcome(loginID,password);
            
        }
        else
        {
            System.out.println("The login failed");
            System.out.println();
        }
		*/
		//welcome();
		
	
	/*
	public static void welcome(String login, String psw) 
	{
		Scanner input = new Scanner(System.in);
		String feature="";
    	
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
		}
		else if(feature.equals("2"))
		{
			//Notification
		}
		else if(feature.equals("3")) 
		{
			//New post
			new feature(data).Newpost();
		}
		else if(feature.equals("4"))
		{
			//Friends
		}
		else if(feature.equals("5"))
		{
			//Update profile
		}
		else if(feature.equals("6"))
		{
			//Send a message
		}
		else if(feature.equals("7"))
		{
			// Send Friend request
		}
		else if(feature.equals("8"))
		{
			//Hashtag in trends
		}
    	}
	}*/
	
	

