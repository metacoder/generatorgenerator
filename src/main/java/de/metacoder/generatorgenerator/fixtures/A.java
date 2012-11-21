package de.metacoder.generatorgenerator.fixtures;

import java.io.Serializable;

public class A implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private B b;
	private String c;
	
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
	
}
