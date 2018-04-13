package objects;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Submission implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private int id;
  private int assignID;
  private int studentID;
  private String title;
  private String path;
  private String timestamp;
  private int grade;
  private String comments;
  private byte filebytes[];

  // ctors
  public Submission(){
    title = null;
    path = null;
    timestamp = null;
    comments = null;
    grade = -1;
  }

  public Submission(int i, int ai, int si, String t, String p, String ts, int g,
                      String c){
    id = i;
    studentID = si;
    assignID = ai;
    title = t;
    path = p;
    timestamp = ts;
    grade = g;
    comments = c;
  }

  // getters
  public int getId(){return id;}
  public int getAssignId(){return assignID;}
  public String getTitle(){return title;}
  public String getPath(){return path;}
  public String getTimestamp(){return timestamp;}
  public String toString(){
    String str = grade + "%";
    if(grade == -1){str = "not graded";}
    return id + " " + title + ", by: " + studentID + ", submitted at: " + timestamp
              + ", " + str + ", " + comments;
  }
  public byte[] getByte() {return filebytes;}

  // setters
  public void setId(int i){id = i;}
  public void setAssignID(int i){assignID = i;}
  public void setStudentID(int i){studentID = i;}
  public void setTitle(String i){title = i;}
  public void setPath(String i){path = i;}
  public void setComments(String c){comments = c;}
  public void setGrade(int i){grade = i;}
  // timestamps the submission
  public void stamp(){
    timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
  }

}
