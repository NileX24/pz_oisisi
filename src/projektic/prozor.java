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
				JMenu novi=new JMenu("New");
				JMenuItem pro_zap=new JMenuItem("Zaposleni");
				JMenuItem pro_soft=new JMenuItem("Softver");
				novi.add(pro_zap);
				novi.add(pro_soft);
				file.add(novi);
				JMenu open=new JMenu("Open");
				JMenuItem tab_zap=new JMenuItem("Zaposleni");
				JMenuItem tab_soft=new JMenuItem("Softver");
				open.add(tab_zap);
				open.add(tab_soft);
				file.add(open);
				JMenuItem exit=new JMenuItem("Exit");
				file.add(exit);
				exit.addActionListener(new zatvori());
				//edit
				
				JMenu edit=new JMenu("Edit");
				menubar.add(edit);
				JMenu izmena=new JMenu("Edit");
				JMenuItem edit_zap=new JMenuItem("Zaposleni");
				JMenuItem edit_soft=new JMenuItem("Softver");
				izmena.add(edit_zap);
				izmena.add(edit_soft);
				edit.add(izmena);
				JMenu delete=new JMenu("Delete");
				JMenuItem del_zap=new JMenuItem("Zaposleni");
				JMenuItem del_soft=new JMenuItem("Softver");
				delete.add(del_zap);
				delete.add(del_soft);
				edit.add(delete);
				
				JMenu help=new JMenu("Help");
				menubar.add(help);
				JMenuItem about=new JMenuItem("About");
				help.add(about);
				
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

