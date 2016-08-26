package net.blissmall.puff;
/*

                      _oo0oo_
                     o8888888o
                     88" . "88
                     (| -_- |)
                     0\  =  /0
                   ___/`---'\___
                 .' \\|     |// '.
                / \\|||  :  |||// \
               / _||||| -:- |||||- \
              |   | \\\  -  /// |   |
              | \_|  ''\---/''  |_/ |
              \  .-\__  '-'  ___/-. /
            ___'. .'  /--.--\  `. .'___
         ."" '<  `.___\_<|>_/___.' >' "".
        | | :  `- \`.;`\ _ /`;.`/ - ` : | |
        \  \ `_.   \_ __\ /__ _/   .-` /  /
    =====`-.____`.___ \_____/___.-`___.-'=====
                      `=---='


    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

              佛祖保佑         永无BUG
*/

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * the entrance of the whole application
 * @Author : pigo
 * @Date : 16/4/13 下午10:58
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@SpringBootApplication(scanBasePackages = {"net.blissmall.puff.core","net.blissmall.puff.service","net.blissmall.puff.web"},
        exclude={DataSourceAutoConfiguration.class, ThymeleafAutoConfiguration.class})
@EnableCaching(proxyTargetClass = true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@ServletComponentScan(basePackages = {"net.blissmall.puff.web.j2ee"})

public class PuffBootApplication implements CommandLineRunner {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(PuffBootApplication.class);
         /*
          * Disabling restart
          */
//        System.setProperty("context.devtools.restart.enabled", "false");

        // 取消banner启动显示
//        app.setBannerMode(Banner.Mode.OFF);

        // 控制web特性
//        app.setWebEnvironment(false);

//  设置环境变量
//        app.setAdditionalProfiles("dev");

        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==========>       the puff has been started         <============");
    }
}
