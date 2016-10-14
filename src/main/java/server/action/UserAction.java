package server.action;

import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.expression.Expression;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.entity.Role;
import server.model.User;
import server.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */

@Controller
public class UserAction {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/listUser")
    public Object listUser(@RequestParam(value = "page", required = false, defaultValue = "0") int start,
                           @RequestParam(value = "rows", required = false, defaultValue = "10") int length) {
        Map<String, Object> result = new HashMap<>();
        int page = start==0?0:(start/length);
        System.out.println(page);
        Page<User> pageUser = userService.listUser(start-1,length);
//        result.put("recordsTotal", pageUser.getTotalElements());
//        result.put("recordsFiltered", pageUser.getTotalElements());
//        result.put("data", pageUser.getContent());
        result.put("total", pageUser.getTotalPages());
        result.put("records", pageUser.getTotalElements());
        result.put("rows", pageUser.getContent());
        result.put("page", start);
        return result;
    }


    @ResponseBody
    @RequestMapping("/user/update")
    public Object updateUser(@RequestParam(value = "id", required = false, defaultValue = "0") long id,
                           @RequestParam(value = "name") String name,
                             @RequestParam(value="passwd") String passwd) {
        userService.updateUser(id,name);

        return "";

    }

    @ResponseBody
    @RequestMapping("/user/add")
    public Object addUser(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name"));
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("管理员");


        roles.add(role);
        user.setRoles(roles);
        userService.addUser(user);
        return "";
    }

    @ResponseBody
    @RequestMapping("/user/del")
    public Object delUser(HttpServletRequest request) {
        String[] idStr = request.getParameter("id").split(",");
        List<Long> ids = new ArrayList<>();
        Arrays.stream(idStr).forEach(s -> {
            ids.add(Long.valueOf(s));});

        userService.delUser(ids);
        return "";
    }




}
