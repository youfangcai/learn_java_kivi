package inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class inetaddress {
    public static void main(String[] args) throws UnknownHostException {
        // 1. 获取本机的InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        // 获取对应的地址(ip地址)
        String hostAddress1 = localHost.getHostAddress();
        System.out.println(hostAddress1);
        // 2. 根据指定的 主机名， 获取InetAddress对象
        InetAddress getByHost = InetAddress.getByName("you");
        System.out.println(getByHost);
        // 3. 据据 域名 返回InetAddress对象
        InetAddress getbyhost1 = InetAddress.getByName("www.baidu.com");
        System.out.println(getbyhost1);
        // 4. 通过 InetAddress对象， 获取对应的 地址(ip地址)
        String hostAddress = getbyhost1.getHostAddress();
        System.out.println(hostAddress);
        // 5. 通过 InetAddress对象，获取对应的 主机名/域名
        String hostName = getbyhost1.getHostName();
        System.out.println(hostName);

    }
}

