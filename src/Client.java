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
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends JFrame implements ActionListener {

  // Create GUI Components
  static JLabel label;
  static JTextField textField;
  static JTextArea textArea;
  static JScrollPane scrollPane;
  static JButton sendBtn;
  static JButton exitBtn;
  static JPanel panel1;
  static JPanel panel2;
  static JPanel panel3;
  static JPanel panel4;
  static JPanel panel5;

  // IO Streams
  static BufferedReader input;
  static PrintWriter output;
  static DataInputStream dis;
  static DataOutputStream dos;
  static int port = 9000;
  static Socket socket;

  static String clientMessage = "";
  static String serverMessage = "";

  public Client() {

    // Initialize GUI Components
    label = new JLabel("Client");
    textField = new JTextField();
    textArea = new JTextArea(40,15);
    scrollPane = new JScrollPane(textArea);
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

    panel2.add(scrollPane);
    panel1.setLayout(flowLayout);
    panel1.add(exitBtn);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(650,400);
    this.setTitle("Client Chat");
    this.setLocationRelativeTo(null);

    GridLayout gridLayout = new GridLayout(2,1);
    this.setLayout(gridLayout);
    this.add(panel2);
    this.add(panel1);

  }



  @Override
  public void actionPerformed(ActionEvent e) {
    // Send Button
    if(e.getSource() == sendBtn){

      try{

          Date date = new Date();
          SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
          String time = dateFormat.format(date);

          clientMessage = time + " Client: " +textField.getText() + "\n";

          dos.writeUTF(clientMessage);
          //textArea.append("\n");
          textArea.append(clientMessage);

          // Clear TextField
          textField.setText("");



      }catch (Exception ex){
        ex.printStackTrace();
      }
    }
    // Exit Button
    if(e.getSource() == exitBtn){
      System.exit(0);
      try {
        socket.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
      Client client = new Client();

    try {
      socket = new Socket("localhost",9000);
      // Create IO Streams
      dos = new DataOutputStream(socket.getOutputStream());

      dis = new DataInputStream(socket.getInputStream());
      while (true){

        String serverMessage = dis.readUTF();
        textArea.append(serverMessage);
      }
      //textArea.append(serverMessage);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
