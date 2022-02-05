package agh.ics.oop;

abstract class AbstractField implements  IField{
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

}
