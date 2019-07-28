package br.com.maikelfenner.contas.converter;

import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BillDTOToEntityTest {

    private BillDTOToEntity converter;

    @Before
    public void setup() {
        converter = new BillDTOToEntity();
    }

    @Test
    public void converterIsWorking() {
        BillDTO dto = new BillDTO("Test", 100.0, 102.20, 2, LocalDate.of(2019, 7, 8), LocalDate.of(2019, 7, 10));
        Bill bill = converter.convert(dto);

        assertEquals(dto.getName(), bill.getName());
        assertEquals(dto.getValue(), bill.getValue());
        assertEquals(dto.getCorrectedValue(), bill.getCorrectedValue());
        assertEquals(dto.getDelayedDays(), bill.getDelayedDays());
        assertEquals(dto.getDueDate(), bill.getDueDate());
        assertEquals(dto.getPaymentDate(), bill.getPaymentDate());
    }

}