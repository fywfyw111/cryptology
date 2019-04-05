package player3;

import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
  
import javax.swing.JFileChooser;   
    
  
public class PlayerMain {  
	//界面类
    static MessageWindows frame;    
    //待加解密的字符串
    public static ArrayList<String> text = new ArrayList();
    //处理后的字符串
    public static ArrayList<String> processed_text = new ArrayList();
    
    //程序的主函数入口，相当于c++的main函数
    public static void main(String[] args) {  
        //创建主程序界面运行窗体  
        frame=new MessageWindows();  
        frame.setVisible(true);   
    }  
    
    //byte数组转十六进制字符串
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
    
    //将字符串转为二进制流
	public static String toBinary(String str){
	    char[] strChar=str.toCharArray();
	    String result="";
	    for(int i=0;i<strChar.length;i++){
	        result +=Integer.toBinaryString(strChar[i])+ " ";
	    }
	    return result;
	}
	
	private static int[] BinstrToIntArray(String binStr) {       
	        char[] temp=binStr.toCharArray();
	        int[] result=new int[temp.length];   
	        for(int i=0;i<temp.length;i++) {
	            result[i]=temp[i]-48;
	        }
	        return result;
	 }
	    
	 //将二进制转换成字符
	private static char BinstrToChar(String binStr){
	         int[] temp=BinstrToIntArray(binStr);
	         int sum=0;
	         for(int i=0; i<temp.length;i++){
	             sum +=temp[temp.length-1-i]<<i;
	         }   
	         return (char)sum;
	 }
	 public static String BinstrToStr(String binStr){
	        String[] tempStr=binStr.split(" ");
	        char[] tempChar=new char[tempStr.length];
	        for(int i=0;i<tempStr.length;i++) {
	           tempChar[i]=BinstrToChar(tempStr[i]);
	        }
	        return String.valueOf(tempChar);
	 }
    
    //十六进制字符串转为byte数组
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
      
    //打开文件  
    public static void openVideo() throws IOException {  
        JFileChooser chooser=new JFileChooser();  
        int v=chooser.showOpenDialog(null);  
        if(v==JFileChooser.APPROVE_OPTION){  
            File file=chooser.getSelectedFile(); 
    		ArrayList<String> tmp = new ArrayList();
    		Scanner sc = new Scanner(file);
    		while(sc.hasNextLine()){
    			tmp.add(sc.nextLine());
    		}
    		System.out.println("tmp="+tmp);
    		text = tmp;
            //把选择的文件传过去
            frame.addmessageleft(text);
        }  
    }  
        
      
}  