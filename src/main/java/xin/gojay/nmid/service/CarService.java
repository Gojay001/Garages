package xin.gojay.nmid.service;

import xin.gojay.nmid.util.ResponseUtil;

/**
 * @author Gojay
 * @date 2018/5/22
 */
public interface CarService {
    /**
     * 设置车位高度
     * @param portId 车位号
     * @param height 高度
     * @return 响应消息
     */
    ResponseUtil setCarHeight(int portId, int height);

    /**
     * 获取所有车位状态
     * @return 响应消息
     */
    ResponseUtil listCarStatus();

    /**
     * 获取车位号对应状态
     * @param portId 车位号
     * @return 响应消息
     */
    ResponseUtil getCarStatus(int portId);
}
