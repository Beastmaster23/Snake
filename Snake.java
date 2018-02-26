import java.awt.*;
import java.util.*;

public class Snake {
	public static DrawingPanel panel= new DrawingPanel(600,600);
	public static Graphics g= panel.getGraphics();
	public static Random ran= new Random();
	public static ArrayList<Rectangle> snPos=new ArrayList<Rectangle>();
	public static Rectangle power=new Rectangle (ran.nextInt(30)*20,ran.nextInt(30)*20,20,20);
	public static boolean upYo=false, downYo=false, leftYo=false,rightYo=false,play=true;
	public static int speed=4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		snPos.add(new Rectangle(0,0,30,30));
		//gameOver();
		while(play) {
			panel.sleep(100);
			g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
			panel.setBackground(Color.white);
			for(int iO=0; iO<snPos.size();iO++) {
				Rectangle pos=snPos.get(iO);
				g.setColor(Color.black);
				g.fillRect(pos.x, pos.y, pos.width, pos.height);
			}
			powerUpCon();
			move();
			for(int i=snPos.size(); i>0;i--) {
				Rectangle pos=snPos.get(0);
				Rectangle pos2=snPos.get(i-1);
				if(i>6&&pos.x==pos2.x&&pos.y==pos2.y) {
					gameOver();
				}
			}
			g.setColor(Color.white);
			//System.out.println(snPos.size());
		}
	}
	public static boolean collide(Rectangle obj1, Rectangle obj2){
	    int objxW=obj1.x+obj1.width;
	    int obj2xW=obj2.x+obj2.width;
	    int objyH=obj1.y+obj1.height;
	    int obj2yH=obj2.y+obj2.height;
	    
	      if(objxW>obj2.x&&objyH>obj2.y&& obj1.y<obj2yH&&obj1.x<obj2xW){
	    	  return true;
	      }
	      return false;
	  }
	public static void move() {
		Rectangle pos=snPos.get(0);
		for(int iO=snPos.size()-1; iO>0;iO--) {
			Rectangle pos2=snPos.get(iO);
			Rectangle pos3=snPos.get(iO-1);
			pos2.y=pos3.y;
			pos2.x=pos3.x;
		}
		if(downYo==true) {
			if(pos.y==0) {
				pos.y=570;
			}else {
			pos.y-=30;
			}
		}
		if(upYo==true) {
			if(pos.y==570) {
				pos.y=0;
			}else {
			pos.y+=30;
			}
		}
		if(leftYo==true) {
			if(pos.x==0) {
				pos.x=570;
			}else {
			pos.x-=30;
			}
			
		}
		if(rightYo==true) {
			if(pos.x==570) {
				pos.x=0;
			}else {
			pos.x+=30;
			}
		}
	}
	public static void powerUpCon() {
		for (int i=0; i<snPos.size();i++) {
		Rectangle pos=snPos.get(i);
		if(collide(pos,power)) {
			snPos.add(new Rectangle (0,30,30,30));
			power.x=ran.nextInt(30)*20;
			power.y=ran.nextInt(30)*20;
		}
		}
		g.setColor(Color.blue);
		g.fillRect(power.x, power.y, power.width, power.height);
	}
	public static void gameOver() {
		play=false;
		g.setColor(Color.darkGray);
		g.fillOval(235, 270, 100, 50);
		g.setColor(Color.red);
		g.drawString("GAME OVER", 250, 300);
	}
}
