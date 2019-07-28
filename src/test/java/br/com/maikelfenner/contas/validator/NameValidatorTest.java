package br.com.maikelfenner.contas.validator;

import br.com.maikelfenner.contas.service.exception.InvalidFieldException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NameValidatorTest {

    private static final String EMPTY_NAME = "";
    private static final String NULL_NAME = null;
    private static final String NAME_201_CHARS = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateEmptyName() throws Exception {
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Name is mandatory");
        NameValidator.validate(EMPTY_NAME);
    }

    @Test
    public void validateNullName() throws Exception {
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Name is mandatory");
        NameValidator.validate(NULL_NAME);
    }

    @Test
    public void validateName200Characters() throws Exception {
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Bill name maximum 200 characters");
        NameValidator.validate(NAME_201_CHARS);
    }
}