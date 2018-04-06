package objects;
import java.util.ArrayList;

public class Student extends User implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private ArrayList<Integer> courses;

  // ctors
  public Student(){
    super();
    courses = new ArrayList<Integer>();
  }

  public Student(int i,String p, String e, String f, String l){
    super(i,p,e,f,l);
    courses = new ArrayList<Integer>();
  }

  // getter
  public ArrayList<Integer> getCourseIDs(){return courses;}
  // course adjustments
  public void addCourse(int cID){courses.add(cID);}
  public void removeCourse(int cID){courses.remove(cID);}

}
