package player3;

//���ð�ť��������ButttonLitener��
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

//ʵ�ֶ�JPanel�ļ����ӿڴ���
public class ButtonListener implements ActionListener{
	public JComboBox box;
	public MessageWindows mw;
	public Fangshe fs=new Fangshe();
	public RSA rsa=new RSA();
	public Stream_cipher rc4=new Stream_cipher();
	public Jk jk=new Jk();
	public DESUtil des=new DESUtil();
	
	//���췽��һ��ֻ��Ҫ���������
	public ButtonListener(MessageWindows mw){
		this.mw=mw;
	}
	//���췽��������Ҫ����������������ѡ��Ķ���
	public ButtonListener(MessageWindows mw,JComboBox box){
		this.mw=mw;
		this.box=box;
	}
	
	//��console��չʾ��ǰ�ı�
	public void showtext(ArrayList<String>text){
		for(int i=0;i<text.size();i++){
			System.out.println(text.get(i));
		}
	}
	
	//�����淢������ʱ���д���
	public void actionPerformed(ActionEvent e) {
		//���ݵ���İ�ť������Ӧ�Ĵ���
		if(e.getActionCommand().equals("����")) {
			//ѡ����з������
			if(mw.choosetype==0){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(fs.encrypt(mw.text.get(i)));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					//�����ܽ��������ļ�
					mw.WriteStringToFile(mw.processed_text, "C:\\Users\\Administrator\\Documents\\��������.txt");
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
			//ѡ��������RC4���м���
			else if(mw.choosetype==1){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(rc4.RC4_encrypt(mw.text.get(i))));	
				}
				showtext(tmp);
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\RC4����.txt");
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
			//ѡ��������LFSRJK���м���
			else if(mw.choosetype==2){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(jk.JK_encrypt(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\LFSRJK����.txt");
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
			//ѡ��DES���м���
			else if(mw.choosetype==3){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.convertByteToHexString(des.jdkDECENcode(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(mw.processed_text, "C:\\Users\\Administrator\\Documents\\DES����.txt");
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
			//ѡ�����RSA����
			else if(mw.choosetype==4){
				ArrayList<String>tmp=new ArrayList();
				for(int i=0;i<mw.text.size();i++){
					tmp.add(PlayerMain.toBinary(rsa.encryption(mw.text.get(i))));
				}
				showtext(tmp);
				mw.processed_text=tmp;
				try {
					mw.WriteStringToFile(tmp, "C:\\Users\\Administrator\\Documents\\RSA����.txt");
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
		//�жϵ�ǰ����İ�ť�ǲ��ǽ���
		else if(e.getActionCommand().equals("����")) {
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
			//ѡ��RC4���н���
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
			//ѡ��������LFSRJK���н���
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
			//ѡ��DES���н���
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
			//ѡ��RSA���н���
			else if(mw.choosetype==4){
				//ѡ�����RSA����
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
		//"����","������RC4","������LFSRJK","DES","RSA"
		else if(box.getSelectedItem().equals("����")) {
			 mw.choosetype=0;
		}
		else if(box.getSelectedItem().equals("������RC4")){
			 mw.choosetype=1;
		}
		else if(box.getSelectedItem().equals("������LFSRJK")){
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

