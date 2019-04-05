package player3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

 
public class MessageWindows extends JFrame implements Config{
	
	public static ArrayList<String>messageList;
	// 三块面板，一块是背景，两块是信息显示界面 
	public JPanel jPanel1,jPanelback2,jPanelback3;
	//两块信息显示界面，分别添加在JPanelback1和JPanelback2上面。直接加载JPanel上会出现大小无法调节等问题
	public JScrollPane jPanel2,jPanel3;
	//文件菜单按钮
	public JButton mnFile;  
	// 两个按钮，一个是加密，一个是解密
	public JButton jButton1, jButton2; 
	//输出台
	public JTextArea jTextAreainput,jTextAreaoutput;
	//选择进行加密的类型
	public int choosetype;
	//待处理的文本
	public static ArrayList<String> text = new ArrayList();
	//待处理的文本
	public static ArrayList<String> processed_text = new ArrayList();

	 
	public MessageWindows() {
		initComp();
		this.choosetype=0;
		messageList=new ArrayList();
	}
	 
	public void initComp() {
		
		this.setTitle("系统消息界面");
		//设置顶级容器的大小，setSize()只对顶级容器有效
		this.setSize(MainWindows_WIDTH,MainWindows_HIGHTH);
		//窗体关闭时结束程序
		this.setDefaultCloseOperation(3);
		//设置窗体居中
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	 
		jPanel1= new JPanel();
		jPanelback2=new JPanel();
		jPanelback3=new JPanel();
		
		//下拉可选框
		String[] choose={"仿射","流密码RC4","流密码LFSRJK","DES","RSA"};
		JComboBox box=new JComboBox(choose);
		//三个按钮
		mnFile= new JButton("选择文件");
		jButton1= new JButton("加密");
		jButton2= new JButton("解密");
		 
		//设置为边界布局
		this.setLayout(new BorderLayout());
		
		box.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		mnFile.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		jButton1.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		jButton2.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		 
		//在画板面板添加组件
		jPanel1.add(mnFile);
		jPanel1.add(box);
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		jPanel1.setPreferredSize(new Dimension(NorthPanel_WIDTH,NorthPanel_HIGHTH));
		jPanelback2.setPreferredSize(new Dimension(WestPanel_WIDTH,WestPanel_HIGHTH));
		jPanelback3.setPreferredSize(new Dimension(EastPanel_WIDTH,EastPanel_HIGHTH));
		
		//待处理的信息，将选择的待处理文本显示到界面上
		jTextAreainput=new JTextArea();
		jPanel2= new JScrollPane(jTextAreainput);
		jPanel2.setBackground(Color.LIGHT_GRAY);
		jPanel2.setBounds(41,34, WestPanel_WIDTH, 194);
		jPanel2.setBorder(BorderFactory.createTitledBorder("待处理的信息"));
		jPanel2.setPreferredSize(new Dimension(WestPanel_WIDTH,WestPanel_HIGHTH));
		
		//处理后的信息，将处理后的文本信息显示到界面上
		jTextAreaoutput=new JTextArea();
		jPanel3= new JScrollPane(jTextAreaoutput);
		jPanel3.setBackground(Color.LIGHT_GRAY);
		jPanel3.setBounds(41,34, EastPanel_WIDTH, 194);
		jPanel3.setBorder(BorderFactory.createTitledBorder("加/解密后的信息"));
		jPanel3.setPreferredSize(new Dimension(EastPanel_WIDTH,EastPanel_HIGHTH));
		
		jPanelback2.add(jPanel2);
		jPanelback3.add(jPanel3);
		
		//将界面加到布局上
		this.add(jPanel1,BorderLayout.NORTH);
		this.add(jPanelback2,BorderLayout.WEST);
		this.add(jPanelback3,BorderLayout.EAST);
		  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置界面可见
		this.setVisible(true);
		 	 
		//为文件选择按钮添加监听机制
		mnFile.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				try {
					PlayerMain.openVideo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		 
		});
		
		//为可选框添加监听机制
		box.addActionListener(new ButtonListener(this,box));
		//为按钮1，2添加监听机制
		jButton1.addActionListener(new ButtonListener(this));
		jButton2.addActionListener(new ButtonListener(this));
	}
	 
	//将处理信息加到左边界面的方法
	public void addmessageleft(ArrayList<String> text) throws FileNotFoundException{
		this.text = text;
		jTextAreainput.append("当前进行加/解密的文本\n");
		jTextAreainput.paintImmediately(jTextAreainput.getBounds());
		for(int i=0;i<text.size();i++){
			jTextAreainput.append(text.get(i)+"\n");
			jTextAreainput.paintImmediately(jTextAreainput.getBounds());
		}
	}

	//将处理后的信息加到右边界面上
	public void addmessageright(ArrayList<String> processed_text) throws FileNotFoundException{
		this.processed_text = processed_text;
		jTextAreaoutput.append("当前加/解密后得到的文本\n");
		jTextAreaoutput.paintImmediately(jTextAreainput.getBounds());
		for(int i=0;i<processed_text.size();i++){
			jTextAreaoutput.append(processed_text.get(i)+"\n");
			jTextAreaoutput.paintImmediately(jTextAreaoutput.getBounds());
		}
	}
	
	//把处理后信息输入到文本文件中
	 public void WriteStringToFile(ArrayList<String> processed_text,String filepath) throws IOException {
         OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filepath),"UTF-8");
         for(int i=0;i<processed_text.size();i++){
        	 osw.append(processed_text.get(i)+"\n");
         }
         osw.close();
	 }
}

