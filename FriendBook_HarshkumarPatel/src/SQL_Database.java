import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SQL_Database implements DataStorage{
	
	 final String DATABASE_URL = 
             "jdbc:mysql://mis-sql.uhcl.edu/patelh0122?useSSL=false";
	 final String db_id = "patelh0122";
	 final String db_psw = "2034984";
     
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet resultSet = null;

	 public void createUserAccount(String loginID, String password, String name, String school)
	 {
	    
		try
        {
            
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "patelh0122", "2034984");
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            
            //do a query
            resultSet = statement.executeQuery("Select * from user "
                    + "where id = '" + loginID + "' or password = '"
                    + password + "'");
            
            if(resultSet.next())
            {
                //either the psw is used or the id is used
                System.out.println("Account creation failed");
            }
            else
            {
                //insert a record into UserAccount
            	int r = statement.executeUpdate("insert into user values"
                        + "('" + loginID + "', '" + password + "', '"
                        + name +"', '"
                        + school +"')");
                System.out.println("Account creation successful!");
                System.out.println();
            }
            connection.commit();
            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
             //close the database
             try
             {
                 //resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
	     
	 }

//@Override
	public AccountUser Accountsignin(String loginID, String password)
	{
		try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       //search the accountID in the user table
	       resultSet = statement.executeQuery("Select * from user "
	               + "where id = '" + loginID + "'");
	       
	       if(resultSet.next())
	       {
	           //the id is found, check the password
	           if(password.equals(resultSet.getString(2)))
	           {
	               System.out.println("Welcome to Friends Book");
	               return new AccountUser(loginID);
	           }
	           else
	           {
	              //password is not correct
	        	   System.out.println("The password id incorrect");
	        	   return null;
	           }
	       }
	       		else
	       		{
					return null;
	       		}
	       
	   }
	   catch (SQLException e)
	   {
	       e.printStackTrace();
	       return null;
	  
	       
	   }
	   finally
	   {
	       //close the database
	       try
	       {
	           connection.close();
	           statement.close();
	           resultSet.close();
	       }
	       catch(Exception e)
	       {
	           e.printStackTrace();
	       }
	       
	   }
	}

	@Override
	public void CreateNewpost(String loginID, String Content,String Date, String type, int parent) {
		// TODO Auto-generated method stub
		
		try{
		      //connect to the database
		      connection = DriverManager.getConnection(DATABASE_URL, 
		              "patelh0122", "2034984");
		      connection.setAutoCommit(false);
		      //crate the statement
		      statement = connection.createStatement();
		      Statement statement1  = connection.createStatement();
		      
		      //do a query
		      int s=statement.executeUpdate("Insert into post (userID, content, datetime, type, parent) values('" + loginID + "', '" + Content + "', '"
						+ Date + "', '" + type + "', '" + parent + "')");
		      System.out.println("Post created sucessfully!");
		      System.out.println();
		      connection.commit();
		     
		     
		      resultSet = statement1.executeQuery("Select * from post where userID = '" + loginID + "' and content = '"+ Content +"' and datetime = '" + Date +"'");
		      
		      while(resultSet.next()) 
		      {
		    	  
		    	  String id = resultSet.getString("id");
		    	  int r= statement.executeUpdate("Insert into updates (userid, type, postID) values ('" + loginID +"', '" +  type + "', '" + id +"')");
		    	  connection.commit();
		      }
		      
//		      System.out.println("Post created sucessfully!");
//		      System.out.println();
//		      connection.commit();
//		      connection.setAutoCommit(true);
//		      
		      connection.setAutoCommit(true);
		    
		  	}
	
		catch (SQLException e){
			e.printStackTrace();
		}
		finally
		{
		   //close the database
		   try
		   {
		       //resultSet.close();
		       statement.close();
		       connection.close();
		   }
		   catch(Exception e)
		   {
		       e.printStackTrace();
		   }
		}
		
	}
	
	@Override
	public void Newfriendrequest(String loginID, String frienduserid, String message, String Date, String type, String status, String requeststatus) 
	{
		try{
		      //connect to the database
		      connection = DriverManager.getConnection(DATABASE_URL, 
		              "patelh0122", "2034984");
		      connection.setAutoCommit(false);
		      //crate the statement
		      statement = connection.createStatement();
		      
		      //do a query
		      statement.executeUpdate("Insert into notifications (userid, userid2, message, datetime, type, status) values('" + loginID + "', '" + frienduserid + "', '"
						+ message + "', '" + Date + "', '" + type + "', '"+ status +"')");
		      statement.executeUpdate("Insert into friend (id1, id2, status) values('" + loginID + "', '" + frienduserid + "','" + requeststatus +"')");
		      
		      System.out.println("Friend request sent sucessfully!");
		      System.out.println();
		      connection.commit();
		      connection.setAutoCommit(true);
		  	}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally
		{
		   //close the database
		   try
		   {
		       //resultSet.close();
		       statement.close();
		       connection.close();
		   }
		   catch(Exception e)
		   {
		       e.printStackTrace();
		   }
		}
		
		
	}
	
	@Override
	public void NewMessage(String loginID,String fuserid,String textmessage,String Date,String type,String status) 
	{
		
		try{
		      //connect to the database
		      connection = DriverManager.getConnection(DATABASE_URL, 
		              "patelh0122", "2034984");
		      connection.setAutoCommit(false);
		      //crate the statement
		      statement = connection.createStatement();
		      
		      //do a query
		      statement.executeUpdate("Insert into notifications (userid, userid2, message, datetime, type, status) values('" + loginID + "', '" + fuserid + "', '"
						+ textmessage + "', '" + Date + "', '" + type + "', '"+ status +"')");
		      System.out.println("Message sent to your friends sucessfully!");
		      System.out.println();
		      connection.commit();
		      connection.setAutoCommit(true);
		  	}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally
		{
		   //close the database
		   try
		   {
		       //resultSet.close();
		       statement.close();
		       connection.close();
		   }
		   catch(Exception e)
		   {
		       e.printStackTrace();
		   }
		}
		
	}
	
	@Override
	public void NewNotification(String loginID, String status, String id, String newstatus) 
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		try{
		      //connect to the database
		      connection = DriverManager.getConnection(DATABASE_URL, 
		              "patelh0122", "2034984");
		      connection.setAutoCommit(false);
		      //crate the statement
		      statement = connection.createStatement();
		      Statement statement1 = connection.createStatement();
		      
		      resultSet = statement.executeQuery("Select * from notifications "
		               + "where userid2 = '" + loginID + "' and status = '" + status + "' ");
		      System.out.println("");
		      int row = 1;
		      System.out.println("Hello '" +loginID+ "' you have following notifications");
		      
		      while (resultSet.next())
		       {
		   
		    	  System.out.println(" "+row+": you have a new " +resultSet.getString("type")+" from: " +resultSet.getString("userid"));
		    	  
		    	  list.add(new String[] {resultSet.getString("id"), resultSet.getString("type"), resultSet.getString("userid"), resultSet.getString("message")});
		    	  row = row + 1;
		    	 
		       }
		      
		      System.out.println("Enter the number to view Notifications and 0 to exit");
	    	  Scanner input = new Scanner(System.in);
	    	  String a = input.nextLine();
	    	  
	    	  while(a!="0")
	    	  {
	    	  
		    	  if(a.equals("1")) 
		    	  {
		    		  
		    		  //System.out.println("You have a " +resultSet.getString("type")+" from: " +resultSet.getString("userid")+" has written "+resultSet1.getString("message") );
		    		  System.out.println("You 	have a " + list.get(0)[1] +" from: " + list.get(0)[2] +" has written "+ list.get(0)[3] );
		    		  id = list.get(0)[0];
		    		  statement.executeUpdate("UPDATE notifications SET status = '"+ newstatus + "' WHERE id = '"+ list.get(0)[0] +"' and type = '"+ list.get(0)[1] +"' ");
		    		  String type = list.get(0)[1];
		    		  if(type.equals("Request"))
		    		  {
		    			  System.out.println("Enter 1 to Approve");
		    			  System.out.println("Enter 2 to Decline");
		    			  System.out.println("Enter 0 to exit");
		    			  String b = input.nextLine();
		    			  
		    			  if(b.equals("0")) {
		    				  break;
		    			  }
		    			  else if(b.equals("1")) 
		    			  {
		    				  String response ="Approved";
		    				  
		    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(0)[2] + "' ");
		    			  }
		    			  else if(b.equals("2")) 
		    			  {
		    				  String response ="Declined";
		    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(0)[2] + "' ");
		    				  
		    			  }
		    			
		    		  }
		    		  else if(type.equals("Message")) 
		    		  {
		    			  System.out.println("1: To reply");
		    			  System.out.println("2: To ignore");
		    			  System.out.println("Enter 0 to exit");
		    			  String c = input.nextLine();
		    			  
		    			  if(c.equals("0"))
		    			  {
		    				  break;
		    			  }
		    			  else if(c.equals("1")) 
		    			  {
		    				  String fuserid = list.get(0)[2];
		    				  String Date = LocalDateTime.now().toString();
		    				  String tp = "Message";
		    				  String s ="Unread";
		    				  System.out.println("Write your message below for your friend you want to send message:");
		    				  String textmessage = input.nextLine();
		    				  statement.executeUpdate("Insert into notifications (userid, userid2, message, datetime, type, status) values('" + loginID + "', '" + fuserid + "', '"
		    							+ textmessage + "', '" + Date + "', '" + tp + "', '"+ s +"')");
		    			      System.out.println("Message sent to your friends sucessfully!");
		    				  
		    			  }
		    			  else if(c.equals("2")) 
		    			  {
		    				  break;
		    			  }
		    			  
		    			  
		    		  }
		    	
		    	  
		    	  }
	    	  
		    	  else if(a.equals("2")) 
		    	  {
		    		  System.out.println("You 	have a " + list.get(1)[1] +" from: " + list.get(1)[2] +" has written "+ list.get(1)[3] );
		    		  id = list.get(1)[0];
		    		  statement.executeUpdate("UPDATE notifications SET status = '"+ newstatus + "' WHERE id = '"+ list.get(1)[0] +"' and type = '"+ list.get(1)[1] +"' ");
		    		  String type = list.get(1)[1];
		    		  if(type.equals("Request"))
		    		  {
		    			  System.out.println("Enter 1 to Approve");
		    			  System.out.println("Enter 2 to Decline");
		    			  System.out.println("Enter 0 to exit");
		    			  String b = input.nextLine();
		    			  
		    			  if(b.equals("0")) {
		    				  break;
		    			  }
		    			  else if(b.equals("1")) 
		    			  {
		    				  String response ="Approved";
		    				  
		    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(1)[2] + "' ");
		    			  }
		    			  else if(b.equals("2")) 
		    			  {
		    				  String response ="Declined";
		    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(1)[2] + "' ");
		    				  
		    			  }
		    			
		    		  }
		    		  else if(type.equals("Message")) 
		    		  {
		    			  System.out.println("1: To reply");
		    			  System.out.println("2: To ignore");
		    			  System.out.println("Enter 0 to exit");
		    			  String c = input.nextLine();
		    			  
		    			  if(c.equals("0"))
		    			  {
		    				  break;
		    			  }
		    			  if(c.equals("1")) 
		    			  {
		    				  String fuserid = list.get(1)[2];
		    				  String Date = LocalDateTime.now().toString();
		    				  String tp = "Message";
		    				  String s ="Unread";
		    				  System.out.println("Write your message below for your friend you want to send message:");
		    				  String textmessage = input.nextLine();
		    				  statement.executeUpdate("Insert into notifications (userid, userid2, message, datetime, type, status) values('" + loginID + "', '" + fuserid + "', '"
		    							+ textmessage + "', '" + Date + "', '" + tp + "', '"+ s +"')");
		    			      System.out.println("Message sent to your friends sucessfully!");
		    				  
		    			  }
		    			  else if(c.equals("2")) 
		    			  {
		    				  break;
		    			  }
		    			  
		    			  
		    		  }
		    		  
		    		  
		    		  
		    	
