package core.interfaces;

import estorePojo.exceptions.UnknownItemException;

public interface IConsult {

    /**
     * @param item a given item
     * @return the price of a given item
     * @throws UnknownItemException if the item is not delivered by the provider
     */
    double getPrice(Object item) throws UnknownItemException;

    /**
     * @param item a given item
     * @param qty  a given quantity
     * @return true if the given quantity of the given item is available
     * directly from the store
     * i.e. without having to re-order it from the provider
     */
    boolean isAvailable(Object item, int qty) throws UnknownItemException;
}
