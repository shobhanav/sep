package order_management;

import java.util.ArrayList;

public interface SubTaskManagementInterface {

	public SubTask createsubTasks( Task task);
	
	public boolean addsubTask(SubTask subTask);
	
	public SubTask getSubTask(int id);
	
	public ArrayList<SubTask> getSubTask(Task task, String teamMember);
	
	public ArrayList<SubTask> getSubTaskTeam(String team);
	
	public ArrayList<SubTask> getSubTaskTeamMember(String teamMember);
	
	public ArrayList<SubTask> getAllSubTask();
}
