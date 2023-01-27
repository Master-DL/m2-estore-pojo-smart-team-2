package core.interfaces;

import core.data.Cart;
import core.data.Order;
import core.services.Client;
import estorePojo.exceptions.*;

public interface ILane {

    /**
     * Add an item to a cart.
     * If the cart does not exist yet, create a new one.
     * This method is called for each item one wants to add to the cart.
     *
     * @param cart   a previously created cart or null
     * @param client
     * @param item
     * @param qty
     * @return Implementation dependant.
     * Either a new cart at each call or the same cart updated.
     * @throws UnknownItemException        if the item is not delivered by the provider
     * @throws MismatchClientCartException if the given client does not own the given cart
     */
    Cart addItemToCart(
            Cart cart,
            Client client,
            Object item,
            int qty)
            throws UnknownItemException, InvalidCartException, MismatchClientCartException;

    /**
     * Once all the items have been added to the cart,
     * this method finish make the payment
     *
     * @param cart
     * @param address
     * @param bankAccountRef
     * @return the order
     * @throws UnknownItemException if the item is not delivered by the provider
     */
    Order pay(Cart cart, String address, String bankAccountRef)
            throws
            InvalidCartException, UnknownItemException,
            InsufficientBalanceException, UnknownAccountException;
}
