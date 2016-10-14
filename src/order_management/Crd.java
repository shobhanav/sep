package order_management;

import java.util.Date;
import java.util.HashMap;

public class Crd {
	
	private int identifier;
	
	private String clientName;
	
	private String eventType;
	
	private Date fromDate;
	
	private Date toDate;
	
	private int no_of_attendees;
	
	private int exp_budget;
	
	private CrdState state;
	
	private HashMap<String,String> tasks = new HashMap<String,String>();
	
	public Crd(Rep rep){		
		identifier = rep.getIdentifier();
		this.clientName = rep.getClientName();
		this.eventType = rep.getEventType();
		this.fromDate = rep.getFromDate();
		this.toDate = rep.getToDate();
		this.no_of_attendees = rep.getNo_of_attendees();
		this.exp_budget = rep.getExp_budget();
		state = CrdState.CREATED;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getNo_of_attendees() {
		return no_of_attendees;
	}

	public void setNo_of_attendees(int no_of_attendees) {
		this.no_of_attendees = no_of_attendees;
	}

	public int getExp_budget() {
		return exp_budget;
	}

	public void setExp_budget(int exp_budget) {
		this.exp_budget = exp_budget;
	}


	public int getIdentifier() {
		return identifier;
	}
	public CrdState getState (){
		return state;
	}
	
	
	public void setState (CrdState state){
		this.state = state;
	}
	
	public void addTask(String team, String task){
		this.tasks.put(team, task);
	}
	
	public String getTask(String team){
		return this.tasks.get(team);
	}

}
