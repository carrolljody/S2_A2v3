package model;

import mediator.MessagePackage;
import utility.observer.subject.NamedPropertyChangeSubject;

import java.io.IOException;

public interface Model extends NamedPropertyChangeSubject
{
  public void login(String userName)
      throws IllegalStateException, IllegalArgumentException, IOException;

  public void sendMessage(MessagePackage message);
  void setMessage(String message);
  String sendRequest(MessagePackage requestMessage) throws InterruptedException;
}
