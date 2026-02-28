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
        // Graph button
        Button graphButton = new Button("Show Function Graphs");

        updateButton.setOnAction(event -> {
           String setAText = textFieldA.getText();
           String setBText = textFieldB.getText();

           Set<String> setA = new HashSet<>(Arrays.asList(setAText.split(",\\s*")));
           Set<String> setB = new HashSet<>(Arrays.asList(setBText.split(",\\s*")));
           Set<String> intersection = intersection(setA, setB);
           updateTextList(setATextList, setA);
           updateTextList(setBTextList, setB);
           updateTextList(intersectionTextList, intersection);

           // Graph button action
           graphButton.setOnAction(actionEvent -> showGraphWindow());
        });

        // Add components to root (added graphButton)
        root.getChildren().addAll(labelA, textFieldA, labelB, textFieldB, updateButton, graphButton, diagramPane);

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

    private void showGraphWindow() {

        Stage graphStage = new Stage();

        Pane graphPane = new Pane();
        graphPane.setPrefSize(600, 600);

        int width = 600;
        int height = 600;
        int centerX = width / 2;
        int centerY = height / 2;
        int scale = 25;

        // Graph grid
        for (int i = -10; i <= 10; i++) {

            javafx.scene.shape.Line vLine = new javafx.scene.shape.Line(centerX + i * scale, 0, centerX + i * scale, height);

            javafx.scene.shape.Line hLine = new javafx.scene.shape.Line(0, centerY - i * scale, width, centerY - i * scale);

            vLine.setStroke(Color.LIGHTGRAY);
            hLine.setStroke(Color.LIGHTGRAY);

            graphPane.getChildren().addAll(vLine, hLine);

            if (i != 0) {
                Text xText = new Text(centerX + i * scale - 5, centerY + 15, String.valueOf(i));

                Text yText = new Text(centerX + 5, centerY - i * scale + 5, String.valueOf(i));

                graphPane.getChildren().addAll(xText, yText);
            }
        }

        // X and Y axis
        javafx.scene.shape.Line xAxis = new javafx.scene.shape.Line(0, centerY, width, centerY);

        javafx.scene.shape.Line yAxis = new javafx.scene.shape.Line(centerX, 0, centerX, height);

        xAxis.setStroke(Color.BLACK);
        yAxis.setStroke(Color.BLACK);

        graphPane.getChildren().addAll(xAxis, yAxis);

        // Functions
        for (double x = -10; x < 10; x += 0.1) {

            // f(x) = 2x
            javafx.scene.shape.Line line1 =
                    new javafx.scene.shape.Line(
                            centerX + x * scale,
                            centerY - (2 * x) * scale,
                            centerX + (x + 0.1) * scale,
                            centerY - (2 * (x + 0.1)) * scale);
            line1.setStroke(Color.BLUE);

            // f(x) = x^2
            javafx.scene.shape.Line line2 =
                    new javafx.scene.shape.Line(
                            centerX + x * scale,
                            centerY - (x * x) * scale,
                            centerX + (x + 0.1) * scale,
                            centerY - ((x + 0.1) * (x + 0.1)) * scale);
            line2.setStroke(Color.GREEN);

            // f(x) = 2^x
            javafx.scene.shape.Line line3 =
                    new javafx.scene.shape.Line(
                            centerX + x * scale,
                            centerY - Math.pow(2, x) * scale,
                            centerX + (x + 0.1) * scale,
                            centerY - Math.pow(2, x + 0.1) * scale);
            line3.setStroke(Color.RED);

            graphPane.getChildren().addAll(line1, line2, line3);
        }

        // f(x) = log₂(x)
        for (double x = 0.1; x < 10; x += 0.1) {
            double y = Math.log(x) / Math.log(2);
            javafx.scene.shape.Line logLine = new javafx.scene.shape.Line(
                    centerX + x * scale,
                    centerY - y * scale,
                    centerX + (x + 0.1) * scale,
                    centerY - (Math.log(x + 0.1) / Math.log(2)) * scale);
            logLine.setStroke(Color.PURPLE);

            graphPane.getChildren().add(logLine);
        }

        // Legend for graphs
        Text legendTitle = new Text(20, 30, "Legend:");
        legendTitle.setStyle("-fx-font-weight: bold;");

        Text f1 = new Text(20, 50, "Blue  →  f(x) = 2x");
        f1.setFill(Color.BLUE);

        Text f2 = new Text(20, 70, "Green →  f(x) = x^2");
        f2.setFill(Color.GREEN);

        Text f3 = new Text(20, 90, "Red   →  f(x) = 2^x");
        f3.setFill(Color.RED);

        Text f4 = new Text(20, 110, "Purple → f(x) = log₂(x)");
        f4.setFill(Color.PURPLE);

        graphPane.getChildren().addAll(legendTitle, f1, f2, f3, f4);

        Scene scene = new Scene(graphPane, width, height);

        graphStage.setTitle("Function Graphs");
        graphStage.setScene(scene);
        graphStage.show();
    }
}
