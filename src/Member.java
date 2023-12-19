import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Member {
    private String name;
    private String contactDetails;

    public Member(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}
