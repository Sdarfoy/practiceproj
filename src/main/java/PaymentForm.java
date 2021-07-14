

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentForm extends DocumentEditor {
    protected JButton makePayment;
    protected JTextField user;
    protected JTextField number;
    protected JTextField employer;
    protected JTextField sum;
    protected JTextField date;
    protected JPanel panel;
    private MainForm mainForm;

    public PaymentForm(MainForm mainForm, List<Document> listOfDocs) {
        this.mainForm = mainForm;
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        makePayment.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                listOfDocs.add(createDocument());
                dispose();
                clearTextFields();
                mainForm.refresh();
            }
        } );
    }

    public Document createDocument() {
       return new Payment (parseDate(date.getText()),
               employer.getText(),
               number.getText(),
               user.getText(),
               "Платёжка",
               Double.parseDouble(sum.getText()));
    }

    public void clearTextFields() {
        date.setText("");
        employer.setText("");
        number.setText("");
        user.setText("");
        sum.setText("");
    }

}
