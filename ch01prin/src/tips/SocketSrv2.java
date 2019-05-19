package tips;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketSrv2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		int port;
		ServerThread thread;
		Socket socket = null;

		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("port = 12000 (Ĭ��)");
			port = 12000; // Ĭ�϶˿�Ϊ12000
		}

		try {
			server = new ServerSocket(port);
			System.out.println("���������ڼ����˿ڣ�" + server.getLocalPort());
	
			while (true) {
				try {
					System.out.println("�ȴ��ͻ�����");
					socket = server.accept();
					System.out.println("�ͻ��ĵ�ַ��" + socket.getInetAddress()
					    + ":" + socket.getPort());
	
				} catch (IOException e) {
					System.out.println("���ڵȴ��ͻ�");
				}
	
				if (socket != null) {
					new ServerThread(socket).start();
				}
				
			}
			
		} catch (IOException e1) {
			System.out.println("���ڼ���");
		}

	}

}


class ServerThread extends Thread{
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	String s;
	ServerThread(Socket t){
		socket = t;
		try{
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
		}catch(IOException e){}
		
	}
	public void run()
	{
		while(true){
			try{
				double s1 = in.readDouble();
				double s2 = in.readDouble();
				double area = s1*s2;
				
				s = "��"+s1+"��"+s2+"�ĳ���������"+area;
				System.out.println(s);
				out.writeUTF(s);
				
			}
			catch (IOException e){
				System.out.println("�ͻ��뿪");
				return;
			}
		}
	}
}

