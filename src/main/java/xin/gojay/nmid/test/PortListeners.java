package xin.gojay.nmid.test;

import org.springframework.stereotype.Controller;
import xin.gojay.nmid.service.PortService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 端口监听器
 * @author Gojay
 * @date 2018/5/8
 */
@Controller
public class PortListeners {
    //连接每个客户端的Socket输出流

    private ArrayList<PrintWriter> allWriter = new ArrayList<>();
    //Socket输入输出流

    private BufferedReader input;
    private PrintWriter output;

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 809;

    @Resource
    private PortService portService;

    /**
     * 主函数
     */
    public static void main(String[] args) {
        new PortListeners().start();
    }

    /**
     * 启动端口监听
     */
    private void start() {
        ServerSocket server;

        try {
            //创建ServerSocket对象
            server = new ServerSocket(PORT);

            while (true) {
                //监听端口
                Socket client = server.accept();
                String ip = client.getInetAddress().getHostAddress();
                System.out.println(ip + "已经成功连接！");

                ///获取输出流，并将输出流加入到ArrayList
                //output = new PrintWriter(client.getOutputStream(), true);
                //allWriter.add(output);

                ///启动处理数据线程
                new Thread(new HandlerThread(client)).start();
            }
        } catch (IOException e) {
            System.out.println("端口被占用");
            System.exit(0);
        }
    }

    class HandlerThread implements Runnable {
        Socket socket = null;

        /**
         * 传递客户端socket并获取输入流
         * @param socket 客户端socket
         */
        private HandlerThread(Socket socket) {
            this.socket = socket;
            //获取Socket输入输出流
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println("客户端连接失败！");
                sendMessage("bad connect");
            }
        }

        @Override
        public void run() {
            String message;
            //读取输入流并存储
            try {
                while ((message = input.readLine()) != null) {
                    System.out.println("message is: " + message);
                    sendMessage(message);
                }
            } catch (IOException e) {
                System.out.println("客户端断开连接！");
                sendMessage("connection down");
            }
        }
    }

//
//    /**
//     * 处理数据的线程
//     */
//    class HandlerThread implements Runnable {
//        Socket socket = null;
//
//        /**
//         * 传递客户端socket并获取输入流
//         * @param socket 客户端socket
//         */
//        public HandlerThread(Socket socket) {
//            this.socket = socket;
//            //获取Socket输入输出流
//            try {
//                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            } catch (IOException e) {
//                System.out.println("客户端连接失败！");
//            }
//        }
//
//        @Override
//        public void run() {
//            String message = "";
//            //读取输入流并转发
//            try {
//                while ((message = input.readLine()) != null) {
//                    sendMessage(message);
//                }
//            } catch (IOException e) {
//                System.out.println("客户端断开连接！");
//            }
//        }
//    }
//
    /**
     * 将客户端输入消息转发到每个客户端
     * @param message 客户端消息
     */
    public void sendMessage(String message) {
        for (PrintWriter printWriter : allWriter) {
            printWriter.println(message);
            printWriter.flush();
        }
    }
}
