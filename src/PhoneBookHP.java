import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PhoneBookHP implements ActionListener{
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
    public static Statement statement1;

    static {
        try {
            statement1 = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Statement statement2;

    static {
        try {
            statement2 = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static JFrame frame = new JFrame();
    public static JFrame frameLogin = new JFrame();
    public static JFrame framePopUp = new JFrame();
    public static JPanel pubPhoneNums = new JPanel();
    public static JPanel prvPhoneNums = new JPanel();
    public static JPanel rmContact = new JPanel();
    public static JPanel addContact = new JPanel();
    public static JPanel searchContact = new JPanel();
    public static JTable pubTable = new JTable();
    public static JTable prvTable = new JTable();
    public static JLabel hpHeaderPub = new JLabel("Your Public Contacts");
    public static JLabel hpHeaderPrv = new JLabel("Your Private Contacts");
    public static JLabel firstNameLabel = new JLabel("FirstName");
    public static JLabel phoneNumLabel = new JLabel("Phone Number");
    public static JLabel accessLabel = new JLabel("Access");
    public static JLabel userLabel = new JLabel("Username");
    public static JLabel passLabel = new JLabel("Password");
    public static JLabel opsLabel = new JLabel("Choose Operation");
    public static JPasswordField passInput= new JPasswordField();
    public static JTextField userInput = new JTextField();
    public static JTextField firstNameInput= new JTextField("Enter Target's FullName");
    public static JTextField searchInput= new JTextField("Enter Target's FullName");
    public static JTextField phoneNumInput = new JTextField("e.g 09123456789");
    public static JButton loginBtn = new JButton("Login");
    public static JButton insertBtn = new JButton("Add");
    public static JButton searchBtn = new JButton("Search");
    public static JButton newNumBtn = new JButton("New Number");
    public static JButton rmBtn = new JButton("remove");
    public static JButton editBtn = new JButton("edit");
    public static JButton rmNumBtn = new JButton("Remove Number");
    public static Border border = new LineBorder(Color.gray, 3, true);


    public static void newTable() throws SQLException {
//        String[] cols = {"FirstName","PhoneNumber" , "Country" , "Province"};
//        DefaultTableModel pubTableModel = (DefaultTableModel) pubTable.getModel();
//        DefaultTableModel prvTableModel = (DefaultTableModel) prvTable.getModel();
//        pubTableModel.setColumnIdentifiers(cols);
//        prvTableModel.setColumnIdentifiers(cols);
//        ResultSet rs1 = readData(1);
//        Object[] data;
//        while (rs1.next()) {
//            data = new Object[colNo];
//            for (int i = 0; i < colNo; i++) {
//                data[i] = rs1.getObject(i + 1);
//            }
//            pubTableModel.addRow(data);
//        }
//        ResultSet rs2 = readData(2);
//        while (rs2.next()) {
//            data = new Object[colNo];
//            for (int i = 0; i < colNo; i++) {
//                data[i] = rs2.getObject(i + 1);
//            }
//            prvTableModel.addRow(data);
//        }
//
//        pubTable.setModel(pubTableModel);
//        prvTable.setModel(prvTableModel);
//        prvPhoneNums.setBorder(border);
        DefaultTableModel model = new DefaultTableModel();
        pubTable.setModel(model);
        prvTable.setModel(model);
    }
    
    public void homePage() throws SQLException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 450);
        frame.setTitle("Home Page");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.cyan);

        hpHeaderPub.setBounds(75, 10, 125, 15);
        pubPhoneNums.setBounds(50, 45, 350, 125);
        pubPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        hpHeaderPrv.setBounds(75, 190, 125, 15);
        prvPhoneNums.setBounds(50, 210, 350, 125);
        prvPhoneNums.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        PhoneBookLogin.showData();
        prvPhoneNums.setBorder(border);

        pubTable.setDefaultEditor(Object.class, null);
        prvTable.setDefaultEditor(Object.class, null);
        TableColumnModel pubColumnModel = pubTable.getColumnModel();
        pubColumnModel.getColumn(0).setPreferredWidth(75);
        pubColumnModel.getColumn(1).setPreferredWidth(120);
        pubColumnModel.getColumn(2).setPreferredWidth(75);
        pubColumnModel.getColumn(3).setPreferredWidth(75);
        TableColumnModel prvColumnModel = prvTable.getColumnModel();
        prvColumnModel.getColumn(0).setPreferredWidth(75);
        prvColumnModel.getColumn(1).setPreferredWidth(120);
        prvColumnModel.getColumn(2).setPreferredWidth(75);
        prvColumnModel.getColumn(3).setPreferredWidth(75);

        rmBtn.addActionListener(this);
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
        frame.add(searchContact);

        PhoneBookRm.removePage();
        PhoneBookInsert insert = new PhoneBookInsert();
        insert.insertPage();
        PhoneBookSearch search = new PhoneBookSearch();
                search.searchPage();
        frame.setVisible(true);
    }
        public static void main(String[] args) throws SQLException {
            PhoneBookHP hp = new PhoneBookHP();
            hp.homePage();
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sql = "Use PhoneBook "+"delete from dbo.contacts where PhoneNumber = ?";
        int col = 2;
        int row = pubTable.getSelectedRow();
        String value = pubTable.getModel().getValueAt(row, col).toString();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1 , value);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
