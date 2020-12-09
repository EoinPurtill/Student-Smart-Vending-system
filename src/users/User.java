package users;

public class User{

    private String id;
    private String pWord;
    private double credit;
    private String salesHistory;
	
	
	public User(String idx, String pass)
	{
		id = idx;
        pWord = pass;
        credit = 0.0;
	}
    


	public boolean assertDetails(String idx, String pass)
	{
		return(id.compareTo(idx) == 0 && pWord.compareTo(pass) == 0);
	}


}