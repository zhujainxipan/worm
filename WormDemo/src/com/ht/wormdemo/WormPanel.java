package com.ht.wormdemo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class WormPanel {
	// ��
	private Worm worm;
	// ʳ��
	private HashSet<Node> foods = new HashSet<Node>();
	// �С���
	private int rows = 10;
	private int cols = 32;

	// �������ʳ��
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

	// ��ӡ����
	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || i == rows - 1)
					System.out.print("-");
				else if (j == 0 || j == cols - 1)
					System.out.print("|");
				else if (worm.contain(i, j))
					System.out.print("#");
				else if (foods.contains(new Node(i, j)))// ������hashCode(),equals()
					System.out.print("0");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	// �ڲ��� ��
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

		// ����ǰ��
		public void step() {
			// �õ�ͷ���
			Node head = she.getFirst();
			
			/*i��j���������ǿ�ͼ���֣������ߵĻ����������һ��i���������겻�䣨j��
			 ��:
			 �����ϵ�ʱ��i =��ȥ��i-1=��ȥ��i + -10����һλ����-10/10��
			 �����µ�ʱ��i =��ȥ��i+1=		   10/10
			���������ҵ�ʱ�����ǿ���λ������������ķ�ʽ����ȷ��
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
			//remove��������� set �Ѱ�����Ԫ�أ��򷵻� true
			//��һ����Ŀ����Ϊ���ж���û�г�ʳ��
			//��һ��ʳ��ߵĳ���������
			if (foods.remove(new Node(i, j))) {
				return;//һ�����أ�����Ͳ�������ִ���ˣ��ͽ�����
			}
			//ɾ�����Ľ�㣬�����������һ��ʳ����һ���ڵ�Ͳ���ɾ���ˣ���Ϊ�Ѿ�������
			she.removeLast();
		}
		
		
		public void step(int dir) {
			if(this.dir + dir == 0)
				throw new RuntimeException("�������ͷ");
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
