package agh.ics.oop;

import agh.ics.oop.data.DataService;
import javafx.scene.text.Text;

public class Engine {
    protected Board mainBoard;
    protected int currentPlayer=0;
    protected Coordinates lastCoordinates=null;
    protected boolean running=false;
    protected Text textOnTop;
    protected DataService dataService;
    public Engine(DataService dataService){
        this.mainBoard=new Board(true);
        this.dataService=dataService;
    }


    public boolean isAvalible(AbstractField child,AbstractField parent){

            if(this.lastCoordinates==null){
                if(parent.isAvalible()&&child.isAvalible()){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(parent.getCoords().equals(this.lastCoordinates)&&parent.isAvalible()&&child.isAvalible()){
                    return true;
                }else{
                    return false;
                }
            }
    }
    public void onSelect(AbstractField child,AbstractField parent){

        if(this.isAvalible(child,parent)){
            child.mark(this.currentPlayer);

            dataService.addData(this.currentPlayer,parent.getCoords(),child.getCoords());
            if(parent instanceof BoxField){
                if(((BoxField) parent).getSmallBoard().checkWin()){

                    parent.mark(this.currentPlayer);
                    ((BoxField) parent).drawCurrentWinner();
                    if(this.mainBoard.checkWin()){
//KONIEC GRY
                        this.mainBoard.drawFinallWinner(this.currentPlayer);
                        this.setRunning(false);
                        if(this.currentPlayer%2==0){
                            this.textOnTop.setText("Wygrana gracza X!");
                        }else{
                            this.textOnTop.setText("Wygrana gracza O!");

                        }
                    }
                }else{
                    ((BoxField) parent).checkAvability();
                }
            }
            if(this.mainBoard.getBoard().get(child.getCoords().y).get(child.getCoords().x).isAvalible()&&this.isRunning()){
                if(this.lastCoordinates!=null){
                ((BoxField)this.mainBoard.getBoard().get(this.lastCoordinates.y).get(this.lastCoordinates.x)).getGrid().setStyle("-fx-background-color: null;");
                }
                this.lastCoordinates=child.getCoords();
                ((BoxField)this.mainBoard.getBoard().get(child.getCoords().y).get(child.getCoords().x)).getGrid().setStyle("-fx-background-color: #bfffe2;");
            }else{
                if(this.lastCoordinates!=null){
                    ((BoxField)this.mainBoard.getBoard().get(this.lastCoordinates.y).get(this.lastCoordinates.x)).getGrid().setStyle("-fx-background-color: null;");
                }
                this.lastCoordinates=null;

            }
            if(this.isRunning()){
                this.currentPlayer+=1;
                if(this.currentPlayer%2==0){
                    this.textOnTop.setText("Ruch gracza X");
                }else{
                    this.textOnTop.setText("Ruch gracza O");

                }
            }

        }
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public void setTextOnTop(Text textOnTop) {
        this.textOnTop = textOnTop;
    }
}
