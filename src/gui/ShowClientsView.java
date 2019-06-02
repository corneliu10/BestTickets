package gui;

import model.Client;
import model.Person;
import service.ClientService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShowClientsView {
    JFrame frame = new JFrame("Show Clients");

    public ShowClientsView() {
        addClientsToFrame();

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addClientsToFrame() {
        List<Client> clients = ClientService.getInstance().getClients();
        int y = 10;
        for (Client c : clients) {
            JLabel label = new JLabel(String.valueOf(c.getId()));
            label.setBounds(10, y, 10,  20);

            JTextField textField = new JTextField(c.getFirstName());
            textField.setBounds(24, y, 80, 20);

            JTextField textField1 = new JTextField(c.getLastName());
            textField1.setBounds(120, y, 80, 20);

            JTextField textField2 = new JTextField(String.valueOf(c.getAge()));
            textField2.setBounds(210, y, 40, 20);

            JButton btn = new JButton("Save");
            btn.setBounds(260, y, 70, 20);
            btn.addActionListener(e -> {
                ClientService.getInstance().updateClient(textField.getText(), textField1.getText(),
                        Integer.parseInt(textField2.getText()), c.getId());
            });

            JButton btn1 = new JButton("Delete");
            btn1.setBounds(340, y, 70, 20);
            btn1.addActionListener(e -> {
                ClientService.getInstance().removeClient(c.getId());
                frame.getContentPane().removeAll();
                addClientsToFrame();
                frame.repaint();
            });

            frame.add(btn);
            frame.add(btn1);
            frame.add(label);
            frame.add(textField);
            frame.add(textField1);
            frame.add(textField2);

            y += 30;
        }
    }
}