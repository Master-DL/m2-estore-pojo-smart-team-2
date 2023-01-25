package main;

import core.services.Bank;
import core.services.Client;
import core.services.Provider;
import core.services.Store;

public class Main {

    public static void main(String[] args) {
        Provider prov = new Provider();
        Bank bank = new Bank();
        Store store = new Store(prov, bank);
        Client cl = new Client(store);

        cl.run();

    }

}
