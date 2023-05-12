package com.aftersales.aftersales.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Installments {


    @JsonProperty("data_calculo")
    private Date calculationDate;

    @JsonProperty("tipo_calculo")
    private String typeCalculus;

    @JsonProperty("valor_total")
    private double totalValue;

    @JsonProperty("quantidade_parcelas")
    private int quantityInstallments;

    @JsonProperty("valor_parcelas")
    private double valueInstallments;

    @JsonProperty("dia_pagamento")
    private int paymentDay;

    @JsonProperty("percentual_taxa_juro")
    private double percentageInterestRate;
}
