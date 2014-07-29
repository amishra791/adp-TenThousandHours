package edu.foothill.tenthousandhours;

public class Activity {

	private Integer id;
	private String name;
	
	Activity(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}

}
