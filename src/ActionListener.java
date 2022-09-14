import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ActionListener extends PhoneBookHP {
    static java.awt.event.ActionListener removeNum = new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    static java.awt.event.ActionListener backToHP = new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            try {
                PhoneBookHP.homePage();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    };
}
