package xin.gojay.nmid.service;

import xin.gojay.nmid.util.ResponseUtil;

/**
 * @author Gojay
 * @date 2018/5/22
 */
public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 响应消息
     */
    ResponseUtil signIn(String username, String password);

    /**
     * 统计指定日期车库车流量
     * @param dateString 查询日期字符串
     * @return 响应消息
     */
    ResponseUtil countTraffic(String dateString);
}
