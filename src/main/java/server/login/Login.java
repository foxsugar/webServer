package server.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.service.UserService;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Controller
public class Login {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "default value")
            String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("00000000000000000");
        userService.test();
        return "index";
    }
}
