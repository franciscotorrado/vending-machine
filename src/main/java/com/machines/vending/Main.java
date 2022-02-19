package com.machines.vending;

import com.machines.vending.application.ApplicationContext;
import lombok.Generated;

public class Main {

    @Generated
    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.restApplication().initialize(args);
    }

}
