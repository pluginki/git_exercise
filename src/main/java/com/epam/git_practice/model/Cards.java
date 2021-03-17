package com.epam.git_practice.model;

public enum Cards {

    CARD {

        @Override
        public long generateCardNumber(String cardType) {
            return 0;
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

}
