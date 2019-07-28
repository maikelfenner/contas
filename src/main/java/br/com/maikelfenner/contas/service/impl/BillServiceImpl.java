package br.com.maikelfenner.contas.service.impl;

import br.com.maikelfenner.contas.converter.BillDTOToEntity;
import br.com.maikelfenner.contas.converter.BillEntityToDTO;
import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import br.com.maikelfenner.contas.repository.BillRepository;
import br.com.maikelfenner.contas.service.BillService;
import br.com.maikelfenner.contas.service.exception.InvalidFieldException;
import br.com.maikelfenner.contas.validator.NameValidator;
import br.com.maikelfenner.contas.validator.ValueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillDTOToEntity dtoToEntity;
    private final BillEntityToDTO entityToDTO;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, BillDTOToEntity dtoToEntity, BillEntityToDTO entityToDTO) {
        this.billRepository = billRepository;
        this.dtoToEntity = dtoToEntity;
        this.entityToDTO = entityToDTO;
    }

    @Override
    public BillDTO save(BillDTO dto) throws InvalidFieldException {
        Bill bill = dtoToEntity.convert(dto);
        NameValidator.validate(bill.getName());
        ValueValidator.validate(bill.getValue());
        bill = billRepository.save(bill);

        return entityToDTO.convert(bill);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }
}
