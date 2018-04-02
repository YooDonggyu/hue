package controller;

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
        }
		return c;
	}
}
