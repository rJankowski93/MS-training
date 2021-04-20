package com.org.rjankowski.ms.payment.repository;

import com.org.rjankowski.ms.payment.data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
