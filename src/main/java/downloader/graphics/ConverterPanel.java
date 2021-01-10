package downloader.graphics;

import downloader.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

public class ConverterPanel extends JPanel implements ActionListener {
    private final JButton jButtonConvert;
    private final JComboBox<String> jComboBoxFrom;
    private final JComboBox<String> jComboBoxTo;
    private final JTextField jTextFieldInput;
    private final JLabel jLabelFrom, jLabelTo,jLabelBackground;
    private final JLabel jLabelTopRes, jLabelBotRes;
    private final JLabel jLabelByNBP;

    private JPanel jPanelStats;

    private final Calculator calculator = new Calculator();


    ConverterPanel() throws IOException {
        //button
        this.jButtonConvert = new JButton();
        this.jButtonConvert.setText("convert");
        this.jButtonConvert.addActionListener(this);
        this.jButtonConvert.setBounds(100,60,90,20);
        //comboBoxes
        String[] currencies = calculator.getCodes();
        this.jComboBoxFrom = new JComboBox<>(currencies);
        this.jComboBoxFrom.addActionListener(this);
        this.jComboBoxFrom.setBounds(20,20,60,20);

        this.jComboBoxTo = new JComboBox<>(currencies);
        this.jComboBoxTo.addActionListener(this);
        this.jComboBoxTo.setBounds(120,20,60,20);

        //textField
        this.jTextFieldInput = new JTextField("20");
        this.jTextFieldInput.setBounds(20,60,60,20);

        //Labels from,to
        this.jLabelFrom = new JLabel("From:");
        this.jLabelFrom.setBounds(25,0,60,20);
        this.jLabelFrom.setFont(new Font("Consolas", Font.BOLD, 14));
        this.jLabelFrom.setForeground(Color.WHITE);
        this.jLabelFrom.setBackground(null);

        this.jLabelTo = new JLabel("To:");
        this.jLabelTo.setBounds(125,0,60,20);
        this.jLabelTo.setFont(new Font("Consolas", Font.BOLD, 14));
        this.jLabelTo.setForeground(Color.WHITE);
        this.jLabelTo.setBackground(null);

        //Labels from,to
        this.jLabelTopRes = new JLabel("",SwingConstants.CENTER);
        this.jLabelTopRes.setBounds(20,100,150,40);
        this.jLabelTopRes.setFont(new Font("Consolas", Font.BOLD, 12));
        this.jLabelTopRes.setForeground(Color.WHITE);
        this.jLabelTopRes.setBackground(null);

        this.jLabelBotRes = new JLabel("",SwingConstants.CENTER);
        this.jLabelBotRes.setBounds(20,140,150,40);
        this.jLabelBotRes.setFont(new Font("Consolas", Font.BOLD, 22));
        this.jLabelBotRes.setForeground(Color.WHITE);
        this.jLabelBotRes.setBackground(null);

        this.jLabelByNBP = new JLabel("from https://www.nbp.pl",SwingConstants.CENTER);
        this.jLabelByNBP.setBounds(20,180,150,20);
        this.jLabelByNBP.setFont(new Font("Consolas", Font.BOLD, 8));
        this.jLabelByNBP.setForeground(Color.WHITE);
        this.jLabelByNBP.setBackground(null);

        //panel with stats
        this.jPanelStats = new ImagePanel(calculator,jComboBoxTo.getSelectedItem().toString());
        this.jPanelStats.setBounds(200,0,400,240);

        //this.jPanelStats.setVisible(true);

        //frame properties
        jLabelBackground = new JLabel(new ImageIcon("background.png"));
        jLabelBackground.setBounds(0,0,600,240);

        setLayout(null);
        add(jButtonConvert);
        add(jComboBoxFrom);
        add(jComboBoxTo);
        add(jTextFieldInput);
        add(jLabelFrom);
        add(jLabelTo);
        add(jLabelBotRes);
        add(jLabelTopRes);
        add(jPanelStats);
        add(jLabelByNBP);
        add(jLabelBackground);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(jButtonConvert)){
            String codeFrom = Objects.requireNonNull(jComboBoxFrom.getSelectedItem()).toString();
            String codeTo = Objects.requireNonNull(jComboBoxTo.getSelectedItem()).toString();
            double inputValue = 100.0;
            try {
                inputValue = Double.parseDouble(jTextFieldInput.getText());
            } catch (NumberFormatException ignored){}
            jLabelTopRes.setText(inputValue + " " + codeFrom + " =");
            jLabelBotRes.setText(calculator.doExchange(inputValue, codeFrom, codeTo,3)
                    + " " + codeTo);

            ArrayList<Double> value = new ArrayList<>();
            for (int i =0;i<3;i++) {
                value.add(calculator.doExchange(inputValue, codeFrom, codeTo, i));
            }
            for (int i =0;i<3;i++){
                ((JLabel) this.jPanelStats.getComponent(12+i)).setText(String.valueOf(value.get(i)));
                this.jPanelStats.getComponent(12+i).setForeground(Color.WHITE);
                if(Collections.max(value).equals(value.get(i))){
                    this.jPanelStats.getComponent(12+i).setForeground(Color.GREEN);
                }
            }
        }else if(e.getSource().equals(jComboBoxFrom)){
            String code = Objects.requireNonNull(jComboBoxFrom.getSelectedItem()).toString();

            for (int i =0;i<3;i++){
                double sellCost = calculator.getCurrency(code,i).getBuyingRate();
                ((JLabel) this.jPanelStats.getComponent(5+2*i)).setText(String.valueOf(sellCost));
            }

        }else if(e.getSource().equals(jComboBoxTo)){
            String code = Objects.requireNonNull(jComboBoxTo.getSelectedItem()).toString();

            for (int i =0;i<3;i++){
                double sellCost = calculator.getCurrency(code,i).getSellingRate();
                ((JLabel) this.jPanelStats.getComponent(6+2*i)).setText(String.valueOf(sellCost));
            }
        }
    }
}
