package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		River temp = model.getAllRiversList().get(3);
		System.out.println(temp);
		model.LoadMisurazioni(temp);
		for (Flow f : model.getMisurazioniFiume())
			System.out.println(f);
	}
	
}
