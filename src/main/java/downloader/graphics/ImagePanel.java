package downloader.graphics;
import downloader.Calculator;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel{

    public JLabel aliorBankImage,mBankImage,nestBankImage;
    private JLabel jLabelSelling,jLabelBuying,titleTotalValue;
    private JLabel aliorBankSelling,aliorBankBuying;
    private JLabel mBankSelling,mBankBuying;
    private JLabel nestBankSelling,nestBankBuying;
    private JLabel jLabelBackground;
    private JLabel aliorBankValue,mBankValue,nestBankValue;
    Calculator calculator;

    public ImagePanel(Calculator calculator, String code) {
        this.calculator = calculator;
        aliorBankImage = new JLabel(new ImageIcon("aliorBank.png"));
        aliorBankImage.setBounds(0,20,100,45);
        mBankImage = new JLabel(new ImageIcon("mBank.png"));
        mBankImage.setBounds(0,80,100,45);
        nestBankImage = new JLabel(new ImageIcon("nestBank.png"));
        nestBankImage.setBounds(0,140,100,45);

        //titles
        jLabelSelling = new JLabel("Sell",SwingConstants.CENTER);
        jLabelSelling.setBounds(120,0,70,20);
        jLabelSelling.setFont(new Font("Consolas", Font.BOLD, 12));
        jLabelSelling.setForeground(Color.WHITE);
        jLabelBuying = new JLabel("Buy",SwingConstants.CENTER);
        jLabelBuying.setBounds(200,0,70,20);
        jLabelBuying.setFont(new Font("Consolas", Font.BOLD, 12));
        jLabelBuying.setForeground(Color.WHITE);
        titleTotalValue = new JLabel("Best",SwingConstants.CENTER);
        titleTotalValue.setBounds(280,0,70,20);
        titleTotalValue.setFont(new Font("Consolas", Font.BOLD, 12));
        titleTotalValue.setForeground(Color.WHITE);

        //seeling and buying costs
        aliorBankSelling = new JLabel("1.0",SwingConstants.CENTER);
        aliorBankSelling.setBounds(120,30,70,20);
        aliorBankSelling.setFont(new Font("Consolas", Font.BOLD, 16));
        aliorBankSelling.setForeground(Color.WHITE);
        aliorBankBuying = new JLabel("1.0",SwingConstants.CENTER);
        aliorBankBuying.setBounds(200,30,70,20);
        aliorBankBuying.setFont(new Font("Consolas", Font.BOLD, 16));
        aliorBankBuying.setForeground(Color.WHITE);
        aliorBankValue = new JLabel("1.0",SwingConstants.CENTER);
        aliorBankValue.setBounds(280,30,70,20);
        aliorBankValue.setFont(new Font("Consolas", Font.BOLD, 16));
        aliorBankValue.setForeground(Color.WHITE);


        mBankSelling = new JLabel("1.0",SwingConstants.CENTER);
        mBankSelling.setBounds(120,90,70,20);
        mBankSelling.setFont(new Font("Consolas", Font.BOLD, 16));
        mBankSelling.setForeground(Color.WHITE);
        mBankBuying = new JLabel("1.0",SwingConstants.CENTER);
        mBankBuying.setBounds(200,90,70,20);
        mBankBuying.setFont(new Font("Consolas", Font.BOLD, 16));
        mBankBuying.setForeground(Color.WHITE);
        mBankValue = new JLabel("1.0",SwingConstants.CENTER);
        mBankValue.setBounds(280,90,70,20);
        mBankValue.setFont(new Font("Consolas", Font.BOLD, 16));
        mBankValue.setForeground(Color.WHITE);

        nestBankSelling = new JLabel("1.0",SwingConstants.CENTER);
        nestBankSelling.setBounds(120,150,70,20);
        nestBankSelling.setFont(new Font("Consolas", Font.BOLD, 16));
        nestBankSelling.setForeground(Color.WHITE);
        nestBankBuying = new JLabel("1.0",SwingConstants.CENTER);
        nestBankBuying.setBounds(200,150,70,20);
        nestBankBuying.setFont(new Font("Consolas", Font.BOLD, 16));
        nestBankBuying.setForeground(Color.WHITE);
        nestBankValue = new JLabel("1.0",SwingConstants.CENTER);
        nestBankValue.setBounds(280,150,70,20);
        nestBankValue.setFont(new Font("Consolas", Font.BOLD, 16));
        nestBankValue.setForeground(Color.WHITE);

        //background
        jLabelBackground = new JLabel(new ImageIcon("background2.png"));
        jLabelBackground.setBounds(0,0,400,240);
        setSize(400, 240);
        setLayout(null);
        setBackground(null);
        add(aliorBankImage);
        add(mBankImage);
        add(nestBankImage);

        add(jLabelSelling);
        add(jLabelBuying);

        add(aliorBankSelling);
        add(aliorBankBuying);
        add(mBankSelling);
        add(mBankBuying);
        add(nestBankSelling);
        add(nestBankBuying);

        add(titleTotalValue);
        add(aliorBankValue);//12
        add(mBankValue);
        add(nestBankValue);

        add(jLabelBackground);
    }
}
