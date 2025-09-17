package com.hallak.CustomerInteractionServer.repositories;

import com.hallak.CustomerInteractionServer.entities.Order;
import com.hallak.CustomerInteractionServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser_Username(String username);
    Optional<Order> findByName(String name);

    @Query("SELECT o.user FROM Order o WHERE o.id = :orderId")
    User findUserByOrderId(@Param("orderId") Long orderId);

}
