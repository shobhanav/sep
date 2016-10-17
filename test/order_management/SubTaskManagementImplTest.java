package order_management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubTaskManagementImplTest {
	SubTaskManagementInterface SubTaskMng;
	
	@Before
	public void setUp() throws Exception {
		SubTaskMng=new SubTaskManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		SubTaskMng = null;
	}

	@Test
	public void createSubTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("the name of the client is wrong", subTask.getClientName().equals("client1"));
		assertTrue("the name of the user is wrong", subTask.getTeam().equals("Decoration"));
		assertTrue("the name of the user is wrong", subTask.getTeamMember().equals("alex"));
		assertTrue("the name of the user is wrong", subTask.getId_crd() == rep.getIdentifier());
		assertTrue("the name of the user is wrong", subTask.getId_task() == task.getIdentifier());
	}
	
	@Test
	public void addSubTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		ArrayList<SubTask> list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
	}
	
	@Test
	public void addTwoElementsSubTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = new Task(crd,"Decoration");
		subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		ArrayList<SubTask> list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
	}
	
	@Test
	public void addTwoElementsIDSubTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		assertFalse("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		ArrayList<SubTask> list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
	}
	
	
	@Test
	public void listElementsSubTaskTeamTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task,"alex2");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		task = new Task(crd,"FoodAndDrink");
		subTask = SubTaskMng.createSubTasks(task,"alex3");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		task = new Task(crd,"FoodAndDrink");
		subTask = SubTaskMng.createSubTasks(task,"alex3");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task,"alex3");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		task = new Task(crd,"Decoration");
		subTask = SubTaskMng.createSubTasks(task,"alex3");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		ArrayList<SubTask> list = SubTaskMng.getSubTaskTeam("FoodAndDrink");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 6);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
	}
	
	@Test
	public void listElementsSubTaskCdrTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd1 = new Crd(rep);
		Task task1 = new Task(crd1,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task1,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task1,"paul");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task1,"louis");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		Task task2 = new Task(crd1,"Decoration");
		subTask = SubTaskMng.createSubTasks(task2,"paul");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		rep = new Rep("cso","client2");
		Crd crd2 = new Crd(rep);		
		Task task3 = new Task(crd2,"Decoration");
		subTask = SubTaskMng.createSubTasks(task3,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task3,"louis");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		Task task4 = new Task(crd2,"FoodAndDrink");
		subTask = SubTaskMng.createSubTasks(task4,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task4,"paul");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		subTask = SubTaskMng.createSubTasks(task4,"paul");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		//get by task
		ArrayList<SubTask> list = SubTaskMng.getSubTaskTask(task1);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskTask(task2);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskTask(task3);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskTask(task4);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		
		//get by crd
		list = SubTaskMng.getSubTaskCrd(crd1);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 4);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskCrd(crd2);
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 5);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		
		//get by team
		list = SubTaskMng.getSubTaskTeam("Decoration");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 6);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskTeam("FoodAndDrink");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		
		//get by team member
		list = SubTaskMng.getSubTaskTeamMember("alex");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		list = SubTaskMng.getSubTaskTeamMember("paul");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 4);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		list = SubTaskMng.getSubTaskTeamMember("louis");
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("SubTaskManagementImpl acess to a bad element", !list.contains(subTask));
		
		//all
		list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 9);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
	}
	
		@Test
	public void addGetSubTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = new Task(crd,"FoodAndDrink");
		subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		assertTrue("SubTaskManagementImpl access invalid at the new SubTask", SubTaskMng.getSubTask(subTask.getIdentifier()).equals(subTask));
	}
	
	
	
	@Test
	public void deleteSubTaskTest(){
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = new Task(crd,"Decoration");
		SubTask subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		ArrayList<SubTask> list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = new Task(crd,"Decoration");
		subTask = SubTaskMng.createSubTasks(task,"alex");
		assertTrue("SubTaskManagementImpl can't add a new SubTask", SubTaskMng.addSubTask(subTask));
		
		list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("SubTaskManagementImpl acess to a bad element", list.contains(subTask));
		
		ArrayList<SubTask> history = SubTaskMng.getSubTaskHistory();
		assertTrue("SubTaskManagementImpl acess to a bad history", history.size() == 0);
		SubTaskMng.deleteSubTask(subTask.getIdentifier());
		list = SubTaskMng.getAllSubTask();
		assertTrue("SubTaskManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("SubTaskManagementImpl acess to a delete element", list.contains(subTask));
		
		history = SubTaskMng.getSubTaskHistory();
		assertTrue("SubTaskManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("SubTaskManagementImpl can't acess to a delete element", history.contains(subTask));	
		
	}
}
