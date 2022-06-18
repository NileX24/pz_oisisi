package projektic;

import projektic.Zaposleni;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.Image;
import java.util.*;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class prozor{
		static List<Zaposleni> zaposleni=new ArrayList<Zaposleni>();
		Zaposleni radnik=new Zaposleni();
		static List<Softver> softver=new ArrayList<Softver>();
		Softver program=new Softver();
		public int tabPressed=0;
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
				window.setMaximumSize(screenSize);
				screenSize= new Dimension(sirina/3,visina/4*3);
				window.setMinimumSize(screenSize);
				
				radnik.ime="Petar";
				radnik.prezime="Petrovic";
				radnik.jmbg="1234567890123";
				radnik.datum_rodjenja="12.12.1976";
				radnik.email="petarpetrovic@gmail.com";
				radnik.adresa="Futoska 1234,Novi Sad";
				radnik.softver="3Ds Max";
				zaposleni.add(radnik);
				
				program.naziv="3DS max";
				program.cetkica="standard";
				program.format="fbx";
				program.alat="Cut";
				program.render="Vray";
				softver.add(program);
				
				
				JTabbedPane tabbedPane = new JTabbedPane();
				window.add(tabbedPane);
				tabbedPane.setFocusable(false);
				
				JPanel panel_zap=new JPanel();
				JPanel panel_soft=new JPanel();
				
				tabbedPane.add("Zaposleni",panel_zap);
				panel_zap.setLayout(new GridLayout(1,0));
				
				ispisiZaposlene(panel_zap,zaposleni);
				
				tabbedPane.add("Softver",panel_soft);
				panel_soft.setLayout(new GridLayout(1,0));
				
				ispisiSoftver(panel_soft,softver);
				
				// preuzeto sa https://docs.oracle.com/javase/8/docs/api/javax/swing/JTabbedPane.html#getSelectedIndex--
				tabbedPane.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						int k=tabbedPane.getSelectedIndex();
						if(k==0||k==1)
							tabPressed = k;
						System.out.println(tabPressed);
			        }
					
				});
				
				
				
				
				JToolBar tb=new JToolBar();
				
				
				JLabel label=new JLabel("Placeholder");
				JPanel panel=new JPanel();
				JPanel panel2=new JPanel();
				
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
						
						tabbedPane.addTab("Dodaj Zaposlene",panel);
						panel.removeAll();
						ispisiUnosZaposlenog(panel, sirina, visina,panel_zap);
					}
				});
				novi.add(pro_soft);
				pro_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Dodaj Softver",panel);
						panel.removeAll();
						ispisiUnosSoftvera(panel, sirina, visina, panel_soft);
					}
				});
				file.add(novi);
				JMenu open=new JMenu("Open");
				JMenuItem tab_zap=new JMenuItem("Zaposleni");
				JMenuItem tab_soft=new JMenuItem("Softver");
				open.add(tab_zap);
				tab_zap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Tabela Zaposlenih",panel);
						panel.removeAll();
						panel.setLayout(new GridLayout(1,0));
						ispisiZaposlene(panel,zaposleni);
					}
				});
				open.add(tab_soft);
				tab_soft.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tabbedPane.addTab("Tabela Softvera",panel);
						panel.removeAll();
						panel.setLayout(new GridLayout(1,0));
						ispisiSoftver(panel,softver);
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
						tabbedPane.addTab("Izmena Zaposleni",panel2);
						// dropdown list : https://youtu.be/vd-k2oBMXUI
						panel2.removeAll();
						panel2.setLayout(new GridLayout(25,25));
						String[] box_data=new String[zaposleni.size()];
						int i=0;
						for(Zaposleni z:zaposleni) {
							box_data[i]=z.ime+" "+z.prezime;
							i++;
							}
						JList box=new JList(box_data);
						box.setVisibleRowCount(5);
						box.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						box.addListSelectionListener(
								new ListSelectionListener() {
									int p=0;
									@Override
									public void valueChanged(ListSelectionEvent e) {
										
										p=box.getSelectedIndex();
										System.out.println(p);
										ispisiIzmenuZaposlenog(panel2, sirina, visina, panel_zap,p);
									}
								}
						);
						
						
						panel2.add(new JScrollPane(box));
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
				
				ImageIcon icon_plus = new ImageIcon(getClass().getResource("add.png"));
				ImageIcon icon_edit = new ImageIcon(getClass().getResource("edit.png"));
				ImageIcon icon_del = new ImageIcon(getClass().getResource("delete.png"));
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
                    	JFrame plus=new JFrame();
                    	Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        				int sirina=(int) screenSize.getWidth();
        				int visina=(int )screenSize.getHeight();
        				screenSize= new Dimension(sirina/3,visina/4*3);
        				plus.setLocation(sirina/3, visina/8);
        				plus.setSize(new Dimension(sirina/3,visina/4*3));
        				plus.setResizable(false);
        				plus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    					//plus.setLayout(new GridLayout(0,1));
                    	if(tabPressed==0) {
                    		plus.setTitle("Unesi novog zaposlenog");
                    		ispisiUnosZaposlenog(panel, sirina, visina, panel_zap);
                    	}
                    	else {
                    		plus.setTitle("Unesi novi softver");
                    		ispisiUnosSoftvera(panel, sirina, visina, panel_soft);
                    	}
                    	plus.add(panel);
                    	plus.setVisible(true);
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
				
				
				
				
				
				window.setVisible(true);
				
		}
		
		
		static class zatvori implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		
		
		static void ispisiZaposlene(JPanel panel,List<Zaposleni> zaposleni)
		{
			panel.removeAll();
			String[][] data_zap= new String[zaposleni.size()][7];
			int i=0;
			for (Zaposleni z:zaposleni) {
				
				data_zap[i][0]=z.ime;
				data_zap[i][1]=z.prezime;
				data_zap[i][2]=z.jmbg;
				data_zap[i][3]=z.datum_rodjenja;
				data_zap[i][4]=z.email;
				data_zap[i][5]=z.adresa;
				data_zap[i][6]=z.softver;
				i+=1;
				
			}
			String[] redovi_zap= {"Ime","Prezime","JMBG","Datum Rodjenja","Email","Adresa","Softver"};
			JTable tabela_zap=new JTable(data_zap,redovi_zap);
			JScrollPane scroll_zap=new JScrollPane(tabela_zap);
			scroll_zap.setBorder(null);
			panel.add(scroll_zap);
		}
		
		
		static void ispisiSoftver(JPanel panel,List<Softver> softver)
		{
			panel.removeAll();
			String[][] data= new String[softver.size()][5];
			int i=0;
			for (Softver z:softver) {
				
				data[i][0]=z.naziv;
				data[i][1]=z.cetkica;
				data[i][2]=z.format;
				data[i][3]=z.alat;
				data[i][4]=z.render;
				i+=1;
				
			}
			String[] redovi= {"Naziv","Cetkica","Format","Alat","Render"};
			JTable tabela=new JTable(data,redovi);
			JScrollPane scroll=new JScrollPane(tabela);
			scroll.setBorder(null);
			panel.add(scroll);
		}
		
		
		static void ispisiUnosZaposlenog(JPanel panel,int sirina,int visina,JPanel panel_zap) {
			
			panel.removeAll();
			panel.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
			panel.setLayout(new GridLayout(25,25));
			
			JLabel promenljiv=new JLabel("Unesi Zaposlene",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			promenljiv=new JLabel("Ime : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_001=new JTextField();
			polje_001.setHorizontalAlignment(JTextField.CENTER);
			polje_001.setBorder(null);
			panel.add(polje_001);
			
			promenljiv=new JLabel("Prezime : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_002=new JTextField();
			polje_002.setHorizontalAlignment(JTextField.CENTER);
			polje_002.setBorder(null);
			panel.add(polje_002);
			
			promenljiv=new JLabel("JMBG : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_003=new JTextField();
			polje_003.setHorizontalAlignment(JTextField.CENTER);
			polje_003.setBorder(null);
			polje_003.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(polje_003.getText().length() >=13)
						e.consume();
					char key=e.getKeyChar();
					if(!(key>='0' && key<='9'))
						e.consume();
				}
			});
			panel.add(polje_003);
			
			promenljiv=new JLabel("Datum Rodjenja : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_004=new JTextField();
			polje_004.setHorizontalAlignment(JTextField.CENTER);
			polje_004.setBorder(null);
			polje_004.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(polje_004.getText().length() >=10)
						e.consume();
					char key=e.getKeyChar();
					if(!(key>='0' && key<='9'))
						e.consume();
					String slash=polje_004.getText();
					if(polje_004.getText().length()==2||polje_004.getText().length()==5)
						polje_004.setText(slash+".");
				}
			});
			panel.add(polje_004);
			
			promenljiv=new JLabel("Email : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_005=new JTextField();
			polje_005.setHorizontalAlignment(JTextField.CENTER);
			polje_005.setBorder(null);
			panel.add(polje_005);
			
			
			promenljiv=new JLabel("Adresa Stanovanja : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_006=new JTextField();
			polje_006.setHorizontalAlignment(JTextField.CENTER);
			polje_006.setBorder(null);
			panel.add(polje_006);
			
			promenljiv=new JLabel("Softveri : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_007=new JTextField();
			polje_007.setHorizontalAlignment(JTextField.CENTER);
			polje_007.setBorder(null);
			panel.add(polje_007);
			
			promenljiv=new JLabel("  ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JButton pokupi_zap=new JButton("Upisi Zaposlenog");
			
			pokupi_zap.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					JFrame proveri=new JFrame();
					proveri.setSize(400, 200);
					proveri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					proveri.setLayout(new GridLayout(0,1));
					String[] labela= new String[7];
					labela[0]=polje_001.getText();
					labela[1]=polje_002.getText();
					labela[2]=polje_003.getText();
					labela[3]=polje_004.getText();
					labela[4]=polje_005.getText();
					labela[5]=polje_006.getText();
					labela[6]=polje_007.getText();
					int count=0;
					for(int i=0;i<6;i++)
						if(labela[i].isEmpty())
							count++;
					
					if(count>0)
					{
						proveri.setTitle("Greska");
						JLabel greska=new JLabel("Sva polja moraju biti popunjena",SwingConstants.CENTER);
						proveri.add(greska);
						
						JButton kraj=new JButton("CLOSE");
						proveri.add(kraj);
						
						
						kraj.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
					else {
						proveri.setTitle("Unos");
						JLabel upseh=new JLabel("Da li ste sigurni da zelite da unesete Zaposlenog",SwingConstants.CENTER);
						proveri.add(upseh);
						
						JButton da=new JButton("DA");
						proveri.add(da);
						
						
						da.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								Zaposleni radnik1=new Zaposleni();
								radnik1.ime=labela[0];
								radnik1.prezime=labela[1];
								radnik1.jmbg=labela[2];
								radnik1.datum_rodjenja=labela[3];
								radnik1.email=labela[4];
								radnik1.adresa=labela[5];
								radnik1.softver=labela[6];
								zaposleni.add(radnik1);
									System.out.println(zaposleni);
								polje_001.setText(null);
								polje_002.setText(null);
								polje_003.setText(null);
								polje_004.setText(null);
								polje_005.setText(null);
								polje_006.setText(null);
								polje_007.setText(null);
								ispisiZaposlene(panel_zap,zaposleni);
								proveri.dispose();
								
							}
						});
						
						
						JButton ne=new JButton("NE");
						proveri.add(ne);
						
						ne.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
				}
			});
			panel.add(pokupi_zap);
		}
		
		
			static void ispisiIzmenuZaposlenog(JPanel panel,int sirina,int visina,JPanel panel_zap, int index) {
			
			panel.removeAll();
			panel.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
			panel.setLayout(new GridLayout(25,25));
			
			Zaposleni radnik2=new Zaposleni();
			int k=0;
			for(Zaposleni z:zaposleni)
			{
				if(index==k)
					radnik2=z;
				k++;
			}
			
			JLabel promenljiv=new JLabel("Izmeni Zaposlenog",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			promenljiv=new JLabel("Ime : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_001=new JTextField();
			polje_001.setHorizontalAlignment(JTextField.CENTER);
			polje_001.setText(radnik2.ime);
			polje_001.setBorder(null);
			panel.add(polje_001);
			
			promenljiv=new JLabel("Prezime : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_002=new JTextField();
			polje_002.setHorizontalAlignment(JTextField.CENTER);
			polje_002.setText(radnik2.prezime);
			polje_002.setBorder(null);
			panel.add(polje_002);
			
			promenljiv=new JLabel("JMBG : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_003=new JTextField();
			polje_003.setHorizontalAlignment(JTextField.CENTER);
			polje_003.setBorder(null);
			polje_003.setText(radnik2.jmbg);
			polje_003.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(polje_003.getText().length() >=13)
						e.consume();
					char key=e.getKeyChar();
					if(!(key>='0' && key<='9'))
						e.consume();
				}
			});
			panel.add(polje_003);
			
			promenljiv=new JLabel("Datum Rodjenja : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_004=new JTextField();
			polje_004.setHorizontalAlignment(JTextField.CENTER);
			polje_004.setBorder(null);
			polje_004.setText(radnik2.datum_rodjenja);
			polje_004.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(polje_004.getText().length() >=10)
						e.consume();
					char key=e.getKeyChar();
					if(!(key>='0' && key<='9'))
						e.consume();
					String slash=polje_004.getText();
					if(polje_004.getText().length()==2||polje_004.getText().length()==5)
						polje_004.setText(slash+".");
				}
			});
			panel.add(polje_004);
			
			promenljiv=new JLabel("Email : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_005=new JTextField();
			polje_005.setHorizontalAlignment(JTextField.CENTER);
			polje_005.setBorder(null);
			polje_005.setText(radnik2.email);
			panel.add(polje_005);
			
			
			promenljiv=new JLabel("Adresa Stanovanja : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_006=new JTextField();
			polje_006.setHorizontalAlignment(JTextField.CENTER);
			polje_006.setBorder(null);
			polje_006.setText(radnik2.adresa);
			panel.add(polje_006);
			
			promenljiv=new JLabel("Softveri : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_007=new JTextField();
			polje_007.setHorizontalAlignment(JTextField.CENTER);
			polje_007.setBorder(null);
			polje_007.setText(radnik2.softver);
			panel.add(polje_007);
			
			promenljiv=new JLabel("  ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JButton pokupi_zap=new JButton("Upisi Zaposlenog");
			
			pokupi_zap.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					JFrame proveri=new JFrame();
					proveri.setSize(400, 200);
					proveri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					proveri.setLayout(new GridLayout(0,1));
					String[] labela= new String[7];
					labela[0]=polje_001.getText();
					labela[1]=polje_002.getText();
					labela[2]=polje_003.getText();
					labela[3]=polje_004.getText();
					labela[4]=polje_005.getText();
					labela[5]=polje_006.getText();
					labela[6]=polje_007.getText();
					int count=0;
					for(int i=0;i<6;i++)
						if(labela[i].isEmpty())
							count++;
					
					if(count>0)
					{
						proveri.setTitle("Greska");
						JLabel greska=new JLabel("Sva polja moraju biti popunjena",SwingConstants.CENTER);
						proveri.add(greska);
						
						JButton kraj=new JButton("CLOSE");
						proveri.add(kraj);
						
						
						kraj.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
					else {
						proveri.setTitle("Unos");
						JLabel upseh=new JLabel("Da li ste sigurni da zelite da unesete Zaposlenog",SwingConstants.CENTER);
						proveri.add(upseh);
						
						JButton da=new JButton("DA");
						proveri.add(da);
						
						
						da.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								Zaposleni radnik1=new Zaposleni();
								radnik1.ime=labela[0];
								radnik1.prezime=labela[1];
								radnik1.jmbg=labela[2];
								radnik1.datum_rodjenja=labela[3];
								radnik1.email=labela[4];
								radnik1.adresa=labela[5];
								radnik1.softver=labela[6];
								zaposleni.set(index, radnik1);
									System.out.println(zaposleni);
								ispisiZaposlene(panel_zap,zaposleni);
								proveri.dispose();
								
							}
						});
						
						
						JButton ne=new JButton("NE");
						proveri.add(ne);
						
						ne.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
				}
			});
			panel.add(pokupi_zap);
		}
		
		
		static void ispisiUnosSoftvera(JPanel panel,int sirina,int visina,JPanel panel_soft) {
			panel.removeAll();
			
			panel.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
			panel.setLayout(new GridLayout(25,25));
			
			JLabel promenljiv=new JLabel("Unesi Softvere",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			promenljiv=new JLabel("Naziv : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_001=new JTextField();
			polje_001.setHorizontalAlignment(JTextField.CENTER);
			polje_001.setBorder(null);
			panel.add(polje_001);
			
			promenljiv=new JLabel("Cetkice : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_002=new JTextField();
			polje_002.setHorizontalAlignment(JTextField.CENTER);
			polje_002.setBorder(null);
			panel.add(polje_002);
			
			promenljiv=new JLabel("Boja : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_003=new JTextField();
			polje_003.setHorizontalAlignment(JTextField.CENTER);
			polje_003.setBorder(null);
			panel.add(polje_003);
			
			promenljiv=new JLabel("Format Fajla : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_004=new JTextField();
			polje_004.setHorizontalAlignment(JTextField.CENTER);
			polje_004.setBorder(null);
			panel.add(polje_004);
			
			promenljiv=new JLabel("Alati za animiranje : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_005=new JTextField();
			polje_005.setHorizontalAlignment(JTextField.CENTER);
			polje_005.setBorder(null);
			panel.add(polje_005);
			
			promenljiv=new JLabel("Render : ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JTextField polje_006=new JTextField();
			polje_006.setHorizontalAlignment(JTextField.CENTER);
			polje_006.setBorder(null);
			panel.add(polje_006);
			
			promenljiv=new JLabel("  ",SwingConstants.CENTER);
			panel.add(promenljiv);
			
			JButton pokupi_soft=new JButton("Upisi Softver");
			
			pokupi_soft.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					JFrame proveri=new JFrame();
					proveri.setSize(400, 200);
					proveri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					proveri.setLayout(new GridLayout(0,1));
					String[] labela= new String[6];
					labela[0]=polje_001.getText();
					labela[1]=polje_002.getText();
					labela[2]=polje_003.getText();
					labela[3]=polje_004.getText();
					labela[4]=polje_005.getText();
					labela[5]=polje_006.getText();
					int count=0;
					for(int i=0;i<6;i++)
						if(labela[i].isEmpty())
							count++;
					
					if(count>0)
					{
						proveri.setTitle("Greska");
						JLabel greska=new JLabel("Sva polja moraju biti popunjena",SwingConstants.CENTER);
						proveri.add(greska);
						
						JButton kraj=new JButton("CLOSE");
						proveri.add(kraj);
						
						
						kraj.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
					else {
						proveri.setTitle("Unos");
						JLabel upseh=new JLabel("Da li ste sigurni da zelite da unesete Zaposlenog",SwingConstants.CENTER);
						proveri.add(upseh);
						
						JButton da=new JButton("DA");
						proveri.add(da);
						
						
						da.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								Softver softveri1=new Softver();
								softveri1.naziv=labela[0];
								softveri1.cetkica=labela[1];
								softveri1.format=labela[3];
								softveri1.alat=labela[4];
								softveri1.render=labela[5];
								softver.add(softveri1);
								polje_001.setText(null);
								polje_002.setText(null);
								polje_003.setText(null);
								polje_004.setText(null);
								polje_005.setText(null);
								polje_006.setText(null);
								ispisiSoftver(panel_soft,softver);
								proveri.dispose();
							}
						});
						
						
						JButton ne=new JButton("NE");
						proveri.add(ne);
						
						ne.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								proveri.dispose();
							}
						});
						
						proveri.setVisible(true);
						proveri.setLocation(sirina/5*2, visina/5*2);
					}
				}
			});
			panel.add(pokupi_soft);
		}
}

