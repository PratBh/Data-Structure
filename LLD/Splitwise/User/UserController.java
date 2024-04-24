package LLD.Splitwise.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
	List<User> userList;
	
	public UserController(){
		this.userList=new ArrayList<User>();
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public User getUser(String id) {
		for(User user:userList) {
			if(user.userId==id)
				return user;
		}
		return null;
	}
	
	public List<User> getAllUsers(){
        return userList;
    }

}
