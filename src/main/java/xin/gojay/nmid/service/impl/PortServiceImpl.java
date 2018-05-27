package xin.gojay.nmid.service.impl;

import org.springframework.stereotype.Service;
import xin.gojay.nmid.dao.CarDao;
import xin.gojay.nmid.dao.PortDao;
import xin.gojay.nmid.entity.Car;
import xin.gojay.nmid.service.PortService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/10
 */
@Service
public class PortServiceImpl implements PortService {
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    @Resource
    private PortDao portDao;

    @Resource
    private CarDao carDao;

    @Override
    public String setCarStatus(HashMap carMap) {
        if (carMap == null || carMap.size() == 0) {
            return "there is no map";
        }
        int portId = (int)carMap.get("portId");
        int status = (int)carMap.get("status");
        if (portDao.setCarStatus(portId, status) == FAILURE) {
            return "error with the info";
        }
        // 车位进车时增加记录
        if (status == 1) {
            if (portDao.insertRecord(portId, new java.sql.Date(new java.util.Date().getTime())) == FAILURE) {
                return "error with the date";
            }
        }
        return "success";
    }

    @Override
    public List<Car> listCarHeight() {
        List<Car> carList = carDao.listCarStatus();
        if (carList == null || carList.size() == 0) {
            return null;
        }
        return carList;
    }
}
