package vgjavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class ShowTable {

    @FXML private Button back;
    @FXML private TableView<Account> table;
    @FXML private TableColumn<Account, String> anvCol;
    @FXML private TableColumn<Account, Integer> saldoCol;

    private Stage stage;
    private ObservableList<Account> observableList;


    public ShowTable() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("show.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Lista");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        back.setOnAction(e -> {
            try {
                onBackClick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void onBackClick(){
        stage.close();
    }

    public void showStage() { stage.showAndWait(); }
    
    //Sätter värderna i tableView
    public void setList(ObservableList<Account> observableList) {
        this.observableList = observableList;
        anvCol.setCellValueFactory(new PropertyValueFactory<>("anv"));
        saldoCol.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        table.setItems(observableList);
        System.out.println(observableList.get(0).getAnv());
    }
}
