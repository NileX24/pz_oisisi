package projektic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;

public class prozor{
		public static void main(String[] args)
		{
			
			new prozor();
		}
		
		public prozor(){
			
				JFrame window=new JFrame();
				window.setTitle("Pojekat");
				// skinuto sa stack overflow-a
				Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
				int sirina=(int) screenSize.getWidth();
				int visina=(int )screenSize.getHeight();
				window.setSize(sirina/4*3,visina/4*3);
				window.setLocation(sirina/8, visina/8);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
				JMenuBar menubar= new JMenuBar();
				window.setJMenuBar(menubar);
				//file bar
				
				JMenu file=new JMenu("File");
				menubar.add(file);
				JMenuItem novi=new JMenuItem("New");
				file.add(novi);
				JMenuItem open=new JMenuItem("Open");
				file.add(open);
				JMenuItem exit=new JMenuItem("Exit");
				file.add(exit);
				exit.addActionListener(new zatvori());
				//edit
				
				JMenu edit=new JMenu("Edit");
				menubar.add(edit);
				JMenu help=new JMenu("Help");
				menubar.add(help);
				
				
				JTabbedPane tabbedPane = new JTabbedPane();
				window.add(tabbedPane);
				tabbedPane.setFocusable(false);
				//tabbedPane.setPreferredSize(new Dimension(50,50));
				
				JLabel label1=new JLabel("egafgawfaaga");
				JLabel label2=new JLabel("dfawfa");
				
				tabbedPane.add("Zaposleni",label1);
				tabbedPane.add("Softver",label2);
				
				window.setVisible(true);
				
		}
		static class zatvori implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
}

