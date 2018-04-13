package objects;
import java.util.ArrayList;
public class User implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  protected int id; // 8 digits long
  protected String password;
  protected String email;
  protected String firstName;
  protected String lastName;
  protected char type;
  private ArrayList<Integer> courses;

  // ctors
  public User(){
    password = null;
    email = null;
    firstName = null;
    lastName = null;
  }

  public User(int i,String p, String e, String f, String l,char t){
    id = i;
    password = p;
    email = e;
    firstName = f;
    lastName = l;
    type= t;
  }

  public User(int i,String p, String e, String f, String l, ArrayList<Integer> a, char t){
    id = i;
    password = p;
    email = e;
    courses = a;
    firstName = f;
    lastName = l;
    type= t;
  }
  // func to check login password
  public boolean samePass(String pass){
    return password.equals(pass);
  }

  // getters
  public int getId(){return id;}
  public String getFirstName(){return firstName;}
  public String getLastName(){return lastName;}
  public String getEmail(){return lastName;}
  public char getType(){return type;}
  public ArrayList<Integer> getCourseIDs(){return courses;}
  public String toString(){ return id + ", " + firstName + ", " + lastName;}

  // course adjustments
  public void addCourse(int cID){courses.add(cID);}
  public void removeCourse(int cID){courses.remove((Integer)cID);}
  public boolean inCourse(int cid){
    if(courses.indexOf(cid) == -1){return false;}
    else{return true;}
  }

  // setters
  public void setId(int i){id = i;}
  public void setFirstName(String f){firstName = f;}
  public void setLastName(String l){lastName = l;}
  public void setEmail(String e){email = e;}
  public void setPassword(String pass){password = pass;}
  public void setType(char t){type = t;}

}
