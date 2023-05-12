package com.aftersales.aftersales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestApi {


    @JsonProperty("definir_quantidade_parcelas")
    private int defineInterestQuantity;

    @JsonProperty("definir_criterio_calculo")
    private String defineCalculationCriterion;

    @JsonProperty("definir_valor_contratacao")
    private BigDecimal defineContractValue;

    @JsonProperty("definir_data_contratacao")
    private String defineContractDate;




}
