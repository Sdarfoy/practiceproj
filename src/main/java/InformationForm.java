import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationForm extends DocumentEditor {
    private JTextArea textArea;
    private JPanel panel;
    private JButton ok;

    InformationForm(Document document) {
        setContentPane(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        textArea.append(document.toStringField());
        setTitle(document.getDocType());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearText();
                dispose();
            }
        });

        ok.addActionListener(actionEvent -> {
            clearText();
            dispose();
        });
    }

    public void clearText() {
        textArea.setText("");
    }

}
