package com.ra.base_spring_boot.services;

import com.stripe.exception.StripeException;

public interface IPaymentService {
    String processPayment(String token, Double amount) throws StripeException;
}
