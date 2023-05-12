package com.aftersales.aftersales.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceQuantityInstallment {

    @JsonProperty("nova_quantidade_parcelas")
    private int newQuantityInstallment;
}
