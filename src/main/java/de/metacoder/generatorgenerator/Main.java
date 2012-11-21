package de.metacoder.generatorgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import de.metacoder.generatorgenerator.fixtures.A;
import de.metacoder.generatorgenerator.fixtures.B;
import de.metacoder.generatorgenerator.fixtures.C;
import de.metacoder.generatorgenerator.typehandler.CodeGenerator;
import de.metacoder.generatorgenerator.typehandler.SequenceLikeVarNameGenerator;

@Component
public class Main {

	@Autowired private CodeGenerator codeGenerator;
	@Autowired private SequenceLikeVarNameGenerator sequenceLikeVarNameGenerator;
	
	public static void main(String... args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Main main = applicationContext.getBean(Main.class);
		main.main();
	}
	
	
	public void main() throws Exception {
		
		final A a = new A();
		final B b = new B();
		final C c = new C();
		
		a.setB(b);
		b.setC(c);
		c.setA(a);
		
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
		
		System.out.println(codeGenerator.generate("Hallo Welt!", "myFirstString"));
		System.out.println(codeGenerator.generate(3, "myThree"));
		System.out.println(codeGenerator.generate(new Integer[]{3,4}, "myIntegerArray"));
		System.out.println(codeGenerator.generate(new Integer[]{}, "myIntegerArrayEmpty"));
		System.out.println(codeGenerator.generate(new int[]{3,4}, "myIntegerArray"));
		System.out.println(codeGenerator.generate(new int[]{}, "myIntegerArrayEmpty"));
		
		System.out.println(codeGenerator.generate(b, sequenceLikeVarNameGenerator.takeVarName("b")));
	}
	
	
}
