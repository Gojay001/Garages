package xin.gojay.nmid.util;

import xin.gojay.nmid.entity.Car;

import java.util.HashMap;
import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/24
 */
public class FormatUtil {
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static HashMap<String, Integer> carMap = new HashMap<>(2);

    public static HashMap<String, Integer> getCarMap(String message) {
        // 截取字符串长度
        String lengthString = message.substring(0, 1);
        int length;
        try {
            length = Integer.parseInt(lengthString);
        } catch (Exception e) {
            return null;
        }
         if (message.length() != length) {
             return null;
         }
         // 截取车位ID和状态
         String portIdString = message.substring(2, 5);
         int portId = Integer.parseInt(portIdString);
         String statusString = message.substring(5);
         int status = Integer.parseInt(statusString);
         // 放入键值对
         carMap.put("portId", portId);
         carMap.put("status", status);
        return carMap;
    }

    public static String getCarHeight(List<Car> carList) {
        StringBuilder message = new StringBuilder();
        if (carList == null || carList.size() == 0) {
            return "error";
        }
        // 获取车位高度信息
        for (Car car : carList) {
            int portId = car.getPortId();
            int height = car.getCarHeight();
            String temp;
            // 给出每条信息格式字符串
            if (height < TEN) {
                temp = "p00" + portId + "00" + height;
            } else if (height < HUNDRED) {
                temp = "p00" + portId + "0" + height;
            } else {
                temp = "p00" + portId + height;
            }
            message.append(temp);
        }
        // 处理字符串长度
        int length = message.length() + 1;
        char front = (char) length;
        message.insert(0, front);
        return message.toString();
    }

//    public static void main(String[] args) {
//        String text = "7p00201";
//        HashMap test = getCarMap(text);
//        if (test == null) {
//            System.out.println("null!");
//        } else {
//            System.out.println(carMap.get("portId"));
//            System.out.println(carMap.get("status"));
//        }
//
//        List<Car> carList = new ArrayList<>(3);
//        Car car1 = new Car(1, 1, 1);
//        Car car2 = new Car(2, 1, 21);
//        Car car3 = new Car(3, 1, 321);
//        carList.add(car1);
//        carList.add(car2);
//        carList.add(car3);
//        System.out.println(getCarHeight(carList));
//
//        int test = 22;
//        char c = (char)test;
//        String s = c + "" + test;
//        System.out.println(s + " " + s.length());
//    }
}
