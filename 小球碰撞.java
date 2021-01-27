import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ball {
	public static void main(String[] args) throws IOException {
		JFrame win = new JFrame();
		MyPane pane = new MyPane();
		
		win.add(pane);
	
		win.addMouseMotionListener(pane);
	
		win.setSize(407, 700);
		win.setLocationRelativeTo(null);//居中
		win.setResizable(false);//固定大小
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setTitle("小球游戏");
		win.setVisible(true);
		pane.start();
	}
}

class MyPane extends JPanel implements MouseMotionListener {
	boolean  ifHit=false;
	int x;
	int y;
	int rx;
	int score=0;
	Image image;
	int dirction = 0;//方向
    int speed=10;
	public MyPane() throws IOException {
		x = 100;
		y = 100;
		rx = 10;
		image = ImageIO.read(new File("time.png"));
	}

	public void paint(Graphics g) {//在窗体形成时 自动调用
		super.paint(g);//画笔工具
		g.drawImage(image, 0, 0, null);
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
		
		g.setColor(Color.BLACK);
		g.fillRect(rx, 600, 150, 20);
		g.setColor(Color.BLUE);
		if(!ifHit){
		g.fillRect(100, 100, 50, 50);
		}
		g.setColor(new Color(205,149,12));
		Font font=g.getFont();
		font =new Font(font.getName(),font.BOLD, 20);
		g.setFont(font);
		g.drawString("score:"+score, 50, 50);
	}

	public void start() {
		while (true) {
			if (dirction == 0) {
				x++;
				y++;
			}
			if (dirction == 1) {
				x--;
				y++;
			}
			if (dirction == 2) {
				x--;
				y--;
			}
			if (dirction == 3) {
				x++;
				y--;
			}
			if (x >= 357) {
				if (dirction == 0)
					dirction = 1;
				if (dirction == 3)
					dirction = 2;
			}
			if (y >= 700 - 50) {//游戏结束
				System.exit(0);
				/*if (dirction == 0)
					dirction = 3;
				if (dirction == 1)
					dirction = 2;*/
			}
			if (x <= 0) {
				if (dirction == 1)
					dirction = 0;
				if (dirction == 2)
					dirction = 3;
			}
			if (y <= 0) {
				if (dirction == 3)
					dirction = 0;
				if (dirction == 2)
					dirction = 1;
			}
			if((y>=550)&&(rx<=25+x)&&(x+25<=rx+150))
			{
				if (dirction == 0)
					dirction = 3;
				if (dirction == 1)
					dirction = 2;
				score++;
			}
			/*if((y<=50)&&(rx<=25+x)&&(x+25<=rx+50))
			{
				if (dirction == 3)
					dirction = 0;
				if (dirction == 2)
					dirction = 1;
				score++;
			}*/
			if((x>=100-25)&&(x<=150-25)&&(y==150)){
				ifHit=true;
			}
			
			if(score>=5)
				speed=8;
			if(score>=6)
				speed=6;
			repaint();//重画方法
			//for (int i = 0; i < 10000000; i++);
			
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {//e 代表鼠标事件
		// TODO Auto-generated method stub
		rx = e.getX();//获取鼠标的X
		if (rx >= 257) {
			rx = 257;
		}
		repaint();
	}
}
