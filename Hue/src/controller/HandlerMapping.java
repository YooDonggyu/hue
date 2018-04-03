package controller;

import controller.holiday.UpdateHolidayController;
import controller.staff.CheckIdController;
import controller.staff.CreateUserController;
import controller.staff.LoginController;
import controller.staff.LogoutController;
import controller.staff.ReadUserController;
import controller.staff.UpdateUserController;


public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	private HandlerMapping() {}
	
	public static HandlerMapping getInstance() {
		return instance;
	}
	
	public Controller create(String command) {
		Controller c = null;
		
		if(command.equals("login")){
			c = new LoginController();
		}else if(command.equals("logout")) {
			c = new LogoutController();
		}else if(command.equals("read_user")) {
			c = new ReadUserController();
		} else if(command.equals("update_user")){
			c=new UpdateUserController();
		}else if(command.equals("create_user")){
			c=new CreateUserController();
        }else if(command.equals("check_id")) {
        	c=new CheckIdController();
        }else if(command.equals("update_holiday")) {
        	c=new UpdateHolidayController();
        }
		return c;
	}
}
