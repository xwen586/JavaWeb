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
		InputStream in = null; // ������
		OutputStream out = null; // �����

		try {
			ServerSocket ss = new ServerSocket(5888);// ����ServerSocket���󣬰󶨼����˿�
			System.out.println(sf.format(new Date()) + " ���������ڼ����˿ڣ�" + ss.getLocalPort());

			Socket socket = ss.accept();// �������Կͻ��˵�����
			System.out.println(sf.format(new Date()) + " �յ��ͻ������ӣ�" + socket.getInetAddress() + ":" + socket.getPort());

			in = socket.getInputStream();// �õ����Կͻ���д�������,���������
		    Thread.sleep(500);  //˯��500���룬�ȴ�HTTP����  
			
			DataInputStream dis = new DataInputStream(in);
			String s = null;// ����ӿͻ��˶������ַ���

			// ��������Ĳ�Ϊ�յĻ�����ͻ��˷���������ip��ַ�����ӵĶ˿ں�
			if ((s = dis.readUTF()) != null) {
				System.out.println(sf.format(new Date()) + " �ͻ�����Ϣ��" + s);
				//System.out.println("from: " + socket.getInetAddress());
				//System.out.println("port: " + socket.getPort());
			}
			
			out = socket.getOutputStream();// �����������������
			DataOutputStream dos = new DataOutputStream(out);
			
			s = "hello success! Wellcome!";
			System.out.println(sf.format(new Date()) + " ������Ϣ��" + s);
			dos.writeUTF(s);// ��ͻ���д��

			System.out.println(sf.format(new Date()) + " �ر����ӡ�");
			dis.close();// �ر�������
			dos.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
