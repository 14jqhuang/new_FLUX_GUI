package com.nicole.loginout;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import loginoutGUI.SuccLogin;

public class Login 
{
	public boolean Login(String username,String password) throws Exception
	{
		boolean issuccessed=false;
		try { 
			// 建立连接 
			URL url = new URL("http://1.1.1.2/ac_portal/login.php");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 
			// //设置连接属性 
			httpConn.setDoOutput(true);//使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存 
			httpConn.setRequestMethod("POST");// 设置URL请求方法 (Method)
			String requestString = "opr=pwdLogin&userName="+username+"&pwd="+password+"&rememberPwd=0"; 
			// 设置请求属性 
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致 
			byte[] requestStringBytes = requestString.getBytes("UTF-8"); 
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 

			// 建立输出流，并写入数据
			OutputStream outputStream = httpConn.getOutputStream(); //包含connect（）方法
			outputStream.write(requestStringBytes); 
			outputStream.close(); 
			System.out.println(httpConn.getResponseCode());
			 if (httpConn.getResponseCode() == 200) {
	               System.out.println("连接成功");
	               // 请求返回的数据
	                InputStream in = httpConn.getInputStream();
	                System.out.println("in : "+in);
	                System.out.println(9.9999878e-1);
	                System.out.println("count: "+in.available());
	                String a = null;
	                try {
	                    byte[] data1 = new byte[in.available()];
	                    System.out.println("data1 : "+data1);
	                    in.read(data1);
	                    // 转成字符串
	                    a = new String(data1);
	                    System.out.println("getdata:"+a);
	                    JSONObject ob = new JSONObject(a);
	                   
	                    //判断登录的状态
	                    switch((String)ob.get("msg"))
	                    {
	                       case "logon success":
	                       {
	                    	   SuccLogin succLogin= new SuccLogin();
	        	               succLogin.Slogin();
	        	               
	        	            //   succLogin.refresh();
	        	               issuccessed=true;
	        	               break;
	                       }
	                       case "用户已在线，不需要再次认证":
	                       {
	                    	   JOptionPane.showMessageDialog(null, "you are online,don't login again", "Warning", JOptionPane.ERROR_MESSAGE);
	        	              // succLogin.refresh();
	        	               issuccessed=true;
	        	               break;
	                       }
	                    	   
	                       case "用户名或密码错误":
	                    	   JOptionPane.showMessageDialog(null, "username or password is wrong", "Warning", JOptionPane.ERROR_MESSAGE);
	                    	   issuccessed=false;
	                    	   break;
	                    	   
	                       default:break;
	                    }
	                    in.close();
	                    new Getflux().Getfluxs();
		
		               }
		             catch(Exception e){
			                e.printStackTrace();
		              }
		  
		     }
		
	  }catch(Exception e)
		{
		   JOptionPane.showMessageDialog(null, "Internet disconnect", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		return issuccessed;
	}
	
	/*public static void main(String[] args) throws Exception {
		Login login=new Login();
		login.Login("14myhe","yiSTU0209");
	}*/
}
