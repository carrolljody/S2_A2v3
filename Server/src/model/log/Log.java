package model.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Log
{
  private static Log instance;
  private static Object lock = new Object();
  private ArrayList<LogLine> logLine;

  private Log()
  {
    this.logLine = new ArrayList<LogLine>();
  }

  public void addLog(String text)
  {
    LogLine textLog = new LogLine(text);
    logLine.add(textLog);
    addToFile(textLog);
    System.out.println(textLog);
  }

  public ArrayList<LogLine> getLog()
  {
    return logLine;
  }

  /*@Override public String toString()
  {
    return "Log{" + "logLine=" + logLine + '}';
  }*/

  public static Log  getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new Log();
        }
      }
    }
    return instance;
  }

  //Appending a logLine to a file (for date 15/3/2021, the file is: "Log-2021-03-15.txt")
  private void addToFile(LogLine log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      String filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(log + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
