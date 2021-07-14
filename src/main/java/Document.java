import java.nio.file.Path;
import java.time.LocalDate;

public abstract class Document {
    protected Path file;
    protected LocalDate date;
    protected String user, docType, number;
    protected double sum;

    @Override
    public String toString() {
        return getTranslatedDocType() + " от " + date + " номер " + number;
    }

    public abstract String getTranslatedDocType();

    public String toStringField() {
        return "Пользователь: " + user + System.lineSeparator() +
                "Номер документа:  " + number + System.lineSeparator() +
                getOtherFields() +
                "Сумма: " + sum + System.lineSeparator() +
                "Дата: " + date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getDocType() {
        return docType;
    }

    public String getNumber() {
        return number;
    }

    public double getSum() {
        return sum;
    }

    public abstract  String getOtherFields();
}
