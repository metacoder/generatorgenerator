package de.metacoder.generatorgenerator.typehandler;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
	private Map<Class<?>, TypeHandler> registeredTypeHandlers = new HashMap<Class<?>, TypeHandler>();
	
	public CodeGenerator(){
		// TODO scan with spring
		StringTypeHandler stringHandler = new StringTypeHandler(this);
		registeredTypeHandlers.put(stringHandler.getTypes()[0], stringHandler); // Dirty FIXME
		
		NumberTypeHandler numberTypeHandler = new NumberTypeHandler(this);
		for(Class<?> c : numberTypeHandler.getTypes()){
			registeredTypeHandlers.put(c, numberTypeHandler);
		}
		
		NumberArrayTypeHandler numberArrayTypeHandler = new NumberArrayTypeHandler(this);
		for(Class<?> c : numberArrayTypeHandler.getTypes()){
			registeredTypeHandlers.put(c, numberArrayTypeHandler);
		}

		registeredTypeHandlers.put(null, new ReflectingTypeHandler(this)); // It is dangerous to use null here, FIXME
		
	}
	
	public String generate(Object o, String varName){
		
		final Class<?> objectClass = o.getClass();
		
		TypeHandler typeHandler = registeredTypeHandlers.get(objectClass);
		
		if(typeHandler == null){
			typeHandler = registeredTypeHandlers.get(null); // pahaha, DANGEROUS. FIXME
		}
		
		String generatedCode = typeHandler.generateCode(o, varName);
		// Guard if implementation of type handler is dirty
		if(!generatedCode.endsWith("\n")) {
			// TODO log warn that type handler is broken
			generatedCode = generatedCode + "\n";
		}
		
		return generatedCode;
	}
}