//		    		  System.out.println("You have a " + list.get(1)[1] +" from: " + list.get(1)[2] +" has written "+ list.get(1)[3]);
//		    		  statement.executeUpdate("UPDATE notifications SET status = '"+ newstatus + "' WHERE id = '"+ list.get(1)[0] +"' and type = '"+ list.get(1)[1] +"' ");
//		    		  String type = list.get(1)[1];
//		    		  if(type.equals("Message")) 
//		    		  {
//		    			  System.out.println("1: To reply");
//		    			  System.out.println("2: To ignore");
//		    		  }

		    		  
	//	    		  
		    	  }
	    	  else if(a.equals("3")) 
	    	  {
	    		  System.out.println("You have a " + list.get(2)[1] +" from: " + list.get(2)[2] +" has written "+ list.get(2)[3] );
	    		  statement.executeUpdate("UPDATE notifications SET status = '"+ newstatus + "' WHERE id = '"+ list.get(2)[0] +"' and type = '"+ list.get(2)[1] +"' ");
	    		  String type = list.get(2)[1];
	    		  if(type.equals("Request"))
	    		  {
	    			  System.out.println("Enter 1 to Approve");
	    			  System.out.println("Enter 2 to Decline");
	    			  System.out.println("Enter 0 to exit");
	    			  String b = input.nextLine();
	    			  
	    			  if(b.equals("0")) {
	    				  break;
	    			  }
	    			  if(b.equals("1")) 
	    			  {
	    				  String response ="Approved";
	    				  
	    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(2)[2] + "' ");
	    			  }
	    			  else if(b.equals("2")) 
	    			  {
	    				  String response ="Declined";
	    				  statement.executeUpdate("UPDATE friend SET status = '"+ response + "' WHERE id1 = '"+ list.get(2)[2] + "' ");
	    				  break;
	    				  
	    			  }
	    			
	    		  }
	    		  else if(type.equals("Message")) 
	    		  {
	    			  System.out.println("1: To reply");
	    			  System.out.println("2: To ignore");
	    			  System.out.println("Enter 0 to exit");
	    			  String c = input.nextLine();
	    			  
	    			  if(c.equals("0"))
	    			  {
	    				  break;
	    			  }
	    			  if(c.equals("1")) 
	    			  {
	    				  String fuserid = list.get(2)[2];
	    				  String Date = LocalDateTime.now().toString();
	    				  String tp = "Message";
	    				  String s ="Unread";
	    				  System.out.println("Write your message below for your friend you want to send message:");
	    				  String textmessage = input.nextLine();
	    				  statement.executeUpdate("Insert into notifications (userid, userid2, message, datetime, type, status) values('" + loginID + "', '" + fuserid + "', '"
	    							+ textmessage + "', '" + Date + "', '" + tp + "', '"+ s +"')");
	    			      System.out.println("Message sent to your friends sucessfully!");
	    				  
	    			  }
	    			  else if(c.equals("2")) 
	    			  {
	    				  break;
	    			  }
	    			  
	    			  
	    		  }
	    	
	    		  
	    	  
	    	  }
	    	  }
	    	  
	    	  connection.commit();
	   
	    	  
	       
		    	  
		      	if (row == 0 )
			   {
				   System.out.println("You have no more notifications");
			   }
		      
		}
		      
		      catch (SQLException e){
					e.printStackTrace();
				}
				finally
				{
				   //close the database
				   try
				   {
				       //resultSet.close();
				       statement.close();
				       connection.close();
				   }
				   catch(Exception e)
				   {
				       e.printStackTrace();
				   }
				}
		
		
	
	

 
}
	@Override
	public void myfriends(String loginID, String status) 
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		try
		   {
		       
		       //connect to the database
				connection = DriverManager.getConnection(DATABASE_URL, 
		             "patelh0122", "2034984");
		       //create statement
		       statement = connection.createStatement();
		       Statement statement1 = connection.createStatement();
		       //search the accountID in the user table
		       resultSet = statement.executeQuery("Select * from friend "
		               + "where (id1 = '" + loginID + "' OR id2 = '" + loginID + "') and status = '" + status + "' ");
		       
		       
		       System.out.println("");
		       int row = 1;
		       
			   System.out.println("Hello '" +loginID+ "' you are friends with the following");
			   
			  
			   		while(resultSet.next())
			   		{
			   			list.add(new String[] {resultSet.getString("id"), resultSet.getString("id1"), resultSet.getString("id2"), resultSet.getString("status")});
			   			String id = resultSet.getString("id1");
			   			if(id.equals(loginID)) 
			   			{
			   			System.out.println(" "+row+" :" +resultSet.getString("id2"));
						row = row +1;
			   			}
			   			String id1 = resultSet.getString("id2");
			   			if(id1.equals(loginID)) 
			   			{
			   				System.out.println(" "+row+" :" +resultSet.getString("id1"));
							row = row +1;
			   				
			   			}		
			   		}
			   		System.out.println("Please enter your friend username to view the post of your friend");
		   			Scanner input = new Scanner(System.in);
			    	String user = input.nextLine();
			    	friend_profile(user);
						 
		
	}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally
		{
		   //close the database
		   try
		   {
		       //resultSet.close();
		       statement.close();
		       connection.close();
		   }
		   catch(Exception e)
		   {
		       e.printStackTrace();
		   }
		}

}

