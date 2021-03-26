package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import viewmodel.ChatViewModel;

public class ChatViewController extends ViewController
{
  @FXML public ListView<String> chat;
  @FXML public TextField messageField;
  public ChatViewModel viewModel;

  public ChatViewController()
  {
  }

  @Override protected void initBindings()
  {
    this.viewModel = getViewModelFactory().getChatViewModel();
    this.chat.setItems(viewModel.getLogs());
    this.messageField.textProperty().bindBidirectional(viewModel.messageProperty());
  }

  public void onEnter()
  {
    onSend();
  }

  public void onSend()
  {
    viewModel.sendMessage();
  }

  public void onDetails() throws InterruptedException
  {
    viewModel.requestDetails();
  }
}
