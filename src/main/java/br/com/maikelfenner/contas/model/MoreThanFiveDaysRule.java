package br.com.maikelfenner.contas.model;

class MoreThanFiveDaysRule extends CalcRule {

    MoreThanFiveDaysRule(int delayedDays) {
        super(delayedDays);
    }

    @Override
    int getFine() {
        return 5;
    }

    @Override
    double getDailyInterest() {
        return 0.3;
    }
}
