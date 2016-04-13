import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
/**
 * Used for displaying info when vendor is clicked
 * 
 * @author Joshua Becker
 */
public class MouseActionListener extends MouseAdapter{
	VendorInfoFrame m_VendorInfoUI;
	JLabel m_Target_L;
	Color m_OldColor;
	public MouseActionListener(JLabel target)
	{
		m_VendorInfoUI = new VendorInfoFrame(target);
		m_VendorInfoUI.addMouseListener(new MouseListener());
		m_Target_L = target;
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent evt) 
	{
		m_OldColor = m_Target_L.getForeground();
		m_Target_L.setForeground(Color.blue);
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent evt) 
	{
		m_Target_L.setForeground(m_OldColor);
		//m_VendorInfoUI.setVisible(false);
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) 
	{
		PointerInfo mousePointer = MouseInfo.getPointerInfo();
		Point location = mousePointer.getLocation();
		m_VendorInfoUI.setLocation((int)location.getX()-5,(int)location.getY()-5);
		m_VendorInfoUI.setVisible(true);
		m_Target_L.setForeground(m_OldColor);
	}
	
	private class MouseListener extends MouseAdapter
	{
		public MouseListener()
		{
		}
		@Override
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{
			m_VendorInfoUI.setVisible(false);
		}
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) 
		{
		}
	}
}
