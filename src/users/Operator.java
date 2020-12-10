package users;

public class Operator
{
	private String id;
	private String passWord;
	
	
	public Operator(String idx, String pass)
	{
		id = idx;
		passWord = pass;
	}
	
	public boolean assertDetails(String idx, String pass)
	{
		return(id.compareTo(idx) == 0 && passWord.compareTo(pass) == 0);
	}
}
