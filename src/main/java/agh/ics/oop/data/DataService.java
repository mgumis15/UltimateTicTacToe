package agh.ics.oop.data;

import agh.ics.oop.Coordinates;
import agh.ics.oop.Move;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataService {
    protected ArrayList<Move> container= new ArrayList<>();
    protected TableView moveList;
    public void addData(int round, Coordinates parent,Coordinates child) {
        Move next=new Move(round,parent,child);
        this.container.add(next);
        this.updateMoveList(next);
    }

    public void updateMoveList(Move next){
        this.moveList.getItems().add(next);
    }


    public void saveData() throws IOException {

        Date date = new Date();
        FileWriter writer=new FileWriter(date.getTime() +".csv");
        writer.append("Round");
        writer.append("  |  ");
        writer.append("Player");
        writer.append("  |  ");
        writer.append("Position");

        writer.append("\n");
        for (Move data:this.container) {
            writer.append(data.round);
            writer.append("  |  ");
            writer.append(data.player);
            writer.append("  |  ");
            writer.append(data.position);
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    public void setMoveList(TableView moveList) {
        this.moveList = moveList;
    }

}
