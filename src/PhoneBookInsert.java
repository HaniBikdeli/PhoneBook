import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PhoneBookInsert extends PhoneBookHP{

    public static void insertPage(){
        frameInsert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInsert.setSize(350,230);
        frameInsert.setTitle("Insert Page");
        frameInsert.setLayout(null);

        firstNameInput.setBounds(135,25,120,25);
        lastNameInput.setBounds(135,55,120,25);
        phoneNumInput.setBounds(135,85,120,25);
        backBtn.setBounds(165 , 150 , 90 , 25);
        insertBtn.setBounds(55 , 150 , 90 , 25);
        firstNameLabel.setBounds(40, 25 , 120 , 25);
        lastNameLabel.setBounds(40, 55 , 120 , 25);
        phoneNumLabel.setBounds(40, 85 , 120 , 25);
        accessLabel.setBounds(40, 115 , 120 , 25);

        String[] accessOptions = {"Public", "Private"};
        JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
        String access = (String) accessMenu.getSelectedItem();
        System.out.println(access);
        accessMenu.setBounds(135, 115, 129, 25);

        backBtn.addActionListener(backToHP);

        frameInsert.add(firstNameInput);
        frameInsert.add(lastNameInput);
        frameInsert.add(phoneNumInput);
        frameInsert.add(insertBtn);
        frameInsert.add(backBtn);
        frameInsert.add(firstNameLabel);
        frameInsert.add(lastNameLabel);
        frameInsert.add(phoneNumLabel);
        frameInsert.add(accessLabel);
        frameInsert.add(accessMenu);

        frameInsert.setVisible(true);
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
        insertPage();
    }
}
