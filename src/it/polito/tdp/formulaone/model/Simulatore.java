package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.formulaone.model.Evento.TipoEvento;

public class Simulatore {
	private double P;
	private int T;
	
	//modello del sistema
	private List<LapTime> giri;
	
	
	private Map<Driver, Integer> classifica;
	private PriorityQueue<Evento> queue;
	
	public void init(List<LapTime> giri, int T, double P, List<Driver> piloti) {
		this.P=P;
		this.T=T;
		classifica = new HashMap<Driver, Integer>();
		for(Driver d :piloti) {
			classifica.put(d, 0);
		}
		
		this.giri=giri;
		
		queue=new PriorityQueue<Evento>();
		for(LapTime l : giri) {
			queue.add(new Evento(l.getDriverId(), l.getLap(), l.getMiliseconds(), TipoEvento.INIZIA_GIRO));
		}
	}
	
	public void run() {
		Evento e;
		while((e=queue.poll())!=null) {
			switch(e.getTipo()) {
			case INIZIA_GIRO:
				break;
			case PIT_STOP:
				
				break;
			}
		}
	}
}
