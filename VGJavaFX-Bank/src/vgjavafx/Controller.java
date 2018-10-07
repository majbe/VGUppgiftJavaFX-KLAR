package vgjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML private TextField user;
    @FXML private PasswordField pass;
    @FXML private Button login,skapa, listaKnapp;
    @FXML private ImageView exit;

    private final Stage stage;
    ObservableList<Account> list = FXCollections.observableArrayList();
    private int id;
    private Account a;



    public Controller() throws Exception {

        stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Bank");
    }

    public void initialize() throws Exception{
        skapa.setOnAction(e -> {
            try {
                onButtonClick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        login.setOnAction(e -> {
            try {
                onLoginButton();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        //Läser in textfilen och sparar i listan
        try {
            BufferedReader fr = new BufferedReader(new FileReader("Bank"));
            String data = null;

            list.clear();
            while ((data = fr.readLine()) != null) {
                Account a = new Account();
                String[] tmp = data.split(";");
                a.setAnv(tmp[0]);
                a.setLos(tmp[1]);
                a.setKonto(tmp[2]);
                a.setSaldo(Integer.parseInt(tmp[3]));
                list.add(a);
            }

        } catch (IOException e) {
            System.out.println("Det gick inte att läsa filen");
        }

        listaKnapp.setOnAction(e -> showTable());

    }

    public void onButtonClick() throws Exception{
        Create create = new Create(this);
        create.showStage();
    }

    public int getId() {return id;}

    public void onLoginButton() throws Exception {

        if (checkInput()) {
            Accounts accounts = new Accounts(this);
            accounts.setInformation(a);
            accounts.showStage();
        }
        else{
            System.out.println("Login failed, try again");
        }

    }
    //Kollar användarens input mot listan och ser så det stämmer överens (login funktionen)
    public boolean checkInput() {

        boolean check = false;

        for(int i = 0; i<list.size(); i++){
            if(user.getText().equals(list.get(i).getAnv()) && pass.getText().equals(list.get(i).getLos())){
                check = true;
                a = list.get(i);
                break;
            }
            else{
                check = false;
            }
        }

        return check;
    }


    public void showStage() {
        stage.show();
    }


    private void showTable() {
        ShowTable showTable = new ShowTable();
        showTable.setList(list);
        showTable.showStage();
    }


}
