package it.polito.tdp.PremierLeague.model;

import java.util.Objects;

public class Adiacenza2 {

	private Match m1;
	private Match m2;
	private int peso;
	
	public Adiacenza2(Match m1, Match m2, int peso) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.peso = peso;
	}
	public Match getM1() {
		return m1;
	}
	public void setM1(Match m1) {
		this.m1 = m1;
	}
	public Match getM2() {
		return m2;
	}
	public void setM2(Match m2) {
		this.m2 = m2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(m1, m2, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza2 other = (Adiacenza2) obj;
		return Objects.equals(m1, other.m1) && Objects.equals(m2, other.m2) && peso == other.peso;
	}
	@Override
	public String toString() {
		return "Adiacenza2 [m1=" + m1 + ", m2=" + m2 + ", peso=" + peso + "]";
	}
	
	
}
