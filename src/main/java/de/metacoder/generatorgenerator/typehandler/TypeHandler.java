package de.metacoder.generatorgenerator.typehandler;

public abstract class TypeHandler {
	public abstract String generateCode(Object o, String varName);
	public abstract Class<?>[] getTypes();

	protected CodeGenerator codeGenerator;

	public TypeHandler(final CodeGenerator codeGenerator){
		this.codeGenerator = codeGenerator;
	}
}
