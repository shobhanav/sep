package order_management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskManagementImplTest {
	TaskManagementInterface TaskMng;
	
	@Before
	public void setUp() throws Exception {
		TaskMng=new TaskManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		TaskMng = null;
	}

	@Test
	public void createTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("the name of the client is wrong", task.getClientName().equals("client1"));
		assertTrue("the name of the user is wrong", task.getTeam().equals("Decoration"));
		assertTrue("the name of the user is wrong", task.getId_crd() == rep.getIdentifier());
	}
	
	@Test
	public void addTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
	}
	
	@Test
	public void addTwoElementsTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
	}
	
	@Test
	public void addTwoElementsIDTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		assertFalse("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
	}
	
	
	@Test
	public void listElementsTaskTeamTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		task = TaskMng.createTasks(crd,"FoodAndDrink");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getTaskTeam("FoodAndDrink");
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("TaskManagementImpl acess to a bad element", !list.contains(task));
		list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
	}
	
	@Test
	public void listElementsTaskCdrTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd1 = new Crd(rep);
		Task task = TaskMng.createTasks(crd1,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		task = TaskMng.createTasks(crd1,"FoodAndDrink");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		rep = new Rep("cso","client2");
		Crd crd2 = new Crd(rep);
		task = TaskMng.createTasks(crd2,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getTaskCrd(crd1);
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("TaskManagementImpl acess to a bad element", !list.contains(task));
		list = TaskMng.getTaskCrd(crd2);
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
		list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
	}
	
		@Test
	public void addGetTaskTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		assertTrue("TaskManagementImpl access invalid at the new task", TaskMng.getTask(task.getIdentifier()).equals(task));
		assertTrue("TaskManagementImpl access invalid at the new task", TaskMng.getTask(crd,"Decoration").equals(task));
	}
	
	
	
	@Test
	public void deleteTaskTest(){
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		Task task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		ArrayList<Task> list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
		
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		task = TaskMng.createTasks(crd,"Decoration");
		assertTrue("TaskManagementImpl can't add a new Task", TaskMng.addTask(task));
		
		list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("TaskManagementImpl acess to a bad element", list.contains(task));
		
		ArrayList<Task> history = TaskMng.getTaskHistory();
		assertTrue("TaskManagementImpl acess to a bad history", history.size() == 0);
		TaskMng.deleteTask(task.getIdentifier());
		list = TaskMng.getAllTask();
		assertTrue("TaskManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("TaskManagementImpl acess to a delete element", list.contains(task));
		
		history = TaskMng.getTaskHistory();
		assertTrue("TaskManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("TaskManagementImpl can't acess to a delete element", history.contains(task));	
		
	}
}
