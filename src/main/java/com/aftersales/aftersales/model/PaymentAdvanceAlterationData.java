package com.aftersales.aftersales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAdvanceAlterationData {

    @JsonProperty("nova_data_pagamento")
    private int newPaymentDate;
}
