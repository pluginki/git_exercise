package com.epam.git_practice;

import com.epam.git_practice.controller.Controller;
import com.epam.git_practice.model.CardTypeWasNotFoundException;
import com.epam.git_practice.view.View;

public class Application {

    public static void main(String[] args) {
        try {
            new Controller().startApplication(args);
        } catch (CardTypeWasNotFoundException e) {
            new View().showError(e);
        }
    }
}
