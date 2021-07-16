import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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
    private JLabel lNumber;
    private JLabel lCurrencyValue;
    private JLabel lAmount;
    private JLabel lDate;
    private JLabel lSum;
    private MainForm mainForm;

    public ConsignmentForm(MainForm mainForm, List<Document> listOfDocs) {
        this.mainForm = mainForm;
        this.setTitle("Накладная");
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        clearTextFields();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearTextFields();
                dispose();
            }
        });

        makeConsignment.addActionListener(actionEvent -> {
                if (createDocument() != null) {
                    listOfDocs.add(createDocument());
                    dispose();
                    clearTextFields();
                    mainForm.refresh();
                } else {
                    startChecking();
                    JOptionPane.showMessageDialog(this, "тут будут неправильные поля");
                }
        });
    }

    public Document createDocument() {
            return new Consignment(LocalDate.parse(date.getText()),
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
        setStyle("2000-01-01", date);
        product.setText("");
        amount.setText("");
        setStyle("Единица валюты в рублях" ,currencyValue);
        setStyle("EUR, USD", currency);
        setStyle("C000" , number);
        user.setText("");
        sum.setText("");
    }

    public void startChecking() {
        boolean doubleError = false;
        boolean dateError = false;
        boolean numberError = false;
        if (Double.valueOf(currencyValue.getText()).isNaN()) {
            doubleError = true;
            lCurrencyValue.setText(lCurrencyValue.getText() + "*");
        }
        if (Double.valueOf(amount.getText()).isNaN()) {
            doubleError = true;
            lAmount.setText(lAmount.getText() + "*");
        }
        if (Double.valueOf(sum.getText()).isNaN()) {
            doubleError = true;
            lSum.setText(lSum.getText() + "*");
        }
        if (!number.getText().startsWith("Н")) {
            numberError = true;
            lNumber.setText(lNumber.getText() + "*");
        }
    }
}
