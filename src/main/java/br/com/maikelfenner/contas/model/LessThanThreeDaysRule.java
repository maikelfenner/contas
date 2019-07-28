package br.com.maikelfenner.contas.model;

class LessThanThreeDaysRule extends CalcRule {

    LessThanThreeDaysRule(int delayedDays) {
        super(delayedDays);
    }

    @Override
    public int getFine() {
        return 2;
    }

    @Override
    public double getDailyInterest() {
        return 0.1;
    }
}
