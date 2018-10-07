package vgjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.StageStyle;

public class Edit {

    @FXML private TextField name, pass, acc, saldo;
    @FXML private Button sparaKnapp, back;

    private final Accounts controller;
    private Stage stage;
    private Account a;

    public Edit(Accounts controller) {
        this.controller = controller;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Edit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize()  {
        sparaKnapp.setOnAction(e -> {
            try {
                updateLabels();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.close();
        });

        back.setOnAction(e -> {
            try {
                backOnClick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.close();
        });
    }

    public void showStage() { stage.showAndWait(); }

    @FXML
    public void backOnClick() throws Exception {
        stage.close();
    }

    @FXML
    public void setLabels(Account a) throws Exception{
        this.a = a;
        name.setText(a.getAnv());
        pass.setText(a.getLos());
        acc.setText(a.getKonto());
        saldo.setText(Integer.toString(a.getSaldo()));
    }

    public void updateLabels() throws Exception {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("Bank"), StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(controller.getName()+";")) {
                fileContent.set(i, name.getText()+";"+ pass.getText()+";"+ acc.getText()+";"+saldo.getText());
                break;
            }
        }
        Files.write(Paths.get("Bank"), fileContent, StandardCharsets.UTF_8);
        a.setAnv(name.getText());
        a.setKonto(acc.getText());
        a.setLos(pass.getText());
        a.setSaldo(Integer.parseInt(saldo.getText()));
        controller.setInformation(a);
    }



}
