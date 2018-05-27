package xin.gojay.nmid.dao;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * @author Gojay
 * @date 2018/5/10
 */
public interface PortDao {
    /**
     * 将消息写入数据库
     * @param message 待写入数据
     * @return 成功状态
     */
    int insertMessage(String message);

    /**
     * 设置车位状态
     * @param portId 车位ID
     * @param status 车位状态
     * @return 成功标志
     */
    int setCarStatus(@Param("portId") int portId, @Param("status") int status);

    /**
     * 存储车位状态变化记录
     * @param portId 车位ID
     * @param date 日期
     * @return 成功标志
     */
    int insertRecord(@Param("portId") int portId, @Param("date") Date date);
}
