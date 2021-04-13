/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;

import java.util.*;
import java.util.ResourceBundle;
//import com.sun.tools.javac.util.List;
import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import it.polito.tdp.corsi.db.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	this.txtRisultato.clear();
    	
    	if (this.txtRisultato.getText()==null) {
    		this.txtRisultato.setText("Periodo didattico errato, inserire 1 o 2");
    		return;
    	}
    	String periodoStringa =txtPeriodo.getText();
    	Integer periodo;
    	
    	try {
    		periodo = Integer.parseInt(periodoStringa);
    	}
    	catch (NumberFormatException ne) {
    		txtRisultato.setText("Periodo didattico errato, inserire 1 o 2");
    		return;
    	}
    	
    	if (periodo<1 || periodo>2) {
    		this.txtRisultato.setText("Periodo didattico errato, inserire 1 o 2");
    		return;
    	}
    	List<Corso> corsi= this.model.getCorsiByPeriodo(periodo);
    	
    	for (Corso c : corsi) {
    		this.txtRisultato.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	
    	if (txtPeriodo.getText() == null) {
    		txtRisultato.setText("Periodo didattico errato, inserire 1 o 2");
    		return;
    	}
    	
    	String periodoStringa = txtPeriodo.getText();
    	Integer periodo;
    	
    	try {
    		periodo = Integer.parseInt(periodoStringa);
    	}
    	catch (NumberFormatException ne) {
    		txtRisultato.setText("Periodo didattico errato, inserire 1 o 2");
    		return;
    	}
    	
    	Map<Corso, Integer> corsiIscrizione = this.model.getIscrittiByPeriodo(periodo);
    	for (Corso c : corsiIscrizione.keySet()) {
    		this.txtRisultato.appendText(c.toString());
    		Integer n = corsiIscrizione.get(c);
    		this.txtRisultato.appendText("\t"+n+"\n");
    	}
    
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	//posso non usare il db perchè la stampa degli studenti
    	//ce l'ho già dal metodo stampStudenti
		this.txtRisultato.clear();
		
		String codice = txtCorso.getText();
		
		if (!model.esisteCorso(codice)) {
			this.txtRisultato.appendText("Il corso non esiste");
			return;
		}
		
		Map<String, Integer> divisione = model.getDivisioneCDS(codice);
		
		for (String cds : divisione.keySet()) {
			txtRisultato.appendText(cds+ " "+ divisione.get(cds)+"\n");
		}
		
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	String codice = txtCorso.getText();
    	
    	if(!model.esisteCorso(codice)) {
    		txtRisultato.appendText("Ilcorsonon esiste");
    	}
    	List<Studente> studenti = model.getStudentiByCorso(codice);
    	
    	if (studenti.size()==0) {
    		txtRisultato.appendText("Il corso non ha iscritti");
    		return;
    	}
    	
    	for (Studente s : studenti) {
    		txtRisultato.appendText(s+"\n");
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}