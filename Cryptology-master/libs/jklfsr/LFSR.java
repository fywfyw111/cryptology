package jklfsr;

public class LFSR {//���þ��������ܵõ�����string���͵�����

	//1+x+x^7
	//��ʼ���У�����Ϊ1000000
	private String stream;
	public LFSR(String stream) {
		super();
		this.stream = stream;
	}
	
	//���þ��������ܵõ�����string���͵�����
	protected String getResult(int len){
		StringBuffer result = new StringBuffer();
		int[] stream_int = new int[stream.length()];
		for(int i=0; i<stream.length(); i++){
			//�ָ��ַ�������ǰ��Ĳ����������
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
