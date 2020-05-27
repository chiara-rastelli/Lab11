package it.polito.tdp.rivers.model;

public class Event implements Comparable<Event>{

	public enum EventType {
		F_IN, F_OUT
	}
	
	EventType type;
	int day;
	
	public Event(EventType type, int day) {
		this.type = type;
		this.day = day;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public int compareTo(Event o) {
		if (this.day == o.day) {
			if(this.type.equals(o.type))
				return 0;
			else 
				if (this.type.compareTo(EventType.F_IN)==0)
					return -1;
				else 
					return 1;
		}
		else
			return this.day - o.day;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ", day=" + day + "]";
	}
	
}
