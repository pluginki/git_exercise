package com.epam.git_practice.controller;

import com.epam.git_practice.model.CardTypeWasNotFoundException;
import com.epam.git_practice.model.Cards;
import com.epam.git_practice.view.View;

public class Controller {
    private static final String ELECTRONIC = "electronic";
    private static final String MAESTRO = "maestro";

    public void startApplication(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            try {
                if ((Cards.MASTERCARD.name().equalsIgnoreCase(args[i]))) {
                    if ((args.length - 1) == i) {
                        getCardNumber(args[i], args[i]);
                    } else if (args[i + 1].equalsIgnoreCase(ELECTRONIC)) {
                        getCardNumber(args[i], args[i + 1]);
                    } else {
                        getCardNumber(args[i], args[i]);
                        i--;
                    }
                } else if (MAESTRO.equalsIgnoreCase(args[i])) {
                    getCardNumber(Cards.MASTERCARD.name(), args[i]);
                } else {
                    getCardNumber(args[i], args[i + 1]);
                }
            } catch (RuntimeException e) {
                throw new CardTypeWasNotFoundException();
            }
        }
    }

    private void getCardNumber(String paymentSystem, String cardType) {
        new View().showCardNumber(paymentSystem, cardType,
                Cards.getCardByPaymentSystem(paymentSystem)
                        .generateCardNumber(cardType));
    }

}
