package model;

public class RouletteFactory implements GameFactory {
    public CasinoGame createGame() {
        return new RouletteGame();
    }
}