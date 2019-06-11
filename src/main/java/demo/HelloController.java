package demo;

import com.jfinal.core.Controller;

/**
 * @Description:  JFinal 框架学习
 * @author: daimin
 * @date: Create in 11:01 2019/4/26
 */
public class HelloController extends Controller {

    public void index() {
        renderText("Hello, JFinal!");
    }

    public void myText() {
       renderText("This is my Text!");
    }
}
