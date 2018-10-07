package vgjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.StageStyle;


public class Create {

    @FXML private Button createNew,back;
    @FXML private TextField newUser,newPass,newAccNr,newSaldo;

    private final Stage stage;

    private Controller controller;

    public Create(Controller controller) throws Exception {
        this.controller = controller;

        stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("NewAccount");
    }

    @FXML
    private void initialize() throws Exception {
        createNew.setOnAction(e -> onButtonClick());
        back.setOnAction(e -> {
            try {
                BackClick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
    //Skapar en ny användare
    @FXML
    public void onButtonClick(){
        Account a = new Account();
        a.setAnv(newUser.getText());
        a.setLos(newPass.getText());
        a.setKonto(newAccNr.getText());
        a.setSaldo(Integer.parseInt(newSaldo.getText()));
        controller.list.add(a);
        printOut();
        stage.close();
    }


    @FXML
    public void BackClick() throws Exception {
        stage.close();
    }


    public void showStage() {
        stage.showAndWait();
    }
    //Skriver listan till filen
    public void printOut(){
        try {
            FileWriter wr = new FileWriter("Bank",true);
            for (Account a : controller.list) {
                wr.write(a.getAnv() + ";" + a.getLos() + ";" + a.getKonto() + ";" + a.getSaldo() + "\n");
            }
            wr.close();

        } catch (IOException e){
            System.out.println("Något gick fel");
        }
    }

}
