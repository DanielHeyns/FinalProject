package Objects;
import java.util.ArrayList;
public class Course implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private int id;
  private String courseName;
  private int profID;
  private String profName;
  private boolean active;
  private ArrayList<Integer> assignments;
  // ctors
  public Course(){
    active = false;
    courseName = null;
    profName = null;
    assignments = new ArrayList<Integer>();
  }
  public Course(int i){
    id = i;
    active = false;
    courseName = null;
    profName = null;
    assignments = new ArrayList<Integer>();
  }
  public Course(int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr){
    id = i;
    profID = pi;
    active = a;
    courseName = cn;
    profName = pn;
    assignments = arr;
  }
  // setters
  public void setId(int i){id = i;}
  public void setCourseName(String str){courseName = str;}
  public void setProfName(String str){profName = str;}
  public void activate(){active = true;}
  public void deactivate(){active = false;}
  public void addAssign(int assignId){assignments.add(assignId);}
  public void setProfID(int pID){profID = pID;}

  // getters
  public int getId(){return id;}
  public String getCourseName(){return courseName;}
  public String getProfName(){return profName;}
  public boolean isActive(){return active;}
  public ArrayList<Integer> getAssignIDs(){return assignments;}
  public int getProfId(){return profID;}

  public String toString(){
    String str = "";
    if(this.isActive()){str = "active";}else{str = "not active";}
    return (id + ", " + courseName + ", " + str);
  }


}
