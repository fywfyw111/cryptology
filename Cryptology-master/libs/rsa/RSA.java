package rsa;

//p=199,q=211,n=41989,(p-1)(q-1)=41580
//e=36709,d=589
//�������������ⳤ��
public class RSA {
	String s1;
	int len,currkey;
	int publicKey,privateKey,modulus;
	
	//�޲εĹ��췽��
	public RSA() {
		this.publicKey=36709;
		this.privateKey=589;
		this.modulus=41989;
	}
	
	//�вεĹ��췽��
	public RSA(int publicKey,int privateKey,int modulus){
		this.publicKey=publicKey;
		this.privateKey=privateKey;
		this.modulus=modulus;
	}
	
	private int mo(int b,int n,int m) {
		int[] z=new int[16];
		int x=1,y=b,t=0;
		while(n>0){
			z[t]=n%2;
			n/=2;
			t++;
		}
		for(int i=0;i<t-1;i++){
			if(z[i]==1)x=(x*y)%m;
			y=(y*y)%m;
		}
		return (x*y)%m;
	}
	private String conversion(int key) {
		String s="";
		for(int i=0;i<len;i++) {
			char c=s1.charAt(i);
			int t=c;
			int tt=mo(t,key,41989);
			char cc=(char)tt;
			s+=cc;
		}
		return s;
	}
	public String encryption(String s1) {
		this.s1=s1;
		this.len=s1.length();
		return conversion(publicKey);
	}
	public String decrypt(String s1) {
		this.s1=s1;
		this.len=s1.length();
		return conversion(privateKey);
	}
	public int getPublicKey() {
		return publicKey;
	}
	public int getPrivateKey() {
		return privateKey;
	}
	public int getModulus() {
		return modulus;
	}
	
//	public class TEST {
//		public static void main(String []args) {
//			String s="123abc���ϴ�ѧ+-*/~!@#$%^&*()_=";
//			System.out.println("����s��"+s);
//			RSA test1=new RSA();
//			String t=test1.encryption(s);//����
//			System.out.println("����t��"+t);
//			String tt=test1.decrypt(t);//����
//			System.out.println("����tt��"+tt);
//			String ttt=test1.encryption(tt);
//			System.out.println("�ټ�������ttt��"+ttt);
//			String tttt=test1.decrypt(ttt);
//			System.out.println("�ٽ���������tttt��"+tttt);
//		}
//	}

}