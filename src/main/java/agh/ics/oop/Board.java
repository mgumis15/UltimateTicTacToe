package agh.ics.oop;

import java.util.ArrayList;

public class Board {

    protected ArrayList<ArrayList<AbstractField>> board=new ArrayList<>(3);
    public Board(boolean main){
        for (int i = 0; i < 3; i++) {
            this.board.add(new ArrayList<>());
        }
        if (main){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.board.get(i).add(new BoxField(new Coordinates(i,j)));
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.board.get(i).add(new Field(new Coordinates(i,j)));
                }
            }
        }

    }


}
