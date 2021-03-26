package mediator;

import com.google.gson.Gson;
import model.Model;
import model.ModelManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Model
{
  public final String HOST = "localhost";
  public final int PORT = 2021;

  private final String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private PropertyChangeSupport property;
  private Model model;
  private String directMessage;

  public Client(String host, int port, ModelManager model) throws
      IOException
  {
    this.host = host;
    this.port = port;
    this.model = model;
  }

  public Client(ModelManager model) throws IOException
  {
    this.host = HOST;
    this.port = PORT;
    this.model = model;
  }

  public synchronized void connect() throws IOException
  {
    this.socket = new Socket(host, port);
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.property = new PropertyChangeSupport(this);
    this.directMessage = null;

    ClientReceiver receiver = new ClientReceiver(in, this);
    Thread receiverThread = new Thread(receiver);
    receiverThread.start();
  }

  public synchronized void disconnect() throws IOException
  {
    in.close();
    out.close();
    socket.close();
  }

  @Override public void login(String userName)
      throws IllegalStateException, IllegalArgumentException, IOException
  {
    Gson gson = new Gson();
    MessagePackage send = new MessagePackage("NewUser", null , userName);
    String sendJson = gson.toJson(send);
    out.println(sendJson);

  }

  @Override public synchronized void sendMessage(MessagePackage message)
  {
    Gson gson = new Gson();
    String sendJson = gson.toJson(message);
    out.println(sendJson);
  }

  @Override public synchronized void setMessage(String message)
  {
    model.setMessage(message);
  }

  @Override public synchronized String sendRequest(MessagePackage requestMessage)
      throws InterruptedException
  {
    Gson gson = new Gson();
    String requestJson = gson.toJson(requestMessage);
    out.println(requestJson);              
    wait();
    return directMessage;
  }

  public synchronized void receive(String message)
  {
    directMessage = message;
    notifyAll();
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
