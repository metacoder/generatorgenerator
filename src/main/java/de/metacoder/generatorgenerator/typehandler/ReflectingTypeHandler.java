package de.metacoder.generatorgenerator.typehandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectingTypeHandler extends TypeHandler {

	public ReflectingTypeHandler(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public String generateCode(Object o, String varName) {
		
		// TODO check if default constructor exists, assume yes + setter for first version
		String generatedCode = o.getClass().getSimpleName() + " " + varName + " = new " + o.getClass().getSimpleName() + "();\n";
		
		
		Field[] fields = o.getClass().getDeclaredFields();
		
		for(Field field : fields){
			// first upper
			String setterName = "set"+field.getName().substring(0,1).toUpperCase() + field.getName().substring(1, field.getName().length());
			
			
			try {
				Method m = o.getClass().getMethod(setterName, new Class<?>[]{field.getType()});
				field.setAccessible(true);
				final String nestedVarName = field.getName() + "Var"; // TODO lookup if already used;

				try {
				
					
					Object nestedObject = field.get(o);
					
					if(nestedObject != null){
						generatedCode = generatedCode + codeGenerator.generate(nestedObject, nestedVarName);
					} else {
						generatedCode = generatedCode + field.getType().getSimpleName() + " " + nestedVarName + " = null;\n";
					}
				
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Found field with setter but cannot access the field!", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Found field with setter but cannot access the field!", e);
				}
				
				generatedCode = generatedCode + varName + "." + setterName + "("+nestedVarName+");\n"; 

			} catch (NoSuchMethodException e) { // TODO info / debug log ?
			} catch (SecurityException e) { // TODO info log / Debug log ?
			}
			
		}
		
		// TODO fields.
		
		return generatedCode;
	}

	@Override
	public Class<?>[] getTypes() {
		return null;
	}

}
