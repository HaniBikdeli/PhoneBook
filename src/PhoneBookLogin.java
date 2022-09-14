import javax.swing.*;

public class PhoneBookLogin extends PhoneBookHP{

    public static void loginPage(){
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(350,230);
        frameLogin.setTitle("Login Page");
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);

        userInput.setBounds(135,55,120,25);
        passInput.setBounds(135,100,120,25);
        loginBtn.setBounds(110 , 140 , 80 , 25);
        userLabel.setBounds(70, 55 , 120 , 25);
        passLabel.setBounds(70,100,120,25);

        frameLogin.add(userInput);
        frameLogin.add(passInput);
        frameLogin.add(loginBtn);
        frameLogin.add(userLabel);
        frameLogin.add(passLabel);

        frameLogin.setVisible(true);
    }

    public static void main(String[] args){
        loginPage();
    }

}