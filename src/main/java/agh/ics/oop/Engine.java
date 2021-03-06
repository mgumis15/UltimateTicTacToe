package agh.ics.oop;

import agh.ics.oop.components.AbstractField;
import agh.ics.oop.components.Board;
import agh.ics.oop.components.BoxField;
import agh.ics.oop.components.Coordinates;
import agh.ics.oop.data.DataService;
import agh.ics.oop.gui.App;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class Engine {
    protected Board mainBoard;
    protected int currentPlayer=0;
    protected Coordinates lastCoordinates=null;
    protected boolean running=false;
    protected Text textOnTop;
    protected App app;
    protected DataService dataService;
    protected Button showButton;
    public Engine(DataService dataService){
        this.mainBoard=new Board(true);
        this.dataService=dataService;
    }


    public boolean isAvalible(AbstractField child, AbstractField parent){

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
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ((BoxField)this.mainBoard.getBoard().get(i).get(j)).getGrid().setStyle("-fx-background-color: null;");
                }
            }
            child.mark(this.currentPlayer);
            this.app.resetTimer();
            dataService.addData(this.currentPlayer,parent.getCoords(),child.getCoords());
            if(parent instanceof BoxField){
                if(((BoxField) parent).getSmallBoard().checkWin()){

                    parent.mark(this.currentPlayer);
                    ((BoxField) parent).drawCurrentWinner();
                    if(this.mainBoard.checkWin()){
//KONIEC GRY
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                ((BoxField)this.mainBoard.getBoard().get(i).get(j)).getGrid().setStyle("-fx-background-color: null;");
                            }
                        }
                       this.onWin();

                    }
                }else{
                    ((BoxField) parent).checkAvability();
                }
            }
            if(this.mainBoard.getBoard().get(child.getCoords().y).get(child.getCoords().x).isAvalible()&&this.isRunning()){

                this.lastCoordinates=child.getCoords();
                ((BoxField)this.mainBoard.getBoard().get(child.getCoords().y).get(child.getCoords().x)).getGrid().setStyle("-fx-background-color: #bfffe2;");
            }else{
                boolean avalible=false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(((BoxField)this.mainBoard.getBoard().get(i).get(j)).isAvalible())avalible=true;
                    }
                }
                if(!avalible){
                    this.onDraw();
                }
                if(this.isRunning()){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if(this.mainBoard.getBoard().get(i).get(j).isAvalible())
                                ((BoxField)this.mainBoard.getBoard().get(i).get(j)).getGrid().setStyle("-fx-background-color: #bfffe2;");
                        }
                    }
                }
                this.lastCoordinates=null;

            }
            if(this.isRunning()){
                this.currentPlayer+=1;
                if(this.currentPlayer%2==0){
                    this.textOnTop.setText("X player turn");
                }else{
                    this.textOnTop.setText("O player turn");

                }
            }

        }
    }

    public void onWin(){

        this.showButton.setVisible(true);
        this.mainBoard.drawFinallWinner(this.currentPlayer);
        this.setRunning(false);
        this.app.resetTimer();
        if(this.currentPlayer%2==0){
            this.textOnTop.setText("X WINS!");
        }else{
            this.textOnTop.setText("O WINS!");
        }
       this.showGrid(false);
    }

    public void onDraw(){

        this.setRunning(false);
        this.app.resetTimer();
        this.textOnTop.setText("DRAW!");

    }

    public void showGrid(boolean show){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3; j++) {
                for (int k = 0; k < 9; k++) {
                    ((BoxField) this.mainBoard.getBoard().get(i).get(j)).getGrid().getChildren().get(k).setVisible(show);
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

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    public void setApp(App app) {
        this.app = app;
    }

    public void setShowButton(Button showButton) {
        this.showButton = showButton;
    }
}
