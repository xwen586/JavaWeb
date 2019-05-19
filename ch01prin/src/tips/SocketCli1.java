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
			
			System.out.println(sf.format(new Date()) + " ����������");
			//����Socket����ָ����Ҫ���ӵķ������ĵ�ַ�Ͷ˿ں�
			Socket socket = new Socket("localhost", 5888);
			
			s = "Hello! server.. ";
			System.out.println(sf.format(new Date()) + " �����˷�����Ϣ��" + s);
			os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(s);// ���ӽ�����ͨ���������������˷���������Ϣ
			
			
			System.out.println(sf.format(new Date()) + " �ȴ��������Ϣ...");
			is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			if ((s = dis.readUTF()) != null) {
				System.out.println(sf.format(new Date()) + s);// ͨ����������ȡ��������Ӧ����Ϣ
			}
			
			System.out.println(sf.format(new Date()) + " �ر����ӡ�");
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
