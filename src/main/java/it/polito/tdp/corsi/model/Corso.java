package it.polito.tdp.corsi.model;

import java.util.*;

public class Corso {

	//chiamo i nomi dei cosiddetti JavaBeans come le colonne delle tabelle del database
	private String codins;
	private Integer crediti;
	private String nome;
	private Integer pd;
	
	
	public Corso(String codins, Integer crediti, String nome, Integer pd) {
		//super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}


	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPd() {
		return pd;
	}
	public void setPd(Integer pd) {
		this.pd = pd;
	}

	
	//lo faccio solo per codins perchè è la primary key dell'hashmap
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return codins +" " + crediti + " " + nome + " " + pd;
	}
	

	
	
}
