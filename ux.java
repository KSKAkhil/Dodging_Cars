import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class A
	{
	public static void main(String []args)
		{
		JFrame f=new JFrame("End Game..");
		f.setSize(1000,1000);
		f.setVisible(true);
		Font f1=new Font("Sans Serif",Font.BOLD,80);
		Font f2=new Font("Sans Serif",Font.BOLD,45);
		JLabel l=new JLabel("End Game..");
		l.setFont(f1);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		l.setBounds(500,30,400,200);
		f.add(l);
		f.setLayout(null);
		JButton b1=new JButton("Noob");
		JButton b2=new JButton("Pro");
		JButton b3=new JButton("Quit");
		b1.setBounds(550,200,300,60);
		b2.setBounds(550,300,300,60);
		b3.setBounds(550,400,300,60);
		b1.setFont(f2);
		b2.setFont(f2);
		b3.setFont(f2);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		b1.addActionListener(new B());
		b2.addActionListener(new C());
		b3.addActionListener(new D());
		}
	
	}
class B implements ActionListener
		{
		public void actionPerformed(ActionEvent e)
			{
			
			EventQueue.invokeLater(() ->{
			JFrame f2=new X();
			f2.setVisible(true);
			f2.setSize(500,500);
			});
		}
class C implements ActionListener
		{
		public void actionPerformed(ActionEvent e)
			{
			JFrame f3=new JFrame("Settings Frame");
			f3.setLayout(new FlowLayout());
			f3.setSize(400,400);
			Font f1=new Font("Sans Serif",Font.BOLD,30);
			JLabel l1=new JLabel("Sound");
			l1.setBounds(150,10,400,200);
			l1.setFont(f1);
			JLabel l2=new JLabel("Brightness");
			l2.setBounds(150,10,400,200);
			l2.setFont(f1);
			JSlider s=new JSlider(JSlider.HORIZONTAL, 0, 300, 25);
			s.setBounds(200,100,0,300);
			f3.add(l1);
			JSlider s1=new JSlider(JSlider.HORIZONTAL, 0, 300, 25);
			s1.setBounds(200,100,0,300);
			f3.add(s); 
			f3.add(l2);
			f3.add(s1);
			f3.setVisible(true);
			f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f3.setResizable(false);
			}
		}
class D implements ActionListener
		{
		public void actionPerformed(ActionEvent e)
			{
			System.exit(0);
			}
		}
public class X extends JFrame{
public X(){
initUi();
}
public void initUi(){
add(new Game());
setResizable(false);
pack();
setLocationRelativeTo(null);
}
}
}