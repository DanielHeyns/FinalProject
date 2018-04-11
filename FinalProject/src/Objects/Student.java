package Objects;
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

  public Student(int i,String p, String e, String f, String l,ArrayList<Integer>c){
    super(i,p,e,f,l);
    courses = c;
  }


  // getters
  public boolean inCourse(int cid){
    if(courses.indexOf(cid) == -1){return false;}
    else{return true;}
  }
  public ArrayList<Integer> getCourseIDs(){return courses;}
  public String toString(){ return id + ", " + firstName + ", " + lastName;}
  // course adjustments
  public void addCourse(int cID){courses.add(cID);}
  public void removeCourse(int cID){courses.remove((Integer)cID);}


}
