package controller;

import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.User;
import view.HomePage;
import view.Login;
import view.Register;

public class UserController {
	
	User user;
	Stage currStage;
	Register rp;
	Login lp;
	
	public UserController(Stage stage) {
		rp = new Register(stage);
		currStage = stage;
		user = new User();
		rp.getRegisterBtn().setOnAction(e -> register());
		rp.getGoToLoginBtn().setOnAction(e -> goToLogin());	
	}

	private void goToLogin() {
		lp = new Login(currStage);
		lp.getLoginBtn().setOnAction(e -> login());
	}

	public void login() {
		String username = lp.getUsernameTF().getText();
		String password = lp.getPasswordTF().getText();
		User searchUser = user.searchUser(username);
		if(searchUser != null) {
			if(searchUser.getPassword().equals(password)) {
				new HomePage(currStage);
			}
			else {
				lp.getErrorLbl().setText("Username and password do not match");
			}
		}
		else {
			lp.getErrorLbl().setText("User not found");
		}
	}
	
	
	public void register() {
		String username = rp.getUsernameTF().getText();
		String password = rp.getPasswordTF().getText();
		String phonenumber = rp.getPhoneTF().getText();
		String address = rp.getAddressTF().getText();
		RadioButton rb = (RadioButton) rp.getRoleGroup().getSelectedToggle();
		String role = null;
		if(rb != null) {
			role = rb.getText();
		}
		user.addUser(username, password, phonenumber, address, role);
		new HomePage(currStage);
	}

}