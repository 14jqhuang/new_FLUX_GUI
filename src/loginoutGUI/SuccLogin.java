package loginoutGUI;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Color;

import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import com.nicole.loginout.Getflux;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.glass.ui.Timer;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.dynalink.support.RuntimeContextLinkRequestImpl;

/*
 * 登陆成功之后的界面
 * 界面元素：
 * annoucunment:显示服务器传给客户端的信息
 * status：显示用户当前的网络状态
 * username：用户名称
 * flux_sum:日流量总额
 * flux_used：已使用的流量额（定时刷新）
 * */
public class SuccLogin{
	
	String[] result=new String[5];
	JFrame jFrame;
    JLabel label1,label2,label3,username,flux_sum,flux_used,status,announcement;
	public void Slogin() throws Exception{
    
    try{
    	//设置窗口的皮肤
	  UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel()); 
      UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
      JFrame.setDefaultLookAndFeelDecorated(true);
      SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
      SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
      SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
      SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
    
    //new 一个jframe和设置一些基本属性
	  jFrame=new JFrame("Success-Login");
	  jFrame.setLayout(null);
	  jFrame.setLocation(1000,100);
	  jFrame.setVisible(true);
	  jFrame.setSize(300,230);
	  jFrame.setResizable(false);
	  jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//显示当前流量
	  Getflux getflux=new Getflux();
  	  result=getflux.Getfluxs();
  	  
  	    announcement=new JLabel("Announcement");
	    announcement.setBounds(30,10,100,30);
	    announcement.setForeground(Color.BLUE);
	    jFrame.add(announcement);
	  
	    status=new JLabel("Status");
	    status.setBounds(30,40,150,30);
	    status.setForeground(Color.BLUE);
	    jFrame.add(status);
	  
	    label1 = new JLabel("username");
	    label1.setForeground(Color.BLUE);
		label1.setBounds(30, 90, 150, 30);
		jFrame.add(label1);
		
	    label2 = new JLabel("Flux_sum");
		label2.setBounds(30, 120, 100, 30);
		label2.setForeground(Color.BLUE);
		jFrame.add(label2);
		
		label3 = new JLabel("Flux_used");
		label3.setBounds(30, 150, 100, 30);
	    label3.setForeground(Color.BLUE);
		jFrame.add(label3);
		
		username = new JLabel(result[0]);
	    username.setBounds(160, 90, 130, 30);
		jFrame.add(username);
		
		flux_sum = new JLabel(result[1]);
		flux_sum.setBounds(160, 120, 130, 30);
		jFrame.add(flux_sum);
		
		
        flux_used = new JLabel(result[2]);
		flux_used.setBounds(160, 150, 130, 30);
		jFrame.add(flux_used);
		
		JLabel status_content = new JLabel("online");
		status_content.setBounds(160, 40, 130, 30);
		jFrame.add(status_content);
		
		JLabel anno_content = new JLabel("Server connected");
		anno_content.setBounds(160, 10, 130, 30);
		jFrame.add(anno_content);
		
		JLabel apart_line=new JLabel("====================================================");
		apart_line.setBounds(0,80,380,10);
		jFrame.add(apart_line);
		
		
		
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
  }
	/*public void refresh()  {
		  // run in a second
		  final long timeInterval = 3000;
		  Runnable runnable = new Runnable() {
		  public void run() {
	
		    while (true) {
		      try {
		    	Getflux getflux=new Getflux();
		      	result=getflux.Getfluxs();
		      	flux_used.setText(result[2]);
		      	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      
		      try {
		       Thread.sleep(timeInterval);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      }
		    }
		  };
		  Thread thread = new Thread(runnable);
		  thread.start();
		 }
	*/
	
}
