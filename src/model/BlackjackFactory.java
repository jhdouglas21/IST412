package model;

public class BlackjackFactory implements GameFactory {
    public CasinoGame createGame() {
        return new BlackjackGame();
    }
}