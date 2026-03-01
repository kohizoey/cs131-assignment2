package edu.elac.cs131.group5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static edu.elac.cs131.group5.Functions.graphPoints;
import static edu.elac.cs131.group5.VennDiagram.intersection;
import static edu.elac.cs131.group5.VennDiagram.union;

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

        HBox diagramsBox = new HBox(20);
        VBox vennDiagramBox = new VBox(10);

        // Components
        Label labelA = new Label("Enter first set (A):");
        TextField textFieldA = new TextField();
        Label labelB = new Label("Enter second set (B):");
        TextField textFieldB = new TextField();

        // Diagram pane
        Pane diagramPane = new Pane();
        diagramPane.setPrefSize(600, 450);

        Circle circleA = new Circle(150, 150, 120); // x, y, radius
        Circle circleB = new Circle(275, 150, 120);
        styleVennDiagramForIntersection(circleA, circleB);

        VBox setATextList = new VBox(2);
        setATextList.setLayoutX(100);
        setATextList.setLayoutY(100);

        VBox setBTextList = new VBox(2);
        setBTextList.setLayoutX(300);
        setBTextList.setLayoutY(100);

        VBox intersectionTextList = new VBox(2);
        intersectionTextList.setLayoutX(200);
        intersectionTextList.setLayoutY(100);

        diagramPane.getChildren().addAll(circleA, circleB, setATextList, setBTextList, intersectionTextList);

        // Intersection button
        Button intersectionButton = new Button("Intersection");
        intersectionButton.setOnAction(event -> {
            styleVennDiagramForIntersection(circleA, circleB);

            Set<String> setA = textFieldToSet(textFieldA);
            Set<String> setB = textFieldToSet(textFieldB);
            Set<String> intersection = intersection(setA, setB);
            updateTextList(setATextList, setA);
            updateTextList(setBTextList, setB);
            updateTextList(intersectionTextList, intersection);
        });

        Button unionButton = new Button("Union");
        unionButton.setOnAction(event -> {
            styleVennDiagramForUnion(circleA, circleB);

            Set<String> setA = textFieldToSet(textFieldA);
            Set<String> setB = textFieldToSet(textFieldB);
            Set<String> union = union(setA, setB);

            setATextList.getChildren().clear();
            setBTextList.getChildren().clear();
            updateTextList(intersectionTextList, union);
        });

        HBox vennDiagramButtonBox = new HBox(10);
        vennDiagramButtonBox.getChildren().addAll(intersectionButton, unionButton);

        // Functions
        VBox functionsBox = new VBox(10);

        Button linearButton = new Button("Linear");
        Button quadraticButton = new Button("Quadratic");
        Button exponentialButton = new Button("Exponential");
        Button logarithmicButton = new Button("Logarithmic");

        HBox functionsButtonBox = new HBox(10);
        functionsButtonBox.getChildren().addAll(linearButton, quadraticButton, exponentialButton, logarithmicButton);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("y");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Functions");
        lineChart.setAnimated(false);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        linearButton.setOnAction(event -> {
            series.getData().clear();
            series.setName("y = x");
            double[][] points = graphPoints(Functions.Type.linear, -10, 10, 41);
            for (double[] point : points) {
                series.getData().add(new XYChart.Data<>(point[0], point[1]));
            }
        });

        quadraticButton.setOnAction(event -> {
            series.getData().clear();
            series.setName("y = x²");
            double[][] points = graphPoints(Functions.Type.quadratic, -10, 10, 41);
            for (double[] point : points) {
                series.getData().add(new XYChart.Data<>(point[0], point[1]));
            }
        });

        exponentialButton.setOnAction(event -> {
            series.getData().clear();
            series.setName("y = 2^x");
            double[][] points = graphPoints(Functions.Type.exponential, -10, 10, 41);
            for (double[] point : points) {
                series.getData().add(new XYChart.Data<>(point[0], point[1]));
            }
        });

        logarithmicButton.setOnAction(event -> {
            series.getData().clear();
            series.setName("y = log₂(x)");
            double[][] points = graphPoints(Functions.Type.logarithmic, -10, 10, 41);
            for (double[] point : points) {
                if (!Double.isNaN(point[1])) {
                    series.getData().add(new XYChart.Data<>(point[0], point[1]));
                }
            }
        });

        lineChart.getData().add(series);
        functionsBox.getChildren().addAll(functionsButtonBox, lineChart);

        // Add components to root
        vennDiagramBox.getChildren().addAll(labelA, textFieldA, labelB, textFieldB, vennDiagramButtonBox, diagramPane);
        diagramsBox.getChildren().addAll(vennDiagramBox, functionsBox);
        root.getChildren().addAll(diagramsBox);

        // Create scene and add it to stage
        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTextList(VBox textList, Set<String> set) {
        textList.getChildren().clear();
        set.forEach(item -> textList.getChildren().add(new Text(item)));
    }

    private void styleVennDiagramForIntersection(Circle circleA, Circle circleB) {
        circleA.setFill(Color.BLUE);
        circleB.setFill(Color.LIGHTCORAL);
        circleA.setOpacity(0.5);
        circleB.setOpacity(0.5);
    }

    private void styleVennDiagramForUnion(Circle circleA, Circle circleB) {
        Color combinedColor = Color.hsb(292, 0.34, 0.74);
        circleA.setFill(combinedColor);
        circleB.setFill(combinedColor);
        circleA.setOpacity(1.0);
        circleB.setOpacity(1.0);
    }

    private Set<String> textFieldToSet(TextField textField) {
        String setAText = textField.getText();

        return new HashSet<>(Arrays.asList(setAText.split(",\\s*")));
    }

}