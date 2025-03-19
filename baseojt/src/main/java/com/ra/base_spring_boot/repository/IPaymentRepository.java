package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, BigDecimal> {

}
