package State;

import Time.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreTime {

    private ExponentialRandomStream customerArrived;
    private UniformRandomStream customerPay, customerPick;

    public StoreTime(StoreState store) {
        this.customerArrived = new ExponentialRandomStream(store.getLambda(), store.getSeed());
        this.customerPay = new UniformRandomStream(store.getPayMinTime(), store.getPayMaxTime(), store.getSeed());
        this.customerPick = new UniformRandomStream(store.getPickMinTime(), store.getPickMaxTime(), store.getSeed());
    }
    /**
     * @return nästa tid för en ankomsthändelse
     */
    public double arrivalTime() {
        return customerArrived.next();
    }

    /**
     * @return nösta tid för en plockhändelse
     */
    public double timePick() {
        return customerPick.next();
    }

    /**
     * @return nästa tid för en betalningshändelse
     */
    public double timePay() {
        return customerPay.next();
    }



}
