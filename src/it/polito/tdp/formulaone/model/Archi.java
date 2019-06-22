package it.polito.tdp.formulaone.model;

public class Archi {
	private Race race1;
	private Race race2;
	private int peso;
	public Archi(Race race1, Race race2, int peso) {
		super();
		this.race1 = race1;
		this.race2 = race2;
		this.peso = peso;
	}
	public Race getRace1() {
		return race1;
	}
	public void setRace1(Race race1) {
		this.race1 = race1;
	}
	public Race getRace2() {
		return race2;
	}
	public void setRace2(Race race2) {
		this.race2 = race2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return race1+" "+race2+" "+peso;
	}
	

}
