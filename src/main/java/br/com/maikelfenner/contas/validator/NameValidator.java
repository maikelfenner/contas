package br.com.maikelfenner.contas.validator;

import br.com.maikelfenner.contas.service.exception.InvalidFieldException;

public class NameValidator {

    public static void validate(String name) throws InvalidFieldException {
        if (name == null || name.isEmpty()) {
            throw new InvalidFieldException("Name is mandatory");
        }

        if (name.length() >= 200) {
            throw new InvalidFieldException("Bill name maximum 200 characters");
        }
    }
}
