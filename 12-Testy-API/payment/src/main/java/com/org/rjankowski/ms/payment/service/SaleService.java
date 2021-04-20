package com.org.rjankowski.ms.payment.service;

import com.org.rjankowski.ms.payment.CustomerApiClient;
import com.org.rjankowski.ms.payment.data.Cart;
import com.org.rjankowski.ms.payment.data.Customer;
import com.org.rjankowski.ms.payment.data.SaleRequest;
import com.org.rjankowski.ms.payment.data.SaleResponse;
import com.org.rjankowski.ms.payment.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {


    private final CustomerApiClient customerApiClient;
    private final CartRepository cartRepository;

    public SaleResponse processSale(SaleRequest saleRequest){
        ResponseEntity<Customer> customer = customerApiClient.getCustomer(saleRequest.getCustomerId());
        if (customer.getBody() == null) {
            throw new RuntimeException("Customer not exist");
        }
        Optional<Cart> cart = cartRepository.findById(saleRequest.getCartId());
        if (cartRepository.findById(saleRequest.getCartId()).isEmpty()) {
            throw new RuntimeException("Cart not exist");

        }
        return new SaleResponse(cart.get().getCartItems().size() * 5L);
    }
}
