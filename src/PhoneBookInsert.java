import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PhoneBookInsert extends PhoneBookHP{

    public static void insertPage(){
        addContact.setBounds(320 , 60 , 250, 110);
        addContact.setBackground(Color.red);

        firstNameInput.setBounds(135,25,120,25);
        phoneNumInput.setBounds(135,85,120,25);
        insertBtn.setBounds(55 , 150 , 90 , 25);
        firstNameLabel.setBounds(40, 25 , 120 , 25);
        phoneNumLabel.setBounds(40, 85 , 120 , 25);
        accessLabel.setBounds(40, 115 , 120 , 25);

        String[] accessOptions = {"Public", "Private"};
        JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
        String access = (String) accessMenu.getSelectedItem();
        System.out.println(access);
        accessMenu.setBounds(135, 115, 129, 25);

        addContact.add(firstNameInput);
        addContact.add(phoneNumInput);
        addContact.add(insertBtn);
        addContact.add(firstNameLabel);
        addContact.add(phoneNumLabel);
        addContact.add(accessLabel);
        addContact.add(accessMenu);
    }
    static ActionListener addNum = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
}
