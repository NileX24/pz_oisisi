package projektic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.Image;
import java.util.*;
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
			
				
				JTabbedPane tabbedPane = new JTabbedPane();
				window.add(tabbedPane);
				tabbedPane.setFocusable(false);
				
				JToolBar tb=new JToolBar();
				
				
				JLabel label=new JLabel("Placeholder");
				
				
				JMenuBar menubar= new JMenuBar();
				window.setJMenuBar(menubar);
				//file bar
				
				JMenu file=new JMenu("File");
				menubar.add(file);
				JMenu novi=new JMenu("New");
				JMenuItem pro_zap=new JMenuItem("Zaposleni");
				JMenuItem pro_soft=new JMenuItem("Softver");
				novi.add(pro_zap);
				pro_zap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Dodaj Zaposlenih",label);
					}
				});
				novi.add(pro_soft);
				pro_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Dodaj Softvera",label);
					}
				});
				file.add(novi);
				JMenu open=new JMenu("Open");
				JMenuItem tab_zap=new JMenuItem("Zaposleni");
				JMenuItem tab_soft=new JMenuItem("Softver");
				open.add(tab_zap);
				tab_zap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Tabela Zaposlenih",label);
					}
				});
				open.add(tab_soft);
				tab_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Tabela Softvera",label);
					}
				});
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
				edit_zap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Izmena Zaposleni",label);
					}
				});
				izmena.add(edit_soft);
				edit_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Izmena Softvera",label);
					}
				});
				edit.add(izmena);
				JMenu delete=new JMenu("Delete");
				JMenuItem del_zap=new JMenuItem("Zaposleni");
				JMenuItem del_soft=new JMenuItem("Softver");
				delete.add(del_zap);
				del_zap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Obrisi Zaposleni",label);
					}
				});
				delete.add(del_soft);
				del_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Obrisi Softver",label);
					}
				});
				edit.add(delete);
				
				JMenu help=new JMenu("Help");
				menubar.add(help);
				JMenuItem about=new JMenuItem("About");
				help.add(about);
				about.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        tabbedPane.addTab("O nama",label);
                    }
                });
				
				final ImageIcon icon_plus = new ImageIcon(getClass().getResource("add.png"));
				final ImageIcon icon_edit = new ImageIcon(getClass().getResource("edit.png"));
				final ImageIcon icon_del = new ImageIcon(getClass().getResource("delete.png"));
				JButton btn_plus=new JButton("");
				btn_plus.setIcon(icon_plus);
				tb.add(btn_plus);
				JButton btn_edit=new JButton("");
				btn_edit.setIcon(icon_edit);
				tb.add(btn_edit);
				JButton btn_del=new JButton("");
				btn_del.setIcon(icon_del);
				tb.add(btn_del);
				window.add(tb,BorderLayout.NORTH);
				
				btn_plus.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	//kod
                    }
                });
				btn_edit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	//kod
                    }
                });
				btn_del.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	//kod
                    }
                });
				//preuzeto sa stackoverflow-a
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); 
				Date date=new Date();
				JLabel status=new JLabel(formatter.format(date),SwingConstants.RIGHT);
				window.add(status,BorderLayout.SOUTH);
				
				
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

