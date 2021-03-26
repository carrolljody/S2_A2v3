package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(Model model)
  {
    ViewState viewState = new ViewState();
    this.loginViewModel = new LoginViewModel(model, viewState);
    this.chatViewModel = new ChatViewModel(model, viewState);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }
}
