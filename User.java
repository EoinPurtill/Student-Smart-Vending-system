
public class User extends Operator
{
	private double balance;
	
	public User(String idx, String pass, double balance)
	{
		super(pass,idx);
		this.balance = balance;
	}
	
	public boolean assertDetails(String idx, String pass)
	{
		return(id.compareTo(idx) == 0 && password.compareTo(pass) == 0);
	}
}