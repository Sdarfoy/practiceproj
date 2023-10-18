import java.time.LocalDate;

public class Request extends Document {
    private final String counterParty;
    private final String currency;
    private final double commission;
    private final double currencyValue;

    Request (LocalDate date,
             String number,
             String user,
             String currency,
             String counterParty,
             String docType,
             double commission,
             double sum,
             double currencyValue) {
        this.date = date;
        this.number = number;
        this.user = user;
        this.currency = currency;
        this.counterParty = counterParty;
        this.docType = docType;
        this.commission = commission;
        this.sum = sum;
        this.currencyValue = currencyValue;
    }

    @Override
    public String getTranslatedDocType() {
        return "Заявка";
    }

    @Override
    public  String getOtherFields() {
        return "Контрагент: " + counterParty + "\n" +
                "Валюта: " + currency + "\n" +
                "Курс валюты: " + currencyValue + "\n" +
                "Коммиссия: " + commission + "\n";
    }

}
