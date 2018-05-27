package xin.gojay.nmid.service;

import xin.gojay.nmid.entity.Car;

import java.util.HashMap;
import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/10
 */
public interface PortService {
    /**
     * 设置车位状态
     * @param carMap 车位Map
     * @return 返回信息
     */
    String setCarStatus(HashMap carMap);

    /**
     * 获取所有车位高度
     * @return 车位列表
     */
    List<Car> listCarHeight();
}
