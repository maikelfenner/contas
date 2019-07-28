package br.com.maikelfenner.contas.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CalcRuleFactoryTest {

    private CalcRuleFactory calcRuleFactory;
    private CalcRule calcRule;

    @Before
    public void setup() {
        calcRuleFactory = new CalcRuleFactory();
    }

    @Test
    public void validateLessThanThreeDaysRule() {
        calcRule = calcRuleFactory.getCalcRule(1);

        assertEquals(calcRule.getFine(), 2);
        assertEquals(calcRule.getDailyInterest(), .1, 0);
    }

    @Test
    public void validateMoreThanThreeDaysRule() {
        calcRule = calcRuleFactory.getCalcRule(4);

        assertEquals(calcRule.getFine(), 3);
        assertEquals(calcRule.getDailyInterest(), .2, 0);
    }

    @Test
    public void validateLessThanFiveDaysRule() {
        calcRule = calcRuleFactory.getCalcRule(8);

        assertEquals(calcRule.getFine(), 5);
        assertEquals(calcRule.getDailyInterest(), .3, 0);
    }

    @Test
    public void validateCorrectedValueWhenNotDelayed() {
        calcRule = calcRuleFactory.getCalcRule(0);

        Double value = calcRule.getCorrectedValue(100.0);

        assertEquals(100.0, value, 0);
    }

    @Test
    public void validateCorrectedValueWhenLessThanThreeDays() {
        calcRule = calcRuleFactory.getCalcRule(2);

        Double value = calcRule.getCorrectedValue(100.0);

        assertEquals(102.20, value, 0);
    }

    @Test
    public void validateCorrectedValueWhenMoreThanThreeDays() {
        calcRule = calcRuleFactory.getCalcRule(4);

        Double value = calcRule.getCorrectedValue(100.0);

        assertEquals(103.80, value, 0);
    }

    @Test
    public void validateCorrectedValueWhenMoreThanFiveDays() {
        calcRule = calcRuleFactory.getCalcRule(7);

        Double value = calcRule.getCorrectedValue(100.0);

        assertEquals(107.10, value, 0);
    }
}