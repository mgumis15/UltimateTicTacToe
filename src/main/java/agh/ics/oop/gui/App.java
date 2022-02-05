package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;


public class App extends Application  {


    public void init(){
        try {

            System.out.println("system wystartował");

        }catch(IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void start(Stage primaryStage){





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
