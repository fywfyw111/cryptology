package fangshe;

import java.util.Scanner;
/*
 输入明文s（为小写）,输入a、b:
 加密函数是 E(x)=(ax+b)(mod m)
 解密函数是 D(x)=a1(x−b)(mod m)
 a1*a=1(mod m)
 */
public class Fangshe {

	int x,y,q;
	int a,b;
	
	//带参数的构造函数
	public Fangshe(int a,int b){
		this.a = a;
		this.b = b;
	}
	
	//不带参数的构造函数，a默认为15，b默认为3。
	public Fangshe(){
		this.a=15;
		this.b=3;
	}
	
	private int ex(int a0,int b0) {//求逆元
		if(b0 == 0)
		{
			x = 1;
			y = 0;
			q = a0;
		}
		else {
			ex(b0,a0%b0);
			int temp = x;
			x = y;
			y = temp - a0/b0*y;
		}
		return x;
	}
	
	public String encrypt(String s) {//加密
		char[] ch = s.toCharArray();
		int length = ch.length;
		int[] in = new int[length];
		int i;
		for(i=0;i<length;i++)
		{
			in[i] = ch[i] - 97;//ASCII变为数字0-25
		}
		for(i=0;i<length;i++)
		{
			in[i] = (in[i]*a + b)%26;//加密算法
		}
		for(i=0;i<length;i++)
		{
			ch[i] = (char)(in[i] + 97);//数字变为字母
		}
		return String.valueOf(ch);//char型数组变为string字符串输出
	}
	
	public String deciphering(String s) {//加密
		int a1=ex(a,26);
		char[] ch = s.toCharArray();
		int length = ch.length;
		int[] in = new int [length];
		int i;
		for(i=0;i<length;i++)
		{
			in[i] = ch[i] -97;
		}
		for(i=0;i<length;i++)
		{
			in[i] = ((in[i] - b)*a1 % 26);
			if(in[i] < 0) in[i] +=26;
		}
		for(i=0;i<length;i++)
		{
			ch[i] = (char)(in[i] + 97);
		}
		return String.valueOf(ch);
	}
	
//	public static void main(String[] args)
//	{
//		Scanner input = new Scanner(System.in);
//		System.out.println("please input the message(please input the lowercase)");
//		String s = input.nextLine();
//		Fangshe f = new Fangshe();
//		String ch1=f.encrypt(s);
//		System.out.println("ch1=" + ch1);
//		String ch2=f.deciphering(ch1);
//		System.out.println("ch2=" + ch2);
//	}
}