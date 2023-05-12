package com.aftersales.aftersales.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aftersales.aftersales.exception.PaymentsException;
import com.aftersales.aftersales.model.Contract;
import com.aftersales.aftersales.model.Installments;
import com.aftersales.aftersales.model.ReturnInterestApi;
import com.aftersales.aftersales.request.dto.ChangePaymentDateRequestDTO;
import com.aftersales.aftersales.request.dto.QuantityInstallmentRequestDTO;
import com.aftersales.aftersales.response.dto.ChangePaymentDateResponseDTO;
import com.aftersales.aftersales.response.dto.QuantityInstallmentResponseDTO;

@Service
public class OperationsService {

    private ReturnInterestApi requestInterestApiApi(Contract contract, Installments installments)throws PaymentsException {
        ReturnInterestApi returnInterestApi = new ReturnInterestApi();
        returnInterestApi.setTotalValue(10000.00);
        returnInterestApi.setPercentInterest(2);
        return returnInterestApi;
    }

    public QuantityInstallmentResponseDTO quantityInstallment(QuantityInstallmentRequestDTO quantityInstallment) throws PaymentsException {

        try{
            RulesService.isActiveContract(quantityInstallment.getContract().getActive());
            RulesService.validateQuantityInstallment(quantityInstallment.getInstallments().get(quantityInstallment.getInstallments().size() - 1).getQuantityInstallments()
                    ,quantityInstallment.getAdvanceQuantityInstallment().getNewQuantityInstallment());

            ReturnInterestApi returnInterestApi = requestInterestApiApi(quantityInstallment.getContract(),quantityInstallment.getInstallments().get(quantityInstallment.getInstallments().size() - 1));

            double installmentAmount = RulesService.recalculatesValue(returnInterestApi.getTotalValue(), quantityInstallment.getAdvanceQuantityInstallment().getNewQuantityInstallment());

            Installments installments = RulesService.newInstallments(installmentAmount,returnInterestApi,quantityInstallment.getAdvanceQuantityInstallment().getNewQuantityInstallment());

            var response = new QuantityInstallmentResponseDTO();

            BeanUtils.copyProperties(quantityInstallment,response);
            response.getInstallments().add(installments);

            return response;

        }catch (PaymentsException e){
            throw new PaymentsException(e.getMessage());
        }

    }

    public ChangePaymentDateResponseDTO changePaymentDate(ChangePaymentDateRequestDTO requestDTO) throws PaymentsException {
        try{
            var response = new ChangePaymentDateResponseDTO();

            RulesService.validatePaymentDate(requestDTO.getInstallments().get(0).getCalculationDate());

            RulesService.isActiveContract(requestDTO.getContract().getActive());
            RulesService.isIinstallmentInArrears(requestDTO.getContract().getOverdueInstallments());


            BeanUtils.copyProperties(requestDTO,response);

            Installments installments = RulesService.updatePaymentDay(requestDTO.getInstallments().get(requestDTO.getInstallments().size() - 1), requestDTO.getPaymentAdvanceAlterationData().getNewPaymentDate());
            response.getInstallments().add(installments);
            return response;

        }catch (PaymentsException e){
            throw new PaymentsException(e.getMessage());
        }

    }

}
