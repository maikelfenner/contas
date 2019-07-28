package br.com.maikelfenner.contas.validator;

import br.com.maikelfenner.contas.service.exception.InvalidFieldException;

public class ValueValidator {

    public static void validate(Double value) throws InvalidFieldException {
        if (value == null) {
            throw new InvalidFieldException("Value is mandatory");
        }

        if (value <= 0) {
            throw new InvalidFieldException("Bill value should be bigger than 0");
        }
    }
}
