package model;

public class Attack {

	private String type = "Attack";
	private int[] cells;

	public Attack(String type, int[] cells) {
		this.setType(type);
		this.setCells(cells);

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int[] getCells() {
		return cells;
	}

	public void setCells(int[] cells) {
		this.cells = cells;
	}

}
