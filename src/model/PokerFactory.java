package model;

public class PokerFactory implements GameFactory {
    public CasinoGame createGame() {
        return new PokerGame();
    }
}