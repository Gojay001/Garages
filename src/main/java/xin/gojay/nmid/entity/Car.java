package xin.gojay.nmid.entity;

/**
 * @author Gojay
 * @date 2018/5/23
 */
public class Car {
    private int portId;
    private int carStatus;
    private int carHeight;

    public Car() {
    }

    public Car(int portId, int carStatus, int carHeight) {
        this.portId = portId;
        this.carStatus = carStatus;
        this.carHeight = carHeight;
    }

    @Override
    public String toString() {
        return "Car{" +
                "portId=" + portId +
                ", carStatus=" + carStatus +
                ", carHeight=" + carHeight +
                '}';
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void setCarHeight(int carHeight) {
        this.carHeight = carHeight;
    }
}
