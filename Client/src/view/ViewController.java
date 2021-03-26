package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public abstract class ViewController
{
  private Region root;
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  public ViewController()
  {
    //
  }

  protected abstract void initBindings();

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.root = root;

    initBindings();
  }

  public void reset() throws IOException
  {

  }

  public Region getRoot()
  {
    return root;
  }

  public ViewHandler getViewHandler()
  {
    return viewHandler;
  }

  public ViewModelFactory getViewModelFactory()
  {
    return viewModelFactory;
  }
}
