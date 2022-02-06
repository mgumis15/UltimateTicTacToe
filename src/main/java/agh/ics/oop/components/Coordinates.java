package agh.ics.oop.components;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Coordinates))
            return false;
        Coordinates that = (Coordinates) other;
        if (this.x == that.x && this.y == that.y) {
            return true;
        } else {
            return false;
        }
    }

}
