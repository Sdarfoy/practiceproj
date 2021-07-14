import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class InformationForm extends DocumentEditor {
    private JTextArea textArea;
    private JPanel panel;
    private JButton button1;
    private Payment payment;

    InformationForm(Document document) {
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearText();
                dispose();
            }
        });
        textArea.append(document.toStringField());
        setTitle(document.getDocType());
    }

    public void clearText() {
        textArea.setText("");
    }

    public String getText() {
        return textArea.getText();
    }
}
