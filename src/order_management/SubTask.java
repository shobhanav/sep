package order_management;

import java.util.Date;

public class SubTask {
	
	private int identifier;
	
	private static int index =0 ;
	
	private int id_task;
	
	private int id_crd;
	
	private String clientName;
	
	private String eventType;
	
	private Date fromDate_project;
	
	private Date toDate_project;
	
	private Date fromDate_subtask;
	
	private Date toDate_subtask;
	
	private int no_of_attendees;
	
	private int exp_budget_for_task;
	
	private Priority priority;
	
	private String team;
	
	private String teamMember;
	
	private SubTaskState state;
	
	public SubTask(Task task){	
		this.identifier = index;
		index = index +1;
		this.id_task = task.getIdentifier();
		this.id_crd = task.getId_crd();
		this.clientName = task.getClientName();
		this.eventType = task.getEventType();
		this.fromDate_project = task.getFromDate_project();
		this.toDate_project = task.getToDate_project();
		this.no_of_attendees = task.getNo_of_attendees();
		state = SubTaskState.CREATED;
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

	public Date getFromDate_subtask() {
		return fromDate_subtask;
	}

	public void setFromDate_subtask(Date fromDate_subtask) {
		this.fromDate_subtask = fromDate_subtask;
	}

	public Date getToDate_subtask() {
		return toDate_subtask;
	}

	public void setToDate_subtask(Date toDate_subtask) {
		this.toDate_subtask = toDate_subtask;
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

	public String getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}

	public SubTaskState getState() {
		return state;
	}

	public void setState(SubTaskState state) {
		this.state = state;
	}

	public int getIdentifier() {
		return identifier;
	}

	public int getId_task() {
		return id_task;
	}

	public int getId_crd() {
		return id_crd;
	}
	
	
}
