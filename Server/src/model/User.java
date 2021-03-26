package model;

public class User
{
  private String name;

  public User(String username)
  {
    if (username == null || username.length() < 3)
    {
      throw new IllegalArgumentException("Username must have at least 3 characters");
    }
    this.name = username;
  }

  public String getUserName()
  {
    return name;
  }

  @Override public String toString()
  {
    return name;
  }
}
