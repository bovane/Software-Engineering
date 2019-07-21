package cn.edu.zjgsu.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录
 * @author Administrator
 *
 */

@Controller
public class LoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping("login.do")
    public String login() {
        return "main/index"; //跳转到main目录下index.jsp页面
    }

    @RequestMapping("bootm.do")
    public String bootm() {
        return "main/bootm"; //跳转到main目录下bootm.jsp页面
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("exit.do")
    public String exit() {
        return "redirect:login.jsp"; //跳回login.jsp页面
    }
}
