import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.logging.*;
public class LoginPage implements ActionListener {
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(userIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userIDField.setPreferredSize(new Dimension(500, 30));
        setPlaceholder(userIDField, "Enter username");
        frame.add(userIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(userPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        userPasswordField.setPreferredSize(new Dimension(500, 30));
        frame.add(userPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setBackground(new Color(0x0066CC)); // Medium Blue
        loginButton.setForeground(Color.WHITE);
        frame.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(messageLabel, gbc);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);

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
//    public static void main(String[] args) {
//        // Example usage:
//        HashMap<String, String> loginInfo = new HashMap<>();
//        loginInfo.put("user1", "password1");
//        loginInfo.put("user2", "password2");
//
//        SwingUtilities.invokeLater(() -> new LoginPage(loginInfo));
//    }
//}
