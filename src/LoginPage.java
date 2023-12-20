import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    HashMap<String, String> logininfo = new HashMap<String, String>();

    LoginPage(HashMap<String, String> loginInfoOriginal) {

        logininfo = loginInfoOriginal;

        // Increase the font size of JLabel username and password
        Font labelFont = userIDLabel.getFont();
        userIDLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() * 2));
        userPasswordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() * 2));

        userIDLabel.setForeground(Color.white);
        userPasswordLabel.setForeground(Color.white);

        messageLabel.setFont(new Font(null, Font.ITALIC, 70));
        messageLabel.setForeground(Color.white);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Set background color
        frame.getContentPane().setBackground(new Color(0x2B3A55));

        // Using GridBagLayout for better control over the layout
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add insets to create a gap

        // userIDLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(userIDLabel, gbc);

        // userIDField
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        userIDField.setPreferredSize(new Dimension(500, 30)); // Increase text field size
        frame.add(userIDField, gbc);

        // userPasswordLabel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(userPasswordLabel, gbc);

        // userPasswordField
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        userPasswordField.setPreferredSize(new Dimension(500, 30)); // Increase text field size
        frame.add(userPasswordField, gbc);

        // loginButton
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        loginButton.setPreferredSize(new Dimension(200, 40)); // Increase button size
        frame.add(loginButton, gbc);

        // resetButton
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        resetButton.setPreferredSize(new Dimension(200, 40)); // Increase button size
        frame.add(resetButton, gbc);

        // messageLabel
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(messageLabel, gbc);

        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);

        // Initialize other components
        userIDLabel.setText("userID:");
        userPasswordLabel.setText("password:");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    // Assuming MainGUI is another class, update accordingly
                    MainGUI mainGUI = new MainGUI(userID);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }
    }
}
