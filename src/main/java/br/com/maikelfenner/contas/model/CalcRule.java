package br.com.maikelfenner.contas.model;

abstract class CalcRule {

    private int delayedDays;

    CalcRule(int delayedDays) {
        this.delayedDays = delayedDays;
    }

    abstract int getFine();
    abstract double getDailyInterest();

    private double getFineValue(double value) {
        return value * getFine() / 100;
    }

    private double getInterestValue(double value) {
        double interest = this.delayedDays * getDailyInterest();

        return value * interest / 100;
    }

    Double getCorrectedValue(Double value) {
        if (delayedDays <= 0) {
            return value;
        }

        return value + getFineValue(value) + getInterestValue(value);
    }
}
