package objects;
public class Account implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  // local vars
  private int id; // 8 digits long
  private String password;

  // ctors
  public Account(){
    password = null;
  }

  public Account(int i,String p){
    id = i;
    password = p;
  }

  // func to check login password
  public boolean samePass(String pass){
    return password.equals(pass);
  }

  // getter
  public int getId(){return id;}
  public String getPass(){return password;}
  // setters
  public void setId(int i){id = i;}
  public void setPassword(String pass){password = pass;}

}
