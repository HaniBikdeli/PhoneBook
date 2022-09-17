import java.awt.*;

public class PhoneBookRm extends PhoneBookHP{
        public static void removePage(){
            rmContact.setBounds(430 , 260, 250, 150);
            rmContact.setBackground(Color.darkGray);

            firstNameLabel.setBounds(10, 25 , 120 , 25);
            firstNameInput.setBounds(115,25,120,25);
            rmNumBtn.setBounds(115, 50, 120, 25);;

            rmContact.add(firstNameLabel);
            rmContact.add(firstNameInput);
            rmContact.add(rmNumBtn);

            rmContact.setLayout(null);
            rmContact.setVisible(true);
        }
}
