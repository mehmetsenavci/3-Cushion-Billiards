package advanceProject;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

public class BallPanel extends JPanel implements ActionListener,MouseListener,KeyListener,MouseMotionListener{


	private static final long serialVersionUID = 1L;
	double sz=40;
	static double Vx,Vy;
	static double Vx2=0,Vy2=0;
	static double Vx3=0,Vy3=0;
	double V=10;
	boolean stick = false;
	static short speed1;
	short speed2;
	double mousex,mousey;
	String cheat;

	boolean cueStick = false;

	boolean selectTurn = true;
	int point1;
	int point2;
	boolean p11 = false, p12 = false;
	boolean p21 = false, p22 = false;
	double cusionCount = 0;


	Ellipse2D.Double ball = new Ellipse2D.Double(800,200,sz,sz);
	Ellipse2D.Double ball2 = new Ellipse2D.Double(300,250,sz,sz);
	Ellipse2D.Double ball3 = new Ellipse2D.Double(300,150,sz,sz);


	Timer t = new Timer(15,this);

	BallPanel(){
		addMouseListener(this);
		//		addKeyListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateBallPosition();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 1016, 508);
		g.setColor(Color.WHITE);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fill(ball);
		g2d.setColor(Color.RED);
		g2d.fill(ball2);
		g2d.setColor(Color.yellow);
		g2d.fill(ball3);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Speed of white ball " + Integer.toString((int)Math.hypot(Vx, Vy)), 10, 400);
		g2d.drawString(Menu.username1 + "'s score: " + Integer.toString(point1),10, 450);
		g2d.drawString("Speed of yellow ball " +Integer.toString((int)Math.hypot(Vx3, Vy3)), 200, 400);
		g2d.drawString(Menu.username2 + "'s score: "  + Integer.toString(point2),200, 450);
		if(stick) {
			if(selectTurn){
				Line2D.Double istaka = new Line2D.Double(ball.getCenterX(),ball.getCenterY(),mousex,mousey);
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(10));
				g2d.draw(istaka);
			}
			if(!selectTurn) {
				Line2D.Double istaka = new Line2D.Double(ball3.getCenterX(),ball3.getCenterY(),mousex,mousey);
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(10));
				g2d.draw(istaka);

			}
		}



		if(point1 >= 50) {
			super.paintComponent(g);
			g.drawString(Menu.username1 + " WON", 500, 250);
		}
		else if(point2 >= 50) {
			super.paintComponent(g);
			g.drawString(Menu.username2 + " WON", 500, 250);
		}
	}
	public void updateBallPosition() {


		ball.x+=Vx;
		ball.y+=Vy;


		ball2.x+=Vx2;
		ball2.y+=Vy2;

		ball3.x+=Vx3;
		ball3.y+=Vy3;

		if(ball.x<0 || ball.x>getWidth()-sz) {
			if(!selectTurn)
				cusionCount++;
			Vx=-Vx;
		}
		if(ball.y<0 || ball.y>getHeight()-sz) { 
			if(!selectTurn)
				cusionCount++;
			Vy=-Vy;
		}
		if(ball2.x<0 || ball2.x>getWidth()-sz) { 
			Vx2=-Vx2;
		}
		if(ball2.y<0 || ball2.y>getHeight()-sz) { 
			Vy2=-Vy2;
		}
		if(ball3.x<0 || ball3.x>getWidth()-sz) { 
			if(selectTurn)
				cusionCount++;
			Vx3=-Vx3;
		}
		if(ball3.y<0 || ball3.y>getHeight()-sz) { 
			if(selectTurn)
				cusionCount++;
			Vy3=-Vy3;
		}

		Vx = Vx*0.99;
		Vy = Vy*0.99;

		Vx2 = Vx2*0.99;
		Vy2 = Vy2*0.99;

		Vx3 = Vx3*0.99;
		Vy3 = Vy3*0.99;

		if(Vx*Vx + Vy*Vy < 0.1 && Vx2*Vx2 + Vy2*Vy2 < 0.1 && Vx3*Vx3 + Vy3*Vy3 < 0.1) {
			Vx = 0;
			Vy = 0;

			Vx2 = 0;
			Vy2 = 0;

			Vx3 = 0;
			Vy3 = 0;

			t.stop();

			if(!selectTurn) {
				if((p11 && p12) && cusionCount > 3) {
					point1+=15;
					selectTurn = true;
				}
			}
			else {
				if((p21 && p22) && cusionCount > 3) {
					point2+=15;
					selectTurn = false;
				}
			}
			cusionCount = 0;

			p11 = false; p12 = false;
			p21 = false; p22 = false;
		}



		double dx = ball.x - ball2.x;
		double dy = ball.y - ball2.y;

		double dx2 = ball2.x - ball.x;
		double dy2 = ball2.y - ball.y;

		double dx3 = ball.x - ball3.x;
		double dy3 = ball.y - ball3.y;

		double dx4 = ball3.x - ball.x;
		double dy4 = ball3.y - ball.y;

		double dx5 = ball2.x - ball3.x;
		double dy5 = ball2.y - ball3.y;

		double dx6 = ball3.x - ball2.x;
		double dy6 = ball3.y - ball2.y;

		double Umag = Math.hypot(dx, dy);
		double Umag2 = Math.hypot(dx2, dy2);
		double Umag3 = Math.hypot(dx3, dy3);
		double Umag4 = Math.hypot(dx4, dy4);
		double Umag5 = Math.hypot(dx5, dy5);
		double Umag6 = Math.hypot(dx6, dy6);


		if(Math.hypot(Vx, Vy)> Math.hypot(Vx2, Vy2)) {
			if(Umag <= ball.width)
			{

				double VrMag = (Vx*dx + Vy*dy )/ Umag;
				double Vrx = VrMag * dx / Umag;
				double Vry = VrMag * dy / Umag;

				double Vtx = Vx - Vrx;
				double Vty = Vy - Vry;


				Vx = Vtx;
				Vy = Vty;
				Vx2 = Vx2 + Vrx;
				Vy2 = Vy2 + Vry;

				p11 = true;



				//Vx=0;Vy=0;
			}
		}
		//repaint();
		else {
			if(Umag2 <= ball2.width)
			{

				double VrMag2 = (Vx2*dx2 + Vy2*dy2 )/ Umag2;
				double Vrx2 = VrMag2 * dx2 / Umag2;
				double Vry2 = VrMag2 * dy2 / Umag2;

				double Vtx2 = Vx2 - Vrx2;
				double Vty2 = Vy2 - Vry2;


				Vx2 = Vtx2;
				Vy2 = Vty2;
				Vx = Vx + Vrx2;
				Vy = Vy + Vry2;

				p11 = true;

				//Vx2=0;Vy2=0;
			}
			//repaint();
		}
		if(Math.hypot(Vx , Vy) > Math.hypot(Vx3, Vy3)) {
			if(Umag3 <= ball.width)
			{

				double VrMag2 = (Vx*dx3 + Vy*dy3 )/ Umag3;
				double Vrx3 = VrMag2 * dx3 / Umag3;
				double Vry3 = VrMag2 * dy3 / Umag3;

				double Vtx3 = Vx - Vrx3;
				double Vty3 = Vy - Vry3;


				Vx = Vtx3;
				Vy = Vty3;
				Vx3 = Vx3 + Vrx3;
				Vy3 = Vy3 + Vry3;

				p12 = true;
				p21 = true;

				//Vx=0;Vy=0;
			}
		}
		//repaint();
		else {
			if(Umag4 <= ball3.width)
			{

				double VrMag4 = (Vx3*dx4 + Vy3*dy4 )/ Umag4;
				double Vrx4 = VrMag4 * dx4 / Umag4;
				double Vry4 = VrMag4 * dy4 / Umag4;

				double Vtx4 = Vx3 - Vrx4;
				double Vty4 = Vy3 - Vry4;


				Vx3 = Vtx4;
				Vy3 = Vty4;
				Vx = Vx + Vrx4;
				Vy = Vy + Vry4;

				p12 = true;
				p21 = true;

				//Vx3=0;Vy3=0;
			}
		}
		//repaint();
		if(Math.hypot(Vx3, Vx3) > Math.hypot(Vx2, Vy2)) {
			if(Umag5 <= ball3.width)
			{

				double VrMag5 = (Vx3*dx5 + Vy3*dy5 )/ Umag5;
				double Vrx5 = VrMag5 * dx5 / Umag5;
				double Vry5 = VrMag5 * dy5 / Umag5;

				double Vtx5 = Vx3 - Vrx5;
				double Vty5 = Vy3 - Vry5;


				Vx3 = Vtx5;
				Vy3 = Vty5;
				Vx2 = Vx2 + Vrx5;
				Vy2 = Vy2 + Vry5;

				p22 = true;

				//Vx3=0;Vy3=0;
			}
		}
		//repaint();

		else {
			if(Umag6 <= ball2.width)
			{

				double VrMag6 = (Vx2*dx6 + Vy2*dy6 )/ Umag6;
				double Vrx6 = VrMag6 * dx6 / Umag6;
				double Vry6 = VrMag6 * dy6 / Umag6;

				double Vtx6 = Vx2 - Vrx6;
				double Vty6 = Vy2 - Vry6;


				Vx2 = Vtx6;
				Vy2 = Vty6;
				Vx3 = Vx3 + Vrx6;
				Vy3 = Vy3 + Vry6;

				p22 = true;


				//Vx2=0;Vy2=0;
			}
		}

		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		/*if(!t.isRunning()) {

			//switch selectTurn to false when the timer stops.
			if(selectTurn) {
				double x1 = e.getX();
				double y1 = e.getY();
				double x2 = ball.x + ball.width/2;
				double y2 = ball.y + ball.height/2;
				double dx = x2-x1;
				double dy = y2-y1;


				Vx = (V*dx/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				Vy = (V*dy/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				//System.out.println(Math.hypot(Vx, Vy));
				speed1 = (short)Math.hypot(Vx, Vy);
				selectTurn = false;

			}

			else {

				double x1 = e.getX();
				double y1 = e.getY();
				double x2 = ball3.x + ball3.width/2;
				double y2 = ball3.y + ball3.height/2;
				double dx = x2-x1;
				double dy = y2-y1;

				Vx3 = (V*dx/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				Vy3 = (V*dy/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;

				//System.out.println(Math.hypot(Vx3, Vy3));
				speed2 = (short)Math.hypot(Vx3, Vy3);
				selectTurn = true;


			}


//			t.start(); 
		}*/
	}

	@Override
	public void mousePressed(MouseEvent e) {


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!t.isRunning()) {
			if(selectTurn) {
				stick= false;
				//	double mousex = e.getX();
				//	double mousey = e.getY();
				double x2 = ball.x + ball.width/2;
				double y2 = ball.y + ball.height/2;
				double dx = x2-mousex;
				double dy = y2-mousey;


				Vx = (V*dx/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				Vy = (V*dy/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				//System.out.println(Math.hypot(Vx, Vy));
				speed1 = (short)Math.hypot(Vx, Vy);
				selectTurn = false;

			}

			else {
				stick = false;
				//	double mousex = e.getX();
				//	double mousey = e.getY();
				double x2 = ball3.x + ball3.width/2;
				double y2 = ball3.y + ball3.height/2;
				double dx = x2-mousex;
				double dy = y2-mousey;

				Vx3 = (V*dx/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;
				Vy3 = (V*dy/Math.sqrt(dx*dx+dy*dy)) * Math.hypot(dx, dy)*0.03;

				//System.out.println(Math.hypot(Vx3, Vy3));
				speed2 = (short)Math.hypot(Vx3, Vy3);
				selectTurn = true;


			}
		}

		t.start(); 

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		System.out.println(key);

		cheat += key;
		if((cheat == "win" || cheat == "WIN") && !selectTurn) {
			point1 = 50;
			repaint();
		}
		else if((cheat == "win" || cheat == "WIN") && selectTurn) {
			point2 = 50;
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!t.isRunning()) {
			if(!t.isRunning())
				stick=true;
			mousex=e.getX();
			mousey=e.getY();
			repaint();
		}

		//switch selectTurn to false when the timer stops.


	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}