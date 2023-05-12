package com.aftersales.aftersales.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.aftersales.aftersales.exception.PaymentsException;
import com.aftersales.aftersales.model.PaymentAdvanceAlterationData;
import com.aftersales.aftersales.model.Contract;
import com.aftersales.aftersales.model.Installments;
import com.aftersales.aftersales.request.dto.ChangePaymentDateRequestDTO;
import com.aftersales.aftersales.response.dto.ChangePaymentDateResponseDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationsServiceTest {

    @Autowired
    private OperationsService operationsService;

    @Test
    void testChangePaymentDate() throws PaymentsException {

        Installments installments = new Installments();
        Date date = new Date();
        installments.setCalculationDate(date);
        installments.setPaymentDay(2);
        installments.setTotalValue(1000);

        Contract contract = new Contract();
        contract.setActive(true);
        contract.setOverdueInstallments(false);

        PaymentAdvanceAlterationData paymentAdvanceAlterationData = new PaymentAdvanceAlterationData();
        paymentAdvanceAlterationData.setNewPaymentDate(2);

        List<Installments> installmentsList = new ArrayList<>();
        installmentsList.add(installments);

        ChangePaymentDateRequestDTO requestDTO = new ChangePaymentDateRequestDTO();
        requestDTO.setContract(contract);
        requestDTO.setInstallments(installmentsList);
        requestDTO.setPaymentAdvanceAlterationData(paymentAdvanceAlterationData);

        ChangePaymentDateResponseDTO responseDTO = operationsService.changePaymentDate(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(2, responseDTO.getInstallments().get(0).getPaymentDay());

    }






}
