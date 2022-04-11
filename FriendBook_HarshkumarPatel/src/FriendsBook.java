import java.util.ArrayList;
import java.util.Scanner;




public class FriendsBook {
	
	public static DataStorage data = new SQL_Database();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String sel ="";
		
		while(!sel.equals("x"))
		{
			System.out.println();
			System.out.println("Welcome to FriendsBook");
			System.out.println("1: Register");
			System.out.println("2: Login");
			System.out.println("x:Leave");
			//user enters an option
			sel=input.nextLine();
			if(sel.equals("1"))
			{
				//register
				new AccountCreator(data).register();
			}
			else if(sel.equals("2"))
			{
				//login
				new AccountLogin(data).signin();
				
				//new AccountCreator(data).signin();
			}
					
				

		}
		


	}
	


}
