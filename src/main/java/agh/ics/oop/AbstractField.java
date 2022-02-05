package agh.ics.oop;

public abstract class AbstractField implements  IField{
    protected Coordinates coords;
    protected boolean avalible=true;
    protected int player;

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
}
