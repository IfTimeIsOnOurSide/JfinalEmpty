package demo;

import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

/**
 * @Description: 配置
 * @author: daimin
 * @date: Create in 10:57 2019/4/26
 */
public class DemoConfig extends JFinalConfig {
    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 8080, true);
    }

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }
    @Override
    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
    }

    @Override
    public void configEngine(Engine me) {}
    @Override
    public void configPlugin(Plugins me) {}
    @Override
    public void configInterceptor(Interceptors me) {}
    @Override
    public void configHandler(Handlers me) {}
}
