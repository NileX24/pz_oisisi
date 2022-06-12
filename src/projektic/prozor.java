package projektic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import projektic.JTabbedPaneCloseButton;
import projektic.TableTab;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class prozor{
		public static void main(String[] args)
		{
			
			myframe frame=new myframe();
			frame.setVisible(true);
		}
}
class myframe extends JFrame{
	/**
	 * 
	 */
	int tabNumber = 0;
	
	private static final long serialVersionUID = 1L;
	
	private JToolBar mainToolbar;
	private JButton addTabButton;
	private JTabbedPaneCloseButton tabbedPane;
	
	public void createToolbar() {
		this.mainToolbar = new JToolBar(JToolBar.HORIZONTAL);
		this.mainToolbar.setFloatable(false);
		
		addTabButton = new JButton(createImageIcon("img/img.png", true, 32, 32));
		addTabButton.setToolTipText("Add new tab.");
		addTabButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addMyTabToTabbedPane("Table " + (++tabNumber));
			}
		});
		this.mainToolbar.add(addTabButton);
		this.add(this.mainToolbar, BorderLayout.NORTH);
	}
	
	private void createTabbedPane() {
		this.tabbedPane = new JTabbedPaneCloseButton();
		this.add(this.tabbedPane, BorderLayout.CENTER);
	}
	
	private void addMyTabToTabbedPane(String tableName) {
		// load icot
		ImageIcon icon = createImageIcon("images/img.png", true, 16, 16);
		// instantiate tab
		TableTab mt = new TableTab(tableName);
		// add tab to tabbed pane
		tabbedPane.addTab(tableName, icon, mt, "Tab Tooltip");
	}
	
	protected static ImageIcon createImageIcon(String path, boolean scaleImage, int width, int height) {
		if (scaleImage) {
			// how to scale image
			ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back
			return imageIcon;

		} else {
			return new ImageIcon(path);
		}
	}
	
	public myframe() {
		this.createToolbar();
		setTitle("Pojekat");
		// skinuto sa stack overflow-a
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		int sirina=(int) screenSize.getWidth();
		int visina=(int )screenSize.getHeight();
		setSize(sirina/4*3,visina/4*3);
		setLocation(sirina/8, visina/8);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel= new JPanel();
		add(panel);
		}
}

