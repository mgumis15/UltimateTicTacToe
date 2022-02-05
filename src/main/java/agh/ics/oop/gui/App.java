package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;


public class App extends Application  {

    private GridPane mainGrid;
    private Engine engine=new Engine();
    private BoardVisualizer visualizer;
    private Text textOnTop;
    public void init(){
        try {

            System.out.println("system wystartował");

        }catch(IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void start(Stage primaryStage){

    this.mainGrid=new GridPane();
    this.mainGrid.setMaxWidth(1000);
//    this.mainGrid.setGridLinesVisible(true);
    this.visualizer=new BoardVisualizer(this.engine,this.mainGrid);

    this.textOnTop=new Text("Ruch gracza X");
    this.textOnTop.setStyle("-fx-font: 24 arial;");

    VBox mainBox=new VBox(this.textOnTop,this.mainGrid);
    mainBox.setSpacing(30);
    mainBox.setAlignment(Pos.CENTER);
    Scene sceneMain=new Scene(mainBox,1250,1000);


    Text title=new Text("Ultimate Tic Tac Toe");
    title.setStyle("-fx-font: 24 arial;");
    Button startButton=new Button("Start Game");

    startButton.setOnAction(action ->{
        this.visualizer.draw();
        primaryStage.setScene(sceneMain);

    });

    VBox startMenu = new VBox(title,startButton);
    startMenu.setSpacing(20);
    startMenu.setAlignment(Pos.CENTER);
    Scene sceneStart=new Scene(startMenu,500,300);
    primaryStage.setScene(sceneStart);
    primaryStage.show();

    }
//    public void newDay(){
//        Platform.runLater(() -> {
//            try{
//
//            } catch (FileNotFoundException e) {
//                System.out.println("Błąd rysowania");
//            }
//        });
//
//    }

}
