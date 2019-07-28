package br.com.maikelfenner.contas.converter;

import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BillEntityToDTOTest {

    private BillEntityToDTO converter;

    @Before
    public void setup() {
        converter = new BillEntityToDTO();
    }

    @Test
    public void converterIsWorking() {
        Bill entity = new Bill("Test", 100.0, LocalDate.of(2019, 7, 5), LocalDate.of(2019, 7, 10));
        BillDTO dto = converter.convert(entity);

        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getValue(), dto.getValue());
        assertEquals(entity.getCorrectedValue(), dto.getCorrectedValue());
        assertEquals(entity.getDelayedDays(), dto.getDelayedDays());
        assertEquals(entity.getDueDate(), dto.getDueDate());
        assertEquals(entity.getPaymentDate(), dto.getPaymentDate());
    }
}