package ru.lember.orderapi.controller;

import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {

    public synchronized String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
