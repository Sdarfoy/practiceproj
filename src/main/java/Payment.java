import java.time.LocalDate;

public class Payment extends Document {

    private final String employer;

    Payment (LocalDate date, String employer, String number, String user, String docType, double sum) {
        this.date = date;
        this.employer = employer;
        this.number = number;
        this.user = user;
        this.sum = sum;
        this.docType = docType;
    }

    @Override
    public String getTranslatedDocType() {
        return "Платёжка";
    }
    @Override
    public  String getOtherFields() {
        return "Сотрудник: " + employer + "\n";
    }
}
