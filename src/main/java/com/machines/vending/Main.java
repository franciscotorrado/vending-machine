package com.machines.vending;

import com.machines.vending.application.ApplicationContext;

public class Main {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.restApplication().initialize(args);
    }

}
