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

	//最简单的映射，返回网页内容
	//此处控制浏览器里访问路径 具体为：http://localhost:8080/ch13mvc/hello
	@RequestMapping("/h0")
	public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //输出字符串
        response.getWriter().append("Hello world by HelloSpringMVC");
    }
	
	@RequestMapping("/h1")   //http://localhost:8080/ch08spring/h1
    public String sayHello1()
    {
		System.out.println("----- Hello Controller-----");
		/*返回值会通过视图解析器解析为实际的物理视图, 
		 * 对于 InternalResourceViewResolver 视图解析器, 会做如下的解析:	
		 * 通过 prefix(前缀） + returnVal + suffix(后缀)
		 */
        return "hello";
    }
	
	@RequestMapping("/h2")   //http://localhost:8080/ch08spring/h2
    public ModelAndView sayHello2() //Model model
    {
		System.out.println("----- Hello Controller-----");

		ModelAndView mv = new ModelAndView();
		// 为这个模型视图起一个逻辑视图名
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
		
		// 为这个模型视图起一个逻辑视图名
		mv.setViewName("hello");
		
		//添加模型数据 可以是任意的POJO对象
		//增加一个属性到model中,底层是调用了request.setAttribute()方法
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
