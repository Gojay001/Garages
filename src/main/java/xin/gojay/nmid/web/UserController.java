package xin.gojay.nmid.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.gojay.nmid.service.UserService;
import xin.gojay.nmid.util.ResponseUtil;

import javax.annotation.Resource;

/**
 * @author Gojay
 * @date 2018/5/22
 */
@Controller
@RequestMapping("api/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil signIn(@Param("username") String username,
                               @Param("password") String password) {
        return userService.signIn(username, password);
    }

    @RequestMapping(value = "/countTraffic/{date}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil countTraffic(@PathVariable String date) {
        return userService.countTraffic(date);
    }
}
