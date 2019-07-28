package br.com.maikelfenner.contas.service.impl;

import br.com.maikelfenner.contas.converter.BillDTOToEntity;
import br.com.maikelfenner.contas.converter.BillEntityToDTO;
import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import br.com.maikelfenner.contas.repository.BillRepository;
import br.com.maikelfenner.contas.service.BillService;
import br.com.maikelfenner.contas.service.exception.InvalidFieldException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BillServiceImplTest {

    private static final String BILL_NAME = "Test";
    private static final Double VALUE = 100.0;
    private static final String BILL_NAME_201_CHARS = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
    private static final LocalDate PAYMENT_DATE = LocalDate.of(2019, 7, 10);
    private static final LocalDate DUE_DATE = LocalDate.of(2019, 7, 5);

    private BillService billService;
    private BillDTO newBillDTO;
    private Bill createdBill;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private BillRepository repository;

    @MockBean
    private BillDTOToEntity dtoToEntity;

    @MockBean
    private BillEntityToDTO entityToDto;

    @Before
    public void setup() {
        billService = new BillServiceImpl(repository, dtoToEntity, entityToDto);
        newBillDTO = new BillDTO(BILL_NAME, VALUE, DUE_DATE, PAYMENT_DATE);
        createdBill = new Bill(BILL_NAME, VALUE, DUE_DATE, PAYMENT_DATE);
    }

    @Test
    public void billFieldsShouldBeInformed() throws Exception{
        when(dtoToEntity.convert(any(BillDTO.class))).thenReturn(createdBill);
        when(repository.save(any(Bill.class))).thenReturn(createdBill);
        when(entityToDto.convert(any(Bill.class))).thenReturn(newBillDTO);
        BillDTO result = billService.save(newBillDTO);

        assertEquals(createdBill.getName(), result.getName());
        assertEquals(createdBill.getValue(), result.getValue());
        assertEquals(createdBill.getDueDate(), result.getDueDate());
        assertEquals(createdBill.getPaymentDate(), result.getPaymentDate());
    }

    @Test
    public void nameFieldNotInformedOnSave() throws Exception{
        createdBill.setName(null);
        when(dtoToEntity.convert(any(BillDTO.class))).thenReturn(createdBill);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Name is mandatory");
        billService.save(newBillDTO);
    }

    @Test
    public void nameFieldShouldHaveLessThan200Characters() throws Exception{
        createdBill.setName(BILL_NAME_201_CHARS);
        when(dtoToEntity.convert(any(BillDTO.class))).thenReturn(createdBill);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Bill name maximum 200 characters");
        billService.save(newBillDTO);
    }

    @Test
    public void valueFieldNotInformedOnSave() throws Exception{
        createdBill.setValue(null);
        when(dtoToEntity.convert(any(BillDTO.class))).thenReturn(createdBill);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Value is mandatory");
        billService.save(newBillDTO);
    }

    @Test
    public void valueFieldShouldBePositive() throws Exception{
        createdBill.setValue(-10.5);
        when(dtoToEntity.convert(any(BillDTO.class))).thenReturn(createdBill);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Bill value should be bigger than 0");
        billService.save(newBillDTO);
    }
}