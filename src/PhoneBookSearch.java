import javax.swing.*;
import java.awt.*;

public class PhoneBookSearch extends PhoneBookHP{
    public static void searchPage(){
        searchContact.setBounds(50 , 360, 300, 40);
        searchContact.setBackground(Color.cyan);

        searchInput.setBounds(15,10,150,25);
        searchBtn.setBounds(180, 10, 90, 25);;

        searchContact.add(searchInput);
        searchContact.add(searchBtn);

        searchContact.setLayout(null);
        searchContact.setVisible(true);
    }
}
