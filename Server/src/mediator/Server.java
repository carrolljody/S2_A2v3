package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable
{
  private final int PORT = 2021;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;

  public Server(Model model)
  {
    this.model = model;
  }

  @Override public synchronized void run()
  {
    try
    {
      model.addLog("Starting Server...");
      welcomeSocket = new ServerSocket(PORT);

      running = true;
      while (running)
      {
        //model.addLog("Waiting for a client...");
        Socket socket = welcomeSocket.accept();
        ClientHandler clientHandler = new ClientHandler(socket, model);
        Thread clientThread = new Thread(clientHandler);
        clientThread.setDaemon(true);
        clientThread.start();
      }
    }
    catch (IOException e)
    {
      model.addLog("Error: " + e.getMessage());
    }
  }

  public synchronized void close()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

}
