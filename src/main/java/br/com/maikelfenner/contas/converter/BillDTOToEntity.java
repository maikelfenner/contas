package br.com.maikelfenner.contas.converter;

import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BillDTOToEntity implements Converter<BillDTO, Bill> {

    @Override
    public Bill convert(BillDTO billDTO) {
        return new Bill(billDTO.getName(),
                billDTO.getValue(),
                billDTO.getDueDate(),
                billDTO.getPaymentDate());
    }
}
