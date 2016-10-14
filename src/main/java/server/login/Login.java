package server.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import server.model.User;
import server.service.UserService;
import server.test.Test;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Controller
public class Login {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "default value")
                                String name, @RequestParam(value = "passwd", required = false, defaultValue = "default value") String passwd,
                        Model model, HttpServletResponse response) {
//        model.addAttribute("name", name);
        //已经登录
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "main";
//        }
        User user = userService.getUser(name);

        Test test = new Test();
        test.test(name);
        //验证成功
//        if (passwd.equals(user.getPasswd())) {
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(name, passwd));
//            return "main";
//        } else {
//            return "redirect:login.jsp";
//        }

        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(name, passwd));
            return "main";
        } catch (UnknownAccountException e) {
            return "redirect:login.jsp";
        } catch (IncorrectCredentialsException e) {
            return "redirect:login.jsp";
        } catch (ExcessiveAttemptsException e) {
            return "redirect:login.jsp";
        } catch (Exception e) {
            return "redirect:login.jsp";
        }

//        if (passwd.equals(user.getName())) {
//
//            return "main";
//        } else {
//            return "login.jsp";
//        }
//        return "redirect:main.jsp";
//        return "forward:main";
//        return "redirect:/main";
//        return "redirect:test1.html";
    }

    @RequestMapping("/register")
    public Object hello1(@RequestParam(value = "name", required = false, defaultValue = "default value")
                                String name, @RequestParam(value = "passwd", required = false, defaultValue = "default value") String passwd,
                        Model model, HttpServletResponse response) {
        userService.register(name, passwd);
        SecurityUtils.getSubject().login(new UsernamePasswordToken(name, passwd));
//        return "redirect:main.jsp";
//        return new ModelAndView("redirect:main.jsp");
        return "main";
    }
}
