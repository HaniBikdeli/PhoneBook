import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PhoneBookHP{
    public static final String URL = "jdbc:sqlserver://localhost\\SQL;database=PhoneBook;trustServerCertificate=true";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "123";
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection(URL , DB_USERNAME , DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Statement statement;

    static {
        try {
            statement = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static JFrame frame = new JFrame();
    public static JFrame frameLogin = new JFrame();
    public static JPanel pubPhoneNums = new JPanel();
    public static JPanel prvPhoneNums = new JPanel();
    public static JPanel rmContact = new JPanel();
    public static JPanel addContact = new JPanel();
    public static JTable pubTable = new JTable();
    public static JTable prvTable = new JTable();
    public static JLabel hpHeaderPub = new JLabel("Your Public Contacts");
    public static JLabel hpHeaderPrv = new JLabel("Your Private Contacts");
    public static JLabel firstNameLabel = new JLabel("FirstName");
    public static JLabel phoneNumLabel = new JLabel("Phone Number");
    public static JLabel accessLabel = new JLabel("Access");
    public static JLabel userLabel = new JLabel("Username");
    public static JLabel passLabel = new JLabel("Password");
    public static JPasswordField passInput= new JPasswordField();
    public static JTextField userInput = new JTextField();
    public static JTextField firstNameInput= new JTextField("Enter Target's FirstName");
    public static JTextField phoneNumInput = new JTextField("e.g 09123456789");
    public static JButton loginBtn = new JButton("Login");
    public static JButton insertBtn = new JButton("Add");
    public static JButton rmBtn = new JButton("Remove Contact");
    public static JButton newNumBtn = new JButton("New Number");
    public static JButton rmNumBtn = new JButton("Remove Number");
    public static Border border = new LineBorder(Color.gray, 3, true);
    private static int colNo;

    public static ResultSet readData(int auth) throws SQLException {
        String sql = "Use PhoneBook"+" select FullName , PhoneNumber from dbo.contacts  where auth = " + auth;
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        colNo = rsmd.getColumnCount();
        return rs;
    }
    
    public static void homePage() throws SQLException {
        System.out.println(readData(1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setTitle("Home Page");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.cyan);

        hpHeaderPub.setBounds(110, 10, 125, 15);
        pubPhoneNums.setBounds(50, 45, 250, 125);
        pubPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        hpHeaderPrv.setBounds(110, 190, 125, 15);
        prvPhoneNums.setBounds(50, 210, 250, 125);
        prvPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        String[] cols = {"FirstName","PhoneNumber" , "btn"};
        DefaultTableModel pubTableModel = (DefaultTableModel) pubTable.getModel();
        DefaultTableModel prvTableModel = (DefaultTableModel) prvTable.getModel();
        pubTableModel.setColumnIdentifiers(cols);
        prvTableModel.setColumnIdentifiers(cols);
        ResultSet rs1 = readData(1);
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
        prvPhoneNums.setBorder(border);

        pubTable.setDefaultEditor(Object.class, null);
        prvTable.setDefaultEditor(Object.class, null);
        TableColumnModel pubColumnModel = pubTable.getColumnModel();
        pubColumnModel.getColumn(0).setPreferredWidth(75);
        pubColumnModel.getColumn(1).setPreferredWidth(120);
        TableColumnModel prvColumnModel = prvTable.getColumnModel();
        prvColumnModel.getColumn(0).setPreferredWidth(75);
        prvColumnModel.getColumn(1).setPreferredWidth(120);

        pubPhoneNums.add(pubTable);
        prvPhoneNums.add(prvTable);

        newNumBtn.setBounds(38, 360, 130, 25);
//        newNumBtn.addActionListener(newNum);
        rmNumBtn.setBounds(180, 360, 130, 25);
//        rmNumBtn.addActionListener(rmNum);
        frame.add(hpHeaderPub);
        frame.add(hpHeaderPrv);
        frame.add(pubPhoneNums);
        frame.add(prvPhoneNums);
        frame.add(addContact);
        frame.add(rmContact);

        PhoneBookRm.removePage();
        PhoneBookInsert.insertPage();
        frame.setVisible(true);
        ResultSet rs = readData(1);
        System.out.println(rs.isClosed());
    }
        public static void main(String[] args) throws SQLException {
            homePage();
        }
}
