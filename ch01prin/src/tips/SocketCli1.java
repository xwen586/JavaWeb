package tips;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketCli1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
		InputStream is = null;
		OutputStream os = null;
		String s = null;

		try {
			
			System.out.println(sf.format(new Date()) + " 向服务端连接");
			//创建Socket对象，指明需要连接的服务器的地址和端口号
			Socket socket = new Socket("localhost", 5888);
			
			s = "Hello! server.. ";
			System.out.println(sf.format(new Date()) + " 向服务端发送信息：" + s);
			os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(s);// 连接建立后，通过输出流向服务器端发送请求信息
			
			
			System.out.println(sf.format(new Date()) + " 等待服务端信息...");
			is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			if ((s = dis.readUTF()) != null) {
				System.out.println(sf.format(new Date()) + s);// 通过输入流获取服务器响应的信息
			}
			
			System.out.println(sf.format(new Date()) + " 关闭连接。");
			dos.close();
			dis.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
