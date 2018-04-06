package objects;
import java.util.ArrayList;

public class Assignment implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private int id;
  private int courseID;
  private String title;
  private boolean active;
  private String path;
  private String dueDate;

  // ctors
  public Assignment(){
    title = null;
    active = false;
    path = null;
    dueDate = null;
  }

  public Assignment(int i,int ci, String t, boolean a, String p, String d){
    id = i;
    courseID = ci;
    title = t;
    active = a;
    path = p;
    dueDate = d;
  }

  // getters
  public int getId(){return id;}
  public int getCourseID(){return courseID;}
  public String getTitle(){return title;}
  public boolean isActive(){return active;}
  public String getPath(){return path;}
  public String getDueDate(){return dueDate;}
  public String toString(){
    String str;
    if(isActive()){str = "true";}else{str = "false";}
    return id + ", " +  title + ", " + str + ", Due: " + dueDate;
  }

  // setters
  public void setId(int i){id = i;}
  public void setCourseID(int i){courseID = i;}
  public void setTitle(String i){title = i;}
  public void setPath(String i){path = i;}
  public void setDueDate(String i){dueDate = i;}
  public void activate(){active = true;}
  public void deactivate(){active = false;}


}
