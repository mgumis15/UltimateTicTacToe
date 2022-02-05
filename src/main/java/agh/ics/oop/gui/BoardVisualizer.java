package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class BoardVisualizer {
    private Board mainBoard;
    private GridPane mainGrid;
    private Engine engine;

    public BoardVisualizer(Engine engine, GridPane mainGrid){
        this.engine=engine;
        this.mainBoard=engine.getMainBoard();
        this.mainGrid=mainGrid;
    }

    public void draw() {
//        this.clear();
        this.mainGrid.setAlignment(Pos.CENTER);
        this.mainGrid.setHgap(25);
        this.mainGrid.setVgap(25);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    this.drawBoxField(this.mainBoard.getBoard().get(i).get(j));
            }
        }

    }
    public void drawBoxField(AbstractField field) {
//        this.clear();
        HBox box=new HBox();
        box.setPrefWidth(180);
        box.setPrefHeight(180);
        box.setSpacing(10);

        GridPane smallGrid=new GridPane();
        smallGrid.setHgap(5);
        smallGrid.setVgap(5);
        box.getChildren().add(smallGrid);
        field.setBox(box);
        this.mainGrid.add(box,field.getCoords().x,field.getCoords().y);
        if(field instanceof BoxField){
            ((BoxField) field).setGrid(smallGrid);
            Board smallBoard= ((BoxField) field).getSmallBoard();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.drawField(smallBoard.getBoard().get(i).get(j),field,smallGrid);
                }
            }
        }
    }

    public void drawField(AbstractField child,AbstractField parent,GridPane smallGrid) {
//        this.clear();
        Text fllText=new Text("");
        fllText.setStyle("-fx-font: 30 arial;");
        HBox box=new HBox(fllText);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(60);
        box.setPrefHeight(60);
        box.setSpacing(5);
        box.setStyle("-fx-background-color: #dfdfdf;");
        smallGrid.add(box,child.getCoords().x,child.getCoords().y);
        child.setBox(box);
        box.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                box.setStyle("-fx-background-color: #03fc98;");
                if(newValue){
                    if(engine.isAvalible(child,parent)){
                        box.setStyle("-fx-background-color: #03fc98;");
                    }else{
                        box.setStyle("-fx-background-color: #ffc7de;");
                    }
                }else{
                    box.setStyle("-fx-background-color: #dfdfdf;");
                }
            }
        });
        box.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(parent.getCoords().toString()+child.getCoords().toString());
                if(engine.isAvalible(child,parent)){

                    if(engine.getCurrentPlayer()%2==0){
                        fllText.setFill(Color.rgb(252,3,82));
                        fllText.setText("X");
                    }else{
                        fllText.setFill(Color.rgb(3,194,252));
                        fllText.setText("O");
                    }

                }
            }
        });


    }

    public void clear(){
        this.mainGrid.getChildren().clear();

        while(this.mainGrid.getRowConstraints().size() > 0){
            this.mainGrid.getRowConstraints().remove(0);
        }

        while(this.mainGrid.getColumnConstraints().size() > 0){
            this.mainGrid.getColumnConstraints().remove(0);
        }
    }

}
