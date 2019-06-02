package gui;

import model.Client;
import service.ClientService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientView {
    JFrame frame = new JFrame("Add client");

    JLabel firstNameLbl = new JLabel("First Name: ");
    JLabel lastNameLbl = new JLabel("Last Name: ");
    JLabel ageLbl = new JLabel("Age: ");

    JTextField firstNameTxt = new JTextField();
    JTextField lastNameTxt = new JTextField();
    JTextField ageTxt = new JTextField();

    JButton addClientBtn = new JButton("Add Client");

    AddClientView() {
        firstNameLbl.setBounds(50, 10, 150, 40);
        firstNameTxt.setBounds(150, 10, 150, 40);

        lastNameLbl.setBounds(50, 70, 150, 40);
        lastNameTxt.setBounds(150, 70, 150, 40);

        ageLbl.setBounds(50, 130, 150, 40);
        ageTxt.setBounds(150, 130, 150, 40);

        addClientBtn.setBounds(150, 200, 150, 40);
        addClientBtn.addActionListener(e -> {
            Client client = new Client(firstNameTxt.getText(), lastNameTxt.getText(),
                                       Integer.parseInt(ageTxt.getText()), -1);
            ClientService.getInstance().insertClient(client);

            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(firstNameLbl);
        frame.add(lastNameLbl);
        frame.add(ageLbl);
        frame.add(firstNameTxt);
        frame.add(lastNameTxt);
        frame.add(ageTxt);
        frame.add(addClientBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
