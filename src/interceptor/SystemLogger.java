package interceptor;

public class SystemLogger implements SystemLogInterceptor {

    @Override
    public void onLogEvent(LogContextObject co) {
        
        String message = co.getMessage();

        if(message == null){
            co.setMessage(null);
        }
        else{
            System.out.println(message);
        }
    }
    
}
