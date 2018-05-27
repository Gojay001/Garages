package xin.gojay.nmid.service.impl;

import org.springframework.stereotype.Service;
import xin.gojay.nmid.dao.UserDao;
import xin.gojay.nmid.entity.User;
import xin.gojay.nmid.service.UserService;
import xin.gojay.nmid.util.DateUtil;
import xin.gojay.nmid.util.ResponseUtil;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * @author Gojay
 * @date 2018/5/22
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    private ResponseUtil responseUtil;

    @Override
    public ResponseUtil signIn(String username, String password) {
        if (username == null || "".equals(username)) {
            responseUtil = new ResponseUtil(400, "ERROR#用户名有误");
            return responseUtil;
        }
        if (password == null || "".equals(password)) {
            responseUtil = new ResponseUtil(400, "ERROR#用户密码有误");
            return responseUtil;
        }

        // 匹配用户名和用户密码
        User user = userDao.signIn(username, password);
        if (user == null) {
            responseUtil = new ResponseUtil(400, "ERROR#用户名或密码有误");
            return responseUtil;
        }
        responseUtil = new ResponseUtil(200, "OK#成功返回");
        responseUtil.setBody(user);
        return responseUtil;
    }

    @Override
    public ResponseUtil countTraffic(String dateString) {
        // 将日期字符串转为java.sql.Date
        Date date = DateUtil.stringToDate(dateString);
        if (date == null) {
            responseUtil = new ResponseUtil(400, "ERROR#日期格式有误");
            return responseUtil;
        }
        int total = userDao.countTraffic(date);
        if (total == 0) {
            responseUtil = new ResponseUtil(500, "ERROR#查询失败");
            return responseUtil;
        }
        responseUtil = new ResponseUtil(200, "OK#成功返回");
        responseUtil.setBody("total = " + total);
        return responseUtil;
    }
}
