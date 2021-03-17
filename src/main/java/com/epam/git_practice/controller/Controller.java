package com.epam.git_practice.controller;

import com.epam.git_practice.model.CardTypeWasNotFoundException;
import com.epam.git_practice.model.Cards;
import com.epam.git_practice.view.View;

public class Controller {

    public void startApplication(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            try {
                new View().showCardNumber(args[i], args[i + 1],
                        Cards.getCardByPaymentSystem(args[i])
                                .generateCardNumber(args[i + 1]));
            } catch (RuntimeException e) {
                throw new CardTypeWasNotFoundException();
            }
        }
    }

}
