import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PhoneBookRm extends PhoneBookHP{
        public static void removePage(){
            rmContact.setBounds(320 , 240, 250, 125);

            firstNameInput.setBounds(60,25,120,25);
            rmBtn.setBounds(50, 200 , 110 , 25);
            firstNameLabel.setBounds(40, 25 , 120 , 25);

            rmContact.add(firstNameLabel);
            rmContact.add(firstNameInput);
            rmContact.add(rmBtn);

            frame.setVisible(true);
        }
        static ActionListener removeNum = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        static ActionListener backToHP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    PhoneBookHP.homePage();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
}
