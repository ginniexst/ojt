package com.ra.base_spring_boot.dto.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentRequest {
    private int paymentId;
    private String token;
    private String paymentMethod;
    private Double amount;
}
