import java.util.Scanner;

public class AccountLogin {
	
	private AccountUser LoginAccount;
	private DataStorage data;
	
    public AccountLogin(DataStorage data2) {
		// TODO Auto-generated constructor stub
    	this.data = data2;
	}

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
	
	
    
    private String loginID="";
	private String password="";
	private String name="";
	private String school="";
	
	
	public void signin() 
	{
		Scanner input = new Scanner(System.in);

		
		System.out.println("Please your your Username for Signin");
		loginID = input.next();
		System.out.println("Please enter your password");
		password = input.next();
	
		LoginAccount = data.Accountsignin(loginID, password);
		
		if(LoginAccount != null)
		{
			System.out.println("The login Succeded");
            System.out.println();
            LoginAccount.setDataStorage(data);
            LoginAccount.welcome();
		}
		else
        {
            System.out.println("The login failed");
            System.out.println();
        }

	}
	
}
