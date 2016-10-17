package order_management;

import java.util.Date;

public class Task {
	private int identifier;
	
	private static int index =0 ;
	
	private int id_crd;
	
	private String clientName;
	
	private String eventType;
	
	private Date fromDate_project;
	
	private Date toDate_project;
	
	private Date fromDate_task;
	
	private Date toDate_task;
	
	private int no_of_attendees;
	
	private int exp_budget_for_task;
	
	private String description;
	
	private Priority priority;
	
	private String team;
	
	private TaskState state;
	
	public Task(Crd crd, String team){	
		this.identifier = index;
		index = index +1;
		this.id_crd = crd.getIdentifier();
		this.clientName = crd.getClientName();
		this.eventType = crd.getEventType();
		this.fromDate_project = crd.getFromDate();
		this.toDate_project = crd.getToDate();
		this.no_of_attendees = crd.getNo_of_attendees();
		this.team = team;
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

	public Date getFromDate_project() {
		return fromDate_project;
	}

	public void setFromDate_project(Date fromDate_project) {
		this.fromDate_project = fromDate_project;
	}

	public Date getToDate_project() {
		return toDate_project;
	}

	public void setToDate_project(Date toDate_project) {
		this.toDate_project = toDate_project;
	}

	public Date getFromDate_task() {
		return fromDate_task;
	}

	public void setFromDate_task(Date fromDate_task) {
		this.fromDate_task = fromDate_task;
	}

	public Date getToDate_task() {
		return toDate_task;
	}

	public void setToDate_task(Date toDate_task) {
		this.toDate_task = toDate_task;
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

	
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
