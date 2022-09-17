import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;

public class PhoneBookInsert extends PhoneBookHP implements ActionListener {
//    static String[] accessOptions = {"Public", "Private"};
//    static JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
//    static String[] provinceOptions = {"Tehran", "Alborz", "Esfahan" , "Fars" , "Hormozgan" , "Yazd" , "Mashhad"};
//    static JComboBox<String> provinceMenu = new JComboBox<>(provinceOptions);
//    static String province = (String) accessMenu.getSelectedItem();
//    static String[] countryOptions;

//    JComboBox<String> countryMenu = new JComboBox<>(countryOptions);
    public void insertPage(){
        addContact.setBounds(450 , 10 , 250, 220);
        addContact.setBorder(border);

        firstNameInput.setBounds(115,25,120,25);
        phoneNumInput.setBounds(115,65,120,25);
        insertBtn.setBounds(115, 175, 120, 25);;
        firstNameLabel.setBounds(10, 25 , 120 , 25);
        phoneNumLabel.setBounds(10, 65 , 120 , 25);
        accessLabel.setBounds(10, 105 , 120 , 25);

        String access = (String) accessMenu.getSelectedItem();
        accessMenu.setBounds(115, 105, 120, 25);
//        String[] provinceOptions = {"Tehran", "Alborz", "Esfahan" , "Fars" , "Hormozgan" , "Yazd" , "Mashhad"};
//        JComboBox<String> provinceMenu = new JComboBox<>(provinceOptions);
//        String province = (String) accessMenu.getSelectedItem();
//        provinceMenu.setBounds(115, 145, 120, 25);
//
//        String country = (String) accessMenu.getSelectedItem();
        JComboBox<String>  = countryMenu JComboBox<String>();

        DataStoreImpl data = new DataStoreImpl();
        List<String> countryList = data.getCountry();

        for (String country : countryList) {
            comboBox.addItem(country);
        }
        add(comboBox, BorderLayout.NORTH);
        countryMenu.setBounds(10, 145, 90, 25);

        insertBtn.addActionListener(this);

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
    @Override
    public void actionPerformed(ActionEvent e) {
        PhoneBookLogin loginInfo = new PhoneBookLogin();
        Connection connection;
        PreparedStatement addData;
        String fullName = firstNameInput.getText();
        String phoneNumber = phoneNumInput.getText();
        int accessIndex = accessMenu.getSelectedIndex();
        int access;
        if (accessIndex == 0){
            access = 1;
        }else{
            access = 2;
        }
            try {
                addData = con.prepareStatement("insert into dbo.contacts (FullName , PhoneNumber ,ownerId , auth) values ( ? , ? ,?,?)");
                addData.setString(1,fullName);
                addData.setString(2,phoneNumber);
                addData.setInt(3,loginInfo.id);
                addData.setInt(4,access);
                addData.executeUpdate();
                JOptionPane.showMessageDialog(null, "Contact Added");
                PhoneBookHP.newTable();
                PhoneBookHP.homePage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
    }

}
