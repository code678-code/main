package Sorry;

import javax.swing.JButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class QiuKun {
	static int i=0;
	static JFrame jf = new JFrame("JFrame");
	static JPanel jp = new JPanel();
	static JTextArea jt=new JTextArea();
	static JButton jb2 = new JButton("按钮2");
	public static void jbut2(int[] it) {
//		int w = 650 ;
//		int h = 450 ;
		for(int i :it) {
			System.out.println(i+" ");
		}
		jb2.setBounds(it[0], it[1], 90, 40);//按钮2的位置和大小
		jf.getContentPane().add(jb2);//添加按钮
		
	}
	public static void main(String[] args) {
		JLayeredPane jl = new JLayeredPane();
		jt.setText("这是文本域");//设置文本内容
		JButton jb1 = new JButton("按钮1");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//无法关闭窗口
		jf.getContentPane().setLayout(null);//设置布局为空
		jf.setResizable(false);
		jf.setSize(900,600);//窗口大小
		jf.setLocation(350, 100);//窗口位置
		jf.getContentPane().add(jb1);//添加按钮
		jb1.setBounds(150, 450, 90, 40);//按钮1的位置和大小
		
		
		mosejb();
		
		
		jt.setBounds(50, 50, 790, 350);
		jt.setRows(5);//行
		jt.setColumns(20);//列
		jt.append("添加内容");
		jt.setEditable(false);//设置文字域无法被重新编辑
		jf.getContentPane().add(jt);
		jf.setVisible(true);  
		jb2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("鼠标移入按钮区域");
				mosejb();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});

	}
	protected static int[] mosejb() {
		int nw = 1+(int)(Math.random()*800);
		int nh = 1+(int)(Math.random()*500);
		System.out.println(nw+"----"+nh);
		int[] it = new int[2];
		it[0] =nw;
		it[1] =nh;
		System.out.println("++++>>>"+i);
		if(i==0) {
			it[0] = 650;
			it[1] = 450;
		}
		i++;
		jbut2(it);
		return it;
	}

}
