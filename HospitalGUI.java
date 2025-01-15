import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class HospitalGUI {
    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> priorityBox;
    private JTextArea outputArea;

    public HospitalGUI() {
        // code here
    }

    public static void main(String[] args) {
        new HospitalGUI();
    }
}
