package model;

public class RouletteGame implements CasinoGame {
    public void play() {
        System.out.println("Playing Roulette...");
    }

    public void handleBet(double amount) {
        // Implement bet handling
    }

    public double calculatePayout() {
        return 0.0; // Add payout logic
    }
}