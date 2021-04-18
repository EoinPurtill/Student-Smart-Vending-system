package interceptor;

import java.util.ArrayList;
import java.util.Collection;

public class Dispatcher {

    private Collection<SystemLogInterceptor> logInterceptors;
    private Collection<DealSaleInterceptor> dealInterceptors;

    public Dispatcher() {
        logInterceptors = new ArrayList<>();
        dealInterceptors = new ArrayList<>();
    }
    public Dispatcher(SystemLogInterceptor logI, DealSaleInterceptor dealI){
        this.logInterceptors = new ArrayList<>();
        this.dealInterceptors = new ArrayList<>();
        this.logInterceptors.add(logI);
        this.dealInterceptors.add(dealI);
    }
    
    public void register(SystemLogInterceptor service) {
        logInterceptors.add(service);
    }
    public void register(DealSaleInterceptor service) {
        dealInterceptors.add(service);
    }

    public void deregister(SystemLogInterceptor service) {
        logInterceptors.remove(service);
    }
    public void deregister(DealSaleInterceptor service) {
        dealInterceptors.remove(service);
    }

    public void dispatchSystemLog(LogContextObject context) {
        logInterceptors.forEach( i -> i.onLogEvent(context));
    }
    public void dispatchDealSale(DealContextObject context) {
        dealInterceptors.forEach( i -> i.onDealSale(context));
    }
}
