import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PhoneBookRm extends PhoneBookHP{
        public static void removePage(){
            frameRm = new JFrame();
            frameRm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameRm.setSize(350,230);
            frameRm.setTitle("Remove Page");
            frameRm.setLayout(null);

            firstNameInput.setBounds(135,25,120,25);
            lastNameInput.setBounds(135,55,120,25);
            backBtn.setBounds(110 , 150 , 110 , 25);
            rmBtn.setBounds(110 , 100 , 110 , 25);
            firstNameLabel.setBounds(40, 25 , 120 , 25);
            lastNameLabel.setBounds(40, 55 , 120 , 25);

            backBtn.addActionListener(backToHP);

            frameRm.add(firstNameInput);
            frameRm.add(lastNameInput);
            frameRm.add(phoneNumInput);
            frameRm.add(rmBtn);
            frameRm.add(backBtn);
            frameRm.add(firstNameLabel);
            frameRm.add(lastNameLabel);
            frameRm.add(phoneNumLabel);
            frameRm.add(accessLabel);

            frameRm.setVisible(true);
        }
        static ActionListener removeNum = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        static ActionListener backToHP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRm.dispose();
                try {
                    PhoneBookHP.homePage();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        public static void main(String[] args) {
            removePage();
        }
}
