import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PhoneBookLogin extends PhoneBookHP implements ActionListener{
    static int id;
    private static int colNo;
    public void loginPage(){
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(350,230);
        frameLogin.setTitle("Login Page");
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);

        userInput.setBounds(135,55,120,25);
        passInput.setBounds(135,100,120,25);
        loginBtn.setBounds(110 , 140 , 80 , 25);
        loginBtn.addActionListener(this);
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
        PhoneBookLogin login = new PhoneBookLogin();
        login.loginPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PhoneBookHP.frameLogin.dispose();
        Connection connection;
        PreparedStatement creds;
        PreparedStatement authorization;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQL;database=PhoneBook;trustServerCertificate=true", "sa", "123");
            creds = connection.prepareStatement("SELECT userId, username, password FROM dbo.users WHERE username = ? AND password = ?");
            creds.setString(1, userInput.getText());
            creds.setString(2, String.valueOf(passInput.getPassword()));
            ResultSet rs = creds.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
                frameLogin.dispose();
                PhoneBookHP.homePage();
            }
            else{
                JOptionPane.showMessageDialog(null, "Login Failed.");
                loginPage();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
//    public static int getOwnerId() throws SQLException {
//        PreparedStatement ownerId = con.prepareStatement("select userId from dbo.users where username = ?");
//        ownerId.setString(1,userInput.getText());
//        ResultSet rs = ownerId.executeQuery();
//        int id = rs.getByte(1);
//        return id;
//    }
public static ResultSet readData(int auth) throws SQLException {
    String sql = "Use PhoneBook"+" select FullName , PhoneNumber from dbo.contacts  where ownerId="+id+"AND auth = " + auth;
    ResultSet rs = statement.executeQuery(sql);
    ResultSetMetaData rsmd = rs.getMetaData();
    colNo = rsmd.getColumnCount();
    return rs;
}
public static void showData() throws SQLException {
    String[] cols = {"FirstName","PhoneNumber" , "Country" , "Province"};
    DefaultTableModel pubTableModel = (DefaultTableModel) pubTable.getModel();
    DefaultTableModel prvTableModel = (DefaultTableModel) prvTable.getModel();
    pubTableModel.setColumnIdentifiers(cols);
    prvTableModel.setColumnIdentifiers(cols);
    ResultSet rs1 = PhoneBookLogin.readData(1);
    Object[] data;
    while (rs1.next()) {
        data = new Object[colNo];
        for (int i = 0; i < colNo; i++) {
            data[i] = rs1.getObject(i + 1);
        }
        pubTableModel.addRow(data);
    }
    ResultSet rs2 = readData(2);
    while (rs2.next()) {
        data = new Object[colNo];
        for (int i = 0; i < colNo; i++) {
            data[i] = rs2.getObject(i + 1);
        }
        prvTableModel.addRow(data);
    }

    pubTable.setModel(pubTableModel);
    prvTable.setModel(prvTableModel);
}
}
