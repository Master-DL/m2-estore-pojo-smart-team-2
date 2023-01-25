package core.data;

import core.services.Client;
import estorePojo.exceptions.UnknownItemException;

import java.util.*;

public class Order {

    /**
     * The total number of orders emitted so far.
     */
    private static int numOrders;

    /**
     * The index of this order.
     */
    private int num;

    private Client client;
    private Object item;
    private String address;
    private String bankAccountRef;

    /**
     * The date at which the ordered is issued.
     */
    public Date date;

    /**
     * The delay for delivering the items in the order.
     */
    private int delay;

    /**
     * The items currently in the order.
     */
    private Set<Object> items = new HashSet<>();

    /**
     * The quantities of each item ordered. key=item, value=quantity.
     */
    private Map<Object, Integer> itemQuantities = new HashMap<>();

    /**
     * The individual prices of each item ordered. key=item, value=price.
     */
    private Map<Object, Double> itemPrices = new HashMap<>();

    private Order() {
        num = numOrders++;
        date = new Date();
    }

    public Order(Client client, String address, String bankAccountRef) {
        this();
        this.client = client;
        this.address = address;
        this.bankAccountRef = bankAccountRef;
    }

    /**
     * Add an item to the order.
     *
     * @param item
     * @param qty
     * @param price
     * @throws UnknownItemException if the item is not delivered by the provider
     */
    public void addItem(Object item, int qty, double price) throws UnknownItemException {

        if (itemPrices.containsKey(item)) {
            double oldPrice = itemPrices.get(item);
            if (oldPrice != price)
                throw new UnknownItemException(
                        "Item " + item + " price (" + price + ") added to cart is different from the price (" + oldPrice
                                + ") of the same item already in the cart");
        }

        items.add(item);
        itemPrices.put(item, price);

        int newQty = qty;
        if (itemQuantities.containsKey(item)) {
            newQty += itemQuantities.get(item);
        }
        itemQuantities.put(item, newQty);
    }

    /**
     * Compute the total amount of the order
     */
    public double computeAmount() {

        double amount = 0;

        for (Object item : items) {
            int qty = itemQuantities.get(item);
            double price = itemPrices.get(item);
            amount += qty * price;
        }

        return amount;
    }

    /**
     * @return Returns the delay for delivering this order.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Set the delay for this order. The delay is the highest delay for delivering
     * all the items of an order.
     */
    public void setDelay(int delay) {
        if (delay > this.delay)
            this.delay = delay;
    }

    public int getKey() {
        return num;
    }

    public String toString() {
        String msg = "Order #" + num + " ";
        msg += "amount: " + computeAmount() + " ";
        msg += "delay: " + getDelay() + "h ";
        msg += "issued on: " + date;
        return msg;
    }
}
