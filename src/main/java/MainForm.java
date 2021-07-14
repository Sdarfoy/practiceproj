import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
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
    private final DefaultListModel<String> saveDefaultListModel = new DefaultListModel<>();
    private JList<String> listOfItems;
    private final List<Document> documents;

    public MainForm(List<Document> documents) {
        this.documents = documents;
        consignmentForm = new ConsignmentForm(this, documents);
        paymentForm = new PaymentForm(this, documents);
        requestForm = new RequestForm(this, documents);
        fileChooser = new JFileChooser("/home/shmelev_ne");
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

        payment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                paymentForm.showForm();
            }
        });
        consignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                consignmentForm.showForm();
            }
        });
        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requestForm.showForm();
            }
        });
        saveDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFilter fileFilter = new FileNameExtensionFilter("Notepad file", "txt");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.showSaveDialog(MainForm.this);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                File file = fileChooser.getSelectedFile();
                try {
                    File fileToSave = new File(file.getAbsolutePath() + ".txt");
                    try (PrintWriter pw = new PrintWriter(new FileWriter(fileToSave))) {
                        pw.println(documents.get(listOfItems.getSelectedIndex()).toStringField());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        viewDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InformationForm informationForm = new InformationForm(documents.get(listOfItems.getSelectedIndex()));
                informationForm.showForm();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onCancel();
            }
        });
        loadDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
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

    public void fileIntoDoc(String string) {
        String[] itemArray = string.split(System.lineSeparator());
        String[] fieldsArray = new String[itemArray.length];
        for (String fields:itemArray) {
        }
    }
}
