package demo1;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//http://www.cnblogs.com/scofi/articles/5885109.html

@Controller
public class HelloWorld {
	//��򵥵�ӳ�䣬������ҳ����
	//�˴���������������·�� ����Ϊ��http://localhost:8080/ch13mvc/hello
	@RequestMapping("/h01")
	public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //����ַ���
        response.getWriter().append("Hello world by HelloWorld��");
    }
}
