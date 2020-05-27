package it.polito.tdp.rivers.model;

import java.util.Comparator;

public class ComparatoreFlowData implements Comparator<Flow>{

	@Override
	public int compare(Flow o1, Flow o2) {
		// TODO Auto-generated method stub
		return o1.getDay().compareTo(o2.getDay());
	}

}
