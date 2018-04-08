package objects;
public abstract class User implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  protected int id; // 8 digits long
  protected String password;
  protected String email;
  protected String firstName;
  protected String lastName;

  // ctors
  protected User(){
    password = null;
    email = null;
    firstName = null;
    lastName = null;
  }

  protected User(int i,String p, String e, String f, String l){
    id = i;
    password = p;
    email = e;
    firstName = f;
    lastName = l;
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



  // setters
  public void setId(int i){id = i;}
  public void setFirstName(String f){firstName = f;}
  public void setLastName(String l){lastName = l;}
  public void setEmail(String e){email = e;}
  public void setPassword(String pass){password = pass;}

}
