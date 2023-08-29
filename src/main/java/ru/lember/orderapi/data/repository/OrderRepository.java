package ru.lember.orderapi.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.lember.orderapi.data.dao.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}

