import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class MainForm extends JFrame {
    private final ConsignmentForm consignmentForm;
    private final PaymentForm paymentForm;
    private final RequestForm requestForm;
    private final JFileChooser fileChooser;
    private JPanel contentPane;
    private JButton exit;
    private JButton payment;
    private JButton consignment;
    private JButton request;
    private JButton saveDocument;
    private JButton loadDocument;
    private JButton viewDocument;
    private final DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JList<String> listOfItems;
    private JScrollPane scrollPane;
    private final List<Document> documents;

    public MainForm(List<Document> documents) {
        this.documents = documents;
        consignmentForm = new ConsignmentForm(this, documents);
        paymentForm = new PaymentForm(this, documents);
        requestForm = new RequestForm(this, documents);
        fileChooser = new JFileChooser("/home/shmelev_ne");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        setContentPane(contentPane);
        pack();
        listOfItems.setModel(defaultListModel);
        refresh();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        payment.addActionListener(actionEvent -> paymentForm.showForm());

        consignment.addActionListener(actionEvent -> consignmentForm.showForm());

        request.addActionListener(actionEvent -> requestForm.showForm());

        saveDocument.addActionListener(actionEvent -> {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showSaveDialog(MainForm.this);
            File file = fileChooser.getSelectedFile();
            try {
                File fileToSave = new File(file + ".txt");
                try (PrintWriter pw = new PrintWriter(new FileWriter(fileToSave))) {
                    pw.println(documents.get(listOfItems.getSelectedIndex()).toStringField());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainForm.this, "Не удалось сохранить документ");
            }
        });

        loadDocument.addActionListener(actionEvent -> {
            try {
                FileFilter fileFilter = new FileNameExtensionFilter("Notepad file", "txt");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.showOpenDialog(MainForm.this);
                File file = fileChooser.getSelectedFile();
                documents.add(stringToDoc(FileUtils.readFileToString(file, StandardCharsets.UTF_8)));
                refresh();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainForm.this, "Не удалось загрузить документ");
            }
        });

        viewDocument.addActionListener(actionEvent -> {
            try {
                InformationForm informationForm = new InformationForm(documents.get(listOfItems.getSelectedIndex()));
                informationForm.showForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainForm.this, "Для просмотра содержимого необходимо выделить документ");
            }
        });

        exit.addActionListener(actionEvent -> onCancel());
    }

    private void onCancel() {
        dispose();
        System.exit(1);
    }

    public void showMainForm() {
        setSize(700, 400);
        setVisible(true);
    }

    public void refresh() {
        defaultListModel.clear();
        for (Document doc : documents) {
            defaultListModel.addElement(doc.toString());
        }
    }

    public LocalDate parseDate (String date) {
        return  LocalDate.parse(date);
    }

    public Document stringToDoc(String string) {
        String[] fields = string.split("\n|: ");
        switch (fields[1]) {
            case ("Платёжка"):
                return new Payment (
                        parseDate(fields[11]),
                        fields[7],
                        fields[5],
                        fields[3],
                        fields[1],
                        Double.parseDouble(fields[9]));
            case ("Заявка"):
                return new Request (
                        parseDate(fields[17]),
                        fields[5],
                        fields[3],
                        fields[9],
                        fields[7],
                        fields[1],
                        Double.parseDouble(fields[13]),
                        Double.parseDouble(fields[15]),
                        Double.parseDouble(fields[11]));
            case ("Накладная"):
                return new Consignment (
                        parseDate(fields[15]),
                        fields[5],
                        fields[3],
                        fields[1],
                        fields[11],
                        fields[7],
                        Double.parseDouble(fields[13]),
                        Double.parseDouble(fields[9]),
                        Double.parseDouble(fields[15]));
            default: return null;
        }
    }
}
