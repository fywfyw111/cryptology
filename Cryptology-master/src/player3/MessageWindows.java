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
	// ������壬һ���Ǳ�������������Ϣ��ʾ���� 
	public JPanel jPanel1,jPanelback2,jPanelback3;
	//������Ϣ��ʾ���棬�ֱ������JPanelback1��JPanelback2���档ֱ�Ӽ���JPanel�ϻ���ִ�С�޷����ڵ�����
	public JScrollPane jPanel2,jPanel3;
	//�ļ��˵���ť
	public JButton mnFile;  
	// ������ť��һ���Ǽ��ܣ�һ���ǽ���
	public JButton jButton1, jButton2; 
	//���̨
	public JTextArea jTextAreainput,jTextAreaoutput;
	//ѡ����м��ܵ�����
	public int choosetype;
	//��������ı�
	public static ArrayList<String> text = new ArrayList();
	//��������ı�
	public static ArrayList<String> processed_text = new ArrayList();

	 
	public MessageWindows() {
		initComp();
		this.choosetype=0;
		messageList=new ArrayList();
	}
	 
	public void initComp() {
		
		this.setTitle("ϵͳ��Ϣ����");
		//���ö��������Ĵ�С��setSize()ֻ�Զ���������Ч
		this.setSize(MainWindows_WIDTH,MainWindows_HIGHTH);
		//����ر�ʱ��������
		this.setDefaultCloseOperation(3);
		//���ô������
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	 
		jPanel1= new JPanel();
		jPanelback2=new JPanel();
		jPanelback3=new JPanel();
		
		//������ѡ��
		String[] choose={"����","������RC4","������LFSRJK","DES","RSA"};
		JComboBox box=new JComboBox(choose);
		//������ť
		mnFile= new JButton("ѡ���ļ�");
		jButton1= new JButton("����");
		jButton2= new JButton("����");
		 
		//����Ϊ�߽粼��
		this.setLayout(new BorderLayout());
		
		box.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		mnFile.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		jButton1.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		jButton2.setPreferredSize(new Dimension(Button_WIDTH, Button_HIGHTH));
		 
		//�ڻ������������
		jPanel1.add(mnFile);
		jPanel1.add(box);
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		jPanel1.setPreferredSize(new Dimension(NorthPanel_WIDTH,NorthPanel_HIGHTH));
		jPanelback2.setPreferredSize(new Dimension(WestPanel_WIDTH,WestPanel_HIGHTH));
		jPanelback3.setPreferredSize(new Dimension(EastPanel_WIDTH,EastPanel_HIGHTH));
		
		//���������Ϣ����ѡ��Ĵ������ı���ʾ��������
		jTextAreainput=new JTextArea();
		jPanel2= new JScrollPane(jTextAreainput);
		jPanel2.setBackground(Color.LIGHT_GRAY);
		jPanel2.setBounds(41,34, WestPanel_WIDTH, 194);
		jPanel2.setBorder(BorderFactory.createTitledBorder("���������Ϣ"));
		jPanel2.setPreferredSize(new Dimension(WestPanel_WIDTH,WestPanel_HIGHTH));
		
		//��������Ϣ�����������ı���Ϣ��ʾ��������
		jTextAreaoutput=new JTextArea();
		jPanel3= new JScrollPane(jTextAreaoutput);
		jPanel3.setBackground(Color.LIGHT_GRAY);
		jPanel3.setBounds(41,34, EastPanel_WIDTH, 194);
		jPanel3.setBorder(BorderFactory.createTitledBorder("��/���ܺ����Ϣ"));
		jPanel3.setPreferredSize(new Dimension(EastPanel_WIDTH,EastPanel_HIGHTH));
		
		jPanelback2.add(jPanel2);
		jPanelback3.add(jPanel3);
		
		//������ӵ�������
		this.add(jPanel1,BorderLayout.NORTH);
		this.add(jPanelback2,BorderLayout.WEST);
		this.add(jPanelback3,BorderLayout.EAST);
		  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ý���ɼ�
		this.setVisible(true);
		 	 
		//Ϊ�ļ�ѡ��ť��Ӽ�������
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
		
		//Ϊ��ѡ����Ӽ�������
		box.addActionListener(new ButtonListener(this,box));
		//Ϊ��ť1��2��Ӽ�������
		jButton1.addActionListener(new ButtonListener(this));
		jButton2.addActionListener(new ButtonListener(this));
	}
	 
	//��������Ϣ�ӵ���߽���ķ���
	public void addmessageleft(ArrayList<String> text) throws FileNotFoundException{
		this.text = text;
		jTextAreainput.append("��ǰ���м�/���ܵ��ı�\n");
		jTextAreainput.paintImmediately(jTextAreainput.getBounds());
		for(int i=0;i<text.size();i++){
			jTextAreainput.append(text.get(i)+"\n");
			jTextAreainput.paintImmediately(jTextAreainput.getBounds());
		}
	}

	//����������Ϣ�ӵ��ұ߽�����
	public void addmessageright(ArrayList<String> processed_text) throws FileNotFoundException{
		this.processed_text = processed_text;
		jTextAreaoutput.append("��ǰ��/���ܺ�õ����ı�\n");
		jTextAreaoutput.paintImmediately(jTextAreainput.getBounds());
		for(int i=0;i<processed_text.size();i++){
			jTextAreaoutput.append(processed_text.get(i)+"\n");
			jTextAreaoutput.paintImmediately(jTextAreaoutput.getBounds());
		}
	}
	
	//�Ѵ������Ϣ���뵽�ı��ļ���
	 public void WriteStringToFile(ArrayList<String> processed_text,String filepath) throws IOException {
         OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filepath),"UTF-8");
         for(int i=0;i<processed_text.size();i++){
        	 osw.append(processed_text.get(i)+"\n");
         }
         osw.close();
	 }
}

