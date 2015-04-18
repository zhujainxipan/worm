package com.ht.wormdemo;

public class Node {
	private int i;
	private int j;
	
	public Node() {
		
	}
	
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public int hashCode() {
		return i << 16 | j;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj instanceof Node) {
			Node node = (Node)obj;
			return this.i == node.i && this.j == node.getJ();
		}
		return false;
	}
}
