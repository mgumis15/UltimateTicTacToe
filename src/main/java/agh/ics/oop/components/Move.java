package agh.ics.oop.components;

public class Move {
    public String round;
    public String player;
    public String position;
    public Move(int round,Coordinates parent,Coordinates child){
        String player="";
        if(round%2==0){
            player="X";
        }else{
            player="O";
        }
        this.round=String.valueOf(round);
        this.player=player;
        this.position=parent.toString()+"->"+child.toString();
    }

    public String getPlayer() {
        return player;
    }

    public String getPosition() {
        return position;
    }

    public String getRound() {
        return round;
    }
}
