package br.com.lucasromagnoli.cashcontrol.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
@JsonPropertyOrder({"paymentTypeEnum", "installment"})
public class Expense {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    @JsonProperty(value = "payment")
    private PaymentTypeEnum paymentTypeEnum;
    
    @JoinColumn(name = "installment_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Installment installment;
    
}
