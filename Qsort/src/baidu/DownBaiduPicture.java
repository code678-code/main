package baidu;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * ��ȡ�ٶ�ͼƬ
 * ������	<�ؼ���>    <ҳ��(30��/ҳ)>    <����/ԭ ͼ>    <�ֱ���(����ͼ������Ч)>
 * ԭͼ��һ����������ʧ��(������³���Բ�ǿ���ҿ���Դվ������������)
 * ����ͼ����
 * @author M
 *
 */
public class DownBaiduPicture {
	static int BUFFERSIZE = 819200;
	/*https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=&z=&word=
	 * &cl=&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=&pv=&ic=0&nc=1 &hd=&latest=&copyright=&se=&showtab=0 &fb=0
	 * &width=1920
	 * &height=1080
	 * &face=0 &istype=2 &ie=utf-8 &sid=
	 * */
	static String UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
	static String baseUrl = "https://image.baidu.com/search/index?ct=&z=&tn=baiduimage&ipn=r&word=";
	static String pnUrl = "&pn=";
	static String connect = "&face=0 &istype=2&ie=utf-8&oe=utf-8&cl=&lm=-1&st=-1&fr=&fmq=&ic=0&se=&sme=";
	static String widthUrl = "&width=";
	static String heightUrl = "&height=";
	private String key = "��Ů";
	private int pn = 0;
	private int width = 0;
	private int height = 0;
	private String file = null;
	private int flag = 0;
	//https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=1920&height=1080&face=0&istype=2&ie=utf-8&sid=&word=������ɽ
	/**
	 * ��ʼ������·��
	 * @param str
	 */
	public DownBaiduPicture(String str){
		file = str;
	}
	/**
	 * ��������ͼƬ����
	 * @param word  <�ؼ���>
	 * @param page	<ҳ��>
	 * @param flg	<0Ϊ����ͼ/1Ϊԭͼ>
	 * @param wid	<�ֱ��ʿ�>
	 * @param hei	<�ֱ��ʸ�>
	 */
	public void setPicture(String word, int page, int flg, int wid, int hei){
		key = word;
		pn = page;
		width = wid;
		height = hei;
		flag = flg;
	}
	/**
	 * ȱʡ�ֱ���
	 * @param word
	 * @param page
	 * @param flg
	 */
	public void setPicture(String word, int page, int flg){
		this.setPicture(word, page, flg, 0, 0);
	}
	/**
	 * ����ͼƬ
	 * @param srcUrl		<ͼƬԴ��ַ>
	 * @param outputFile	<����ļ�·����>
	 * @throws IOException	<�ļ��쳣>
	 */
	public void downloadEach(String srcUrl, String outputFile) throws IOException{
		System.out.println(srcUrl+"\t"+"start");
		URL url = new URL(srcUrl);
		URLConnection uc = url.openConnection();
		if(flag == 0){
			HttpsURLConnection hus = (HttpsURLConnection)uc;
			hus.setDoOutput(true);
			hus.setRequestProperty("User-Agent", UserAgent);
			hus.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
			hus.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			hus.setRequestProperty("Connection", "keep-alive");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(hus.getInputStream());
				bos = new BufferedOutputStream(new FileOutputStream(outputFile));
				byte[] temp = new byte[BUFFERSIZE];
				int count = 0;
				while((count = bis.read(temp)) != -1){
					bos.write(temp, 0, count);
					bos.flush();
				}
				System.out.println(srcUrl+"\t"+"end");
			}catch (IOException e) {
				System.out.println(srcUrl+"\t"+"error");
				errorFileDel(outputFile);
			}finally {
				bos.close();
				bis.close();
			}
			return;
		}
		HttpURLConnection huc = (HttpURLConnection)uc;
		huc.setDoOutput(true);
		huc.setRequestProperty("User-Agent", UserAgent);
		huc.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
		huc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		huc.setRequestProperty("Connection", "keep-alive");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(huc.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(outputFile));
			byte[] temp = new byte[BUFFERSIZE];
			int count = 0;
			while((count = bis.read(temp)) != -1){
				bos.write(temp, 0, count);
				bos.flush();
			}
			System.out.println(srcUrl+"\t"+"end");
		}catch (IOException e) {
			System.out.println(srcUrl+"\t"+"error");
			errorFileDel(outputFile);
		}finally {
			bos.close();
			bis.close();
		}
	}
	/**
	 * ����ҳ���ͼƬ����
	 * @throws IOException
	 */
	public void downLoad() throws IOException{
		for(int i = 0; i < pn; i ++){
			String urlRes = baseUrl+key+pnUrl+(i*30)+connect+widthUrl;
			urlRes += width == 0? "": width;
			urlRes += height == 0? heightUrl : heightUrl + height;
			
			System.out.println(urlRes);
			Document document = null;
			document = Jsoup.connect(new String(urlRes.getBytes("utf-8")))
							.userAgent(UserAgent)
							.get();
			String str = document.toString();
			String reg = flag == 0? "thumbURL\":\"https://.+?\"" : "objURL\":\"http://.+?\"" ;
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(str);
			String pathname = file+"/"+key+"/"+i;
			new File(pathname).mkdirs();
			int count = 0;
			while(matcher.find()){
				count++;
				int start = flag == 0? 11 : 9;
				String findUrl = matcher.group().substring(start, matcher.group().length()-1);
				String opn;
				int index;
				if((index = findUrl.lastIndexOf("."))!=-1&&
						(findUrl.substring(index).equals(".png")||
						 findUrl.substring(index).equals(".PNG")||
						 findUrl.substring(index).equals(".jif")||
						 findUrl.substring(index).equals(".GIF"))){
					opn = count + findUrl.substring(index);
				}
				else{
					opn = count + ".jpg";
				}
				try {
					downloadEach(findUrl, pathname+"/"+opn);
				} catch (Exception e) {
					System.out.println(findUrl+"\terror");
					continue;
				}
			}
		}
	}
	/**
	 * ֻ������ԭͼʱ�����ã�
	 * ɾ�����ִ����ͼƬ(��Ȼ�ž�����ͼƬ��ʽ������)
	 * @param outputFile <�����ļ�·��>
	 */
	public static void errorFileDel(String outputFile){
		File errorFile = new File(outputFile);
		if(errorFile.exists()){
			errorFile.delete();
		}
	}
}
