package com.org.rjankowski.ms.coupons;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepository extends JpaRepository<Coupon, Long> {
    Coupon findByBarcode(String barcode);
}
