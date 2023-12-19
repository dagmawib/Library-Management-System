import java.util.HashMap;
public class IDandPasswords {

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
