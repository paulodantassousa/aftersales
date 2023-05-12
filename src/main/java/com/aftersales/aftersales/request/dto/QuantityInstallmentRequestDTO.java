package com.aftersales.aftersales.request.dto;


import java.io.Serializable;
import java.util.List;

import com.aftersales.aftersales.model.AdvanceQuantityInstallment;
import com.aftersales.aftersales.model.Contract;
import com.aftersales.aftersales.model.Installments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuantityInstallmentRequestDTO implements Serializable {

    private Contract contract;
    private List<Installments> Installments;
    private AdvanceQuantityInstallment advanceQuantityInstallment;


}
