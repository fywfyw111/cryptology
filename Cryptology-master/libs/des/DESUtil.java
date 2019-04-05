package des;

import java.util.ArrayList;
import java.util.Scanner;
import java.security.KeyFactory;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {
	//��Կ
	public String password;
	
	//�޲ι��캯��,Ĭ����ԿΪ��12345678��
	public DESUtil(){
		password="12345678";
	}
	
	//��������ѡ����Կ�Ĺ��캯��
	public DESUtil(String key){
		this.password = key;
	}
	
    public static String convertByteToHexString(byte[] bytes) {
    	String result = "";
    	for(int i=0;i<bytes.length; i++) {
    		int temp = bytes[i]&0xff;
    		String tempHex = Integer.toHexString(temp);
    		if(tempHex.length()<2) {
    			result +="0"+tempHex;
    		}
    		else {
    			result += tempHex;
    		}
    	}
    	return result;
    }
    
    //DES����
    public byte[] jdkDECENcode(String message) {
    	try {
    		//��ȡ�������
    		SecureRandom random = new SecureRandom();
    		
    		DESKeySpec keySpec = new DESKeySpec(password.getBytes() );
    		//��Կ����
    		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    		SecretKey secretKey =keyFactory.generateSecret(keySpec);
    		Cipher cipher = Cipher.getInstance("DES");
    		cipher.init(Cipher.ENCRYPT_MODE,secretKey,random);
			byte[] result = cipher.doFinal(message.getBytes());
    		return result;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public byte[] jdkDECDecode(byte[] message) {
    	try {
    		SecureRandom random = new SecureRandom();
    		DESKeySpec keySpec = new DESKeySpec(password.getBytes() );
    		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    		SecretKey secretKey =keyFactory.generateSecret(keySpec);
    		Cipher cipher = Cipher.getInstance("DES");
    		cipher.init(Cipher.DECRYPT_MODE,secretKey,random);
			byte[] result = cipher.doFinal(message);
    		return result;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
	public static byte[] convertHexStringToByte(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
    
//	public static void main(String[] args) {
//		DESUtil des=new DESUtil();
//        Scanner in = new Scanner(System.in);
//		System.out.println("����Ҫ���ܵ���Ϣ��");
//		String message = in.nextLine();
//		byte[] desEncode=des.jdkDECENcode(message);
//		String tmp=new String(desEncode);
//        System.out.println("DES ���ܺ������:"+convertByteToHexString(desEncode));
//        byte[] desDecode=des.jdkDECDecode(tmp.getBytes());
//        System.out.println("DES ���ܺ������:"+new String(desDecode));
//        
//        String hex=in.nextLine();
//        byte[] tmp2=convertHexStringToByte(hex);
//        byte[] newtext=des.jdkDECDecode(tmp2);
//        System.out.println("DES ���ܺ������:"+new String(newtext));
//	}
	
}
