package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler extends ViewCreator
{
  private Stage primaryStage;
  private final Scene currentScene;
  private final ViewModelFactory viewModelFactory;


  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage) throws IOException
  {
    this.primaryStage = primaryStage;
    openView(View.LOGIN);
  }

  public void openView(View id) throws IOException
  {
    ViewController open = getViewController(id);
    Region root = open.getRoot();
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }

  @Override protected void initViewController(ViewController controller,
      Region root)
  {
    controller.init(this, viewModelFactory, root);
  }
}
