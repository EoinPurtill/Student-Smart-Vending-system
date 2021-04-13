package interceptor;

import java.util.Collection;

public class Dispatcher {

    private Collection<Interceptor> interceptors;

    public Dispatcher(Collection<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    /** Register for all dispatched events */
    public void register(Interceptor service) {
        interceptors.add(service);
    }

    /** Deregister service to no longer receive dispatched events. */
    public void deregister(Interceptor service) {
        interceptors.remove(service);
    }

    /** This event is called as soon as the request is received by the framework. */
    public void dispatchIncomingDealRequest(ContextObject context) {
        interceptors.forEach( i -> i.interceptorExecute(context));
    }

    /** This event is called prior to the HttpResponse being sent. */
    public void dispatchOutgoingDealResponse(ContextObject context) {
        interceptors.forEach(i -> i.interceptorExecute(context));
    }
}
