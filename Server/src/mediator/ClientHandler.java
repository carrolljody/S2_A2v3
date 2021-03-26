package mediator;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private boolean running;
  private Gson gson;
  private String ip;

  public ClientHandler(Socket socket, Model model) throws IOException
  {
    this.model = model;
    this.socket = socket;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();
    this.ip = socket.getInetAddress().getHostAddress();
    model.addListener(null,this);
  }

  @Override public void run()
  {
    running = true;
    while (running)
    {
      try
      {
        String request = in.readLine();
        MessagePackage requestMessage = gson
            .fromJson(request, MessagePackage.class);

        switch (requestMessage.getType())
        {
          case "Message":
            model.addLog(requestMessage.getUsername() + "> " + requestMessage.getMessage());
            break;
          case "NewUser":
            model.addUser(requestMessage.getUsername());
            System.out.println(requestMessage.getUsername());
            break;
          case "Request":
            int users = model.getNumberOfUsers();
            String reply = "";
            if(users ==1)
              reply += "Server>  Just you in here, buddy";
            else
             reply += "Server>  There are " + model.getNumberOfUsers()
                + " users online.";
            MessagePackage replyPackage = new MessagePackage("Request", reply,
                null);
            String replyJson = gson.toJson(replyPackage);
            out.println(replyJson);
            break;

        }
      }
      catch (Exception e)
      {
        model.addLog("Client error");
      }
    }
    close();
  }

  public synchronized void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override public synchronized void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if(evt.getPropertyName().equals("Message"))
      {
        MessagePackage broadcast = new MessagePackage(evt.getPropertyName(), (String) evt.getNewValue(), null);
        String broadcastJson = gson.toJson(broadcast);
        System.out.println(broadcastJson);
        out.println(broadcastJson);
      }else
      {
        MessagePackage broadcast = new MessagePackage(evt.getPropertyName(), (String) evt.getOldValue(), (String) evt.getNewValue());
        String broadcastJson = gson.toJson(broadcast);
        System.out.println(broadcastJson);
        out.println(broadcastJson);
      }
    });
  }
}
