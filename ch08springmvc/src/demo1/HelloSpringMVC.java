package demo1;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//https://www.cnblogs.com/pcmcnblogs/p/6088777.html

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloSpringMVC {

	//��򵥵�ӳ�䣬������ҳ����
	//�˴���������������·�� ����Ϊ��http://localhost:8080/ch13mvc/hello
	@RequestMapping("/h0")
	public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //����ַ���
        response.getWriter().append("Hello world by HelloSpringMVC");
    }
	
	@RequestMapping("/h1")   //http://localhost:8080/ch08spring/h1
    public String sayHello1()
    {
		System.out.println("----- Hello Controller-----");
		/*����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ, 
		 * ���� InternalResourceViewResolver ��ͼ������, �������µĽ���:	
		 * ͨ�� prefix(ǰ׺�� + returnVal + suffix(��׺)
		 */
        return "hello";
    }
	
	@RequestMapping("/h2")   //http://localhost:8080/ch08spring/h2
    public ModelAndView sayHello2() //Model model
    {
		System.out.println("----- Hello Controller-----");

		ModelAndView mv = new ModelAndView();
		// Ϊ���ģ����ͼ��һ���߼���ͼ��
		mv.setViewName("hello");
        mv.addObject("msg","oooooooooooooooOK!");
        return mv;
    }
}

//https://blog.csdn.net/luo4105/article/details/72325022
/*
public class HelloSpringMVC implements Controller {
	
	//@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----- Hello Controller-----");
		ModelAndView mv = new ModelAndView();
		
		// Ϊ���ģ����ͼ��һ���߼���ͼ��
		mv.setViewName("hello");
		
		//���ģ������ �����������POJO����
		//����һ�����Ե�model��,�ײ��ǵ�����request.setAttribute()����
		mv.addObject("msg", "hello page");
		return mv;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

}*/
