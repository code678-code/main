package Sort;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//java爬虫源码
public class getHtm12 {
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void getPicture(String url) {
		URL httpUrl;
		BufferedInputStream in;
		FileOutputStream out;
		try {
			System.out.println("==========抓取网络图片 Start==========");
			String PictrueName = url.substring(url.lastIndexOf("/"));
			String savePath = "D:/Pic";
			File file = new File(savePath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			httpUrl = new URL(url);
			in = new BufferedInputStream(httpUrl.openStream());
			out = new FileOutputStream(new File(file + PictrueName));
			byte[] buffer = new byte[1024 * 1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			System.out.println("==========抓取网络图片 End==========");
			num++;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHtmlCode(String url) throws Exception {
		String content = "";
		URL httpUrl = new URL(url);
		BufferedReader buf = new BufferedReader(new InputStreamReader(
				httpUrl.openStream()));
		String str;
		while ((str = buf.readLine()) != null) {
			content += str + "\n";
		}
		// System.out.println(new String(content.getBytes("GBK"),"ISO-8859-1"));
		return new String(content.getBytes("GBK"), "UTF-8");
	}

	public void get(String url) throws Exception {
		String searchImgReg = "(?x)(src|SRC|background|BACKGROUND|image|images|Image|Images|Img)=('|\")/?(([\\w-]+/)*([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
		String searchImgReg2 = "(?x)(src|SRC|background|BACKGROUND|image|images|Image|Images|Img)=('|\")((http://)/?([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-]+)*(/[\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
		String content = this.getHtmlCode(url);
		System.out.println(content);
		Pattern pattern = Pattern.compile(searchImgReg);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			this.getPicture(url + matcher.group(3));
			System.out.println(matcher.group(3));
		}
		pattern = Pattern.compile(searchImgReg2);
		matcher = pattern.matcher(content);
		while (matcher.find()) {
			this.getPicture(matcher.group(3));
			System.out.println(matcher.group(3));
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("请输入要抓取的网址");
		Scanner scanner=new Scanner(System.in);
		String url = scanner.nextLine();
		getHtm12 ge = new getHtm12();
		ge.get(url);
		System.out.println("抓取完毕，本次抓取到" + ge.getNum() + "张图片");
	}
}