package agh.ics.oop;

public class BoxField  extends  AbstractField{

    protected Board smallBoard;
    public BoxField(Coordinates coords){

        super(coords);
        this.smallBoard=new Board(false);
    }

    public Board getSmallBoard() {
        return smallBoard;
    }
}
