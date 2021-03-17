package State;

import Time.*;

public class StoreTime {

    private ExponentialRandomStream customerArrived;
    private UniformRandomStream customerPay, customerPick;

    public StoreTime(StoreState store) {
        this.customerArrived = new ExponentialRandomStream(store.getLambda(), store.getSeed());
        this.customerPay = new UniformRandomStream(store.getPayMinTime(), store.getPayMaxTime(), store.getSeed());
        this.customerPick = new UniformRandomStream(store.getPickMinTime(), store.getPickMaxTime(), store.getSeed());
    }
    /**
     * @return n�sta tid f�r en ankomsth�ndelse
     */
    public double arrivalTime() {
        return customerArrived.next();
    }

    /**
     * @return n�sta tid f�r en plockh�ndelse
     */
    public double timePick() {
        return customerPick.next();
    }

    /**
     * @return n�sta tid f�r en betalningsh�ndelse
     */
    public double timePay() {
        return customerPay.next();
    }



}
