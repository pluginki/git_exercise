package com.epam.git_practice.model;

import java.util.Random;

public enum Cards {

    MIR {
        private static final String CLASSIC = "classic";
        private static final String PREMIUM = "premium";
        private static final String DEBIT = "debit";
        private final int[] numberLengths = new int[]{16};
        private final int[] mirBins = new int[]{2200, 2201, 2202, 2203, 2204};

        @Override
        public long generateCardNumber(String cardType) {
            long cardNumber;
            if (CLASSIC.equalsIgnoreCase(cardType)
                || PREMIUM.equalsIgnoreCase(cardType)
                || DEBIT.equalsIgnoreCase(cardType)) {
                cardNumber = Cards.generatePartOfCardNumber(mirBins,
                        numberLengths);
            } else {
                throw new CardTypeWasNotFoundException();
            }
            cardNumber = new ControlNumberGenerator()
                             .addControlNumber(cardNumber);
            return cardNumber;
        }

    };

    public abstract long generateCardNumber(String cardType);

    public static Cards getCardByPaymentSystem(String paymentSystem) {
        try {
            return Cards.valueOf(paymentSystem.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CardTypeWasNotFoundException();
        }
    }

    private static long generatePartOfCardNumber(int[] bin,
                                                 int[] numberLengths) {
        long numberLength = (long) Math.pow(10,
                Cards.getRandomNumberLength(numberLengths) - (double) 5);
        return getRandomBin(bin, numberLength)
                + (long) (Math.random() * numberLength);
    }

    private static int getRandomNumberLength(int[] numberLengths) {
        return numberLengths[new Random().nextInt(numberLengths.length)];
    }

    private static long getRandomBin(int[] bins, long numberLength) {
        return bins[new Random().nextInt(bins.length)] * numberLength;
    }

}
