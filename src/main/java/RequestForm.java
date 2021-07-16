import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

public class RequestForm extends DocumentEditor {

    private JButton makeRequest;
    private JTextField user;
    private JTextField number;
    private JTextField counterParty;
    private JTextField commission;
    private JTextField sum;
    private JTextField date;
    private JTextField currency;
    private JTextField currencyValue;
    private JPanel panel;
    private MainForm mainForm;

    RequestForm(MainForm mainForm, List<Document> listOfDocs) {
        this.mainForm = mainForm;
        this.setTitle("Заявка");
        setContentPane(panel);
        clearTextFields();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearTextFields();
                dispose();
            }
        });

        makeRequest.addActionListener(actionEvent -> {
            try {
                listOfDocs.add(createDocument());
                dispose();
                clearTextFields();
                mainForm.refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainForm, "Не все поля заполнены корректно");
            }
        });
    }

    public Document createDocument() {
        return new Request (LocalDate.parse(date.getText()),
                number.getText(),
                user.getText(),
                currency.getText(),
                counterParty.getText(),
                "Заявка",
                Double.parseDouble(commission.getText()),
                Double.parseDouble(sum.getText()),
                Double.parseDouble(currencyValue.getText()));
    }

    public void clearTextFields() {
        setStyle("2000-01-01", date);
        setStyle("Компания партнёр", counterParty);
        commission.setText("");
        setStyle("Единица валюты в рублях" ,currencyValue);
        setStyle("EUR, USD", currency);
        setStyle("R000" , number);
        user.setText("");
        sum.setText("");
    }
}
