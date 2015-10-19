package com.bluezhang.sqliteopenhelper.internet_state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Author: blueZhang
 * DATE:2015/10/19
 * Time: 21:36
 * AppName:SqliteOpenHelper
 * PckageName:com.bluezhang.sqliteopenhelper.internet_state
 */

/**
 * 获取网络连接状态的工具类
 * <h1>使用这个工具类注意添加两条权限：</h1>
 * <!-- 获取网络状态的权限 -->
 * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" <p/>
 * uses-permission android:name="android.permission.ACCESS_WIFI_STATE"
 * <ol>
 *     <li>String NET_TYPE_WIFI = "WIFI"</li>
 *     <li> NET_TYPE_MOBILE = "MOBILE"</li>
 *     <li>String NET_TYPE_NO_NETWORK = "no_network"</li>
 * </ol>
 */
public class NetworkUtils {
    public static final String NET_TYPE_WIFI = "WIFI";
    public static final String NET_TYPE_MOBILE = "MOBILE";
    public static final String NET_TYPE_NO_NETWORK = "no_network";
    private NetworkUtils(Context pContext) {

    }
    /**
     * 存储Ip地址
     */
    public static final String IP_DEFAULT = "0.0.0.0";

    /**
     * 判断网络是否连接
     * @param pContext
     * @return
     */
    public static boolean isConnectInternet(final Context pContext)
    {
        final ConnectivityManager conManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        if (networkInfo != null)
        {
            return networkInfo.isAvailable();
        }

        return false;
    }

    /**
     * 判断是不是链接的WIFI
     * @param pContext
     * @return
     */
    public static boolean isConnectWifi(final Context pContext) {
        ConnectivityManager mConnectivity = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        //判断网络连接类型，只有在3G或wifi里进行一些数据更新。
        int netType = -1;
        if(info != null){
            netType = info.getType();
        }
        if (netType == ConnectivityManager.TYPE_WIFI) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    /**
     * 根据传进来的数字确定网络类型
     * @param pNetType
     * @return
     */
    public static String getNetTypeName(final int pNetType)
    {
        switch (pNetType)
        {
            case 0:
                return "unknown";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA: Either IS95A or IS95B";
            case 5:
                return "EVDO revision 0";
            case 6:
                return "EVDO revision A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDen";
            case 12:
                return "EVDO revision B";
            case 13:
                return "LTE";
            case 14:
                return "eHRPD";
            case 15:
                return "HSPA+";
            default:
                return "unknown";
        }
    }

    /**
     * 获取IP地址
     * @return
     */
    public static String getIPAddress()
    {
        try
        {
            final Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaceEnumeration.hasMoreElements())
            {
                final NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

                final Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();

                while (inetAddressEnumeration.hasMoreElements())
                {
                    final InetAddress inetAddress = inetAddressEnumeration.nextElement();

                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress();
                    }
                }
            }

            return NetworkUtils.IP_DEFAULT;
        }
        catch (final SocketException e)
        {
            return NetworkUtils.IP_DEFAULT;
        }
    }


    /**
     * 获取网络链接类型名称
     * @param context 上下文对象
     * @return
     */
    public String getConnTypeName(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null) {
            return NET_TYPE_NO_NETWORK;
        } else {
            return networkInfo.getTypeName();
        }
    }
}
