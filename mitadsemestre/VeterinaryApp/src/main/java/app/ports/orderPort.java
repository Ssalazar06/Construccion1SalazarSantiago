package app.ports;

import java.util.List;

import app.domain.models.Order;

public interface OrderPort {
    public void saveOrder(Order order)throws Exception;
    public Order findByOrderId(long orderId)throws Exception;
    public List<Order> getAllOrder()throws Exception;
}
