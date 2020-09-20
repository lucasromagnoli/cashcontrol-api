package br.com.lucasromagnoli.cashcontrol.movimentation;

import lombok.Data;

@Data
public class MovimentationDTO {
    //Movimentation
    private String value;
    private String description;
    private String type; //Receita/Despesa
    private String date;
    private String frequency; // null, none, monthly, yearly

    //Expense
    private String payment; //Cash, installment ou subscription

    //Installment
    private String quantity;
    private String amount;

    // Origin
    private String originId;
}

/**
 * Cadastrando Despesa
 * value - obrigatório
 * payment - obrigatório
 * description - opcional
 * type - obrigatório
 * date - obrigatório
 * frequency - opcional
 * origin_id - obrigatório
 */

/** 
 * Cadastrando Receita
 * value - obrigatório
 * description - opcional
 * type - obrigatório
 * date - obrigatório
 * frequency - opcional
 * origin_id - opcional
 */