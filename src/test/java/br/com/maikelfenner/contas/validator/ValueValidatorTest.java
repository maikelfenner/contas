package br.com.maikelfenner.contas.validator;

import br.com.maikelfenner.contas.service.exception.InvalidFieldException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class ValueValidatorTest {

    private static final Double NULL_VALUE = null;
    private static final Double NEGATIVE_VALUE = -1250.25;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateNullValue() throws Exception {
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Value is mandatory");
        ValueValidator.validate(NULL_VALUE);
    }

    @Test
    public void validateNegativeValue() throws Exception {
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Bill value should be bigger than 0");
        ValueValidator.validate(NEGATIVE_VALUE);
    }
}