<<<<<<< HEAD
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
=======
package Objects;
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
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
