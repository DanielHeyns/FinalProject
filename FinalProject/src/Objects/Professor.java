package Objects;
import java.util.ArrayList;

public class Professor extends User implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private ArrayList<Integer> courses;

  // ctors
  public Professor(){
    super();
    courses = new ArrayList<Integer>();
  }

  public Professor(int i,String p, String e, String f, String l){
    super(i,p,e,f,l);
    courses = new ArrayList<Integer>();
  }

  // getter
  public ArrayList<Integer> getCourseIDs(){return courses;}
  // setter
  public void addCourseID(int cID){courses.add(cID);}

}
