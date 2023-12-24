import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.logging.*;
public class LoginPage implements ActionListener {
//    Declares instance variables for the components of the login page and a HashMap for storing login information.
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    JLabel logoLabel;  // New JLabel for the logo
   HashMap<String, String> logininfo = new HashMap<>();


    LoginPage(HashMap<String, String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;

//        Configures font styles and colors for various components.
        Font labelFont = userIDLabel.getFont();
        userIDLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() * 2));
        userPasswordLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, labelFont.getSize() * 2));
        userIDLabel.setForeground(Color.white);
        userPasswordLabel.setForeground(Color.white);

        messageLabel.setFont(new Font(null, Font.ITALIC, 70));
        messageLabel.setForeground(Color.white);

        // Set background image
        try {
            java.net.URL imageUrl = LoginPage.class.getResource("/background.png");

            if (imageUrl != null) {
                ImageIcon backgroundImage = new ImageIcon(imageUrl);
                frame.setContentPane(new JLabel(backgroundImage));
            } else {
                Logger logger = Logger.getLogger(LoginPage.class.getName());
                logger.log(Level.SEVERE, "Background image not found.");
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(LoginPage.class.getName());
            logger.log(Level.SEVERE, "An error occurred while loading the background image.", e);
        }
//        Configures frame properties such as size, layout, and default close operation.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon image = new ImageIcon("/logo.png");
        frame.setIconImage(image.getImage());
        gbc.insets = new Insets(20, 20, 20, 20);
        // Add logo
        try {
            java.net.URL imageUrl = LoginPage.class.getResource("/logo.jpg");

            if (imageUrl != null) {
                ImageIcon logoImage = new ImageIcon(imageUrl);
                logoImage.setImage(logoImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // Adjust the logo size
                logoLabel = new JLabel(logoImage);

//                Attempts to load and set a logo image in the frame.
                gbc.gridx = 1;
                gbc.gridy = 0;  // Logo above the username
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.PAGE_START;  // Align to the top
                frame.add(logoLabel, gbc);
            } else {
                Logger logger = Logger.getLogger(LoginPage.class.getName());
                logger.log(Level.SEVERE, "Logo image not found.");
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(LoginPage.class.getName());
            logger.log(Level.SEVERE, "An error occurred while loading the logo image.", e);
        }
//        Adds the username field to the frame.
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(userIDLabel, gbc);

//        Adds the username field to the frame.
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userIDField.setPreferredSize(new Dimension(500, 30));
        setPlaceholder(userIDField, "Enter username");
        frame.add(userIDField, gbc);

//        Adds the password label to the frame.
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(userPasswordLabel, gbc);

//        Adds the password field to the frame.
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        userPasswordField.setPreferredSize(new Dimension(500, 30));
        frame.add(userPasswordField, gbc);

  //      Adds the login button to the frame.
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setBackground(new Color(0x0066CC)); // Medium Blue
        loginButton.setForeground(Color.WHITE);
        frame.add(loginButton, gbc);

//        Adds the message label to the frame.
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(messageLabel, gbc);

//        Centers the frame on the screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);

//        Adjusts labels, sets focus properties, and adds an action listener to the login button.
        userIDLabel.setText("Username:");
        userPasswordLabel.setText("Password:");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        frame.setVisible(true);
    }

    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(new Color(0x666666)); // Dark Gray
        textField.setText(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(new Color(0x666666)); // Dark Gray
                    textField.setText(placeholder);
                }
            }
        });
    }
//    Implements the actionPerformed method required by the ActionListener interface, handling login attempts.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(new Color(0x009933)); // Green
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

