import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;
/**
 * 
 * @author Joshua Becker
 * shows the vender Info
 */
public class VendorInfoUI extends JFrame
{
	JLabel m_Target_L;
	JPanel m_Main_P;
	
	public VendorInfoUI(JLabel target)
	{
		m_Target_L = target;
		createComponents();
		designComponents();
		addComponents();
	}

	private void createComponents() {
		m_Main_P = new JPanel();
	}

	private void designComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.GRAY);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		//setLayout(new BorderLayout(5,5));
		setSize((int) (MenuUI.FRAME_WIDTH*.5) , (int)(MenuUI.FRAME_HEIGHT*.2));
		setMinimumSize(new Dimension((int) (MenuUI.FRAME_WIDTH*.5) , (int)(MenuUI.FRAME_HEIGHT*.2)));
		setMaximumSize(new Dimension((int) (MenuUI.FRAME_WIDTH*.5) , (int)(MenuUI.FRAME_HEIGHT*.2)));
		setResizable(false);
		
		m_Main_P.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void addComponents() {
		m_Main_P.add(new JLabel("This WIll Be Info"));
		add(m_Main_P);
	}
	
}
