import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PhoneBookSearch extends PhoneBookHP implements ActionListener {
    static DefaultTableModel pubTableModel;
    static PhoneBookLogin login = new PhoneBookLogin();
    static DefaultTableModel prvTableModel;
    static int colNo;
        public void searchPage(){
        searchContact.setBounds(50, 360, 300, 40);
        searchContact.setBackground(Color.cyan);

        searchInput.setBounds(15, 10, 150, 25);
        searchBtn.setBounds(180, 10, 90, 25);

        searchBtn.addActionListener(this);
        searchContact.add(searchInput);
        searchContact.add(searchBtn);

        searchContact.setLayout(null);
        searchContact.setVisible(true);
    }
    public static ResultSet readSearchData(int auth) throws SQLException {
        String input = searchInput.getText();
        String sql = "Use PhoneBook"+" select FullName , PhoneNumber from dbo.contacts  where ownerId="+login.id+" AND auth =" + auth + " and FullName like '"+input+"'";
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        colNo = rsmd.getColumnCount();
        return rs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println(readSearchData(1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        String[] cols = {"FirstName", "PhoneNumber", "Country", "Province"};
        pubTableModel = (DefaultTableModel) pubTable.getModel();
        prvTableModel = (DefaultTableModel) prvTable.getModel();
        pubTableModel.setColumnIdentifiers(cols);
        prvTableModel.setColumnIdentifiers(cols);
        ResultSet rs1 = null;
        try {
            rs1 = readSearchData(1);
            Object[] searchdata1;
            while (rs1.next()) {
                searchdata1 = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    searchdata1[i] = rs1.getObject(i + 1);
                }
                pubTableModel.addRow(searchdata1);
            }
            ResultSet rs2 = readSearchData(2);
            while (rs2.next()) {
                searchdata1 = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    searchdata1[i] = rs2.getObject(i + 1);
                }
                prvTableModel.addRow(searchdata1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        pubTable.setModel(pubTableModel);
        prvTable.setModel(prvTableModel);
    }
}

