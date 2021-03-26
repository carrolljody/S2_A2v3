package mediator;

public class MessagePackage
{
  private String type;
  private String message;
  private String username;

  public MessagePackage(String type, String message, String username)
  {
    this.type = type;
    this.message = message;
    this.username = username;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public String toString()
  {
    return "MessagePackage{" + "type='" + type + '\'' + ", message='" + message
        + '\'' + ", username='" + username + '\'' + '}';
  }
}
