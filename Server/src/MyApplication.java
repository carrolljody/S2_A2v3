import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Server;
import model.Model;
import model.ModelManager;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
    Model model = new ModelManager();

    Server server = new Server(model);
    Thread t1 = new Thread(server);
    t1.start();
  }
}
