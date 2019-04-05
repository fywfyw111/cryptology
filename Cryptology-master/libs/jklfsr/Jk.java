package jklfsr;

import java.util.Scanner;

public class Jk {
	//Jk������������
	private String initial_stream1;
    private String initial_stream2;
    LFSR lfsr1;
    LFSR lfsr2;
    
    //�޲ε�Ĭ�Ϲ��캯��
    public Jk(){
    	this.initial_stream1="1000000";
    	this.initial_stream2="0100000";
    	lfsr1 = new LFSR(initial_stream1);
    	lfsr2 = new LFSR(initial_stream2);
    }
    
    //����������Jk�������Ĺ��캯��
    public Jk(String initial_stream1,String initial_stream2){
    	this.initial_stream1= initial_stream1;
    	this.initial_stream2= initial_stream2;
    	lfsr1 = new LFSR(initial_stream1);
    	lfsr2 = new LFSR(initial_stream2);
    }
	
	public String JK_encrypt(String plaintext){
		//����LFSR���о�JK�����õ���Կ��keystream Ȼ�����������õ�����
		Character[] keystream = new Character[plaintext.length()];
		 
		StringBuffer ciphertext = new StringBuffer();
		String aa = lfsr1.getResult(plaintext.length());//�õ����ĳ��ȵ�����a
		String bb = lfsr2.getResult(plaintext.length());//�õ����ĳ��ȵ�����b
		
		char[] a = aa.toCharArray();
		char[] b = bb.toCharArray();
		keystream[0] = (char)a[0];
		
		//ͨ��jk������Կ��
		for(int i=1; i<plaintext.length(); i++)
		{
			keystream[i]= (char)((a[i]+b[i]+1)*keystream[i-1]+a[i]);
		}
		
		//�ӽ��ܣ��������
		for (int i = 0; i < plaintext.length(); ++i) {
            ciphertext.append((char) (plaintext.charAt(i) ^ keystream[i]));
        }
        return ciphertext.toString();
	}	
	
//	public static void main(String[] args) {
//	Jk jk = new Jk();
//    Scanner in =  new Scanner(System.in);
//	System.out.println("ʹ�á�LFSR+J-K��������������Կ����\n ����LFSR��������ʽΪ��f(x) = 1+x+x^7 Ϊn=7�����Է�����λ�Ĵ���\n"
//	+ "���ó�ʼ����ak=(1000000)  bk=(0100000)\n");
//    System.out.println("����������\n");
//    String plaintext = in.nextLine();//��������
//    
//    String crypttext = jk.JK_encrypt(plaintext);
//    String decrytext = jk.JK_encrypt(crypttext);
//    System.out.println("����Ϊ��" + plaintext);
//    System.out.println("����Ϊ��" + crypttext);
//    System.out.println("����Ϊ��" + decrytext);
    
		
//		String plaintext="Hell";
//		StringBuffer ciphertext = new StringBuffer();
//		char[] keystream={'0','1','1','0'};
//		for (int i = 0; i < plaintext.length(); ++i) {
//			ciphertext.append((char) (plaintext.charAt(i) ^ keystream[i]));
//        }
//        for(int i=0;i<plaintext.length();i++){
//        	System.out.print(ciphertext.toString());
//        }
//        //char���ַ���0��������������1�������������1���1
//        char a=(char)(67);
//        System.out.println();
//        System.out.println("\n"+(a^1));
//	}
}
