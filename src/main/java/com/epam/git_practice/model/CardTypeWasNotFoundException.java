package com.epam.git_practice.model;

public class CardTypeWasNotFoundException extends IllegalArgumentException {
    private static final String CARD_TYPE_WAS_NOT_FOUND
            = "This card type was not found, please try again" + "\n"
            + "Input example: ";

    public CardTypeWasNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return CARD_TYPE_WAS_NOT_FOUND;
    }

}
