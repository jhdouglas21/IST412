package controller;

import model.*;

public class GameSelector {
    public static void main(String[] args) {
        GameFactory factory = new BlackjackFactory(); //update to be chose dynamically
        CasinoGame game = factory.createGame();

        game.play();
    }
}