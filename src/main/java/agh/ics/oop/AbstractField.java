package agh.ics.oop;

import javafx.scene.layout.HBox;

public abstract class AbstractField implements  IField{
    protected Coordinates coords;
    protected boolean avalible=true;
    protected int player;
    protected HBox box;
    public AbstractField(Coordinates coords){
        this.coords=coords;
    }

    public void mark(int player){
        if(this.avalible){
            this.player=player;
            this.avalible=false;
        }
    }

    public boolean isAvalible() {
        return avalible;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public int getPlayer() {
        return player;
    }

    public void setBox(HBox box) {
        this.box = box;
    }

    public HBox getBox() {
        return box;
    }
}
