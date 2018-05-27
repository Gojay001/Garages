package xin.gojay.nmid.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.gojay.nmid.service.PortService;

import javax.annotation.Resource;

/**
 * @author Gojay
 * @date 2018/5/10
 */
@Controller
public class TestController {
    @Resource
    private PortService portService;

    @RequestMapping(value = "api/test/{message}", method = RequestMethod.GET)
    @ResponseBody
    public int createUser(@PathVariable("message") String message) {
        return 0;
    }
}
