package xin.gojay.nmid.dao;

import org.apache.ibatis.annotations.Param;
import xin.gojay.nmid.entity.User;

import java.sql.Date;

/**
 * @author Gojay
 * @date 2018/5/23
 */
public interface UserDao {
    /**
     * 登录验证，查询用户名密码是否存在且匹配
     * @param username 用户名
     * @param password 用户密码
     * @return 用户信息
     */
    User signIn(@Param("username") String username, @Param("password") String password);

    /**
     * 统计指定日期车库车流量
     * @param date 日期
     * @return 数量
     */
    int countTraffic(@Param("date")Date date);
}
