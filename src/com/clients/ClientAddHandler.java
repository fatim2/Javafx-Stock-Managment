package com.clients;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

public class ClientAddHandler {
    IClientDAO pdao = new ClientDaolmpl();
    FormClientsWindow formClient = null;
    public ClientAddHandler(){}
    public ClientAddHandler(FormClientsWindow formClient){
        this.formClient = formClient;
    }
    public void addClick(){
        String nom =  formClient.clientNomTextField.getText();
        String prenom =  formClient.clientPrenomTextField.getText();
        String telephone =  formClient.clientTelephoneTextField.getText();
        String email = formClient.clientEmailTextField.getText();
        String adresse =  formClient.clientAdressTextField.getText();
        
        if(validateNumber()&&validateEmail())
            {Client client = new Client(0, nom, prenom, telephone, email, adresse);
            pdao.add(client);
            
        }
    }
        public boolean validateNumber(){
            Pattern p = Pattern.compile("06[0-9]{8}");
            Matcher m = p.matcher(formClient.clientTelephoneTextField.getText());
            if(m.find()&& m.group().equals(formClient.clientTelephoneTextField.getText())){
                return true;
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validation numéro");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un numéro de téléphone valide");
                alert.showAndWait();
                return false;
            }
        }
        public boolean validateNumber(String n){
            Pattern p = Pattern.compile("06[0-9]{8}");
            Matcher m = p.matcher(n);
            if(m.find()&& m.group().equals(n)){
                return true;
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validation numéro");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un numéro de téléphone valide");
                alert.showAndWait();
                return false;
            }
        }

        public boolean validateEmail(){
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(formClient.clientEmailTextField.getText());
            if(m.find() && m.group().equals(formClient.clientEmailTextField.getText())){
                return true;
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validation Email");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un email valide");
                alert.showAndWait();
                return false;
            }
        }
        public boolean validateEmail(String em){
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(em);
            if(m.find() && m.group().equals(em)){
                return true;
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validation Email");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un email valide");
                alert.showAndWait();
                return false;
            }
        }
    }
