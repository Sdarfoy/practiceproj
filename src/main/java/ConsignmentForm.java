import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ConsignmentForm extends DocumentEditor {

    private JButton makeConsignment;
    private JTextField user;
    private JTextField number;
    private JTextField currency;
    private JTextField sum;
    private JTextField date;
    private JPanel panel;
    private JTextField product;
    private JTextField amount;
    private JTextField currencyValue;
    private MainForm mainForm;

    public ConsignmentForm(MainForm mainForm, List<Document> listOfDocs) {
        this.mainForm = mainForm;
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        makeConsignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listOfDocs.add(createDocument());
                dispose();
                clearTextFields();
                mainForm.refresh();
            }
        });
    }

    public Document createDocument() {
        return new Consignment (parseDate(date.getText()),
                number.getText(),
                user.getText(),
                "Накладная",
                currency.getText(),
                product.getText(),
                Double.parseDouble(currencyValue.getText()),
                Double.parseDouble(amount.getText()),
                Double.parseDouble(sum.getText()));
    }

    public void clearTextFields() {
        date.setText("");
        product.setText("");
        amount.setText("");
        currencyValue.setText("");
        currency.setText("");
        number.setText("");
        user.setText("");
        sum.setText("");
    }

}
