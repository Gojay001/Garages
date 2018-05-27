package xin.gojay.nmid.dao;

import org.apache.ibatis.annotations.Param;
import xin.gojay.nmid.entity.Car;

import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/23
 */
public interface CarDao {
    /**
     * 查询所有车位状态
     * @return 车位信息列表
     */
    List<Car> listCarStatus();

    /**
     * 查询指定车位ID的状态
     * @param portId 车位ID
     * @return 车位状态
     */
    Car getCarStatus(@Param("portId") int portId);

    /**
     * 设置车位高度
     * @param portId 车位ID
     * @param height 高度
     * @return 成功标志
     */
    int setCarHeight(@Param("portId") int portId, @Param("height") int height);
}
