package mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable
{
  private BufferedReader in;
  private Client client;

  public ClientReceiver(BufferedReader in, Client client)
  {
    this.in = in;
    this.client = client;
  }

  @Override public synchronized void run()
  {
    Gson gson = new Gson();
    while(true)
    {
      try
      {
        String reply = in.readLine();
        MessagePackage replyMessage = gson.fromJson(reply, MessagePackage.class);
        if("Request".equalsIgnoreCase(replyMessage.getType()))
          client.receive(replyMessage.getMessage());
        else
          client.setMessage(replyMessage.getMessage());
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
