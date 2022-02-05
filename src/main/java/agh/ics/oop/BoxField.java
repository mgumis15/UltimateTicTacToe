package agh.ics.oop;

import javafx.scene.layout.GridPane;

public class BoxField  extends  AbstractField{

    protected Board smallBoard;
    protected GridPane grid;
    public BoxField(Coordinates coords){

        super(coords);
        this.smallBoard=new Board(false);
    }

    public Board getSmallBoard() {
        return smallBoard;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public GridPane getGrid() {
        return grid;
    }
    public void drawCurrentWinner(){
        if(super.player%2==0){
            this.smallBoard.getBoard().get(0).get(0).getBox().setStyle("-fx-background-color: #de527e;");
            this.smallBoard.getBoard().get(1).get(1).getBox().setStyle("-fx-background-color: #de527e;");
            this.smallBoard.getBoard().get(2).get(2).getBox().setStyle("-fx-background-color: #de527e;");
            this.smallBoard.getBoard().get(0).get(2).getBox().setStyle("-fx-background-color: #de527e;");
            this.smallBoard.getBoard().get(2).get(0).getBox().setStyle("-fx-background-color: #de527e;");
        }else{
            this.smallBoard.getBoard().get(0).get(1).getBox().setStyle("-fx-background-color: #75dbfa;");
            this.smallBoard.getBoard().get(1).get(0).getBox().setStyle("-fx-background-color: #75dbfa;");
            this.smallBoard.getBoard().get(1).get(2).getBox().setStyle("-fx-background-color: #75dbfa;");
            this.smallBoard.getBoard().get(2).get(1).getBox().setStyle("-fx-background-color: #75dbfa;");
        }
    }
}
