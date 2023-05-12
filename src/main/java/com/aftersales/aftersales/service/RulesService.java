package com.aftersales.aftersales.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aftersales.aftersales.exception.PaymentsException;
import com.aftersales.aftersales.model.Installments;
import com.aftersales.aftersales.model.ReturnInterestApi;

@Service
public class RulesService {

    private static String ADITAMENTO = "ADITAMENTO";


    public static void validateQuantityInstallment(double currentInstallment, double installmentContract){

        if (installmentContract < currentInstallment) {
            throw new PaymentsException("A parcela é inferior a atual");
        }
    }


    public static void isIinstallmentInArrears(Boolean installmentInArrears){
        if(installmentInArrears.equals(true)){
            throw new PaymentsException("Contrato com parcelas em atraso");
        }
    }

    public static Double recalculatesValue(Double totalValue, Integer qtdinstallment){
        return totalValue / qtdinstallment;
    }

    public static Installments updatePaymentDay(Installments installments, int paymentDay) {

        Installments installment = new Installments();

        BeanUtils.copyProperties(installments,installment);
        installment.setTypeCalculus(ADITAMENTO);
        installment.setPaymentDay(paymentDay);

        return installment;

    }

    public static void validatePaymentDate(Date date){

        LocalDate today =  LocalDate.now();

        LocalDate paymentDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long days = ChronoUnit.DAYS.between(today, paymentDay);

        if (days >= 10) {
            throw new PaymentsException("Dia de pagamento não pode estar mais que 10 dias adiante do dia atual de pagamento");
        }
    }

    public static Installments newInstallments(double value, ReturnInterestApi returnInterestApi, int advancePayment){


        Installments installments = new Installments();

        Date date = new Date();

        installments.setTotalValue(returnInterestApi.getTotalValue());

        installments.setValueInstallments(value);

        installments.setPaymentDay(02);
        installments.setQuantityInstallments(advancePayment);

        installments.setTypeCalculus(ADITAMENTO);
        installments.setCalculationDate(date);

        installments.setPercentageInterestRate(returnInterestApi.getPercentInterest());

        return installments;
    }

    public static void isActiveContract(Boolean active){
        if(active.equals(false)){
            throw new PaymentsException("O contrato precisa estar ativo");
        }
    }

}
