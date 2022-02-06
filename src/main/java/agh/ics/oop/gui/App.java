package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.data.DataService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;


public class App extends Application  {

    private GridPane mainGrid;

    private BoardVisualizer visualizer;
    private Text textOnTop;
    private DataService dataService=new DataService();
    private Engine engine=new Engine(this.dataService);
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
    this.visualizer=new BoardVisualizer(this.engine,this.mainGrid);

    this.textOnTop=new Text("Ruch gracza X");
    this.textOnTop.setStyle("-fx-font: 35 arial;");
    engine.setTextOnTop(this.textOnTop);

    TableView<Move> moveList=new TableView<>();
    TableColumn<Move,String> columnRound = new TableColumn<>("Round");
        columnRound.setMaxWidth(100);
        columnRound.setCellValueFactory(new PropertyValueFactory<>("round"));
    TableColumn<Move,String> columnPlayer = new TableColumn<>("Player");
        columnPlayer.setMaxWidth(100);
        columnPlayer.setCellValueFactory(new PropertyValueFactory<>("player"));
    TableColumn<Move,String> columnPosition = new TableColumn<>("Position");
        columnPosition.setMaxWidth(200);
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

    moveList.getColumns().add(columnRound);
    moveList.getColumns().add(columnPlayer);
    moveList.getColumns().add(columnPosition);

        Timer timer=new Timer();

    this.dataService.setMoveList(moveList);

    Button saveDataButton=new Button("Save list");

        saveDataButton.setOnAction(action ->{
            try{
                this.dataService.saveData();
            }catch (IOException ex){
                System.out.println(ex);
            }


    });
    ScrollPane scrollPane=new ScrollPane();
    scrollPane.pannableProperty().set(true);
    scrollPane.setMaxWidth(400);
    scrollPane.setMaxHeight(800);

    scrollPane.setContent(moveList);


    VBox boardBox=new VBox(this.textOnTop,this.mainGrid);
        boardBox.setSpacing(30);
        boardBox.setAlignment(Pos.CENTER);

    VBox dataBox=new VBox(scrollPane,saveDataButton);
        dataBox.setSpacing(30);
        dataBox.setAlignment(Pos.CENTER);

    HBox mainBox=new HBox(boardBox,dataBox);
        mainBox.setSpacing(100);
        mainBox.setAlignment(Pos.CENTER);
    Scene sceneMain=new Scene(mainBox,1250,1000);


    Text title=new Text("Ultimate Tic Tac Toe");
    title.setStyle("-fx-font: 24 arial;");
    Button startButton=new Button("Start Game");

    startButton.setOnAction(action ->{
        this.engine.setRunning(true);
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


}
