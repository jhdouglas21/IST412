package model;

public class PokerGame implements CasinoGame {
    public void play() {
        System.out.println("Playing Poker...");
    }

    public void handleBet(double amount) {
        // Implement bet handling
    }

    public double calculatePayout() {
        return 0.0; // Add payout logic
    }
}