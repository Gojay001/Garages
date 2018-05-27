package xin.gojay.nmid.listener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import xin.gojay.nmid.entity.Car;
import xin.gojay.nmid.service.PortService;
import xin.gojay.nmid.util.FormatUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gojay
 * @date 2018/5/10
 */
public class PortListener implements ServletContextListener {
    private static final int PORT = 809;
    private BufferedReader input;
    private PrintWriter output;

    private PortService portService;
    // Spring Service服务

    private SocketThread socketThread;
    // socket server线程

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if(socketThread != null && !socketThread.isInterrupted())
        {
            socketThread.closeSocketServer();
            socketThread.interrupt();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 注入PortService服务
        WebApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        portService = appctx.getBean(PortService.class);

        if(socketThread == null)
        {
            // 新建线程类
            socketThread = new SocketThread(null);
            // 启动线程
            socketThread.start();
        }
    }


    /**
     * socket线程类
     */
    class SocketThread extends Thread{
        private ServerSocket serverSocket = null;

        private SocketThread(ServerSocket serverScoket){
            try {
                if(serverSocket == null){
                    this.serverSocket = new ServerSocket(PORT);
                    System.out.println("socket start");
                }
            } catch (Exception e) {
                System.out.println("SocketThread failed to create socket!");
                e.printStackTrace();
            }

        }

        @Override
        public void run(){
            // 监听端口
            while(!this.isInterrupted()){
                try {
                    Socket socket = serverSocket.accept();

                    if(socket != null && !socket.isClosed()){
                        // 处理接受的数据
                        new SocketOperate(socket).start();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void closeSocketServer(){
            try {
                if(serverSocket != null && !serverSocket.isClosed())
                {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 多线程处理socket接收的数据
     */
    class SocketOperate extends Thread{
        private Socket socket;

        private SocketOperate(Socket socket) {
            this.socket = socket;

            // 获取client输入输出流
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                sendMessage("client failed to connect!");
            }
        }

        @Override
        public void run()
        {
            // 读取输入流并存储
            String message;
            try {
                // 获取并发送所有车辆高度
                List<Car> carList = portService.listCarHeight();
                String heightMessage = FormatUtil.getCarHeight(carList);
                sendMessage(heightMessage);

                while ((message = input.readLine()) != null) {
                    // 成功接收信息
                    sendMessage("get message success : " + message);

                    // 更新车位状态
                    HashMap carMap = FormatUtil.getCarMap(message);
                    if (carMap != null) {
                        String receive = portService.setCarStatus(carMap);
                        sendMessage(receive);
                    }
                }
            } catch (IOException e) {
                sendMessage("failed to get message");
            }
        }

        /**
         * 将客户端输入消息转发到客户端
         * @param message 客户端消息
         */
        private void sendMessage(String message) {
            output.println(message);
            output.flush();

        }
    }
}
