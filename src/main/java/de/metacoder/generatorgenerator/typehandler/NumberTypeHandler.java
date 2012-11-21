package de.metacoder.generatorgenerator.typehandler;

public class NumberTypeHandler extends TypeHandler {

	public NumberTypeHandler(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public String generateCode(Object o, String varName) {
		return o.getClass().getSimpleName() + " " + varName + " = " + o + ";\n";
	}

	@Override
	public Class<?>[] getTypes() {
		return new Class<?>[] {
				Integer.class,
				Float.class,
				Long.class,
				Double.class,
				Short.class
		};
	}

	
}
