package test.com;

public class NetUtil {
	
    /**
     * 获取url链接的图片名称
     * @param url
     * @return
     */
    public static String getStrName(String url) {  
        String[] sarry = url.split("/");  
        return sarry[sarry.length - 1];  
    }  
}
