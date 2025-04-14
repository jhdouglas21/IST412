package model;

public interface CasinoGame {
    void play();
    void handleBet(double amount);
    double calculatePayout();
}