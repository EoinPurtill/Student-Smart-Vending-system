package interceptor;

import java.util.Collection;

public class Dispatcher {

    private Collection<SystemLogInterceptor> interceptors;

    public Dispatcher(Collection<SystemLogInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    /** Register for all dispatched events */
    public void register(SystemLogInterceptor service) {
        interceptors.add(service);
    }

    /** Deregister service to no longer receive dispatched events. */
    public void deregister(SystemLogInterceptor service) {
        interceptors.remove(service);
    }

    public void dispatchSystemLog(LogContextObject context) {
        interceptors.forEach( i -> i.onLogEvent(context));
    }


}
