package com.aftersales.aftersales.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @JsonProperty("id_contrato")
    private String idContract;

    @JsonProperty("ultimo_digito_contrato")
    private String lastContractDigit;

    @JsonProperty("numero_cpf_cnpj_cliente")
    private String customerCpfCnpjcnumber;

    @JsonProperty("data_contratacao")
    private String dateContract;

    @JsonProperty("ativo")
    private Boolean active;

    @JsonProperty("parcelas_em_atraso")
    private Boolean overdueInstallments;

}
