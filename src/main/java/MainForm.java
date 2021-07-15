import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        setContentPane(contentPane);
        pack();
        listOfItems.setModel(defaultListModel);
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
            int result = fileChooser.showSaveDialog(MainForm.this);
            try {
                documents.get(listOfItems.getSelectedIndex()).toStringField();
                File file = fileChooser.getSelectedFile();
                if (result == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = new File(file + ".txt");
                    if (fileToSave.exists()) {
                        int option = JOptionPane.showConfirmDialog(MainForm.this, "Перезаписать файл", "Файл с таким названием уже существует, перезаписать файл?", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (option == 0) {
                            PrintWriter pw = new PrintWriter(new FileWriter(fileToSave));
                            pw.println(documents.get(listOfItems.getSelectedIndex()).toStringField());
                            pw.close();
                        }
                    } else {
                        try (PrintWriter pw = new PrintWriter(new FileWriter(fileToSave))) {
                            pw.println(documents.get(listOfItems.getSelectedIndex()).toStringField());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainForm.this, "Не удалось сохранить документ");
            }
        });

        loadDocument.addActionListener(actionEvent -> {
            try {
                FileFilter fileFilter = new FileNameExtensionFilter("Notepad file", "txt");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setFileFilter(fileFilter);
                fileChooser.showOpenDialog(MainForm.this);
                File file = fileChooser.getSelectedFile();
                documents.add(stringToDoc(Files.readString(file.toPath(), StandardCharsets.UTF_8)));
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

    public Document stringToDoc(String string) {
        return Document.parseDoc(string);
    }
}
