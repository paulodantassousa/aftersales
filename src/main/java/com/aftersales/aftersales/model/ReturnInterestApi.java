package com.aftersales.aftersales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnInterestApi {

    @JsonProperty("percentual_juros")
    private double percentInterest;

    @JsonProperty("valor_total")
    private double totalValue;
}
