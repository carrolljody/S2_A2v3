package viewmodel;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.MessagePackage;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty message;
  private ObservableList<String> logs;
  private Model model;
  private ViewState viewState;

  public ChatViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.message = new SimpleStringProperty();
    this.logs = FXCollections.observableArrayList();
    this.viewState = viewState;

    model.addListener(null, this);
  }

  public void sendMessage()
  {
    try
    {
      MessagePackage messagePackage = new MessagePackage("Message", messageProperty().get(), viewState.getUserName());
      model.sendMessage(messagePackage);
      messageProperty().set("");
    }catch (Exception e)
    {
      logs.add(e.getMessage());
    }
  }


  public StringProperty messageProperty()
  {
    return message;
  }

  public ObservableList<String> getLogs()
  {
    return logs;
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      logs.add((String) evt.getNewValue());
    });  }

  public void requestDetails() throws InterruptedException
  {
    MessagePackage requestMessage = new MessagePackage("Request", null,
        viewState.getUserName());
    logs.add(model.sendRequest(requestMessage));

  }
}
