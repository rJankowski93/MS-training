package com.org.rjankowski.ms.payment.resource;

import com.org.rjankowski.ms.payment.data.Cart;
import com.org.rjankowski.ms.payment.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartResource {

    private final CartRepository cartRepository;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Cart>> getCart(@PathVariable(value = "cartId") Long cartId) {
        return new ResponseEntity(cartRepository.findById(cartId), HttpStatus.OK);
    }

    @RequestMapping()
    public ResponseEntity<List<Cart>> list() {
        return new ResponseEntity(cartRepository.findAll(),HttpStatus.OK);
    }
}
