package de.metacoder.generatorgenerator.fixtures;

import java.io.Serializable;

public class B implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private C c;

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
	
}
