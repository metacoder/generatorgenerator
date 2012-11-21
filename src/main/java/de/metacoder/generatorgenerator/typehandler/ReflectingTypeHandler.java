package de.metacoder.generatorgenerator.typehandler;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ReflectingTypeHandler extends TypeHandler {

	private final SequenceLikeVarNameGenerator sequenceLikeVarNameGenerator;
	private static final Logger LOGGER = Logger.getLogger(ReflectingTypeHandler.class);
	@Autowired
	public ReflectingTypeHandler(CodeGenerator codeGenerator, SequenceLikeVarNameGenerator sequenceLikeVarNameGenerator) {
		super(codeGenerator);
		this.sequenceLikeVarNameGenerator = sequenceLikeVarNameGenerator;
	}
	
	

	@Override
	public String generateCode(Object o, String varName) {
		
		// TODO check if default constructor exists, assume yes + setter for first version
		String generatedCode = o.getClass().getSimpleName() + " " + varName + " = new " + o.getClass().getSimpleName() + "();\n";
		
		for(Field field : o.getClass().getDeclaredFields()){
			
			// first upper
			final String setterName = "set"+field.getName().substring(0,1).toUpperCase() + field.getName().substring(1, field.getName().length());
			
			try {
				o.getClass().getMethod(setterName, new Class<?>[]{field.getType()});
				field.setAccessible(true);
				
				
				String nestedVarName = sequenceLikeVarNameGenerator.takeVarName(field.getName());

				try {

					final Object nestedObject = field.get(o);
					
					if(nestedObject != null){
						
						if(codeGenerator.getReferenceCache().containsKey(nestedObject)){ // detect cyclic reference
							nestedVarName = codeGenerator.getReferenceCache().get(nestedObject); 
						} else {
							generatedCode = generatedCode + codeGenerator.generate(nestedObject, nestedVarName);
						}
					
					} else {
						generatedCode = generatedCode + field.getType().getSimpleName() + " " + nestedVarName + " = null;\n";
					}
				
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Found field with setter but cannot access the field!", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Found field with setter but cannot access the field!", e);
				}
				
				generatedCode = generatedCode + varName + "." + setterName + "("+nestedVarName+");\n"; 

			} catch (NoSuchMethodException e) {
				LOGGER.debug("Didn't find setter method for " + o.getClass() + "." + field.getName() + ". Ignoring this field.");
			} catch (SecurityException e) { 
				throw new RuntimeException("fatal error while using reflection :(", e);
			}
			
		}
		
		return generatedCode;
	}

	@Override
	public Class<?>[] getTypes() {
		return null;
	}

}
