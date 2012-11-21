package de.metacoder.generatorgenerator.fixtures;

import java.io.Serializable;

public class C implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
