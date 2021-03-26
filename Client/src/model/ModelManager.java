package model;

import mediator.Client;
import mediator.MessagePackage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model
{
  private Client client;
  private PropertyChangeSupport property;

  public ModelManager() throws IOException
  {
    this.client = new Client(this);
    client.connect();
    this.property = new PropertyChangeSupport(this);
  }

  @Override public synchronized void login(String userName)
      throws IllegalStateException, IllegalArgumentException, IOException
  {
    client.login(userName);
  }

  @Override public synchronized void sendMessage(MessagePackage message)
  {
    client.sendMessage(message);
  }

  @Override public synchronized void setMessage(String message)
  {
    property.firePropertyChange("Message", null, message);
  }

  @Override public String sendRequest(MessagePackage requestMessage)
      throws InterruptedException
  {
    return client.sendRequest(requestMessage);
  }

  @Override public synchronized void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      property.addPropertyChangeListener(listener);
    }
    else
    {
      property.addPropertyChangeListener(propertyName, listener);
    }
  }

  @Override public synchronized void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      property.removePropertyChangeListener(listener);
    }
    else
    {
      property.removePropertyChangeListener(propertyName, listener);
    }
  }


  /*@Override public synchronized void addLog(String log)
  {
    String logValue = converter.getLogSize() + ": " + log;
    converter.addLog(logValue);
  }*/
}
