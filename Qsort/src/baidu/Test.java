package baidu;

import java.io.IOException;
public class Test {

	public static void main(String[] args) {
		String basepath = "D://test";
		DownBaiduPicture dbp = new DownBaiduPicture(basepath);
		dbp.setPicture("贵州梵净山", 2 , 1 , 1920 , 1080);
		try {
			dbp.downLoad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("网络不通！");
			//https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1580907407497_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=1920&height=1080&face=0&istype=2&ie=utf-8&sid=&word=贵州梵净山
		}
	}
}
