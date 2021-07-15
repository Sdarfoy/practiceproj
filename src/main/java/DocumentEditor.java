import javax.swing.*;
import java.time.LocalDate;

public abstract class DocumentEditor extends JDialog {

    public void showForm() {
        setSize(500, 300);
        setModal(true);
        setVisible(true);
    }
    public LocalDate parseDate (String date) {
            return LocalDate.parse(date);
    }
}
