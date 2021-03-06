package controller;

import controller.holiday.ReadHolidayController;
import controller.holiday.ReadRemainHolidayController;
import controller.holiday.CreateHolidayController;
import controller.holiday.DeleteHolidayController;
import controller.holiday.ReadCalendarController;
import controller.holiday.ReadDetailHolidayController;
import controller.holiday.UpdateHolidayController;
import controller.holiday.UpdateHolidayFlagController;
import controller.staff.CheckIdController;
import controller.staff.CreateUserController;
import controller.staff.DeleteUserController;
import controller.staff.LoginController;
import controller.staff.LoginViewController;
import controller.staff.LogoutController;
import controller.staff.ReadTotalUserController;
import controller.staff.ReadUserController;
import controller.staff.UpdateUserController;
import controller.staff.UpdateUserPositionController;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		return instance;
	}

	public Controller create(String command) {
		Controller c = null;
		if (command.equals("login")) {
			c = new LoginController();
		} else if (command.equals("logout")) {
			c = new LogoutController();
		} else if (command.equals("read_user")) {
			c = new ReadUserController();
		} else if (command.equals("update_user")) {
			c = new UpdateUserController();
		} else if (command.equals("create_user")) {
			c = new CreateUserController();
		} else if (command.equals("check_id")) {
			c = new CheckIdController();
		} else if (command.equals("read_detail_holiday")) {
			c = new ReadDetailHolidayController();
		} else if (command.equals("update_holiday")) {
			c = new UpdateHolidayController();
		} else if (command.equals("read_holiday")) {
			c = new ReadHolidayController();
		} else if (command.equals("delete_holiday")) {
			c = new DeleteHolidayController();
		} else if (command.equals("update_holiday_flag")) {
			c = new UpdateHolidayFlagController();
		} else if(command.equals("login_view")) {
			c= new LoginViewController();
		}else if(command.equals("create_holiday")) {
        	c=new CreateHolidayController();
        }else if(command.equals("remain_holiday")) {
        	c=new ReadRemainHolidayController();
        }else if(command.equals("read_total_user")) {
			c = new ReadTotalUserController();
		}else if(command.equals("update_user_position")) {
			c = new UpdateUserPositionController();
		}else if( command.equals("delete_user")) {
			c = new DeleteUserController();
        }else if(command.equals("read_calendar")){
          c = new ReadCalendarController();
		}
		
		return c;
	}
}
