package it.polito.tdp.PremierLeague.model;

import java.util.Objects;

public class Adiacenza {

	private int iD1;
	private int iD2;
	private int peso;
	
	public Adiacenza(int iD1, int iD2, int peso) {
		super();
		this.iD1 = iD1;
		this.iD2 = iD2;
		this.peso = peso;
	}

	public int getiD1() {
		return iD1;
	}

	public void setiD1(int iD1) {
		this.iD1 = iD1;
	}

	public int getiD2() {
		return iD2;
	}

	public void setiD2(int iD2) {
		this.iD2 = iD2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iD1, iD2, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		return iD1 == other.iD1 && iD2 == other.iD2 && peso == other.peso;
	}

	@Override
	public String toString() {
		return iD1 + " - " + iD2 + ", (" + peso + ")\n";
	}
	
	
}
