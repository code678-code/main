package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FromP {

    /**
     * 浠庤緭鍏ユ祦涓幏鍙栧瓧绗︿覆
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        System.out.println(new String(bos.toByteArray(),"utf-8"));
        return new String(bos.toByteArray(),"utf-8");
    }

    /**
     * 杩斿洖缃戦〉鏂囨湰
     * @param urlStr
     * @return
     */
    private static String readFileByUrl(String urlStr) {
        String res=null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //璁剧疆瓒呮椂闂翠负3绉�
            conn.setConnectTimeout(3*1000);
            //闃叉灞忚斀绋嬪簭鎶撳彇鑰岃繑鍥�403閿欒
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //寰楀埌杈撳叆娴�
            InputStream inputStream = conn.getInputStream();
            res = readInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *瀵逛簬澶ч儴鍒嗗浘鐗囨潵璇达紝鏈変釜id鍙蜂负鍚嶅瓧锛岃繖涓氨鏄幏鍙栧浘鐗囩殑id鍚�
     */
    private static String patternUtil(String str){
        Pattern pattern = Pattern.compile("id=[0-9]+");
        Matcher matcher = pattern.matcher(str);
        String doc = "";
        while(matcher.find()){
            doc += matcher.group();
        }
        return doc;
    }

    /**
     * 瀵硅幏鍙栫殑鏂囨。搴忓垪鍖�
     * @param url
     * @return
     */
    private static Document parse(String url){
        String html =readFileByUrl(url);
        Document doc = Jsoup.parse(html,"utf-8");
        return doc;
    }

    /**
     * 鑾峰彇鍥剧墖鐨勭埗鐩掑瓙
     * @param url
     * @return
     */
    private static Elements getEl(String url){
        Elements els = parse(url).select(".content_nr");
        return els;
    }

    /**
     * 鏌ヨ褰撳墠鐨勫浘鐗囨湁鍑犻〉
     * @param url
     * @return
     */
    private static  int getPages(String url){
        try{
            Elements pages = parse(url).select(".pagenum");
            return pages.size();
        }catch (Exception e){
            return 1;
        }
    }



    /**
     * 鐩存帴涓嬭浇鍥剧墖锛屽悕瀛楃敤uuid鍚嶅瓧
     * 鐩存帴鍐嶄竴涓摼鎺ヤ笅杞�
     */
    private static void beginDownload(String path,String url){
        Elements els = getEl(url);
        Elements imgs = els.select("img");
        int len = imgs.size();
        List<ImgPojo> list = new ArrayList<ImgPojo>();
        //鍖瑰鍥剧墖鐩稿搴旂殑id鍚�
        for (int i = 0;i<len-1;i++){
            String id = UUID.randomUUID().toString();
            String src = imgs.get(i).attr("src");
            ImgPojo imgPojo = new ImgPojo(id,src);
            list.add(imgPojo);
        }
        new Download().downloadList(path, list);
    }

    /**
     * p绔欏ぇ閮ㄥ垎鐨勫ご鍍忓浘鐗囬兘鏈変竴涓猧d鍚嶏紝鎵�浠ヤ互姝ゅ懡鍚嶄簡銆�
     * 褰撶劧鍑℃槸鏈塱d鍚嶇殑鍥剧墖搴旇鏄兘鑳戒笅杞�
     */
    public static void beginDownloadHeadPortrait(String path,String url){
        Elements els = getEl(url);
        Elements imgs = els.select("img");
        String[] ids = patternUtil(els.toString()).split("id=");
        int len = imgs.size();
        List<ImgPojo> list = new ArrayList<ImgPojo>();
        //鍖瑰鍥剧墖鐩稿搴旂殑id鍚�
        for (int i = 0;i<len-1;i++){
            String id = ids[i+1];
            String src = imgs.get(i).attr("src");
            ImgPojo imgPojo = new ImgPojo(id,src);
            list.add(imgPojo);
        }
        new Download().downloadList(path, list);
    }

    /**
     * 浼犲叆鐨勯摼鎺ュ繀椤绘槸.html缁撳熬鐨勯摼鎺�
     * @param url1
     * @param path
     */
    public static void beginDownloadImg(String path,String url1){
        String page = "";
        int pages = getPages(url1);
        List<String> urls = new ArrayList<String>();
        urls.add(url1);
        for (int i=2;i<=pages;i++)
        {
            page= "_"+i+".html";
            String url = url1.split(".html")[0]+page;
            urls.add(url);
        }
        for (String i:urls) beginDownload(path,i);
    }
}