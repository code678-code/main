package test.com;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
/***
 * @author God
 * @see java抓取网站 图片
 * @info 一个简单的爬虫 不涉及广度优先和深度优先  仅仅做为理解
 */
public class JavaReptileUtil {
	
	// 地址
	private static final String WEB_SITE = "https://huaban.com/explore/xuancaishoujibizhi";
	// 获取img标签正则
	private static final String IMAGE_TAG_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMAGE_SRC_REG = "https:\"?(.*?)(\"|>|\\s+)";
	/**
	 * 测试小爬虫
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 1.获取美女图片官网地址
		String htmlInfo = getHtmlInfo(WEB_SITE);
		//获取图片url链接地址
		List<String> imageSrc = getImageSrc(htmlInfo);
		//下载美女图片
		downloadImage(imageSrc);
		
	}
	/**
	 * 解析html页面
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlInfo(String host) throws Exception{
		URL url=new URL(host);
		URLConnection urlConnection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String buffer=null;
		StringBuffer sbf=new StringBuffer();
		while((buffer=bufferedReader.readLine())!=null){
			sbf.append(buffer);
		}
		return sbf.toString();
	}
	
	/**
	 * 解析所有的image标签文的src属性
	 * @param args
	 * @throws Exception
	 */
	public static List<String> getImageSrc(String htmlInfo){
		Matcher matcher_image = Pattern.compile(IMAGE_TAG_REG).matcher(htmlInfo);
		List<String> imageSrc = new ArrayList<String>();
		while (matcher_image.find()) {
			Matcher matcher_src = Pattern.compile(IMAGE_SRC_REG).matcher(matcher_image.group());
			while (matcher_src.find()) {
				imageSrc.add(matcher_src.group().substring(0, matcher_src.group().length() - 1));
			}
		}
		return imageSrc;
	}
	
	/**
	 * 下载爬到的url链接
	 * @param imageSrc
	 * @throws Exception 
	 */
	public static void downloadImage(List<String> imageSrc) throws IOException {
		for (String src : imageSrc) {
			URL url =null;
			try {
				url = new URL(src);
			} catch (IOException e) {
				continue;
			}
			// 下在资源
			DataInputStream dataInputStream = new DataInputStream(url.openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\beauty\\" + NetUtil.getStrName(src)));
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = dataInputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, length);
				System.out.println("下载中....");
			}
			System.out.println("下载完成...");
			dataInputStream.close();
			fileOutputStream.close();
		}
	}
    
}
