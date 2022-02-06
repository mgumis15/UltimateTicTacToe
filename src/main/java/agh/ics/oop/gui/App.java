package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.components.Move;
import agh.ics.oop.data.DataService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class App extends Application  {

    private GridPane mainGrid;

    private BoardVisualizer visualizer;
    private Text textOnTop;
    private DataService dataService=new DataService();
    private Engine engine=new Engine(this.dataService);
    protected TimerTask timerTask;
    protected Timer timer;
    protected Text timerText;
    public Button showButon;
    protected boolean visibleGrid=false;
    protected TextField timerSpan;
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
    this.timerText=new Text("");
    this.timerText.setStyle("-fx-font: 24 arial;");
    engine.setTextOnTop(this.textOnTop);
    engine.setApp(this);

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



    this.dataService.setMoveList(moveList);

    Button saveDataButton=new Button("Save list");

        saveDataButton.setOnAction(action ->{
            try{
                this.dataService.saveData();
            }catch (IOException ex){
                System.out.println(ex);
            }


    });

    this.showButon=new Button("Show board");
    this.showButon.setVisible(false);
    this.showButon.setOnAction(action ->{
        this.engine.showGrid(this.visibleGrid);
        this.visibleGrid=!this.visibleGrid;
    });
    this.engine.setShowButton(this.showButon);

    ScrollPane scrollPane=new ScrollPane();
    scrollPane.pannableProperty().set(true);
    scrollPane.setMaxWidth(400);
    scrollPane.setMaxHeight(800);

    scrollPane.setContent(moveList);


    VBox boardBox=new VBox(this.textOnTop,this.mainGrid,this.showButon);
        boardBox.setSpacing(30);
        boardBox.setAlignment(Pos.CENTER);

    VBox dataBox=new VBox(timerText,scrollPane,saveDataButton);
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
        this.resetTimer();

        this.visualizer.draw();
        primaryStage.setScene(sceneMain);

    });

    Text timerLabel=new Text("Podaj czas: ");
    this.timerSpan=new TextField();
        this.timerSpan.setText("15");
        HBox timerSpanBox=new HBox(timerLabel,this.timerSpan);
        timerSpanBox.setSpacing(15);
        timerSpanBox.setAlignment(Pos.CENTER);
    VBox startMenu = new VBox(title,timerSpanBox,startButton);
    startMenu.setSpacing(20);
    startMenu.setAlignment(Pos.CENTER);
    Scene sceneStart=new Scene(startMenu,500,300);
    primaryStage.setScene(sceneStart);
    primaryStage.show();

    }

    public void  resetTimer(){
        if(this.timer!=null){
            this.timer.cancel();
        }
        if(this.engine.isRunning()){
            this.timer=new Timer();
            this.timerTask=new TimerTask() {
                int i=Integer.parseInt(timerSpan.getText());
                @Override
                public void run() {
                    timerText.setText("Czas na ruch: "+String.valueOf(i));
                    i--;
                    if(i<0){
                        timer.cancel();
                        timerText.setText("Koniec czasu");
                        engine.setCurrentPlayer(engine.getCurrentPlayer()+1);
                        engine.onWin();
                    }
                }
            };
            this.timer.schedule(this.timerTask,0,1000);
        }

    }


}
