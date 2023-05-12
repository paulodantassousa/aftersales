package com.aftersales.aftersales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aftersales.aftersales.exception.PaymentsException;
import com.aftersales.aftersales.request.dto.ChangePaymentDateRequestDTO;
import com.aftersales.aftersales.request.dto.QuantityInstallmentRequestDTO;
import com.aftersales.aftersales.response.dto.ChangePaymentDateResponseDTO;
import com.aftersales.aftersales.response.dto.QuantityInstallmentResponseDTO;
import com.aftersales.aftersales.service.OperationsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class OperationsController {

    @Autowired
    private OperationsService operationsService;

    @RequestMapping(value =  "/quantidade/parcelas", method = RequestMethod.POST)
    public ResponseEntity<QuantityInstallmentResponseDTO> quantityInstallment(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody QuantityInstallmentRequestDTO payment) throws PaymentsException{

        String afterSalesHeader = request.getHeader("itau-pos-venda-teste:" + " UUID");

        if (afterSalesHeader == null ||
                    !afterSalesHeader.matches("[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")) {
            throw new PaymentsException("'itau-pos-venda-teste' inválidado.");
        }
        response.addHeader("itau-pos-venda-teste", afterSalesHeader);
        return ResponseEntity.ok().body(operationsService.quantityInstallment(payment));
    }
    @RequestMapping(value =  "/alteracao/data", method = RequestMethod.POST)
    public ResponseEntity<ChangePaymentDateResponseDTO> changePaymentDate(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody ChangePaymentDateRequestDTO payment) throws PaymentsException{
        String afterSalesHeader = request.getHeader("itau-pos-venda-teste:" + " UUID");
        if (afterSalesHeader == null ||
                !afterSalesHeader.matches("[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")) {
            throw new PaymentsException("'itau-pos-venda-teste' inválidado.");
        }
        response.addHeader("itau-pos-venda-teste", afterSalesHeader);
        return ResponseEntity.ok().body(operationsService.changePaymentDate(payment));
    }
}
