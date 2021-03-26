package model;

import model.log.LogLine;
import utility.observer.subject.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
  public int getNumberOfUsers();
  public User getUser(int index) throws IndexOutOfBoundsException;
  public User getUserByName(String name);
  public void addUser(User user)
      throws IllegalStateException, IllegalArgumentException;
  public void addUser(String userName)
      throws IllegalStateException, IllegalArgumentException;
  public boolean contains(User user);

  void addLog(String log);
  ArrayList<LogLine> getLog();
  String addMessage(String message);
  void addListener(String propertyName, PropertyChangeListener listener);
  void removeListener(String propertyName, PropertyChangeListener listener);
}
