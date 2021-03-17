package com.epam.git_practice.model;

public enum Cards {

    MIR {
        private static final long CLASSIC_BIN = 2200_0000_0000_000L;
        @Override
        public long generateCardNumber(String cardType) {
            long cardNumber;
            switch (cardType.toLowerCase()) {
                case "classic":
                    cardNumber = Cards.generatePartOfCardNumber(CLASSIC_BIN);
                    break;
                default:
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

    private static long generatePartOfCardNumber(long bin) {
        return bin + (long) (Math.random() * 1000_0000_0000L);
    }

}
