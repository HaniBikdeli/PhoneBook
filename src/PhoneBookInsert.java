import javax.swing.*;
import java.awt.*;

public class PhoneBookInsert extends PhoneBookHP{

    public static void insertPage(){
        addContact.setBounds(320 , 10 , 250, 220);
        addContact.setBorder(border);

        firstNameInput.setBounds(115,25,120,25);
        phoneNumInput.setBounds(115,65,120,25);
        insertBtn.setBounds(115, 175, 120, 25);;
        firstNameLabel.setBounds(10, 25 , 120 , 25);
        phoneNumLabel.setBounds(10, 65 , 120 , 25);
        accessLabel.setBounds(10, 105 , 120 , 25);

        String[] accessOptions = {"Public", "Private"};
        JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
        String access = (String) accessMenu.getSelectedItem();
        accessMenu.setBounds(115, 105, 120, 25);
        String[] provinceOptions = {"Tehran", "Alborz", "Esfahan" , "Fars" , "Hormozgan" , "Yazd" , "Mashhad"};
        JComboBox<String> provinceMenu = new JComboBox<>(provinceOptions);
        String province = (String) accessMenu.getSelectedItem();
        provinceMenu.setBounds(115, 145, 120, 25);
        String[] countryOptions = {"Iran", "Iraq" , "Afghanistan","Uzbakestan" , "Kuwait"};
        JComboBox<String> countryMenu = new JComboBox<>(countryOptions);
        String country = (String) accessMenu.getSelectedItem();
        countryMenu.setBounds(10, 145, 90, 25);

        addContact.add(firstNameLabel);
        addContact.add(firstNameInput);
        addContact.add(phoneNumLabel);
        addContact.add(phoneNumInput);
        addContact.add(accessLabel);
        addContact.add(accessMenu);
        addContact.add(countryMenu);
        addContact.add(provinceMenu);
        addContact.add(insertBtn);

        addContact.setLayout(null);
        addContact.setVisible(true);
    }
}
