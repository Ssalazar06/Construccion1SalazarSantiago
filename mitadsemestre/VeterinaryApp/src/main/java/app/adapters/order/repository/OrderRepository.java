package app.adapters.order.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.order.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    public OrderEntity findByPetPetId(long petId);
    public OrderEntity findByPersonDocument(long personDocument);
    public OrderEntity findByUserUserId(long userId);
    public OrderEntity findByOrderId(long orderId);
}
