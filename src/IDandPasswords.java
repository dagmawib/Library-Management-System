import java.util.HashMap;
public class IDandPasswords {

/* This well-structured HashMap named 'logininfo' elegantly manages user credentials
with a clear key-value association. The use of String types for both keys and values
enhances readability, making it a secure and efficient solution for handling login information.
 * */
    HashMap<String,String> logininfo = new HashMap<String,String>();
    IDandPasswords(){

        logininfo.put("Abdi","123");
        logininfo.put("Abay","456");
        logininfo.put("Asmare","789");
        logininfo.put("Anan","321");
        logininfo.put("Dagmawi","654");
    }
    public HashMap getLoginInfo(){
        return logininfo;
    }
}
