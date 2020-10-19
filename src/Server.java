/*
  Pearson Pretoria
  BSC IT Level 3 2020
  ITJA321 - Take Home Assessment
  Question 4
  Gullian Van Der Walt - H5G8YT7X3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends JFrame implements ActionListener {

  // Create GUI Components
  static JLabel label;
  static JTextField textField;
  static JTextArea textArea;
  static JButton sendBtn;
  static JButton exitBtn;
  static JPanel panel1;
  static JPanel panel2;
  static JPanel panel3;
  static JPanel panel4;
  static JPanel panel5;


  public Server() throws HeadlessException {

    // Initialize GUI Components
    label = new JLabel("Server");
    textField = new JTextField();
    textArea = new JTextArea(40,15);
    sendBtn = new JButton("Send");
    exitBtn = new JButton("Exit");
    panel1 = new JPanel();
    panel2 = new JPanel();
    panel3 = new JPanel();
    panel4 = new JPanel();
    panel5 = new JPanel();

    textField.setColumns(15);

    // Add ActionListeners
    sendBtn.addActionListener(this);
    exitBtn.addActionListener(this);
FlowLayout flowLayout = new FlowLayout();
flowLayout.setHgap(10);
    panel5.add(label);
    panel4.setLayout(flowLayout);
    panel4.add(textField);
    panel4.add(sendBtn);
    FlowLayout fLayout = new FlowLayout();
    panel3.setLayout(flowLayout);
    panel3.add(panel5);
    panel3.add(panel4);
    panel2.setLayout(new GridLayout(1,2));
    panel2.add(panel3);

    panel2.add(textArea);
    panel1.setLayout(flowLayout);
    panel1.add(exitBtn);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(650,400);
    this.setTitle("Server Chat");
    this.setLocationRelativeTo(null);

    GridLayout gridLayout = new GridLayout(2,1);
    this.setLayout(gridLayout);
    this.add(panel2);
    this.add(panel1);


  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  public static void main(String[] args){
    Server server = new Server();
  }

}
