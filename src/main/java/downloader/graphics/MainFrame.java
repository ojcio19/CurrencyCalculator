package downloader.graphics;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    public MainFrame(){
        super("Converter");
        try {
            JPanel converterPanel = new ConverterPanel();
            add(converterPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 240);
            setResizable(false);
            setVisible(true);
        }catch (IOException e){
            e.printStackTrace();
        }
        //ImageIcon imageIcon = new ImageIcon("test.png");
        //setIconImage(imageIcon.getImage());
    }
}
