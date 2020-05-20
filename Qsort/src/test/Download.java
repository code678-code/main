package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Download {
  public void downloadList(String path,List<ImgPojo> list) {
    try {
      for (ImgPojo imgPojo : list) downloadLocal(path,imgPojo.getId(), imgPojo.getSrc());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void downloadLocal(String path,String name, String imgUrl) throws Exception {
    // new涓�涓猆RL瀵硅薄
    URL url = new URL(imgUrl);
    // 鎵撳紑閾炬帴
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // 璁剧疆璇锋眰鏂瑰紡涓�"GET"
    conn.setRequestMethod("GET");
    // 瓒呮椂鍝嶅簲鏃堕棿涓�5绉�
    conn.setConnectTimeout(5 * 1000);
    // 閫氳繃杈撳叆娴佽幏鍙栧浘鐗囨暟鎹�
    InputStream inStream = conn.getInputStream();
    // 寰楀埌鍥剧墖鐨勪簩杩涘埗鏁版嵁锛屼互浜岃繘鍒跺皝瑁呭緱鍒版暟鎹紝鍏锋湁閫氱敤鎬�
    byte[] data = readInputStream(inStream);
    // new涓�涓枃浠跺璞＄敤鏉ヤ繚瀛樺浘鐗囷紝榛樿淇濆瓨褰撳墠宸ョ▼鏍圭洰褰�
    File imageFile = new File(path+"\\id="+name+".jpg");
    // 鍒涘缓杈撳嚭娴�
    FileOutputStream outStream = new FileOutputStream(imageFile);
    // 鍐欏叆鏁版嵁
    outStream.write(data);
    // 鍏抽棴杈撳嚭娴�
    outStream.close();
    System.out.println(name+"涓嬭浇ok");
  }

  public byte[] readInputStream(InputStream inStream) throws Exception {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // 鍒涘缓涓�涓狟uffer瀛楃涓�
    byte[] buffer = new byte[1024];
    // 姣忔璇诲彇鐨勫瓧绗︿覆闀垮害锛屽鏋滀负-1锛屼唬琛ㄥ叏閮ㄨ鍙栧畬姣�
    int len = 0;
    // 浣跨敤涓�涓緭鍏ユ祦浠巄uffer閲屾妸鏁版嵁璇诲彇鍑烘潵
    while ((len = inStream.read(buffer)) != -1) {
      // 鐢ㄨ緭鍑烘祦寰�buffer閲屽啓鍏ユ暟鎹紝涓棿鍙傛暟浠ｈ〃浠庡摢涓綅缃紑濮嬭锛宭en浠ｈ〃璇诲彇鐨勯暱搴�
      outStream.write(buffer, 0, len);
    }
    // 鍏抽棴杈撳叆娴�
    inStream.close();
    // 鎶妎utStream閲岀殑鏁版嵁鍐欏叆鍐呭瓨
    return outStream.toByteArray();
  }
}
