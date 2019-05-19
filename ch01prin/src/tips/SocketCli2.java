package tips;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.*;

public class SocketCli2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new myWindows();
	}

}

class myWindows extends JFrame implements Runnable, ActionListener {
	JButton connection, send;
	JTextField inputText1;
	JTextField inputText2;
	JTextArea showResult;
	Socket socket;
	DataInputStream in; // 数据输入流
	DataOutputStream out;
	Thread thread;

	public myWindows() {
		socket = new Socket();
		connection = new JButton("连接服务器");
		send = new JButton("发送");
		send.setEnabled(false);

		inputText1 = new JTextField(6);
		inputText2 = new JTextField(6);
		showResult = new JTextArea();
		add(connection, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.add(new JLabel("请输入长方形的长和宽："));
		p.add(inputText1);
		p.add(inputText2);
		p.add(send);
		add(new JScrollPane(showResult), BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		connection.addActionListener(this);
		send.addActionListener(this);
		thread = new Thread();
		setBounds(10, 30, 460, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connection) {
			try {
				if (socket.isConnected()) {
				} else {
					InetAddress address = InetAddress.getByName("localhost");
					InetSocketAddress socketAddress = new InetSocketAddress(address, 12000);
					socket.connect(socketAddress);
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
					send.setEnabled(true);
					if (!(thread.isAlive()))
						thread = new Thread(this);
					thread.start();
				}
			} catch (IOException ee) {
				System.out.println(ee);
				socket = new Socket();
			}
		}
		if (e.getSource() == send) {
			String s1 = inputText1.getText();
			String s2 = inputText2.getText();
			double r1 = Double.parseDouble(s1);
			double r2 = Double.parseDouble(s2);
			try {
				out.writeDouble(r1);
				out.writeDouble(r2);
			}

			catch (IOException e1) {
			}

		}

	}

	public void run() {
		String s;
		double result = 0;
		while (true) {
			try {
				s = in.readUTF();
				showResult.append("\n" + s);

			} catch (IOException e) {
				showResult.setText("与服务器已断开" + e);
				socket = new Socket();
				break;
			}
		}
	}

}
