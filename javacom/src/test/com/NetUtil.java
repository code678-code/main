package test.com;

public class NetUtil {
	
    /**
     * ��ȡurl���ӵ�ͼƬ����
     * @param url
     * @return
     */
    public static String getStrName(String url) {  
        String[] sarry = url.split("/");  
        return sarry[sarry.length - 1];  
    }  
}
