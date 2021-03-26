package model;

import model.log.Log;
import model.log.LogLine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private UserList users;
  private PropertyChangeSupport property;

  public ModelManager()
  {
    this.users = new UserList();
    this.property = new PropertyChangeSupport(this);
  }

  @Override public int getNumberOfUsers()
  {
    Log chatLog = Log.getInstance();
    chatLog.addLog("Request for number of users : " +users.size());

    return users.size();
  }

  @Override public User getUser(int index) throws IndexOutOfBoundsException
  {
    Log chatLog = Log.getInstance();
    chatLog.addLog("Request for user: " +users.getUser(index).getUserName());

    return users.getUser(index);
  }

  @Override public User getUserByName(String name)
  {
    Log chatLog = Log.getInstance();
    chatLog.addLog("Request for user: " +users.getUserByName(name));

    return users.getUserByName(name);
  }

  @Override public void addUser(User user)
      throws IllegalStateException, IllegalArgumentException
  {
    users.addUser(user);
    Log chatLog = Log.getInstance();
    chatLog.addLog("New User: " +user.getUserName());
    String welcomeMessage  = "Welcome "+user.getUserName()+" to the Chat";
    property.firePropertyChange("NewUser", welcomeMessage, user.getUserName());
  }

  @Override public void addUser(String userName)
      throws IllegalStateException, IllegalArgumentException
  {
    addUser(new User(userName));
  }


  @Override public boolean contains(User user)
  {
    return users.contains(user);
  }



  @Override public void addLog(String log)
  {
    Log chatLog = Log.getInstance();
    chatLog.addLog(log);
    property.firePropertyChange("Message", null, log);
  }

  @Override public ArrayList<LogLine> getLog()
  {
    Log chatLog = Log.getInstance();
    return chatLog.getLog();
  }

  @Override public String addMessage(String message)
  {
    return null;
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
}
