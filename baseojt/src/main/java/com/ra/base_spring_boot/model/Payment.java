package com.ra.base_spring_boot.model;

import com.ra.base_spring_boot.model.base.BaseObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment extends BaseObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "method_id", nullable = false)
    private PaymentMethod method;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointments appointment;

    private Status status;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    private String transactionId;

    private LocalDateTime createdAt = LocalDateTime.now();
}
