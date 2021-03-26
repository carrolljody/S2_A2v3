package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.LoginViewModel;

import java.io.IOException;

public class LoginViewController extends ViewController
{

  @FXML private TextField usernameField;
  @FXML private Label errorLabel;
  private LoginViewModel viewModel;

  public LoginViewController()
  {
  }

  @Override protected void initBindings()
  {
    this.viewModel = getViewModelFactory().getLoginViewModel();
    usernameField.textProperty()
        .bindBidirectional(viewModel.usernameProperty());
    errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
  }

  @FXML public void onLogin() throws IOException
  {
    if(usernameField.textProperty().get()!=null && usernameField.textProperty().get().length()>2)
    {
      viewModel.login();
      getViewHandler().openView(View.CHAT);
    }else{
      usernameField.textProperty().set("");
      errorLabel.textProperty().set("Username must have at least 3 characters");
    }
  }

  @FXML public void onEnter() throws IOException
  {
    onLogin();
  }
}
