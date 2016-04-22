package core;
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
		int mouseX = (int)location.getX();
		int mouseY = (int)location.getY();
		if((mouseY + m_VendorInfoUI.getHeight()) > MenuWindow.s_SCREEN_HEIGHT) // make sure that the window is on screen.
		{
			m_VendorInfoUI.setLocation(mouseX-5,MenuWindow.s_SCREEN_HEIGHT - m_VendorInfoUI.getHeight() - 15); // set window at the bottom of the screen.
		}else
		{
			m_VendorInfoUI.setLocation(mouseX-5,mouseY-5);
		}
		
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
			if(!m_VendorInfoUI.isOnLink())
				m_VendorInfoUI.setVisible(false);
			
			m_VendorInfoUI.setOnLink(true);
		}
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) 
		{
		}
	}
}
