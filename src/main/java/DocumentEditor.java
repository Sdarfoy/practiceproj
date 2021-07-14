

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

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