public void friend_profile(String user) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * FROM POST where userID ='" + user + "' ORDER BY id desc");
	       System.out.println(" '" +user +"' have following updates on his/her wall");
	       
	       while(resultSet.next()) 
	       {
	    	   String username = resultSet.getString("userID");
	    	   System.out.println(" "+username+" has posted : " +resultSet.getString("content") );
	    	   System.out.println(" on " +resultSet.getString("datetime"));
	    	   
	       }
	    	   
	    }
	catch (SQLException e)
	   {
	       e.printStackTrace();
	       
	   }
	   finally
	   {
	       //close the database
	       try
	       {
	           connection.close();
	           statement.close();
//	           resultSet.close();
	       }
	       catch(Exception e)
	       {
	           e.printStackTrace();
	       }
	       
	   }
	
	
}
public void newname(String loginID,String newname, String newschool, String type, int postid) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       statement.executeUpdate("UPDATE user SET name ='" + newname +"', school = '" + newschool +"'  WHERE id = '" + loginID +"'");
	       statement.executeUpdate("INSERT INTO updates (userid, type, postID) values('" + loginID + "', '" + type + "','" + postid +"')");
	       
	       System.out.println("You have successfully updated your name/school to your user account");
	       
	       
	       
	}
	catch (SQLException e)
	   {
	       e.printStackTrace();
	       
	   }
	   finally
	   {
	       //close the database
	       try
	       {
	           connection.close();
	           statement.close();
//	           resultSet.close();
	       }
	       catch(Exception e)
	       {
	           e.printStackTrace();
	       }
	       
	   }
}

