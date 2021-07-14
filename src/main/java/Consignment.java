import java.time.LocalDate;
import java.util.*;

public class Consignment extends Document {
    private final String currency;
    private final String product;
    private final double currencyValue;
    private final double amount;

    Consignment (LocalDate date,
                 String number,
                 String user,
                 String docType,
                 String currency,
                 String product,
                 double currencyValue,
                 double amount,
                 double sum) {
        this.date = date;
        this.number = number;
        this.user = user;
        this.docType = docType;
        this.currency = currency;
        this.product = product;
        this.currencyValue = currencyValue;
        this.amount = amount;
        this.sum = sum;
    }
    @Override
    public String getTranslatedDocType() {
        return "Накладная";
    }
    @Override
    public  String getOtherFields() {
        return "Товар: " + product + System.lineSeparator() +
                "Количество: " + amount + System.lineSeparator() +
                "Валюта: " + currency + System.lineSeparator() +
                "Курс валюты: " + currencyValue + System.lineSeparator();
    }

}
