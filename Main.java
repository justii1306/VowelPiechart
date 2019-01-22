package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

public class Main extends Application {

    ObservableList<Data> data = FXCollections.observableArrayList(
            new Data("Test"), new Data("Drugi test"), new Data("Dlugie zdanie, w ktorym beda zliczane wszystkie samogloski")
    );

    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                    new PieChart.Data("a", data.get(0).getaCount()),
                    new PieChart.Data("e", data.get(0).geteCount()),
                    new PieChart.Data("i", data.get(0).getiCount()),
                    new PieChart.Data("o", data.get(0).getoCount()),
                    new PieChart.Data("u", data.get(0).getuCount())
            );

    TableView<Data> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        SplitPane root = new SplitPane();
        root.setDividerPositions(0.3093645484949833);

        AnchorPane leftAnchorPane = new AnchorPane();
        leftAnchorPane.setMaxWidth(182);
        leftAnchorPane.setMinWidth(182);

        tableView.setLayoutX(3);
        tableView.setLayoutY(31);
        tableView.setPrefSize(175.0, 200.0);

        TableColumn stringColumn = new TableColumn("Napis");
        stringColumn.setPrefWidth(175);
        stringColumn.setCellValueFactory(
                new PropertyValueFactory<>("napis"));
        tableView.getColumns().addAll(stringColumn);
        tableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

        tableView.setRowFactory(tv -> {
            TableRow<Data> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Data rowData = row.getItem();
                pieChartData.clear();
                pieChartData.add(new PieChart.Data("a", rowData.getaCount()));
                pieChartData.add(new PieChart.Data("e", rowData.geteCount()));
                pieChartData.add(new PieChart.Data("i", rowData.getiCount()));
                pieChartData.add(new PieChart.Data("o", rowData.getoCount()));
                pieChartData.add(new PieChart.Data("u", rowData.getuCount()));
                pieChartData.forEach(data ->
                        data.nameProperty().bind(
                                Bindings.concat(data.getName(), " ", data.pieValueProperty())
                        )
                );
            });
            return row ;
        });

        tableView.setItems(data);

        Label stringLabel = new Label("Napis: ");
        stringLabel.setLayoutX(45);
        stringLabel.setLayoutY(265);

        TextField newTextField = new TextField();
        newTextField.setLayoutX(90);
        newTextField.setLayoutY(260);
        newTextField.setPrefWidth(85);

        Button button = new Button("Aktualizuj");
        button.setLayoutX(100);
        button.setLayoutY(330);

        button.setOnAction((ActionEvent e) -> {
            data.add(new Data(newTextField.getText()));
        });

        leftAnchorPane.getChildren().addAll(tableView, stringLabel, newTextField, button);


        AnchorPane rightAnchorPane = new AnchorPane();

        PieChart pieChart = new PieChart(pieChartData);
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " ", data.pieValueProperty())
                )
        );
        pieChart.setPrefSize(390, 280);
        pieChart.setTitle("Liczba wystąpień");

        rightAnchorPane.getChildren().addAll(pieChart);

        root.getItems().addAll(leftAnchorPane,rightAnchorPane);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
