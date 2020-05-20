package com;



import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class FileUtils {
	private static String URL = "https://vacations.ctrip.com/tour/detail/p1797091s2.html#ctm_ref=ssc_hp_tour_ot_pro_01";
	
	public static void main(String[] args) throws IOException {

			Connection conn = Jsoup.connect(URL);
			Document doc = conn.timeout(10000).get();
			Elements list = doc.getElementsByTag("img");
			for(int i=0;i<list.size();i++) {
				Element li = list.get(i);
				Element img = li.getElementsByTag("img").first();
				String imgURL = img.attr("src");
				System.out.println(imgURL);
			}
	
			
		
		
	}


}