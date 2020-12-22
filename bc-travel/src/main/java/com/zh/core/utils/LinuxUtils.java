package com.zh.core.utils;

import com.jcraft.jsch.*;
import com.zh.core.constant.Host01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;

/**
 * @author biehl
 * @Description TODO
 * @Date 2018年10月11日 上午10:20:11
 * <p>
 * 说明:exec用于执行命令;sftp用于文件处理
 */
public class LinuxUtils {

    // 私有的对象
    private static LinuxUtils linuxUtils;

    /**
     * 私有的构造方法
     */
    private LinuxUtils() {
    }

    // 懒汉式,线程不安全,适合单线程
    public static LinuxUtils getInstance() {
        if (linuxUtils == null) {
            linuxUtils = new LinuxUtils();
        }
        return linuxUtils;
    }

    // 懒汉式,线程安全,适合多线程
    public static synchronized LinuxUtils getInstance2() {
        if (linuxUtils == null) {
            linuxUtils = new LinuxUtils();
        }
        return linuxUtils;
    }

    private static final int DEFAULT_PORT = 8964;        // 改端口了

    private static String ipAddress = Host01.ipAddress;        // ip地址
    private static String userName = "root";        // 账号
    private static String password = Host01.password;        // 密码

    private Session session;        // JSCH session

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private boolean logined = false;        // 是否登陆

    /**
     * 构造方法,可以直接使用DEFAULT_PORT
     *
     * @param ipAddress
     * @param userName
     * @param password
     */
    public LinuxUtils(String ipAddress, String userName, String password) {
        this(ipAddress, DEFAULT_PORT, userName, password);
    }

    /**
     * 构造方法,方便直接传入ipAddress,userName,password进行调用
     *
     * @param ipAddress
     * @param port
     * @param userName
     * @param password
     */
    public LinuxUtils(String ipAddress, int port, String userName, String password) {
        super();
        LinuxUtils.ipAddress = ipAddress;
        LinuxUtils.userName = userName;
        LinuxUtils.password = password;
        // 端口号
    }

