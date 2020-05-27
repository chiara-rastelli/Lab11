package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO db;
	private Map<Integer, River> riverMap;
	private List<Flow> misurazioniFiume;
	private Simulator simulatore;
	
	public void LoadMisurazioni(River fiume) {
		this.misurazioniFiume = new ArrayList<>(this.db.getInfoDatoFiume(fiume));
		/*
		for (Flow f : this.db.getFlow(riverMap)) {
			if (f.getRiver().equals(fiume))
				this.misurazioniFiume.add(f);
		}*/
		
		Collections.sort(this.misurazioniFiume, new ComparatoreFlowData());
		this.simulatore.setDataSet(misurazioniFiume);
	}
	
	public Flow getPrimaMisurazione() {
		if (this.misurazioniFiume.size()==0) {
			System.out.println("ERRORE");
			return null;
		}else
			return this.misurazioniFiume.get(0);
	}
	
	public Flow getUltimaMisurazione() {
		if(this.misurazioniFiume.size()==0) {
			System.out.println("ERRORE");
			return null;
		}else
			return this.misurazioniFiume.get(this.misurazioniFiume.size()-1);
	}
	
	public double getMediaMisurazioni() {
		double somma = 0.0;
		for (Flow f : this.misurazioniFiume)
			somma += f.getFlow();
		return somma/this.misurazioniFiume.size();
	}
	
	public int getNumeroMisurazioni() {
		return this.misurazioniFiume.size();
	}
	
	public List<Flow> getMisurazioniFiume(){
		return this.misurazioniFiume;
	}
	
	

	public Model() {
		
		this.db  = new RiversDAO();
		this.riverMap = new HashMap<>();
		this.db.getAllRivers(this.riverMap);
		this.simulatore = new Simulator();
		
	}
	
	public List<River> getAllRiversList(){
		List<River> temp = new ArrayList<River>();
		for (River r : this.riverMap.values())
			temp.add(r);
		return temp;
	}

}
