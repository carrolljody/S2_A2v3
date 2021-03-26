package viewmodel;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;

public class LoginViewModel
{
  private StringProperty username;
  private StringProperty error;
  private Model model;
  private ViewState viewState;

  public LoginViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    this.viewState = viewState;
    // model.addListener("Broadcast", this);
  }

  public void clear()
  {
    username.set("");
    error.set("");
  }

  public void login()
  {
    try{
      viewState.setUserName(username.get());
      model.login(username.get());
    }catch(Exception e){
      clear();
      error.set(e.getMessage());
    }
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

 /* @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      error.set((String) evt.getNewValue());
    });  }*/
}
