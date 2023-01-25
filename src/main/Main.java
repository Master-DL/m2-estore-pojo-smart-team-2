package main;

import core.interfaces.IBank;
import core.interfaces.IProvider;
import core.services.Bank;
import core.services.Client;
import core.services.Provider;
import core.services.Store;

public class Main {

    public static void main(String[] args) {
        IProvider prov = new Provider();
        IBank bank = new Bank();
        Store store = new Store(prov, bank);
        Runnable cl = new Client(store);

        cl.run();

    }

}
