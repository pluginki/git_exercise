package com.epam.git_practice.view;

public class View {
    private static final String OUTPUT_PATTERN = "%s %s %d%n";

    public void showCardNumber(String paymentSystem, String cardType,
                               long cardNumber) {
        System.out.printf(OUTPUT_PATTERN, paymentSystem, cardType, cardNumber);
    }

}
