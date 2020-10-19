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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Server extends JFrame implements ActionListener {

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


  // SocketServer Port
  static int port = 9000;
  static DataInputStream dis;
  static DataOutputStream dos;
  // ServerSocket Variable
  static ServerSocket serverSocket;
  static Socket socket;
  public Server() throws HeadlessException {

    // Initialize GUI Components
    label = new JLabel("Server");
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
    this.setTitle("Server Chat");
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

      try {

        // Set Server Message
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = dateFormat.format(date);

        String serverMessage = time + " Server: " +textField.getText() + "\n";
        dos.writeUTF(serverMessage);
        //textArea.append("\n");
        textArea.append(serverMessage);



      } catch (IOException ioException) {
        ioException.printStackTrace();
      }

      // Clear TextField
      textField.setText("");

    }
    // Exit Button
    if(e.getSource() == exitBtn){
      System.exit(0);
      try {
        serverSocket.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

  }

  public static void main(String[] args)  {

    // Server Class Object
    Server server = new Server();

    try{
      //ServerSocket Object
      serverSocket = new ServerSocket(port);
      System.out.println("Server is starting!");
      socket = serverSocket.accept();
      // Create IO Streams
      dos = new DataOutputStream(socket.getOutputStream());
      dis = new DataInputStream(socket.getInputStream());

      while (true){

        // Get Client Message
        String input  = dis.readUTF();
        textArea.append(input);

      }

    }catch (IOException ex){
      ex.printStackTrace();
    }
  }


}
