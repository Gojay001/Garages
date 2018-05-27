package xin.gojay.nmid.service.impl;

import org.springframework.stereotype.Service;
import xin.gojay.nmid.dao.CarDao;
import xin.gojay.nmid.entity.Car;
import xin.gojay.nmid.service.CarService;
import xin.gojay.nmid.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/22
 */
@Service
public class CarServiceImpl implements CarService {
    @Resource
    private CarDao carDao;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    private ResponseUtil responseUtil;

    @Override
    public ResponseUtil setCarHeight(int portId, int height) {
        if (carDao.setCarHeight(portId, height) == FAILURE) {
            responseUtil = new ResponseUtil(500, "ERROR#设置失败");
            return responseUtil;
        }
        Car car = carDao.getCarStatus(portId);
        responseUtil = new ResponseUtil(204, "OK#设置成功");
        responseUtil.setBody(car);
        return responseUtil;
    }

    @Override
    public ResponseUtil listCarStatus() {
        List<Car> carList = carDao.listCarStatus();
        if (carList == null) {
            responseUtil = new ResponseUtil(200, "OK#信息为空");
            return responseUtil;
        }
        responseUtil = new ResponseUtil(200, "OK#成功返回");
        responseUtil.setBody(carList);
        return responseUtil;
    }

    @Override
    public ResponseUtil getCarStatus(int portId) {
        if (portId < 1) {
            responseUtil = new ResponseUtil(400, "ERROR#车位ID有误");
            return responseUtil;
        }
        Car car = carDao.getCarStatus(portId);
        if (car == null) {
            responseUtil = new ResponseUtil(400, "ERROR#车位ID有误");
            return responseUtil;
        }
        responseUtil = new ResponseUtil(200, "SUCCESS#成功返回");
        responseUtil.setBody(car);
        return responseUtil;
    }
}
