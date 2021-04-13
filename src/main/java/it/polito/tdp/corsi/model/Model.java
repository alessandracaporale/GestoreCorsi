package it.polito.tdp.corsi.model;

import it.polito.tdp.corsi.db.*;
import it.polito.tdp.corsi.model.*;
import java.util.*;

public class Model {
	
	private CorsoDAO corsoDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
	
	}
	
	public List<Corso> getCorsiByPeriodo (Integer pd) {
		return corsoDAO.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo (Integer pd) {
		return corsoDAO.getIscrittiByPeriodo(pd);
	}
	
	public List<Studente> getStudentiByCorso(String codice) {
		return corsoDAO.getStudentiByCorso(new Corso(codice, null, null, null));
	}
	
	public Map<String, Integer> getDivisioneCDS (String codice) {
		//cosa ci aspettiamo
		//dato il corso con codice ABC
		// GEST -> 50
		//INF -> 40
		//MEC -> 30
		
		//soluzione 1
		/*
		 * Map<String, Integer> divisione = new HashMap<String, Integer>();
		List<Studente> studenti = this.getStudentiByCorso(codice);
		for (Studente s : studenti) {
			if (s.getCDS() != null && !s.getCDS().equals("")) {
				if (divisione.get(s.getCDS()) == null) {
					divisione.put(s.getCDS(), 1);
				}
				else {
					divisione.put(s.getCDS(), divisione.get(s.getCDS())+1);
				}
			}		
		}
		return divisione;
		*/
		
		return corsoDAO.getDivisioneStudenti(new Corso (codice, null, null, null));
		
		
	}
	
	public boolean esisteCorso(String codice) {
		return corsoDAO.esisteCorso(new Corso(codice, null, null, null));
	}
	
}
