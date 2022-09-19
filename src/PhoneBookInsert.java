import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;

public class PhoneBookInsert extends PhoneBookHP implements ActionListener {
    static String[] accessOptions = {"Public", "Private"};
    static JComboBox<String> accessMenu = new JComboBox<>(accessOptions);
//    static String[] provinceOptions = {"Tehran", "Alborz", "Esfahan" , "Fars" , "Hormozgan" , "Yazd" , "Mashhad"};
    public void insertPage() throws SQLException {
        addContact.setBounds(450, 10, 250, 220);
        addContact.setBorder(border);

        firstNameInput.setBounds(115, 25, 120, 25);
        phoneNumInput.setBounds(115, 65, 120, 25);
        insertBtn.setBounds(115, 175, 120, 25);

        firstNameLabel.setBounds(10, 25, 120, 25);
        phoneNumLabel.setBounds(10, 65, 120, 25);
        accessLabel.setBounds(10, 105, 120, 25);
        accessMenu.setBounds(115, 105, 129, 25);

        statement1 = con.createStatement();;
        statement2 = con.createStatement();;

        String query1 = "Use PhoneBook "+"select * from dbo.provinces";
        String query2 = "Use PhoneBook "+"select * from dbo.countries";

        try {
//            st1 = conn.createStatement(query1);
//            st2 = conn.createStatement(query2);
            ResultSet rs1 = statement1.executeQuery(query1);
            ResultSet rs2 = statement2.executeQuery(query2);

            while ((rs1.next()) && (rs2.next())) {
                provinceMenu.addItem(rs1.getString(2));
                countryMenu.addItem(rs2.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement1.close();
            statement2.close();
        }
        JComboBox<String> provinceMenu = new JComboBox<>();
        JComboBox<String> countryMenu = new JComboBox<>();
        int countryId;
            PreparedStatement comboBoxCountries = con.prepareStatement("Use PhoneBook "+"select * from dbo.countries");
            ResultSet rs1=comboBoxCountries.executeQuery();
            countryId = rs1.getInt("countryId");
            while (rs1.next()) {
                countryMenu.addItem(rs1.getString("CountryName"));
            }
            System.out.println(countryId);
            PreparedStatement comboBoxProvince = con.prepareStatement("Use PhoneBook "+"select * from dbo.provinces where provinceFk ="+countryId);
            ResultSet rs2=comboBoxProvince.executeQuery();

            while (rs2.next()) {
                provinceMenu.addItem(rs2.getString("ProvinceName"));
            }
            countryMenu.setBounds(10, 145, 90, 25);
            provinceMenu.setBounds(115, 145, 120, 25);

            insertBtn.addActionListener(this);

            addContact.add(firstNameLabel);
            addContact.add(firstNameInput);
            addContact.add(phoneNumLabel);
            addContact.add(phoneNumInput);
            addContact.add(accessLabel);
            addContact.add(accessMenu);
//            addContact.add(countryMenu);
//            addContact.add(provinceMenu);
            addContact.add(insertBtn);

            addContact.setLayout(null);
            addContact.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            PhoneBookLogin loginInfo = new PhoneBookLogin();
            Connection connection;
            PreparedStatement addData;
            String fullName = firstNameInput.getText();
            String phoneNumber = phoneNumInput.getText();
            int accessIndex = accessMenu.getSelectedIndex();
            int access;
            if (accessIndex == 0) {
                access = 1;
            } else {
                access = 2;
            }
            try {
                addData = con.prepareStatement("Use PhoneBook "+"insert into dbo.contacts (FullName , PhoneNumber ,ownerId , auth) values ( ? , ? ,?,?)");
                addData.setString(1, fullName);
                addData.setString(2, phoneNumber);
                addData.setInt(3, loginInfo.id);
                addData.setInt(4, access);
                addData.executeUpdate();
                JOptionPane.showMessageDialog(null, "Contact Added");

                PhoneBookHP hp = new PhoneBookHP();
//                hp.newTable();
                hp.homePage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

}
