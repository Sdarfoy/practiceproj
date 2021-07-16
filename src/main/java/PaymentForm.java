import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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
        this.setTitle("Платёжка");
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        clearTextFields();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearTextFields();
                dispose();
            }
        });

        makePayment.addActionListener (actionEvent -> {
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
       return new Payment (LocalDate.parse(date.getText()),
               employer.getText(),
               number.getText(),
               user.getText(),
               "Платёжка",
               Double.parseDouble(sum.getText()));
    }

    public void clearTextFields() {
        setStyle("2000-01-01", date);
        employer.setText("");
        setStyle("P000" , number);
        user.setText("");
        sum.setText("");
    }

}
