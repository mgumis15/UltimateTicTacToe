package agh.ics.oop.components;

import javafx.scene.layout.GridPane;

public class BoxField  extends AbstractField {

    protected Board smallBoard;
    protected GridPane grid;
    public BoxField(Coordinates coords){

        super(coords);
        this.smallBoard=new Board(false);
    }


    public void drawCurrentWinner(){
        if(super.player%2==0){

            this.smallBoard.getBoard().get(0).get(0).setColor("#de527e");

            this.smallBoard.getBoard().get(1).get(1).setColor("#de527e");

            this.smallBoard.getBoard().get(2).get(2).setColor("#de527e");

            this.smallBoard.getBoard().get(0).get(2).setColor("#de527e");

            this.smallBoard.getBoard().get(2).get(0).setColor("#de527e");
        }else{

            this.smallBoard.getBoard().get(0).get(1).setColor("#75dbfa");

            this.smallBoard.getBoard().get(1).get(0).setColor("#75dbfa");

            this.smallBoard.getBoard().get(1).get(2).setColor("#75dbfa");

            this.smallBoard.getBoard().get(2).get(1).setColor("#75dbfa");
        }
    }

    public void checkAvability(){
        if(this.isAvalible()){
            boolean avalible=false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(this.smallBoard.getBoard().get(i).get(j).isAvalible())avalible=true;
                }
            }
            if(!avalible)this.mark(-1);
        }

    }

    @Override
    public void setColor(String color) {
        if(color!=null){
            this.color = color;
        }
        this.grid.setStyle("-fx-background-color:"+super.color+";");
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
}
