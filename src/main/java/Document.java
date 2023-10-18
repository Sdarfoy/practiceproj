import java.time.LocalDate;

public abstract class Document {
    protected LocalDate date;
    protected String user, docType, number;
    protected double sum;

    @Override
    public String toString() {
        return getTranslatedDocType() + " от " + date + " номер " + number;
    }

    public static Document parseDoc(String string){
        String[] fields = string.split("\n|: ");
        switch (fields[1]){
            case "Платёжка":
                return new Payment(
                        LocalDate.parse(fields[11]),
                        fields[7],
                        fields[5],
                        fields[3],
                        fields[1],
                        Double.parseDouble(fields[9]));
            case "Заявка":
                return new Request (
                        LocalDate.parse(fields[17]),
                        fields[5],
                        fields[3],
                        fields[9],
                        fields[7],
                        fields[1],
                        Double.parseDouble(fields[13]),
                        Double.parseDouble(fields[15]),
                        Double.parseDouble(fields[11]));
            case "Накладная":
                return new Consignment (
                        LocalDate.parse(fields[15]),
                        fields[5],
                        fields[3],
                        fields[1],
                        fields[11],
                        fields[7],
                        Double.parseDouble(fields[13]),
                        Double.parseDouble(fields[9]),
                        Double.parseDouble(fields[15]));
            default:
                return null;
        }
    }

    public String toStringField() {
        return "Тип документа: " + docType + "\n" +
                "Пользователь: " + user + "\n" +
                "Номер документа: " + number + "\n" +
                getOtherFields() +
                "Сумма: " + sum + "\n" +
                "Дата: " + date + "\n";
    }

    public abstract String getTranslatedDocType();

    public abstract  String getOtherFields();

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
}
