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

    },

    VISA {
        private static final String ELECTRON = "electron";
        private static final String CLASSIC = "classic";
        private static final String GOLD = "gold";
        private final int[] electronNumberLengths = new int[]{16};
        private final int[] visaNumberLengths = new int[]{16, 13};
        private final int[] electronBins
                = new int[]{4026, 417500, 4508, 4844, 4913, 4917};
        private final int[] visaBins = new int[]{4};

        @Override
        public long generateCardNumber(String cardType) {
            long cardNumber;
            if (ELECTRON.equalsIgnoreCase(cardType)) {
                cardNumber = Cards.generatePartOfCardNumber(electronBins,
                        electronNumberLengths);
            } else if (CLASSIC.equalsIgnoreCase(cardType)
                    || GOLD.equalsIgnoreCase(cardType)) {
                cardNumber = Cards.generatePartOfCardNumber(visaBins,
                        visaNumberLengths);
            } else {
                throw new CardTypeWasNotFoundException();
            }
            cardNumber = new ControlNumberGenerator()
                    .addControlNumber(cardNumber);
            return cardNumber;
        }

    },

    MASTERCARD {
        private static final String MASTERCARD = "mastercard";
        private static final String ELECTRONIC = "electronic";
        private static final String MAESTRO = "maestro";
        private final int[] mastercardNumberLengths = new int[]{16};
        private final int[] maestroNumberLengths = new int[]{12, 13, 14, 15,
                16, 17, 18, 19};
        private final int[] mastercardBins
                = new int[]{51, 52, 53, 54, 55, 2221, 2222, 2223, 2224};
        private final int[] maestroBins
                = new int[]{5018, 5020, 5038, 5893, 6304, 6759, 6761, 6762,
                6763};

        @Override
        public long generateCardNumber(String cardType) {
            long cardNumber;
            if (MASTERCARD.equalsIgnoreCase(cardType)
                    || ELECTRONIC.equalsIgnoreCase(cardType)) {
                cardNumber = Cards.generatePartOfCardNumber(mastercardBins,
                        mastercardNumberLengths);
            } else if (MAESTRO.equalsIgnoreCase(cardType)) {
                cardNumber = Cards.generatePartOfCardNumber(maestroBins,
                        maestroNumberLengths);
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

    private static long generatePartOfCardNumber(int[] bins,
                                                 int[] numberLengths) {
        int bin = (int) getRandomBin(bins);
        long numberLength = (long) Math.pow(10,
                Cards.getRandomNumberLength(numberLengths)
                        - (double) String.valueOf(bin).length() - 1);
        return (bin * numberLength) + (long) (Math.random() * numberLength);
    }

    private static int getRandomNumberLength(int[] numberLengths) {
        return numberLengths[new Random().nextInt(numberLengths.length)];
    }

    private static long getRandomBin(int[] bins) {
        return bins[new Random().nextInt(bins.length)];
    }

}
