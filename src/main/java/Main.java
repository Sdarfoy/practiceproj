import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private List<Document> listOfDocs;

    public static void main(String[] args) {
        List<Document> listOfDocs = new ArrayList<>();
        MainForm mainForm = new MainForm(listOfDocs);
        mainForm.showMainForm();
    }

    public List<Document> getListOfDocs() {
        return listOfDocs;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    public static void createRequest(List<Document> list){

    }
}

