package com.ht.wormdemo;

import java.util.Scanner;

public class WormDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		WormPanel panel = new WormPanel();
		WormPanel.Worm worm = panel.getWorm();

		while (true) {
			panel.print();
			String dir = scanner.nextLine();
			if ("u".equalsIgnoreCase(dir))
				worm.step(WormPanel.Worm.UP);
			else if ("d".equalsIgnoreCase(dir))
				worm.step(WormPanel.Worm.DOWN);
			else if ("l".equalsIgnoreCase(dir))
				worm.step(WormPanel.Worm.LEFT);
			else if ("r".equalsIgnoreCase(dir))
				worm.step(WormPanel.Worm.RIGHT);
			else if ("q".equalsIgnoreCase(dir)) {
				break;

			} else {
				worm.step();
			}

		}

	}

}
