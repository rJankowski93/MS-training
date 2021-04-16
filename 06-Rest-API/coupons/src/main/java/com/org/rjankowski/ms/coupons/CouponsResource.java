package com.org.rjankowski.ms.coupons;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponsResource {
    private final CouponsApiClient couponsApiClient;
    private final CouponsRepository couponsRepository;

    @PostMapping("/issue")
    public ResponseEntity issue(@RequestBody CouponsRequest couponsRequest) {
        ResponseEntity<Customer> customer = couponsApiClient.getCustomerById(couponsRequest.getCustomerId());
        if (customer.getBody() == null) {
            throw new RuntimeException();
        }
        Coupon coupon = new Coupon();
        coupon.barcode = "random";
        coupon.status = "ISSUED";
        coupon.customerId = customer.getBody().getId();
        couponsRepository.save(coupon);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/use")
    public ResponseEntity use(@RequestBody CouponsUseRequest couponsRequest) {
        ResponseEntity<Customer> customer = couponsApiClient.getCustomerById(couponsRequest.getCustomerId());
        if (customer.getBody() == null) {
            throw new RuntimeException();
        }
        Coupon byBarcode = couponsRepository.findByBarcode(couponsRequest.getBarcode());
        if (byBarcode == null) {
            throw new RuntimeException();
        }
        byBarcode.status = "USED";
        couponsRepository.save(byBarcode);
        return new ResponseEntity(HttpStatus.OK);
    }
}
