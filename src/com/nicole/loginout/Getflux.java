package com.nicole.loginout;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.security.jgss.ExtendedGSSContext;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.fastinfoset.util.StringArray;

public class Getflux {
	String[] result=new String[5];

		    public String[] Getfluxs ()
		    {
		        try {

		            URL url = new URL("http://1.1.1.2/ac_portal/userflux");
		            // 建立http连接
		            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		            //设置连接属性 
					httpConn.setDoOutput(true);//使用 URL 连接进行输出
					httpConn.setDoInput(true);// 使用 URL 连接进行输入
					httpConn.setUseCaches(false);// 忽略缓存 
					httpConn.setRequestMethod("POST");// 设置URL请求方法 (Method)
					String requestString = ""; 
					// 设置请求属性 
					// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致 
					byte[] requestStringBytes = requestString.getBytes("UTF-8"); 
					//System.out.println(requestStringBytes);
					// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
					httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					// 建立输出流，并写入数据
					OutputStream outputStream = httpConn.getOutputStream(); //包含connect（）方法
					outputStream.write(requestStringBytes); 
					outputStream.close(); 
					//System.out.println(httpConn.getResponseCode());
		            // 请求返回的状态
					
		            if (httpConn.getResponseCode() == 200) {
		                System.out.println("连接成功1");
		                // 请求返回的数据
		             
		                InputStream in = httpConn.getInputStream();
		                String a = "null";
		                try {
		                    byte[] data1 = new byte[in.available()];
		                    in.read(data1);
		                    // 转成字符串
		                    a = new String(data1);
		                    
		                    
		                    //截取字符串
		                    int usernamerange=a.indexOf("日流量额");
		                    int todayfluxrange=a.indexOf("当天流量");
		                    int currentfluxrange=a.indexOf("过期时间");
		                    System.out.println("username:"+usernamerange);
		                    //int test=a.indexOf("流量信息如下：");
		                    //System.out.println("test大小为："+test);
		                    String uname=a.substring(106,usernamerange-20);
		                    String todayflux=a.substring(todayfluxrange-22,todayfluxrange-20);
		                    String currentflux=a.substring(todayfluxrange+15,currentfluxrange-20);
		                    result[0]=uname;
		                    result[1]=todayflux;
		                    result[2]=currentflux;
		                 // System.out.println("输出的信息为"+a);
		                } catch (Exception e1) {
		                    // TODO Auto-generated catch block
		                    e1.printStackTrace();
		                }
		                finally{
		                	//System.out.println("inputstream went wrong");
		                	in.close();
		                }
		              }
		            else {
		                System.out.println("no++");
		            }
		              

		        } catch (Exception e) {

		        }
		        return result;

		    }
		    
		    
	  /*public static void main(String args[])
		    {
		    	Getflux getflux=new Getflux();
		    	getflux.Getfluxs();
		    }
		   
		   */ 
		
		   
}
