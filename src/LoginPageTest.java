import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
//this is our LoginPageTest class


public class LoginPageTest {

    @Test
    public void loginPageVerification() {
        HashMap<String, String> loginInfoOriginals = new HashMap<>();
        //insert into loginInfoOriginals
        loginInfoOriginals.put("username", "Anan");
        loginInfoOriginals.put("password", "321");
        // insert username and password in the assertions
        Assertions.assertEquals("Anan", loginInfoOriginals.get("username"));
        Assertions.assertEquals("321", loginInfoOriginals.get("password"));
    }

}
