package agh.ics.oop.components;


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
                    this.board.get(i).add(new BoxField(new Coordinates(j,i)));
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.board.get(i).add(new Field(new Coordinates(j,i)));
                }
            }
        }

    }

    public boolean checkWin(){
        int previousPlayer;
        boolean mached=false;
//        Horizontal
        for (int i = 0; i < 3; i++) {

            if(this.board.get(i).get(0).isAvalible()||this.board.get(i).get(0).getPlayer()<0)continue;
            previousPlayer=this.board.get(i).get(0).player;
            mached=true;
            for (int j = 1; j < 3; j++) {
                if (this.board.get(i).get(j).isAvalible()||this.board.get(i).get(j).player%2!=previousPlayer%2){
                    mached=false;
                    break;
                }
            }
            if(mached)return true;
        }
 //        Vertival

        mached=false;
        for (int i = 0; i < 3; i++) {
            if(this.board.get(0).get(i).isAvalible()||this.board.get(0).get(i).getPlayer()<0)continue;
            previousPlayer=this.board.get(0).get(i).player;
            mached=true;
            for (int j = 1; j < 3; j++) {
                if (this.board.get(j).get(i).isAvalible()||this.board.get(j).get(i).player%2!=previousPlayer%2){
                    mached=false;
                    break;
                }
            }
            if(mached)return true;
        }
//        Crossed
        mached=false;
        if(!this.board.get(0).get(0).isAvalible()&&this.board.get(0).get(0).getPlayer()>=0){
            previousPlayer=this.board.get(0).get(0).player;
            mached=true;
            for (int i = 1; i < 3; i++) {
                if (this.board.get(i).get(i).isAvalible()||this.board.get(i).get(i).player%2!=previousPlayer%2){
                    mached=false;
                    break;
                }
            }
            if(mached)return true;
        }
        mached=false;
        if(!this.board.get(0).get(2).isAvalible()&&this.board.get(0).get(2).getPlayer()>=0){
            previousPlayer=this.board.get(0).get(2).player;
            mached=true;
            for (int i = 1; i < 3; i++) {

                if (this.board.get(i).get(2-i).isAvalible()||this.board.get(i).get(2-i).player%2!=previousPlayer%2){
                    mached=false;
                    break;
                }
            }
            if(mached)return true;
        }

        return false;

    }

    public void drawFinallWinner(int player){
        if(player%2==0){
            ((BoxField) this.board.get(0).get(0)).setColor("#de527e");
            ((BoxField) this.board.get(1).get(1)).setColor("#de527e");
            ((BoxField) this.board.get(2).get(2)).setColor("#de527e");
            ((BoxField) this.board.get(0).get(2)).setColor("#de527e");
            ((BoxField) this.board.get(2).get(0)).setColor("#de527e");
        }else{
            ((BoxField) this.board.get(0).get(1)).setColor("#75dbfa");
            ((BoxField) this.board.get(1).get(0)).setColor("#75dbfa");
            ((BoxField) this.board.get(1).get(2)).setColor("#75dbfa");
            ((BoxField) this.board.get(2).get(1)).setColor("#75dbfa");
        }

    }

    public ArrayList<ArrayList<AbstractField>> getBoard() {
        return board;
    }
}
