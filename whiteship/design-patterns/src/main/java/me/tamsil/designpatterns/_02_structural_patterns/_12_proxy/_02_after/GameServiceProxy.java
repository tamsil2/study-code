package me.tamsil.designpatterns._02_structural_patterns._12_proxy._02_after;

public class GameServiceProxy implements GameService{

    private GameService gameService;

    public GameServiceProxy(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        if (this.gameService == null) {
            this.gameService = new DefaultGameService();
        }
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}