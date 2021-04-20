package com.org.rjankowski.ms.payment.resource;

import com.org.rjankowski.ms.payment.data.SaleRequest;
import com.org.rjankowski.ms.payment.data.SaleResponse;
import com.org.rjankowski.ms.payment.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sale")
@RequiredArgsConstructor
public class SaleResource {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponse> processSale(@RequestBody SaleRequest saleRequest) {
        return new ResponseEntity<>(saleService.processSale(saleRequest), HttpStatus.OK);
    }
}
