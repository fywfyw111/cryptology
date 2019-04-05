package jklfsr;

public class LFSR {//作用就是最终能得到两个string类型的序列

	//1+x+x^7
	//初始序列：假设为1000000
	private String stream;
	public LFSR(String stream) {
		super();
		this.stream = stream;
	}
	
	//作用就是最终能得到两个string类型的序列
	protected String getResult(int len){
		StringBuffer result = new StringBuffer();
		int[] stream_int = new int[stream.length()];
		for(int i=0; i<stream.length(); i++){
			//分割字符串包括前面的不包括后面的
			stream_int[i] = Integer.parseInt(stream.substring(i, i+1));
		}	
		
		while(result.length()!=len){
			int temp = stream_int[6]^stream_int[0];
			for(int i=stream.length()-1; i>0; i--)
			{
				stream_int[i] = stream_int[i-1];
			}
			 		
			stream_int[0] = temp;
		    char ss = (char) (stream_int[6]+48);
	   
			result.append(ss);	
		}
		return result.toString();	
	}
}
