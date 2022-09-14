import java.awt.*;

public class PhoneBookRm extends PhoneBookHP{
        public static void removePage(){
            rmContact.setBounds(320 , 210, 250, 150);
            rmContact.setBackground(Color.green);

            firstNameLabel.setBounds(10, 25 , 120 , 25);
            firstNameInput.setBounds(115,25,120,25);
            rmBtn.setBounds(50, 90 , 110 , 25);

            rmContact.add(firstNameLabel);
            rmContact.add(firstNameInput);
            rmContact.add(rmBtn);

            rmContact.setLayout(null);
            rmContact.setVisible(true);
        }
}
