import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        makeRequest.addActionListener(new ActionListener() {
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
        return new Request (parseDate(date.getText()),
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
        date.setText("");
        counterParty.setText("");
        commission.setText("");
        currencyValue.setText("");
        currency.setText("");
        number.setText("");
        user.setText("");
        sum.setText("");
    }
}
