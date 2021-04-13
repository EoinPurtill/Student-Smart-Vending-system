package commands;

import users.User;

public class ViewBalanceCommand extends Command implements CommandInterface{
    User user;

    public ViewBalanceCommand(User user){
        this.user = user;
    }

    public void execute(){
        System.out.printf("Balance:  $%.2f\n", user.getCredit());
    }
}
