package gamestates;
import java.awt.*;
import java.awt.event.*;
public interface StateMethods {
	//+ Data methods
	public void update();
	public void draw(Graphics g);
	//+ Keyboard methods
	public void KeyPressed(KeyEvent e);
	public void KeyReleased(KeyEvent e);
	//+ Mouse methods
	public void MouseClicked(MouseEvent e);
	public void MouseReleased(MouseEvent e);
	public void MousePressed(MouseEvent e);
	public void MouseEntered(MouseEvent e);
	public void MouseExited(MouseEvent e);
	public void MouseDragged(MouseEvent e);
	
}
