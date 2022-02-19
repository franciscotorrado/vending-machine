package com.machines.vending.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationContextTest {


    @Test
    void shouldCreateRestApplicationInstance() {
        //given
        final ApplicationContext applicationContext = new ApplicationContext();

        //when
        final Class<?> applicationClass = applicationContext.restApplication().getClass();

        //then
        assertTrue(applicationClass.isAssignableFrom(RestApplication.class));
    }
}
