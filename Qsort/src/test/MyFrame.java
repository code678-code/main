package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {
    JTextField filepath=new JTextField();
    JTextField hrefurl = new JTextField();
    JPanel alert = new JPanel();
    JButton bl=new JButton("开始");
    JButton bg=new JButton("关闭");
    JButton btn = new JButton("选择");

    public MyFrame(){
        this.setBounds(200,200,400,145);
        Container c = getContentPane();
        c.setLayout(new GridLayout(3,3,5,15));
//        c.setLayout(new FlowLayout(FlowLayout.LEFT));
        c.add(new JLabel("下载图片的路径"));
        filepath.setSize(30,100);
        c.add(filepath);
        c.add(btn);

        c.add(new JLabel("网页链接"));
        hrefurl.setSize(30,100);
        c.add(hrefurl);
        c.add(new JLabel("只能是p站的链接"));

        c.add(bl);
        c.add(bg);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        bg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //鎸夐挳鐐瑰嚮浜嬩欢
                JFileChooser chooser = new JFileChooser();             //璁剧疆閫夋嫨鍣�
//                chooser.setMultiSelectionEnabled(true);             //璁句负澶氶��
                chooser.setCurrentDirectory(new File("D:\\"));
                chooser.setFileSelectionMode(1);                     //鍙兘閫夋嫨鏂囦欢澶�
                int returnVal = chooser.showOpenDialog(btn);        //鏄惁鎵撳紑鏂囦欢閫夋嫨妗�
                System.out.println("returnVal="+returnVal);

                if (returnVal == JFileChooser.APPROVE_OPTION) {          //濡傛灉绗﹀悎鏂囦欢绫诲瀷

                    String path = chooser.getSelectedFile().getAbsolutePath();      //鑾峰彇缁濆璺緞
                    System.out.println(path);
//                    System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());  //杈撳嚭鐩稿璺緞
                    filepath.setText(path);
                }
            }
        });

        bl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = filepath.getText();
                String url = hrefurl.getText();
                try{
                    FromP.beginDownloadImg(path,url);
                    JOptionPane.showMessageDialog(alert,"下载成功");
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(alert,"下载失败");
                }
            }
        });


    }
    public static void main(String[] args){
        new MyFrame();
    }
}
