import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class LoginPageTest {

    @Test
    public void testHashMapInsertion() {
        HashMap<String, String> loginInfoOriginals = new HashMap<>();
        loginInfoOriginals.put("username", "Anan");
        loginInfoOriginals.put("password", "321");

        Assertions.assertEquals("Anan", loginInfoOriginals.get("username"));
        Assertions.assertEquals("321", loginInfoOriginals.get("password"));
    }

}
