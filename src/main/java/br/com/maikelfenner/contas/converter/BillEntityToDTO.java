package br.com.maikelfenner.contas.converter;

import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BillEntityToDTO implements Converter<Bill, BillDTO> {

    @Override
    public BillDTO convert(Bill bill) {
        return new BillDTO(bill.getName(),
                bill.getValue(),
                bill.getCorrectedValue(),
                bill.getDelayedDays(),
                bill.getDueDate(),
                bill.getPaymentDate());
    }
}
