package de.metacoder.generatorgenerator.fixtures;

import java.io.Serializable;

public class B implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String c;
	private B nestedBinB;
	
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public B getNestedBinB() {
		return nestedBinB;
	}

	public void setNestedBinB(B nestedBinB) {
		this.nestedBinB = nestedBinB;
	}

	@Override
	public String toString() {
		return "B [c=" + c + "]";
	}
	
}
