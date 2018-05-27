package xin.gojay.nmid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.gojay.nmid.service.CarService;
import xin.gojay.nmid.util.ResponseUtil;

import javax.annotation.Resource;

/**
 * @author Gojay
 * @date 2018/5/22
 */
@Controller
@RequestMapping("api/car")
public class CarController {
    @Resource
    private CarService carService;

    /**
     * 设置车位高度
     * @param portId 车位ID
     * @param height 高度
     * @return 响应消息
     */
    @RequestMapping(value = "/setCarHeight/{portId}/{height}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil setCarHeight(@PathVariable int portId,
                                        @PathVariable int height) {
        return carService.setCarHeight(portId, height);
    }

    /**
     * 列出所有车位信息
     * @return 响应消息
     */
    @RequestMapping(value = "/listCarStatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil listCarStatus() {
        return carService.listCarStatus();
    }

    /**
     * 查询指定车位状态
     * @param portId 车位ID
     * @return 响应消息
     */
    @RequestMapping(value = "/getCarStatus/{portId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil getCarStatus(@PathVariable int portId) {
        return carService.getCarStatus(portId);
    }
}
