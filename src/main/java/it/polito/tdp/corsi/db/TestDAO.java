package it.polito.tdp.corsi.db;

import it.polito.tdp.corsi.model.*;

public class TestDAO {

	public static void main(String[] args) {
		
		CorsoDAO dao = new CorsoDAO();
		System.out.println(dao.getStudentiByCorso(new Corso("01KSUPG", null, null, null)));
	}
}

//approccio bottom-up:
//parto dal CorsoDAO, risalgo al model, risalgo al controller