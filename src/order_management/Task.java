package order_management;

import java.util.Date;

public class Task {
	private int identifier;
	
	private static int index =0 ;
	
	private int id_crd;
	
	private String clientName;
	
	private String eventType;
	
	private Date fromDate;
	
	private Date toDate;
	
	private int no_of_attendees;
	
	private int exp_budget_for_task;
	
	private Priority priority;
	
	private TaskState state;
	
	public Task(Crd crd){	
		this.identifier = index;
		index = index +1;
		this.identifier = 
		this.id_crd = crd.getIdentifier();
		this.clientName = crd.getClientName();
		this.eventType = crd.getEventType();
		this.fromDate = crd.getFromDate();
		this.toDate = crd.getToDate();
		this.no_of_attendees = crd.getNo_of_attendees();
		state = TaskState.CREATED;
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

	public int getExp_budget_for_task() {
		return exp_budget_for_task;
	}

	public void setExp_budget_for_task(int exp_budget_for_task) {
		this.exp_budget_for_task = exp_budget_for_task;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public int getIdentifier() {
		return identifier;
	}

	public int getId_crd() {
		return id_crd;
	}
	
}
