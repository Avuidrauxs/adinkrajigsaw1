package com.faarentie.adinkra.jigsaw;

public class LoadObjects {
	
	private String color;
	private int imageResource;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getImageResource() {
		return imageResource;
	}
	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}
	public LoadObjects(String color, int imageResource) {
		super();
		this.color = color;
		this.imageResource = imageResource;
	}
	
	

}
