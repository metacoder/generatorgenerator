package de.metacoder.generatorgenerator.fixtures;

import java.io.Serializable;
import java.util.Collection;

public class A implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Collection<B> b;

	public Collection<B> getB() {
		return b;
	}

	public void setB(Collection<B> b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "A [b=" + b + "]";
	}
	
}
