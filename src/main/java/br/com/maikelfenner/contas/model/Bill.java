package br.com.maikelfenner.contas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private Double value;

    @Column(name = "value_corrected", nullable = false)
    private Double correctedValue;

    @Column(name = "delayed_days", nullable = false)
    private int delayedDays;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    public Bill(String name, Double value, LocalDate dueDate, LocalDate paymentDate) {
        this.name = name;
        this.value = value;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        delayedDays = calculateDelayedDays();
        correctedValue = calculateCorrectedValue();
    }

    private int calculateDelayedDays() {
        int days = (int) ChronoUnit.DAYS.between(dueDate, paymentDate);

        return days > 0 ? days : 0;
    }

    private Double calculateCorrectedValue() {
        if (delayedDays > 0) {
            CalcRule calcRule = new CalcRuleFactory().getCalcRule(delayedDays);
            return calcRule.getCorrectedValue(value);
        }

        return value;
    }
}
