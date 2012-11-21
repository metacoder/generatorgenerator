package de.metacoder.generatorgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import de.metacoder.generatorgenerator.fixtures.A;
import de.metacoder.generatorgenerator.fixtures.B;
import de.metacoder.generatorgenerator.typehandler.CodeGenerator;

public class Main {
	public static void main(String... args) throws Exception {
		
		final A a = new A();
		final B b = new B();
		b.setC("Test");
		Collection<B> bCollection = new ArrayList<B>();
		bCollection.add(b);
		a.setB(bCollection);
		b.setNestedBinB(new B());
		
		
		System.out.println("TestFixture A: " + a);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(a);
		
		
		byte[] bytes = byteArrayOutputStream.toByteArray();
		
		System.out.println("ByteArray size: " + bytes.length);
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		
		Object o = objectInputStream.readObject();
		System.out.println("Read object from object input stream: " + o);
		
		// generation stuff
		
		final CodeGenerator codeGenerator = new CodeGenerator();
		
		System.out.println(codeGenerator.generate("Hallo Welt!", "myFirstString"));
		System.out.println(codeGenerator.generate(3, "myThree"));
		System.out.println(codeGenerator.generate(new Integer[]{3,4}, "myIntegerArray"));
		System.out.println(codeGenerator.generate(new Integer[]{}, "myIntegerArrayEmpty"));
		System.out.println(codeGenerator.generate(new int[]{3,4}, "myIntegerArray"));
		System.out.println(codeGenerator.generate(new int[]{}, "myIntegerArrayEmpty"));
		System.out.println(codeGenerator.generate(b, "b"));
	}
	
	
}