public void update_post(String loginID, String Date) 
{
	ArrayList<String[]> list = new ArrayList<String[]>();
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       resultSet = statement.executeQuery("select * from updates u where u.userID != '" + loginID + "' and EXISTS (select 1 from Friend f where (f.id1 = '" + loginID + "' or f.id2 = '" + loginID + "' ) and f.status = 'Approved') order by u.id DESC LIMIT 0,3");
	       int row =1;
	       
	       while(resultSet.next()) 
	       {
	    	   list.add(new String[] {resultSet.getString("id"), resultSet.getString("userid"), resultSet.getString("type"), resultSet.getString("postID")});
	    	   System.out.println(" Hello '"+ loginID +"' you have following updates on your wall");
	    	   
	    	   System.out.println(" '"+ row+ "' :'" + resultSet.getString("userid") +"' has updated '"+ resultSet.getString("type") +"'");
	    	   row = row +1;
//	    	   System.out.println("'"+ row+ "''" +list.get(1)[1]+"' has updated '"+ list.get(1)[2] +"'");
//	    	   row = row +1;
//	    	   System.out.println("'"+ row+ "''" +list.get(2)[1]+"' has updated '"+ list.get(2)[2] +"'");
	    		      
	       }
	       System.out.println("Enter the number to view the update");
    	   Scanner input = new Scanner(System.in);
    	   String num = input.nextLine();
    	   
    	   while(num != "0") 
    	   {
    		   if(num.equals("1"))
    				   { 
    			   		String type = list.get(0)[2];
    		    	   String id = list.get(0)[3];
    		    	   
    		    	   if(type.equals("Post")) 
    		    	   {
    		    		   wall_post(loginID, id, Date);
    		    	   }
    		    	   
    		    	   else if (type.equals("Profile")) 
    		    	   {
    		    		   String userid = list.get(0)[1];
    		    		   profile_update(userid);
    		    	   }
    				   }
    		   if(num.equals("2")) 
    		   {
    			   String type = list.get(1)[2];
		    	   String id = list.get(1)[3];
		    	   
		    	   if(type.equals("Post")) 
		    	   {
		    		   wall_post(loginID, id, Date);
		    	   }
		    	   
		    	   else if (type.equals("Profile")) 
		    	   {
		    		   String userid = list.get(1)[1];
		    		   profile_update(userid);
		    	   }
				   }
    		   if(num.equals("3"))
    		   {
    			   String type = list.get(2)[2];
		    	   String id = list.get(2)[3];
		    	   
		    	   if(type.equals("Post")) 
		    	   {
		    		   wall_post(loginID, id, Date);
		    	   }
		    	   
		    	   else if (type.equals("Profile")) 
		    	   {
		    		   String userid = list.get(2)[1];
		    		   profile_update(userid);
		    	   }
				   }
    		   }
    	  
    	   }
	catch (SQLException e){
		e.printStackTrace();
	}
	finally
	{
	   //close the database
	   try
	   {
	       //resultSet.close();
	       statement.close();
	       connection.close();
	   }
	   catch(Exception e)
	   {
	       e.printStackTrace();
	   }
	}

	    	
	       

}
public void wall_post(String loginID,String id, String Date) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       Statement statement1 = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * FROM post where id = '" + id +"'");
	       if(resultSet.next()) 
	       {
	    	   System.out.println( '"'+ resultSet.getString("userID")+"' have posted  '" + resultSet.getString("content") +"' on  '" + resultSet.getString("datetime") +"'");
	    	   System.out.println("Press 1 to comment/Press 2 to view comments on the post or zero to exit");
	    	   Scanner input = new Scanner(System.in);
	    	   String opt = input.next();
	    	   while(opt.equals("1")) 
	    	   {
	    		  
	    		   System.out.println("Enter your comment");
	    		   String comment = input.nextLine();
	    		   String ctype = "comment";
	    		   statement.executeUpdate("INSERT INTO POST (userID, content, datetime, type, parent) values('" + loginID + "', '" + comment + "', '"+ Date + "', '" + ctype + "', '" + id + "')");
//	    		   post_comment(loginID, ctype, Date);
//	    		   statement.executeUpdate("INSERT INTO UPDATES (userID, type, postID) values('" + loginID + "', '" + comment + "', '"+ Date + "', '" + ctype + "', '" + parent + "')");
	    		   System.out.println("Your comment is posted on the post");
	    	   }
	    	   if(opt.equals("2")) 
    		   {
    			   String thetype ="comment";
    			   comments(id, thetype);
    		   }
	    	 
	       }
	       else
	       {
	    	   System.out.println("You have no more updates on post");
	       }
	       
	       
	
}
	catch (SQLException e){
		e.printStackTrace();
	}
	finally
	{
	   //close the database
	   try
	   {
	       //resultSet.close();
	       statement.close();
	       connection.close();
	   }
	   catch(Exception e)
	   {
	       e.printStackTrace();
	   }
	}

}
public void comments(String id, String thetype) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       Statement statement1 = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * from post where parent = '" + id + "' and type = '" + thetype + "' ");
	       int row = 1;
	       while(resultSet.next()) 
	       {
	    	   System.out.println(" '"+ row+ "' :'" + resultSet.getString("userid") +"' has commented '"+ resultSet.getString("content") +"'");
	    	   row =row +1;
	       }
	       
	    	   System.out.println("There is no more comment on the post");
	     
	   }
	catch (SQLException e){
		e.printStackTrace();
	}
	finally
	{
	   //close the database
	   try
	   {
	       resultSet.close();
	       statement.close();
	       connection.close();
	   }
	   catch(Exception e)
	   {
	       e.printStackTrace();
	   }
	}
}
//public void post_comment(String loginID, String ctype, String Date) 
//{
//	try
//	   {
//	       
//	       //connect to the database
//			connection = DriverManager.getConnection(DATABASE_URL, 
//	             "patelh0122", "2034984");
//	       //create statement
//	       statement = connection.createStatement();
////	       statement statement1  = connection.createStatement();
//	       resultSet = statement.executeQuery("Select * from post where userid = '" + loginID + "' and type = '" + ctype + "' and datetime = '"+ Date + "' ");
//	       String postid = resultSet.getString("id");
//	       
//	       while (resultSet.next())
//	       {
//	    	   statement.executeUpdate("INSERT INTO UPDATES (userID, type, postID) values('" + loginID + "', '" + ctype + "', '" + postid + "')");  
//	    	   System.out.println("Your comment is successfully updated on the post");
//	       }
//	   }
//
//	catch (SQLException e)
//	{
//	   		e.printStackTrace();
//	}
//	
//	   	finally
//	   	{
//	   	   //close the database
//	   	   try
//	   	   {
//	   	       resultSet.close();
//	   	       statement.close();
//	   	       connection.close();
//	   	   }
//	   	   catch(Exception e)
//	   	   {
//	   	       e.printStackTrace();
//	   	   }
//	   	}
//	       
//}

