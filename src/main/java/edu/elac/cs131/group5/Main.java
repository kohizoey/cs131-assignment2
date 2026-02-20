package edu.elac.cs131.group5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Root
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Components
        Label labelA = new Label("Enter first set (A):");
        TextField textFieldA = new TextField();
        Label labelB = new Label("Enter second set (B):");
        TextField textFieldB = new TextField();

        // Diagram pane
        Pane diagramPane = new Pane();
        diagramPane.setPrefSize(600, 450);

        Circle circleA = new Circle(250, 225, 120); // x, y, radius
        circleA.setFill(Color.BLUE);
        circleA.setStroke(Color.BLACK);
        circleA.setOpacity(0.5);

        Circle circleB = new Circle(375, 225, 120);
        circleB.setFill(Color.LIGHTCORAL);
        circleB.setStroke(Color.BLACK);
        circleB.setOpacity(0.5);

        VBox setATextList = new VBox(2);
        setATextList.setLayoutX(200);
        setATextList.setLayoutY(150);

        VBox setBTextList = new VBox(2);
        setBTextList.setLayoutX(400);
        setBTextList.setLayoutY(150);

        VBox intersectionTextList = new VBox(2);
        intersectionTextList.setLayoutX(300);
        intersectionTextList.setLayoutY(150);

        diagramPane.getChildren().addAll(circleA, circleB, setATextList, setBTextList, intersectionTextList);

        // Update button
        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> {
           String setAText = textFieldA.getText();
           String setBText = textFieldB.getText();

           Set<String> setA = new HashSet<>(Arrays.asList(setAText.split(",\\s*")));
           Set<String> setB = new HashSet<>(Arrays.asList(setBText.split(",\\s*")));
           Set<String> intersection = intersection(setA, setB);
           updateTextList(setATextList, setA);
           updateTextList(setBTextList, setB);
           updateTextList(intersectionTextList, intersection);
        });

        // Add components to root
        root.getChildren().addAll(labelA, textFieldA, labelB, textFieldB, updateButton, diagramPane);

        // Create scene and add it to stage
        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> intersection = new HashSet<>(a);
        intersection.retainAll(b);

        return intersection;
    }

    private void updateTextList(VBox textList, Set<String> set) {
        textList.getChildren().clear();
        set.forEach(item -> textList.getChildren().add(new Text(item)));
    }

}