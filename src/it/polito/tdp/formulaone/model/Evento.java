package it.polito.tdp.formulaone.model;

public class Evento implements Comparable<Evento>{
	public enum TipoEvento{
		INIZIA_GIRO,
		PIT_STOP
	}
	
	private int pilota;
	private int giro;
	private int tempo;
	private TipoEvento tipo;
	public int getPilota() {
		return pilota;
	}
	public void setPilota(int pilota) {
		this.pilota = pilota;
	}
	public int getGiro() {
		return giro;
	}
	public void setGiro(int giro) {
		this.giro = giro;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public Evento(int pilota, int giro, int tempo, TipoEvento tipo) {
		super();
		this.pilota = pilota;
		this.giro = giro;
		this.tempo = tempo;
		this.tipo = tipo;
	}
	@Override
	public int compareTo(Evento o) {
		return this.giro - o.getGiro();
	}
}
