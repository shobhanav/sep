package main;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class TestLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Robot robot = new Robot();			
			
			Login window = new Login();
			window.startGUI(window);
			
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_SPACE);
			
			
			robot.delay(5000);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
