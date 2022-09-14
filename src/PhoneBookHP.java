import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PhoneBookHP{
    public static final String URL = "jdbc:sqlserver://localhost\\SQL;databaseName=PhoneBook;trustServerCertificate=true";
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

    public static JFrame frameHP = new JFrame();
    public static JFrame frameLogin = new JFrame();
    public static JFrame frameRm = new JFrame();
    public static JFrame frameInsert = new JFrame();
    public static JPasswordField passInput= new JPasswordField();
    public static JTextField userInput = new JTextField();
    public static JButton loginBtn = new JButton("Login");
    public static JLabel userLabel = new JLabel("Username");
    public static JLabel passLabel = new JLabel("Password");
    public static JTextField firstNameInput= new JTextField();
    public static JTextField lastNameInput = new JTextField();
    public static JTextField phoneNumInput = new JTextField("e.g 09123456789");
    public static JButton insertBtn = new JButton("Add");
    public static JButton rmBtn = new JButton("Remove Contact");
    public static JButton backBtn = new JButton("Back");
    public static JLabel firstNameLabel = new JLabel("FirstName");
    public static JLabel lastNameLabel = new JLabel("LastName");
    public static JLabel phoneNumLabel = new JLabel("Phone Number");
    public static JLabel accessLabel = new JLabel("Access");
    public static JPanel pubPhoneNums = new JPanel();
    public static JPanel prvPhoneNums = new JPanel();
    public static JButton newNumBtn = new JButton("New Number");
    public static JButton rmNumBtn = new JButton("Remove Number");
    public static JLabel hpHeaderPub = new JLabel("Your Public Contacts");
    public static JLabel hpHeaderPrv = new JLabel("Your public Contacts");
    public static Border border = new LineBorder(Color.ORANGE, 4, true);
    public static JTable pubTable = new JTable();
    public static JTable prvTable = new JTable();

//    private static ResultSet rs;
    private static int colNo;

    public static ResultSet readData(int auth) throws SQLException {
        Connection con = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = con.createStatement();
        String sql = "Use PhoneBook;" + " select FirstName,LastName,PhoneNumber from dbo.contacts where auth = " + auth;
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        colNo = rsmd.getColumnCount();
        return rs;
    }
    
    public static void homePage() throws SQLException {

        frameHP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHP.setSize(350, 450);
        frameHP.setTitle("Home Page");
        frameHP.setLocationRelativeTo(null);
        frameHP.setLayout(null);

        hpHeaderPub.setBounds(110, 10, 125, 15);
        pubPhoneNums.setBounds(50, 45, 250, 125);
        pubPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        pubPhoneNums.setBackground(Color.GRAY);

        hpHeaderPrv.setBounds(110, 190, 125, 15);
        prvPhoneNums.setBounds(50, 210, 250, 125);
        prvPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        prvPhoneNums.setBackground(Color.GRAY);

        String[] cols = {"FirstName","LastName","PhoneNumber"};
        DefaultTableModel pubTableModel = (DefaultTableModel) pubTable.getModel();;
        DefaultTableModel prvTableModel = (DefaultTableModel) prvTable.getModel();;
        pubTableModel.setColumnIdentifiers(cols);
        prvTableModel.setColumnIdentifiers(cols);

        ResultSet rs1 = readData(1);
        ResultSet rs2 = readData(2);
        Object[] data = new Object[0];
        while (rs1.next()) {
            data = new Object[colNo];
            for (int i = 0; i < colNo; i++) {
                data[i] = rs1.getObject(i + 1);
            }
            pubTableModel.addRow(data);
        }
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
        pubColumnModel.getColumn(0).setPreferredWidth(60);
        pubColumnModel.getColumn(1).setPreferredWidth(60);
        pubColumnModel.getColumn(2).setPreferredWidth(90);
        TableColumnModel prvColumnModel = prvTable.getColumnModel();
        prvColumnModel.getColumn(0).setPreferredWidth(60);
        prvColumnModel.getColumn(1).setPreferredWidth(60);
        prvColumnModel.getColumn(2).setPreferredWidth(90);

        pubPhoneNums.add(pubTable);
        prvPhoneNums.add(prvTable);

        newNumBtn.setBounds(38, 360, 130, 25);
        newNumBtn.addActionListener(newNum);
        rmNumBtn.setBounds(180, 360, 130, 25);
        rmNumBtn.addActionListener(rmNum);

        frameHP.add(hpHeaderPub);
        frameHP.add(hpHeaderPrv);
        frameHP.add(pubPhoneNums);
        frameHP.add(prvPhoneNums);
        frameHP.add(newNumBtn);
        frameHP.add(rmNumBtn);

        frameHP.setVisible(true);
    }
        static ActionListener newNum = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameHP.dispose();
            PhoneBookInsert.insertPage();
        }
        };
        static ActionListener rmNum = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameHP.dispose();
                PhoneBookRm.removePage();
            }
        };

        public static void main(String[] args) throws SQLException {
            homePage();
        }
}
