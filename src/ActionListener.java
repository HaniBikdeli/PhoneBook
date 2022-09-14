import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionListener extends PhoneBookHP {
    static ActionListener removeNum = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

        }
    };
    static ActionListener addNewContact = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String fn = firstNameInput.getText();
            String sql = "Use PhoneBook;" + " insert into dbo.contacts ";
//            statement.execute();
        }
    };
}
