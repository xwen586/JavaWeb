package demo1;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//http://www.cnblogs.com/scofi/articles/5885109.html

@Controller
public class HelloWorld {
	//最简单的映射，返回网页内容
	//此处控制浏览器里访问路径 具体为：http://localhost:8080/ch13mvc/hello
	@RequestMapping("/h01")
	public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //输出字符串
        response.getWriter().append("Hello world by HelloWorld！");
    }
}
