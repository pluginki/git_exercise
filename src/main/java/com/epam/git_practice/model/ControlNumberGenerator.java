package com.epam.git_practice.model;

public class ControlNumberGenerator {

    public long addControlNumber(long cardNumber) {
        return cardNumber * 10 + calculateControlNumber(cardNumber);
    }

    private long calculateControlNumber(long cardNumber) {
        long sumOfNumbersWithLuhnAlgorithm = 0;
        for (int i = 0; cardNumber != 0; i++) {
            long nextDigitFromReversedCardNumber = cardNumber % 10;
            if (i % 2 == 0) {
                nextDigitFromReversedCardNumber *= 2;
                if (nextDigitFromReversedCardNumber > 9) {
                    nextDigitFromReversedCardNumber -= 9;
                }
            }
            sumOfNumbersWithLuhnAlgorithm += nextDigitFromReversedCardNumber;
            cardNumber /= 10;
        }
        return 10 - sumOfNumbersWithLuhnAlgorithm % 10;
    }

}
