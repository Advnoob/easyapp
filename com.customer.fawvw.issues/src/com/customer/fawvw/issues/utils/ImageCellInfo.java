package com.customer.fawvw.issues.utils;

public class ImageCellInfo {
	private int row; // ��

	private int column; // ��

	private int toRow; // ��..��

	private int toColumn; // ��...��

	public ImageCellInfo(int row, int column, int toRow, int toColumn) {
		super();
		this.row = row;
		this.column = column;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
	public ImageCellInfo() {
		super();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getToRow() {
		return toRow;
	}

	public void setToRow(int toRow) {
		this.toRow = toRow;
	}

	public int getToColumn() {
		return toColumn;
	}

	public void setToColumn(int toColumn) {
		this.toColumn = toColumn;
	}
}
