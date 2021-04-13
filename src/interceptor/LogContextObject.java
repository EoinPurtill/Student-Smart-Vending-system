package interceptor;

public class LogContextObject extends ContextObject {
    
    String message;
    public LogContextObject(){
        
    }

    public LogContextObject(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
