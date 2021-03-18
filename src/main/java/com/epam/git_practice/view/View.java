package com.epam.git_practice.view;

import com.epam.git_practice.model.CardTypeWasNotFoundException;

public class View {
    private static final String OUTPUT_PATTERN = "%s %s %d%n";

    public void showCardNumber(String paymentSystem, String cardType,
                               long cardNumber) {
        System.out.printf(OUTPUT_PATTERN, paymentSystem.toUpperCase(),
                cardType.toLowerCase(), cardNumber);
    }

    public void showError(CardTypeWasNotFoundException e) {
        System.err.println(e.getMessage());
    }

}
