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
			System.out.println("port = 12000 (默认)");
			port = 12000; // 默认端口为12000
		}

		try {
			server = new ServerSocket(port);
			System.out.println("服务器正在监听端口：" + server.getLocalPort());
	
			while (true) {
				try {
					System.out.println("等待客户呼叫");
					socket = server.accept();
					System.out.println("客户的地址：" + socket.getInetAddress()
					    + ":" + socket.getPort());
	
				} catch (IOException e) {
					System.out.println("正在等待客户");
				}
	
				if (socket != null) {
					new ServerThread(socket).start();
				}
				
			}
			
		} catch (IOException e1) {
			System.out.println("正在监听");
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
				
				s = "长"+s1+"宽"+s2+"的长方体的面积"+area;
				System.out.println(s);
				out.writeUTF(s);
				
			}
			catch (IOException e){
				System.out.println("客户离开");
				return;
			}
		}
	}
}

