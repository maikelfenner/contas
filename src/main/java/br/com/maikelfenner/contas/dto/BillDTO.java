package br.com.maikelfenner.contas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class BillDTO implements Serializable {

    private String name;
    private Double value;
    private Double correctedValue;
    private int delayedDays;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    public BillDTO(String name, Double value, Double correctedValue, int delayedDays, LocalDate dueDate, LocalDate paymentDate) {
        this.name = name;
        this.value = value;
        this.correctedValue = correctedValue;
        this.delayedDays = delayedDays;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public BillDTO(String name, Double value, LocalDate dueDate, LocalDate paymentDate) {
        this.name = name;
        this.value = value;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
