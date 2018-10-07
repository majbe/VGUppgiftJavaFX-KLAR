package vgjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.stage.StageStyle;


public class Accounts {

    @FXML private Button backToLogin, edit, remove;
    @FXML private Label getName,getPass,getAccNr,getSaldo;

    private final Stage stage;
    private final Controller controller;
    private Account a;


    public Accounts(Controller controller) throws Exception {
        this.controller = controller;

        stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("AccountPage");
    }

    public void showStage() {
        stage.showAndWait();
    }

    public void initialize() {
        //setInformation();
        backToLogin.setOnAction(e -> {
            try {
                backClick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        edit.setOnAction(e -> {
            try {
                editUser();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        remove.setOnAction(e -> {
            try {
                removeUser();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    public void backClick() throws Exception {
        stage.close();
    }

    public void setInformation(Account a) {
        this.a = a;
        getName.setText(a.getAnv());
        getPass.setText(a.getLos());
        getAccNr.setText(a.getKonto());
        getSaldo.setText(Integer.toString(a.getSaldo()));
        /*getName.setText(controller.list.get(controller.getId()).getAnv());
        getPass.setText(controller.list.get(controller.getId()).getLos());
        getAccNr.setText(controller.list.get(controller.getId()).getKonto());
        getSaldo.setText(Integer.toString(controller.list.get(controller.getId()).getSaldo()));*/
    }



    private void editUser() throws Exception{
        Edit edit = new Edit(this);
        edit.setLabels(a);
        edit.showStage();
    }

    public void removeUser()throws Exception{
        BufferedWriter wr = new BufferedWriter(new FileWriter("Bank",false));

        for(int i = 0; i<controller.list.size(); i++){
            if(controller.list.get(i).getAnv() == getName.getText()){
                controller.list.remove(i);
            }
        }

        getName.setText(" ");
        getPass.setText(" ");
        getAccNr.setText(" ");
        getSaldo.setText(" ");

        for(int i = 0; i<controller.list.size(); i++){
            Account tmp = controller.list.get(i);
            wr.write(tmp.getAnv() + ";" + tmp.getLos() + ";" + tmp.getKonto() + ";" + tmp.getSaldo());
            wr.newLine();
        }

        wr.flush();
        wr.close();
    }

    public void nyaLabels(String namn, String konto, String losen, int saldo) {
        getName.setText(namn);
        getAccNr.setText(konto);
        getPass.setText(losen);
        getSaldo.setText(Integer.toString(saldo));
    }

    public String getName() { return getName.getText(); }

    public String getKonto() { return getAccNr.getText(); }

    public String getLos() { return getPass.getText(); }

    public String getSaldo() { return getSaldo.getText(); }



}