    /**
     * 远程登陆
     *
     * @throws Exception
     */
    public boolean linuxUtilsLogin(String ipAddress, String userName, String password) throws Exception { ;
        // 没有会话或会话无连接，标志位回置
        if (session == null || !session.isConnected())
            logined = false;
        // 如果登陆就直接返回    周神点评：牛子
        if (logined) {
            return true;
        }
        // 创建jSch对象
        JSch jSch = new JSch();
        try {
            // 获取到jSch的session, 根据用户名、主机ip、端口号获取一个Session对象
            session = jSch.getSession(userName, ipAddress, DEFAULT_PORT);
            // 设置密码
            session.setPassword(password);
            // 通过Session建立连接

            // java.com.zh.common.util.Properties;
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);        // 为Session对象设置properties
            session.setTimeout(3000);        // 设置超时
            session.connect();        //        // 通过Session建立连接

            // 设置登陆状态
            logined = true;
            return true;
        } catch (JSchException e) {
            // 设置登陆状态为false
            logined = false;
            System.out.println("主机登录失败, IP = " + ipAddress + ", USERNAME = " + userName + ", Exception:" + e.getMessage());
            return false;
        }
    }

    /**
     * 关闭连接
     */
    public void closeSession() {
        // 调用session的关闭连接的方法
        if (session != null) {
            // 如果session不为空,调用session的关闭连接的方法
            session.disconnect();
            logined = false;        //【周神妙改】标志位不归位是要干嘛啊？
        }

    }

    /**
     * 执行相关的命令
     *
     * @param command
     * @throws IOException
     */
    public void execCommand(String command) throws IOException {
        InputStream in = null;        // 输入流(读)
        Channel channel = null;        // 定义channel变量
        try {
            // 如果命令command不等于null
            if (command != null) {
                // 打开channel
                //说明：exec用于执行命令;sftp用于文件处理
                channel = session.openChannel("exec");
                // 设置command
                ((ChannelExec) channel).setCommand(command);
                // channel进行连接
                channel.connect();
                // 获取到输入流
                in = channel.getInputStream();
                // 执行相关的命令
                String processDataStream = processDataStream(in);
                // 打印相关的命令
                System.out.println("1、打印相关返回的命令: " + processDataStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }

    }

    /**
     * 对将要执行的linux的命令进行遍历
     *
     * @param in
     * @return
     * @throws Exception
     */
    public String processDataStream(InputStream in) throws Exception {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String result = "";
        try {
            while ((result = br.readLine()) != null) {
                sb.append(result);
                // System.out.println(sb.toString());
            }
        } catch (Exception e) {
            throw new Exception("获取数据流失败: " + e);
        } finally {
            br.close();
        }
        return sb.toString();
    }

    /**
     * 上传文件 可参考:https:        //www.cnblogs.com/longyg/archive/2012/06/25/2556576.html
     *
     * @param directory  上传文件的目录
     * @param uploadFile 将要上传的文件
     */
    public void uploadFile(String directory, String uploadFile) {
        try {
            // 打开channelSftp
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            // 远程连接
            channelSftp.connect();
            // 创建一个文件名称问uploadFile的文件
            File file = new File(uploadFile);
            // 将文件进行上传(sftp协议)
            // 将本地文件名为src的文件上传到目标服务器,目标文件名为dst,若dst为目录,则目标文件名将与src文件名相同.
            // 采用默认的传输模式:OVERWRITE
            channelSftp.put(new FileInputStream(file), directory, ChannelSftp.OVERWRITE);
            // 切断远程连接
            channelSftp.exit();
            System.out.println("2、" + file.getName() + " 文件上传成功.....");
        } catch (JSchException | SftpException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 下载文件 采用默认的传输模式：OVERWRITE
     *
     * @param src linux服务器文件地址
     * @param dst 本地存放地址
     * @throws JSchException
     * @throws SftpException
     */
    public void fileDownload(String src, String dst) throws JSchException, SftpException {
        // src 是linux服务器文件地址,dst 本地存放地址
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        // 远程连接
        channelSftp.connect();
        // 下载文件,多个重载方法
        channelSftp.get(src, dst);
        // 切断远程连接,quit()等同于exit(),都是调用disconnect()
        channelSftp.quit();
        // channelSftp.disconnect();
        System.out.println("3、" + src + " ,下载文件成功.....");
    }

    /**
     * 删除文件
     * 接收参数为文件的坐标
     */
    public void deleteFile(String directoryFile) throws SftpException, JSchException {
        // 打开openChannel的sftp
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        // 远程连接
        channelSftp.connect();
        // 删除文件，统一拼接fastdfs文件仓库路径前缀
        channelSftp.rm(Host01.storagePath+directoryFile);
        // 切断远程连接
        channelSftp.exit();
        System.out.println(directoryFile + " 删除的文件.....");
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     *                  sftp
     * @return
     * @throws SftpException
     * @throws JSchException
     */
    public Vector listFiles(String directory) throws JSchException, SftpException {
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        // 远程连接
        channelSftp.connect();
        // 显示目录信息
        Vector ls = channelSftp.ls(directory);
        System.out.println("5、" + ls);
        // 切断连接
        channelSftp.exit();
        return ls;
    }

    public static void main(String[] args) {
        // 连接到指定的服务器
        try {
            // 1、首先远程连接ssh
            LinuxUtils.getInstance().linuxUtilsLogin(ipAddress, userName, password);
            // 打印信息
            System.out.println("0、连接192.168.110.130,ip地址: " + ipAddress + ",账号: " + userName + ",连接成功.....");

            // 2、执行相关的命令
            // 查看目录信息
            // String command = "ls /home/hadoop/package ";
            // 查看文件信息
            // String command = "cat /home/hadoop/package/test ";
            // 查看磁盘空间大小
            // String command = "df -lh ";
            // 查看cpu的使用情况
            // String command = "top -bn 1 -i -c ";
            // 查看内存的使用情况
            //            String command = "free ";
            //            LinuxUtils.getInstance().execCommand(command);

            // 3、上传文件
            //            String directory = "/home/hadoop/package/poi.xlsx";        // 目标文件名
            //            String uploadFile = "E:\\poi.xlsx";        // 本地文件名
            //            LinuxUtils.getInstance().uploadFile(directory, uploadFile);

            // 4、下载文件
            // src 是linux服务器文件地址,dst 本地存放地址,采用默认的传输模式：OVERWRITE
            //test为文件名称哈
            //            String src = "/home/hadoop/package/test";
            //            String dst = "E:\\";
            //            LinuxUtils.getInstance().fileDownload(src, dst);

            // 5、刪除文件
            String deleteDirectoryFile = "/usr/storage/data/00/08/wKicgF7-6d6AIOwIAA75dSP4bv4227.png";
            LinuxUtils.getInstance().deleteFile(deleteDirectoryFile);

            // 6、展示目录下的文件信息
            //            String lsDirectory = "/home/hadoop/package";
            //            LinuxUtils.getInstance().listFiles(lsDirectory);

            // 7、关闭连接
            LinuxUtils.getInstance().closeSession();
        } catch (Exception e) {
            // 打印错误信息
            System.err.println("远程连接失败......");
            e.printStackTrace();
        }
    }

}
