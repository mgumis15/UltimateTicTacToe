package agh.ics.oop;

public class Engine {
    protected Board mainBoard;
    protected int currentPlayer=0;
    protected Coordinates lastCoordinates=null;
    public Engine(){
        this.mainBoard=new Board(true);
    }


    public boolean isAvalible(AbstractField child,AbstractField parent){

            if(this.lastCoordinates==null){
                if(parent.isAvalible()&&child.isAvalible()){
                    return true;
                }else{
                    return false;
                }
            }else{
            }


        return false;
    }
    public void onSelect(AbstractField child,AbstractField parent){
        if(this.isAvalible(child,parent)){
            child.mark(this.currentPlayer);
            if(parent instanceof BoxField){
                if(((BoxField) parent).getSmallBoard().checkWin()){
                    parent.mark(this.currentPlayer);
                    ((BoxField) parent).drawCurrentWinner();
                    if(this.mainBoard.checkWin()){
//KONIEC GRY
                        this.mainBoard.drawFinallWinner(this.currentPlayer);
                    }
                }
            }
            if(this.mainBoard.getBoard().get(child.getCoords().y).get(child.getCoords().x).isAvalible()){
                this.lastCoordinates=child.getCoords();
            }else{
                this.lastCoordinates=null;
            }

            this.currentPlayer+=1;
        }
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }


}
