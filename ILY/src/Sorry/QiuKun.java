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
	static JButton jb2 = new JButton("��ť2");
	public static void jbut2(int[] it) {
//		int w = 650 ;
//		int h = 450 ;
		for(int i :it) {
			System.out.println(i+" ");
		}
		jb2.setBounds(it[0], it[1], 90, 40);//��ť2��λ�úʹ�С
		jf.getContentPane().add(jb2);//��Ӱ�ť
		
	}
	public static void main(String[] args) {
		JLayeredPane jl = new JLayeredPane();
		jt.setText("�����ı���");//�����ı�����
		JButton jb1 = new JButton("��ť1");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�޷��رմ���
		jf.getContentPane().setLayout(null);//���ò���Ϊ��
		jf.setResizable(false);
		jf.setSize(900,600);//���ڴ�С
		jf.setLocation(350, 100);//����λ��
		jf.getContentPane().add(jb1);//��Ӱ�ť
		jb1.setBounds(150, 450, 90, 40);//��ť1��λ�úʹ�С
		
		
		mosejb();
		
		
		jt.setBounds(50, 50, 790, 350);
		jt.setRows(5);//��
		jt.setColumns(20);//��
		jt.append("�������");
		jt.setEditable(false);//�����������޷������±༭
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
				System.out.println("������밴ť����");
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
