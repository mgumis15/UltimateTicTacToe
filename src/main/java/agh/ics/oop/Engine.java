package agh.ics.oop;

public class Engine {
    protected Board mainBoard;
    protected int currentPlayer=0;
    public Engine(){
        this.mainBoard=new Board(true);
    }

    public void start(){
        System.out.println("Jedziemy");
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

}
