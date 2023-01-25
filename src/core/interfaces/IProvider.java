package core.interfaces;

import core.services.Store;
import estorePojo.exceptions.UnknownItemException;

public interface IProvider {
    double getPrice(Object item) throws UnknownItemException;

    int order(Store store, Object item, int qty) throws UnknownItemException;
}
