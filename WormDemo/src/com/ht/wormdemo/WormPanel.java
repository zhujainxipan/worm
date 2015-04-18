package com.ht.wormdemo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class WormPanel {
	// 蛇
	private Worm worm;
	// 食物
	private HashSet<Node> foods = new HashSet<Node>();
	// 行、列
	private int rows = 10;
	private int cols = 32;

	// 随机生成食物
	public void initFoods(int num) {
		Random random = new Random();
		while (true) {
			int i = random.nextInt(rows - 2) + 1;
			int j = random.nextInt(cols - 2) + 1;
			if (worm.contain(i, j)) {
				continue;
			}
			Node node = new Node(i, j);
			foods.add(node);
			if (foods.size() == num)
				break;
		}

	}

	// 打印功能
	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || i == rows - 1)
					System.out.print("-");
				else if (j == 0 || j == cols - 1)
					System.out.print("|");
				else if (worm.contain(i, j))
					System.out.print("#");
				else if (foods.contains(new Node(i, j)))// 依据是hashCode(),equals()
					System.out.print("0");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	// 内部类 蛇
	class Worm {
		private int dir;
		private LinkedList<Node> she = new LinkedList<Node>();
		public static final int UP = -10;
		public static final int DOWN = 10;
		public static final int LEFT = -1;
		public static final int RIGHT = 1;

		public Worm() {
			she.add(new Node(3, 9));
			she.add(new Node(4, 9));
			she.add(new Node(5, 9));
			she.add(new Node(5, 10));
			she.add(new Node(5, 11));
			she.add(new Node(6, 11));
			she.add(new Node(7, 11));
			dir = RIGHT;
		}

		public boolean contain(int i, int j) {
			return she.contains(new Node(i, j));
		}

		// 蛇向前走
		public void step() {
			// 得到头结点
			Node head = she.getFirst();
			
			/*i与j分析，我们看图发现，向上走的话，横坐标减一（i），纵坐标不变（j）
			 即:
			 当向上的时候i =过去的i-1=过去的i + -10右移一位（即-10/10）
			 当向下的时候i =过去的i+1=		   10/10
			当向左向右的时候则是看个位数，所以下面的方式是正确的
			     	 i j
			     UP -1 0
			   DOWN  1 0
			   LEFT  0-1
			  RIGHT  0 1
			  
			  */
			int i = head.getI() + dir / 10;
			int j = head.getJ() + dir % 10;
			head = new Node(i, j);
			she.addFirst(head);
			//remove方法如果此 set 已包含该元素，则返回 true
			//这一步的目的是为了判断有没有吃食物
			//吃一个食物，蛇的长度增加了
			if (foods.remove(new Node(i, j))) {
				return;//一旦返回，程序就不会往下执行了，就结束了
			}
			//删除最后的结点，就是如果吃了一个食物，最后一个节点就不会删除了，因为已经返回了
			she.removeLast();
		}
		
		
		public void step(int dir) {
			if(this.dir + dir == 0)
				throw new RuntimeException("不允许掉头");
			this.dir = dir;
			step();
		}
	}
	
	public Worm  getWorm()
	{
		return this.worm;
	}

	public WormPanel() {
		worm = new Worm();
		initFoods(5);
	}

}
