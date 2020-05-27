package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	// PARAMETRI DI SIMULAZIONE
	private double fMed = 0.0;
	private int giorniSimulazione = 30;
	private double fattoreScala = 1.0;
	private double capienzaTotale = 0.0; // Q, espressa in m^3
	private double occupazioneBacino = 0.0; // C
	private double flussoUscitaMinimo = 0.0;
	
	public void setDataSet(List<Flow> dataSet) {
		this.dataSet = dataSet;
	}

	// valori iniziali 
	private double occupazioneIniziale = 0.0;
	private List<Flow> dataSet;
	
	// VALORI DA CALCOLARE
	private double cMed = 0.0; // occupazione media del bacino in corso di simulazione
	private int giorniDisservizio = 0; // numero di giorni in cui non si e' potuta garantire l'erogazione minima richiesta
	
	public void setFMed(double input) {
		this.fMed = input;
		this.flussoUscitaMinimo = 0.8 * input;
	}
	
	public void setFattoreScala(String input) {
		double fattore;
		try{
			fattore = Double.parseDouble(input);
		}catch (Exception e) {
			throw new NumberFormatException("Non e' stato inserito un valore valido per k");
		}
		if (fattore <= 0) {
			throw new NumberFormatException("Il valore k inserito deve essere maggiore di zero!");
		}else
			this.fattoreScala = fattore;
		this.capienzaTotale = this.fattoreScala * this.fMed * 30 * 24 * 60 * 60;
		this.occupazioneIniziale = this.capienzaTotale / 2;
	}

	public double getcMed() {
		return cMed;
	}

	public int getGiorniDisservizio() {
		return giorniDisservizio;
	}
	
	public void run() {
		this.queue.clear();
		this.cMed = 0.0;
		this.giorniDisservizio = 0;
		this.occupazioneBacino = this.occupazioneIniziale;
		
		this.queue.add(new Event(EventType.F_IN, 1));
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
//			System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(it.polito.tdp.rivers.model.Event e) {
		
		switch (e.getType()){
		
		case F_IN:
			this.occupazioneBacino += this.dataSet.get(e.getDay()-1).getFlow();
			this.queue.add(new Event(EventType.F_OUT, e.getDay()));
			
		case F_OUT:
			
			
		}
		
	}
	
}
