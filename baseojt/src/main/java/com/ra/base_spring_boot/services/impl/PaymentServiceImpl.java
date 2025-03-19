package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.services.IPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public String processPayment(String token, BigDecimal amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) (amount * 100));
        chargeParams.put("currency", "usd");
        chargeParams.put("source", token);
        return "";
    }
}
