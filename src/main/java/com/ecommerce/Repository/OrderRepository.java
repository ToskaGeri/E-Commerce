package com.ecommerce.Repository;

import com.ecommerce.Models.Cart;
import com.ecommerce.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByCart(Cart cart);
}
