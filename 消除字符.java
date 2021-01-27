package Com.sy.li1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Zifu {
	public static void main(String[] args) throws IOException {
		JFrame win = new JFrame();
		Pane pane = new Pane();
		win.addKeyListener(pane);
		win.add(pane);
		win.setSize(407, 700);
		win.setLocationRelativeTo(null);// 居中
		win.setResizable(false);// 固定大小
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setTitle("字符游戏");
		win.setVisible(true);
		pane.start();
	}
}

class Pane extends JPanel implements KeyListener {
	char[] cc;
	int[] xx;
	int[] yy;
	int score = 0;
	Image image;
	Random r;

	public Pane() throws IOException {
		r = new Random();
		cc = new char[10];
		xx = new int[10];
		yy = new int[10];
		for (int i = 0; i < 10; i++) {
			cc[i] = (char) (r.nextInt(26) + 97);
			xx[i] = r.nextInt(350) + 20;
			yy[i] = r.nextInt(100) + 20;
		}
		image = ImageIO.read(new File("time.png"));
	}

	public void paint(Graphics g) {// 在窗体形成时 自动调用
		super.paint(g);// 画笔工具
		g.drawImage(image, 0, 0, null);

		Font font = g.getFont();
		font = new Font(font.getName(), font.BOLD, 20);
		g.setFont(font);
		g.drawString("分数:" + score, 50, 50);
		for (int i = 0; i < 10; i++) {
			g.drawString(cc[i] + "", xx[i], yy[i]);
		}
		g.setColor(Color.BLACK);
		g.drawLine(0, 550, 400, 550);
	}

	public void start() {
		while (true) {
			int index = 0;
			for (int i = 0; i < 10; i++) {
				yy[i] = yy[i] + r.nextInt(5);
				// xx[i]+=(int)(Math.pow(-1,index)*r.nextInt(5));
				index++;
				if (yy[i] >= 550) {
					cc[i] = (char) (r.nextInt(26) + 97);
					xx[i] = r.nextInt(350) + 20;
					yy[i] = r.nextInt(100) + 20;
					score -= 100;
				}
			}

			// for (int i = 0; i < 10000000; i++);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();// 重画方法
		}
	}

	public void KeyPreeesd(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		for (int i = 0; i < 10; i++) {
			if (c == cc[i]) {
				cc[i] = (char) (r.nextInt(26) + 97);
				xx[i] = r.nextInt(350) + 20;
				yy[i] = r.nextInt(100) + 20;
				score += 100;
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		for (int i = 0; i < 10; i++) {
			if (c == cc[i]) {
				cc[i] = (char) (r.nextInt(26) + 97);
				xx[i] = r.nextInt(350) + 20;
				yy[i] = r.nextInt(100) + 20;
				score += 100;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
