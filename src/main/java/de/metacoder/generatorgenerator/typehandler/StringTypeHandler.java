package de.metacoder.generatorgenerator.typehandler;

public class StringTypeHandler extends TypeHandler {

	public StringTypeHandler(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public String generateCode(Object o, String varName) {
		return "String " + varName + " = \"" + o + "\";\n";
	}

	@Override
	public Class<?>[] getTypes() {
		return new Class<?>[] { String.class };
	}

}
