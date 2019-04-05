package jklfsr;

import java.util.Scanner;

public class Jk {
	//Jk触发器的输入
	private String initial_stream1;
    private String initial_stream2;
    LFSR lfsr1;
    LFSR lfsr2;
    
    //无参的默认构造函数
    public Jk(){
    	this.initial_stream1="1000000";
    	this.initial_stream2="0100000";
    	lfsr1 = new LFSR(initial_stream1);
    	lfsr2 = new LFSR(initial_stream2);
    }
    
    //可自行配置Jk输入流的构造函数
    public Jk(String initial_stream1,String initial_stream2){
    	this.initial_stream1= initial_stream1;
    	this.initial_stream2= initial_stream2;
    	lfsr1 = new LFSR(initial_stream1);
    	lfsr2 = new LFSR(initial_stream2);
    }
	
	public String JK_encrypt(String plaintext){
		//两个LFSR序列经JK处理后得到密钥流keystream 然后与明文异或得到密文
		Character[] keystream = new Character[plaintext.length()];
		 
		StringBuffer ciphertext = new StringBuffer();
		String aa = lfsr1.getResult(plaintext.length());//得到明文长度的序列a
		String bb = lfsr2.getResult(plaintext.length());//得到明文长度的序列b
		
		char[] a = aa.toCharArray();
		char[] b = bb.toCharArray();
		keystream[0] = (char)a[0];
		
		//通过jk生成密钥流
		for(int i=1; i<plaintext.length(); i++)
		{
			keystream[i]= (char)((a[i]+b[i]+1)*keystream[i-1]+a[i]);
		}
		
		//加解密，进行异或
		for (int i = 0; i < plaintext.length(); ++i) {
            ciphertext.append((char) (plaintext.charAt(i) ^ keystream[i]));
        }
        return ciphertext.toString();
	}	
	
//	public static void main(String[] args) {
//	Jk jk = new Jk();
//    Scanner in =  new Scanner(System.in);
//	System.out.println("使用“LFSR+J-K触发器”生成密钥流：\n 设置LFSR反馈多项式为：f(x) = 1+x+x^7 为n=7的线性反馈移位寄存器\n"
//	+ "设置初始序列ak=(1000000)  bk=(0100000)\n");
//    System.out.println("请输入明文\n");
//    String plaintext = in.nextLine();//输入明文
//    
//    String crypttext = jk.JK_encrypt(plaintext);
//    String decrytext = jk.JK_encrypt(crypttext);
//    System.out.println("明文为：" + plaintext);
//    System.out.println("密文为：" + crypttext);
//    System.out.println("解密为：" + decrytext);
    
		
//		String plaintext="Hell";
//		StringBuffer ciphertext = new StringBuffer();
//		char[] keystream={'0','1','1','0'};
//		for (int i = 0; i < plaintext.length(); ++i) {
//			ciphertext.append((char) (plaintext.charAt(i) ^ keystream[i]));
//        }
//        for(int i=0;i<plaintext.length();i++){
//        	System.out.print(ciphertext.toString());
//        }
//        //char型字符与0异或等于它本身，与1异或等于它本身加1或减1
//        char a=(char)(67);
//        System.out.println();
//        System.out.println("\n"+(a^1));
//	}
}
