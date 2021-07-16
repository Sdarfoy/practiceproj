import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public abstract class DocumentEditor extends JDialog {

    public void showForm() {
        setSize(500, 300);
        setModal(true);
        setVisible(true);
    }

    public void setStyle(String string, JTextField field) {
        field.setText("");
        PromptSupport.setPrompt(string, field);
        PromptSupport.setFontStyle(Font.BOLD, field);
        PromptSupport.setForeground(Color.lightGray, field);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, field);
    }
}
