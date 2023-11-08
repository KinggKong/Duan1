package com.thuvien.entity;

public class NXB {
	private String tenNXB;
        private int ID;

	public NXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}
        
	public NXB() {
	}

    public NXB(String tenNXB, int ID) {
        this.tenNXB = tenNXB;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

}
