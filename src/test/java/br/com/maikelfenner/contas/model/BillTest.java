package br.com.maikelfenner.contas.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class BillTest {

    Bill bill;

    @Test
    public void delayedDaysShouldNotBeNegative() {
        bill = new Bill("test",
                1000.0,
                LocalDate.of(2019, 7, 10),
                LocalDate.of(2019, 7, 5));

        assertThat(bill.getDelayedDays(), is(0));
    }

    @Test
    public void validateDelayedDays() {
        bill = new Bill("test",
                1000.0,
                LocalDate.of(2019, 7, 10),
                LocalDate.of(2019, 7, 15));

        assertThat(bill.getDelayedDays(), is(5));
    }
}