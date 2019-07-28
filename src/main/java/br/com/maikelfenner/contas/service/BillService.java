package br.com.maikelfenner.contas.service;

import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import br.com.maikelfenner.contas.service.exception.InvalidFieldException;

import java.util.List;

public interface BillService {

    BillDTO save(BillDTO bill) throws InvalidFieldException;
    List<Bill> findAll();
}
