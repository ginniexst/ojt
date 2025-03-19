package com.ra.base_spring_boot.dto.resp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {
    private String status;
    private String transactionId;
    private String message;
}
