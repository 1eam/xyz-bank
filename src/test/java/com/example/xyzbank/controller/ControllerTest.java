package com.example.xyzbank.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();

    @Test
    void greetingTest() {
        String result = controller.greeting();
        assertEquals("Hello world", result);
    }
}