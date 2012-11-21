package de.metacoder.generatorgenerator.typehandler;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class TypeHandler {
	
	public abstract String generateCode(Object o, String varName);
	public abstract Class<?>[] getTypes();

	protected CodeGenerator codeGenerator;

	@Autowired
	public TypeHandler(final CodeGenerator codeGenerator){
		this.codeGenerator = codeGenerator;
		codeGenerator.registerTypeHandler(this);
	}
}
