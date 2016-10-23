package loginoutGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import org.json.JSONObject;
import org.jvnet.substance.*;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import com.nicole.loginout.Getflux;
import com.nicole.loginout.GetjsonData;
import com.nicole.loginout.Login;
import com.sun.javafx.font.Disposer;

import oracle.jrockit.jfr.tools.ConCatRepository;

/*nicole - 20161020
 * 设置简单的界面
 * 通过学校的登录机制判断账号密码是否正确  用json来解析页面的内容
 *********界面：
 *   info:用来返回账号密码的验证结果，如果验证成功，直接跳到动态显示流量的界面，如果失败会显示失败信息
 *   username:用户名称
 *   password：账号所对应的密码
 *   auto:自动登录
 *   login：登录按钮，进入账号密码验证阶段
 *   logout：登出，会清空原有用户输入的信息，并且在info显示你已经登出
 * */

public class LoginGui extends JFrame {

	public LoginGui() throws Exception {
	 try{
		//设置自定义的字体
		 final int SystemFontSize = 12;
	     final int DataFontSize = 15;
	     final Font SystemFont = new Font("微软雅黑", Font.PLAIN,SystemFontSize);
	     final Font DataFont = new Font("Comic Sans MS", Font.PLAIN,DataFontSize);
	     javax.swing.plaf.FontUIResource fontResource = new javax.swing.plaf.FontUIResource(SystemFont);
		 javax.swing.plaf.FontUIResource datafontResource = new javax.swing.plaf.FontUIResource(DataFont);
		 
		 //使用substance包来润色，设置皮肤
		 UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel()); 
		 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceModerateLookAndFeel"); 
         //设置各个组件都使用这些装饰
		 JFrame.setDefaultLookAndFeelDecorated(true);
         JDialog.setDefaultLookAndFeelDecorated(true);
	     SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
         SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
         SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
         SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
       
         //UI管理器之设置各个组件的字体
         UIManager.put("Button.font", datafontResource);
         UIManager.put("InternalFrame.titleFont", datafontResource);
         UIManager.put("Label.font", datafontResource);
         UIManager.put("TextField.font", datafontResource);
         UIManager.put("PasswordFiled.font", datafontResource);
         
       }catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//定义一个jFrane,已经一些基本属性；这里采用自定义布局
		JFrame jFrame = new JFrame("Login GUI");
		jFrame.setSize(400,300);
		jFrame.setLocation(100,100);
		jFrame.setLocationRelativeTo(null); 
		jFrame.setResizable(false);
		jFrame.setLayout(null);
		jFrame.setBackground(Color.cyan);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
	
		JLabel info=new JLabel(" ");
		info.setForeground(Color.red);
		info.setBounds(100, 10, 250, 30);
		jFrame.add(info);
		
		JLabel label1 = new JLabel("username");
		label1.setBounds(92, 50, 150, 30);
		jFrame.add(label1);
		
		JLabel label2 = new JLabel("password");
		label2.setBounds(92, 100, 100, 30);
		jFrame.add(label2);
		
		final JTextField username = new JTextField();
	    username.setBounds(160, 50, 130, 30);
		jFrame.add(username);
		
		final JTextField password = new JPasswordField();
		password.setBounds(160, 100, 130, 30);
		jFrame.add(password);
		
		
		JCheckBox auto=new JCheckBox("auto login");
		auto.setBounds(100,130,100,40);
		jFrame.add(auto);
		
		JButton login = new JButton("Login");
		login.setBounds(100, 180, 100, 40);
		jFrame.add(login);
		
		JButton logout=new JButton("Logout");
		jFrame.add(logout);
		logout.setBounds(210,180,100,40);
		
		//为login按钮设置一个监听器
		login.addActionListener(new ActionListener() {
		 	@Override
			public void actionPerformed(ActionEvent e) {
		 		String str1=username.getText();
		 		String str2=password.getText();
		 		if(str1.equals("")||str2.equals(""))
		 		{
		 			info.setText("username or password is invalid");
		 		}
		 		else if (str1.length()<0||str1.length()>10) {
		 			info.setText("username's length is out of range");
				}
		 		else{
		 			//进入验证阶段
		 	        Login login=new Login();
		 			try {
		 				if(login.Login(str1, str2))
						{
							jFrame.dispose();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
		 			
		 	    }
		 		
		 	    
		 	  
		 	}
			
		});
		
		//为logout按钮设置一个监听器
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				username.setText("");
				password.setText("");
				info.setText("You are logout");
			}
		});
		
		
		
		
		
	}
	
	   
	public static void main(String args[]) throws Exception
	{
		new LoginGui();
	}
	
	
	

	

}