package br.com.maikelfenner.contas.model;

class MoreThanThreeDaysRule extends CalcRule {

    MoreThanThreeDaysRule(int delayedDays) {
        super(delayedDays);
    }

    @Override
    public int getFine() {
        return 3;
    }

    @Override
    public double getDailyInterest() {
        return 0.2;
    }
}
