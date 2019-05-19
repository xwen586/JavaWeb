package tips;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketSrv1 {

	public static void main(String[] args) throws InterruptedException {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
		InputStream in = null; // 输入流
		OutputStream out = null; // 输出流

		try {
			ServerSocket ss = new ServerSocket(5888);// 创建ServerSocket对象，绑定监听端口
			System.out.println(sf.format(new Date()) + " 服务器正在监听端口：" + ss.getLocalPort());

			Socket socket = ss.accept();// 接受来自客户端的请求
			System.out.println(sf.format(new Date()) + " 收到客户端连接：" + socket.getInetAddress() + ":" + socket.getPort());

			in = socket.getInputStream();// 得到来自客户端写入的数据,获得输入流
		    Thread.sleep(500);  //睡眠500毫秒，等待HTTP请求  
			
			DataInputStream dis = new DataInputStream(in);
			String s = null;// 定义从客户端读出的字符串

			// 如果读出的不为空的话。向客户端发出本机的ip地址和连接的端口号
			if ((s = dis.readUTF()) != null) {
				System.out.println(sf.format(new Date()) + " 客户端信息：" + s);
				//System.out.println("from: " + socket.getInetAddress());
				//System.out.println("port: " + socket.getPort());
			}
			
			out = socket.getOutputStream();// 服务器端输出流对象
			DataOutputStream dos = new DataOutputStream(out);
			
			s = "hello success! Wellcome!";
			System.out.println(sf.format(new Date()) + " 返回信息：" + s);
			dos.writeUTF(s);// 向客户端写入

			System.out.println(sf.format(new Date()) + " 关闭连接。");
			dis.close();// 关闭流对象
			dos.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
