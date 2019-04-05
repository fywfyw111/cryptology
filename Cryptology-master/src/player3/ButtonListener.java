package player3;

//设置按钮监听方法ButttonLitener类
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import des.DESUtil;
import jklfsr.Jk;
import fangshe.Fangshe;
import rc4.Stream_cipher;
import rsa.RSA;

//实现对JPanel的监听接口处理
public class ButtonListener implements ActionListener{
	public JComboBox box;
	public MessageWindows mw;
	public Fangshe fs=new Fangshe();
	public RSA rsa=new RSA();
	public Stream_cipher rc4=new Stream_cipher();
	public Jk jk=new Jk();
	public DESUtil des=new DESUtil();
	
	//构造方法一，只需要传界面对象
	public ButtonListener(MessageWindows mw){
		this.mw=mw;
	}
	//构造方法二，需要传界面对象和下拉可选框的对象
	public ButtonListener(MessageWindows mw,JComboBox box){
		this.mw=mw;
		this.box=box;
	}
	
	//在console中展示当前文本
	public void showtext(ArrayList<String>text){
		for(int i=0;i<text.size();i++){
			System.out.println(text.get(i));
		}
	}
	
	//当界面发生操作时进行处理
	public void actionPerformed(ActionEvent e) {
		//根据点击的按钮做出相应的处理
		if(e.getActionCommand().equals("加密")) {
			//选择进行仿射加密
			if(mw.choosetype==0){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(fs.encrypt(mw.text.get(i)));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					//将加密结果输出到文件
					mw.WriteStringToFile(mw.processed_text, "C:\\Users\\Administrator\\Documents\\仿射密文.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//选择流密码RC4进行加密
			else if(mw.choosetype==1){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(rc4.RC4_encrypt(mw.text.get(i))));	
				}
				showtext(tmp);
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\RC4密文.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//选择流密码LFSRJK进行加密
			else if(mw.choosetype==2){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(jk.JK_encrypt(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\LFSRJK密文.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//选择DES进行加密
			else if(mw.choosetype==3){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.convertByteToHexString(des.jdkDECENcode(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(mw.processed_text, "C:\\Users\\Administrator\\Documents\\DES密文.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
			//选择进行RSA加密
			else if(mw.choosetype==4){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(rsa.encryption(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\RSA密文.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//判断当前点击的按钮是不是解密
		else if(e.getActionCommand().equals("解密")) {
			if(mw.choosetype==0){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(fs.deciphering(mw.text.get(i)));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//选择RC4进行解密
			else if(mw.choosetype==1){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					System.out.println(mw.text.get(i));
					tmp.add(rc4.RC4_encrypt(PlayerMain.BinstrToStr(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			//选择流密码LFSRJK进行解密
			else if(mw.choosetype==2){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(jk.JK_encrypt(PlayerMain.BinstrToStr(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//选择DES进行解密
			else if(mw.choosetype==3){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					System.out.println(mw.text.get(i));
					byte[] bytetmp=PlayerMain.convertHexStringToByte(mw.text.get(i));
					tmp.add(new String(des.jdkDECDecode(bytetmp)));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			//选择RSA进行解密
			else if(mw.choosetype==4){
				//选择进行RSA解密
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(rsa.decrypt(PlayerMain.BinstrToStr(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.addmessageright(mw.processed_text);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		//"仿射","流密码RC4","流密码LFSRJK","DES","RSA"
		else if(box.getSelectedItem().equals("仿射")) {
			 mw.choosetype=0;
		}
		else if(box.getSelectedItem().equals("流密码RC4")){
			 mw.choosetype=1;
		}
		else if(box.getSelectedItem().equals("流密码LFSRJK")){
			mw.choosetype=2;
		}
		else if(box.getSelectedItem().equals("DES")) {
			 mw.choosetype=3;
		}
		else if(box.getSelectedItem().equals("RSA")){
			 mw.choosetype=4;
		}
		
	}
	
}

