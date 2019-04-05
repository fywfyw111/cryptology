package rc4;
import java.util.Scanner;

public class Stream_cipher {
	//��Կ
	public String key;
	
	
	//�޲εĹ��캯��
	public Stream_cipher(){
		//Ĭ����ԿΪ50
		this.key="50";
	}
	
	//����Կ�Ĺ��캯��
	public Stream_cipher(String key){
		this.key = key;
	}	
	
    //byte����תʮ�������ַ���
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
    
    //ʮ�������ַ���תΪbyte����
	public static byte[] convertHexStringToByte(String str) {
		System.out.println(str);
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

	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	        // sb.append(' ');
	    }
	    return sb.toString().trim();
	}
	
	public static String hexStr2Str(String hexStr) {
	    String str = "0123456789ABCDEF";
	    char[] hexs = hexStr.toCharArray();
	    byte[] bytes = new byte[hexStr.length() / 2];
	    int n;
	    for (int i = 0; i < bytes.length; i++) {
	        n = str.indexOf(hexs[2 * i]) * 16;
	        n += str.indexOf(hexs[2 * i + 1]);
	        bytes[i] = (byte) (n & 0xff);
	    }
	    return new String(bytes);
	}
	
	// 1 ����
    public String RC4_encrypt(final String plaintext) {
        Integer[] S = new Integer[256]; // S��
        Character[] keySchedul = new Character[plaintext.length()]; // ���ɵ���Կ��
        StringBuffer ciphertext = new StringBuffer();

        ksa(S);
        rpga(S, keySchedul, plaintext.length());

        //ֱ�������
        for (int i = 0; i < plaintext.length(); ++i) {
            ciphertext.append((char) (plaintext.charAt(i) ^ keySchedul[i]));
        }
        return ciphertext.toString();
    }

    // 1.1 KSA--��Կ�����㷨--����key����S����һ���û���Ҳ���Ƕ�S����������
    public void ksa(Integer[] s) {
    	//��ʼ��S��
        for (int i = 0; i < 256; ++i) {
            s[i] = i;
        }
        //��S�����û�
        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + s[i] + key.charAt(i % key.length())) % 256;
            swap(s, i, j);
        }
    }

    // 1.2 RPGA--α��������㷨--���������������е�S�����������ⳤ�ȵ���Կ�����ʹ������ı�������ͬ��
    private void rpga(Integer[] s, Character[] keySchedul, int plaintextLength) {
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; ++k) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedul[k] = (char) (s[(s[i] + s[j]) % 256]).intValue();
        }
    }

    // 1.3 �û�
    private void swap(Integer[] s, int i, int j) {
        Integer mTemp = s[i];
        s[i] = s[j];
        s[j] = mTemp;
    }
    
    //������
//	public static void main(String[] args) {
//		//����Ϊ���� &&��Կ
//        Stream_cipher rc4 = new Stream_cipher();
//        Scanner in =  new Scanner(System.in);
//        System.out.print("����������\n");
//        String plaintext = in.nextLine();
//
//        String ciphertext = rc4.RC4_encrypt(plaintext);
//        
////        String tmp = str2HexStr(ciphertext);
////        String tmps = hexStr2Str(tmp);
//
//        
//        String tmp=toBinary(ciphertext);
//        String tmps=BinstrToStr(tmp);
//        String decryptText = rc4.RC4_encrypt(tmps);
//        
//        
//        System.out.println(tmp);
//        System.out.println( "ʹ��RC4������Կ����\n����Ϊ��" + plaintext);
//        System.out.println("��ԿΪ��" + rc4.key);
//        System.out.println("����Ϊ��" + ciphertext );
//        System.out.println("����Ϊ��" + decryptText);
//        
//        String x=in.nextLine();
//        System.out.println(rc4.RC4_encrypt(BinstrToStr(x)));
//        
//    }	
//
//	public static String toBinary(String str){
//	    char[] strChar=str.toCharArray();
//	    String result="";
//	    for(int i=0;i<strChar.length;i++){
//	        result +=Integer.toBinaryString(strChar[i])+ " ";
//	    }
//	    return result;
//	}
//	
//	private static int[] BinstrToIntArray(String binStr) {       
//	        char[] temp=binStr.toCharArray();
//	        int[] result=new int[temp.length];   
//	        for(int i=0;i<temp.length;i++) {
//	            result[i]=temp[i]-48;
//	        }
//	        return result;
//	 }
//	    
//	 //��������ת�����ַ�
//	private static char BinstrToChar(String binStr){
//	         int[] temp=BinstrToIntArray(binStr);
//	         int sum=0;
//	         for(int i=0; i<temp.length;i++){
//	             sum +=temp[temp.length-1-i]<<i;
//	         }   
//	         return (char)sum;
//	 }
//	 public static String BinstrToStr(String binStr){
//	        String[] tempStr=binStr.split(" ");
//	        char[] tempChar=new char[tempStr.length];
//	        for(int i=0;i<tempStr.length;i++) {
//	           tempChar[i]=BinstrToChar(tempStr[i]);
//	        }
//	        return String.valueOf(tempChar);
//	 }
}



