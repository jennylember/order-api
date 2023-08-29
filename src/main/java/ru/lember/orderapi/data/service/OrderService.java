package ru.lember.orderapi.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lember.orderapi.controller.dto.OrderDTO;
import ru.lember.orderapi.data.dao.Order;
import ru.lember.orderapi.data.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderDTO orderDTO) {
        orderRepository.save(convertToEntity(orderDTO));
    }

    public boolean updateOrder(OrderDTO orderDTO) {
        Optional<Order> orderToUpdate = orderRepository.findById(orderDTO.getId());
        if (orderToUpdate.isPresent()) {
            orderRepository.save(convertToEntity(orderDTO));
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteById(String orderId) {
        if (orderRepository.findById(orderId).isPresent()) {
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OrderDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::convertToDTO).orElse(null);
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        return dto;
    }

    public Order convertToEntity(OrderDTO dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        return entity;
    }

}
