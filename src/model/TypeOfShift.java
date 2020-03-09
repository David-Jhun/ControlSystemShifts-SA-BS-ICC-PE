package model;

public class TypeOfShift {
	
	private String name;
	private double duration;
	
	public TypeOfShift( String name, double duration ) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public double getDuration() {
		return duration;
	}
	
}
