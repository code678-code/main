package dasd;




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class lianxi {

    /**
     * @param args
     * ��ϰ�⣺ ��һ��ͼƬ����������һ���ط���
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO �Զ����ɵķ������
        String str1 = "D:\\Users\\cdlx2016\\Desktop\\1\\12�ַ���.png";
        String str2 = "D:\\Users\\cdlx2016\\Desktop\\2\\12�ַ���.png";
//        copyFile1(str1, str2);
//        copyFile2(str1, str2);
        copyFile3(str1, str2);
        
    }
    // ����һ
    public static void copyFile1(String srcPath, String destPath) throws IOException {
        // ��������
        FileInputStream fis = new FileInputStream(srcPath);
        // �������
        FileOutputStream fos = new FileOutputStream(destPath);
        
        // ��ȡ��д����Ϣ
        int len = 0;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }
        
        // �ر���  �ȿ����  ���ȹ�
        fos.close(); // ���ȹ�
        fis.close(); // �ȿ����
        
    }
    // ������
    public static void copyFile2(String srcPath, String destPath) throws IOException {
        
        // ��������
        FileInputStream fis = new FileInputStream(srcPath);
        // �������
        FileOutputStream fos = new FileOutputStream(destPath);
        
        // ��ȡ��д����Ϣ
        int len = 0;
        // ����һ���ֽ����飬����������
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b);
        }
        
        // �ر���  �ȿ����  ���ȹ�
        fos.close(); // ���ȹ�
        fis.close(); // �ȿ����
        
    }
    // ������
    public static void copyFile3(String srcPath, String destPath) throws IOException {
        
        // ��������
        FileInputStream fis = new FileInputStream(srcPath);
        // �������
        FileOutputStream fos = new FileOutputStream(destPath);
        
        // ��ȡ��д����Ϣ
        int len = 0;
        // ����һ���ֽ����飬����������
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        
        // �ر���  �ȿ����  ���ȹ�
        fos.close(); // ���ȹ�
        fis.close(); // �ȿ����
        
    }

}