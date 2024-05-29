package Parking_Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jframe {

    public static void handleJframe() {
        // Membuat label untuk judul dan logo
        ImageIcon OriginalIcon= new ImageIcon("Logo.png");
        Image originalImage = OriginalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
        ImageIcon parkingTicketIcon = new ImageIcon(resizedImage);


        JLabel label = new JLabel();
        label.setText("Welcome to our Parking Ticket program");
        label.setIcon(parkingTicketIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Membuat frame utama
        JFrame frame = new JFrame();
        frame.setTitle("Parking Ticket Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900, 700); // Mengatur ukuran jendela
        frame.setLayout(new BorderLayout());

        // Membuat panel untuk logo dan label
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.WHITE); // Set background color to white
        logoPanel.add(label, new GridBagConstraints());

        // Menambahkan label ke frame
        frame.add(logoPanel, BorderLayout.NORTH);

        // Membuat panel untuk tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        // Membuat tombol
        JButton guestButton = new JButton("Continue As Guest");
        JButton memberButton = new JButton("Member");


        // Menambahkan tombol ke panel
        panel.add(guestButton);
        panel.add(memberButton);

        // Menambahkan panel ke frame
        frame.add(panel, BorderLayout.CENTER);

        // Mengatur latar belakang frame
        ImageIcon image = new ImageIcon("kirby2.jpg"); // Membuat ikon gambar
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.WHITE); // Warna latar belakang

//        guestButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Guest.handleGuest(frame);
//            }
//        });

        // Membuat action listener untuk tombol
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guestPanel.handleGuestPanel(frame);
            }
        });

        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMember(frame);
            }
        });




        // Menampilkan frame
        frame.setVisible(true);
    }



    private static void handleMember(JFrame parentFrame) {
        // Membuat dialog untuk memilih antara login dan sign up
        JDialog memberDialog = new JDialog(parentFrame, "Member Options", true);
        memberDialog.setSize(300, 150);
        memberDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Choose an option:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton signUpButton = new JButton("Sign Up");
        JButton logInButton = new JButton("Log In");

        memberDialog.add(label);
        memberDialog.add(signUpButton);
        memberDialog.add(logInButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn.handleSignIn();
                memberDialog.dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(parentFrame);
                memberDialog.dispose();
            }
        });

        memberDialog.setLocationRelativeTo(parentFrame);
        memberDialog.setVisible(true);
    }

    private static void handleLogin(JFrame parentFrame) {
        // Membuat dialog untuk login
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel emailLabel = new JLabel("Enter your email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter your password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginDialog.add(emailLabel);
        loginDialog.add(emailField);
        loginDialog.add(passwordLabel);
        loginDialog.add(passwordField);
        loginDialog.add(new JLabel()); // Spacer
        loginDialog.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredEMAIL = emailField.getText();
                String enteredPASSWORD = new String(passwordField.getPassword());

                boolean found = SignIn.verifyCredentials(enteredEMAIL, enteredPASSWORD);

                if (found) {
                    JOptionPane.showMessageDialog(loginDialog, "Login Successful!");
                    loginDialog.dispose();
                    parking.handleParking();
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Invalid email or password. Please try again.");
                }
            }
        });

        loginDialog.setLocationRelativeTo(parentFrame);
        loginDialog.setVisible(true);
    }


    public static void main(String[] args) {
        handleJframe();
    }
}
