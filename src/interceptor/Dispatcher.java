package interceptor;

import java.util.Collection;

public class Dispatcher {

    private Collection<Logger> interceptors;

    public Dispatcher(Collection<Logger> interceptors) {
        this.interceptors = interceptors;
    }

    /** Register for all dispatched events */
    public void register(Logger service) {
        interceptors.add(service);
    }

    /** Deregister service to no longer receive dispatched events. */
    public void deregister(Logger service) {
        interceptors.remove(service);
    }

    /** This event is called as soon as the request is received by the framework. */
    public void dispatchDealLog(ContextObject context) {
        interceptors.forEach( i -> i.onDealPurchase(context));
    }


}
