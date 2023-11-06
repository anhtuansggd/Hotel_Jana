package GUI;

import java.awt.Font;

import javax.swing.JLabel;

public class AccountInfoPanel extends ChildrenPanel {
    JLabel infoLabel;
    
    JLabel idAttributeLabel;
    JLabel typeAttributeLabel;
    JLabel usernameAttributeLabel;
    JLabel nameAttributeLabel;
    JLabel raceAttributeLabel;

    JLabel idLabel;
    JLabel typeLabel;
    JLabel usernameLabel;
    JLabel nameLabel;
    JLabel raceLabel;

    public AccountInfoPanel(MainFrame f) {
        super(f);

        infoLabel = getFormattedLabel("Account Information", 30, 24, 300, 30);
        infoLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(infoLabel);

        idAttributeLabel = getFormattedAttributeLabel("Account ID", 0);
        add(idAttributeLabel);

        typeAttributeLabel = getFormattedAttributeLabel("Account type", 1);
        add(typeAttributeLabel);

        usernameAttributeLabel = getFormattedAttributeLabel("Username", 2);
        add(usernameAttributeLabel);

        nameAttributeLabel = getFormattedAttributeLabel("Name", 3);
        add(nameAttributeLabel);

        raceAttributeLabel = getFormattedAttributeLabel("Race", 4);
        add(raceAttributeLabel);

        idLabel = getFormattedValueLabel(f.account.getId(), 0);
        add(idLabel);

        typeLabel = getFormattedValueLabel(String.valueOf(f.account.getAccountType()), 1);
        add(typeLabel);

        usernameLabel = getFormattedValueLabel(f.account.getUsername(), 2);
        add(usernameLabel);

        nameLabel = getFormattedValueLabel(f.account.getName(), 3);
        add(nameLabel);

        raceLabel = getFormattedValueLabel(String.valueOf(f.account.getRace()), 4);
        add(raceLabel);
    }

    private JLabel getFormattedAttributeLabel(String s, int pos) {
        return getFormattedLabel(s, 30, 60 + pos * 30, 120, 30);
    }

    private JLabel getFormattedValueLabel(String s, int pos) {
        return getFormattedLabel(":   " + s, 130, 60 + pos * 30, 200, 30);
    }
}
