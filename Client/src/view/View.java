package view;

public enum View
{
  LOGIN("LoginClientView.fxml"), CHAT("ChatClientView.fxml");

  private String fxmlFile;

  private View(String fxmlFile)
  {
    this.fxmlFile = fxmlFile;
  }

  public String getFxmlFile()
  {
    return fxmlFile;
  }
}
