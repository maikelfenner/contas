package br.com.maikelfenner.contas.model;

class CalcRuleFactory {

    CalcRule getCalcRule(int delayedDays) {
        if (delayedDays < 3) {
            return new LessThanThreeDaysRule(delayedDays);
        } else if(delayedDays < 5) {
            return new MoreThanThreeDaysRule(delayedDays);
        } else {
            return new MoreThanFiveDaysRule(delayedDays);
        }
    }
}
