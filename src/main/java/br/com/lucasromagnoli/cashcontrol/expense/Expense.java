package br.com.lucasromagnoli.cashcontrol.expense;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class Expense {
    
    @Column(name = "paymentType")
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentTypeEnum;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "installment_id")
    private Installment installment;
    
}
