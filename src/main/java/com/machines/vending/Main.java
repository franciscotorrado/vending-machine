package com.machines.vending;

import com.machines.vending.application.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.restApplication().initialize(args);
    }

}