public void profile_update(String userid) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * from user where id = '" + userid + "' ");
	       while(resultSet.next()) 
	       {
	    	   System.out.println(""+userid+" has updated '" + resultSet.getString("name"));
	       }
	
}
	catch (SQLException e)
	{
	   		e.printStackTrace();
	}
	
	   	finally
	   	{
	   	   //close the database
	   	   try
	   	   {
	   	       //resultSet.close();
	   	       statement.close();
	   	       connection.close();
	   	   }
	   	   catch(Exception e)
	   	   {
	   	       e.printStackTrace();
	   	   }
	   	}
}
public void update_hashtag(String hashtag) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * from hashtags where word ='" + hashtag + "' ");
	       int freq = 1;
	       if (resultSet.getString("word").equals(null)) 
	       {
	    	   statement.executeUpdate("INSERT into hashtags (word, freq) values ('" + hashtag + "','" + freq + "' )");
	       }
	       else if(resultSet.getString("word").equals(hashtag)) 
	       {
	    	   int newfreq = resultSet.getInt("freq");
	    	   newfreq = newfreq + 1;
	    	   statement.executeUpdate("UPDATE hashtags SET freq = '"+ newfreq + "' WHERE word ='" + hashtag + "' ");
	    	   hashtag_post(hashtag);
	    	   
	       }
	   }
	catch (SQLException e)
	{
	   		e.printStackTrace();
	}
	
	   	finally
	   	{
	   	   //close the database
	   	   try
	   	   {
	   	       //resultSet.close();
	   	       statement.close();
	   	       connection.close();
	   	   }
	   	   catch(Exception e)
	   	   {
	   	       e.printStackTrace();
	   	   }
	   	}
}
public void hashtag_post(String hashtag) 
{
	try
	   {
	       
	       //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
	             "patelh0122", "2034984");
	       //create statement
	       statement = connection.createStatement();
	       resultSet = statement.executeQuery("SELECT * from post where type ='Post' ");
	       while(resultSet.next()) 
	       {
	    	   if(resultSet.getString("content").contains(hashtag)) 
	    	   {
	    		   System.out.println(resultSet.getString("content") );
	    	   }
	       }
	   }
	catch (SQLException e)
	{
	   		e.printStackTrace();
	}
	
	   	finally
	   	{
	   	   //close the database
	   	   try
	   	   {
	   	       //resultSet.close();
	   	       statement.close();
	   	       connection.close();
	   	   }
	   	   catch(Exception e)
	   	   {
	   	       e.printStackTrace();
	   	   }
	   	}

} 
}

        
        
        
