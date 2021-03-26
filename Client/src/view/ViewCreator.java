package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator
{
  private Map<View, ViewController> views = new HashMap<>();

  public ViewCreator()
  {
    //
  }

  public ViewController getViewController(View id) throws IOException
  {
    ViewController request = views.get(id);
    if (request == null)
    {
      try
      {
        request = loadFromFXML(id.getFxmlFile());
        views.put(id, request);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    request.reset();
    return request;
  }

  protected abstract void initViewController(ViewController controller,
      Region root);

  private ViewController loadFromFXML(String fxmlFile) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(fxmlFile));
    Region root = loader.load();
    ViewController controller = loader.getController();
    initViewController(controller, root);
    return controller;
  }

}

