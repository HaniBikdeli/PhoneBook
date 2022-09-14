import javax.swing.*;
import java.awt.*;

public class PhoneBookInsert extends PhoneBookHP{

    public static void insertPage(){
        addContact.setBounds(320 , 10 , 250, 180);
        addContact.setBackground(Color.red);

        firstNameInput.setBounds(115,25,120,25);
        phoneNumInput.setBounds(115,65,120,25);
        insertBtn.setBounds(115 , 145 , 90 , 25);
        firstNameLabel.setBounds(10, 25 , 120 , 25);
        phoneNumLabel.setBounds(10, 65 , 120 , 25);
        accessLabel.setBounds(10, 105 , 120 , 25);

        String[] accessOptions = {"Public", "Private"};
        JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
        String access = (String) accessMenu.getSelectedItem();
        System.out.println(access);
        accessMenu.setBounds(115, 105, 129, 25);

        addContact.add(firstNameLabel);
        addContact.add(firstNameInput);
        addContact.add(phoneNumLabel);
        addContact.add(phoneNumInput);
        addContact.add(accessLabel);
        addContact.add(accessMenu);
        addContact.add(insertBtn);

        addContact.setLayout(null);
        addContact.setVisible(true);
    }
}
