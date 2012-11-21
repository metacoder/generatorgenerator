package de.metacoder.generatorgenerator.typehandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {
	
	private Map<Class<?>, TypeHandler> registeredTypeHandlers = new HashMap<Class<?>, TypeHandler>();
	
	// cache of all already created objects (with its var name) for the circular dependency detection logic
	private final Map<Object, String> referenceCache = new IdentityHashMap<Object, String>();
	
	@Autowired private SequenceLikeVarNameGenerator sequenceLikeVarNameGenerator;
	
	private static final Logger LOGGER = Logger.getLogger(CodeGenerator.class);
	
	private TypeHandler fallbackTypeHandler = null;

	public void registerTypeHandler(TypeHandler t){
		
		System.out.println("registering typehandler " + t );
		
		if(t.getTypes() == null){ // register fallback typehandler
			if(fallbackTypeHandler != null){
				throw new IllegalStateException("Tried to register second fallbackTypeHandler! Only one FallbackTypeHandler allowed! First: " + fallbackTypeHandler.getClass().getName() + " - second: " + t.getClass().getName());
			}
			fallbackTypeHandler = t;
		} else {
			for(Class<?> c : t.getTypes()){
				if(registeredTypeHandlers.containsKey(c)){
					throw new IllegalStateException("Tried to register second TypeHandler for "+c.getName()+"! Only one Handler for each type allowed! Already registered: " + registeredTypeHandlers.get(c).getClass().getName() + " - new: " + t.getClass().getName());
				}
				registeredTypeHandlers.put(c, t);
			}
		}
			
	}
	
	public Map<Object, String> getReferenceCache() {
		return Collections.unmodifiableMap(referenceCache);
	}

	public String generate(Object o, String varName){
		
		referenceCache.put(o, varName);
		
		final Class<?> objectClass = o.getClass();
		
		TypeHandler typeHandler = registeredTypeHandlers.get(objectClass);
		
		if(typeHandler == null){
			typeHandler = fallbackTypeHandler;
		}
		
		String generatedCode = typeHandler.generateCode(o, varName);
		
		// Guard if implementation of type handler is dirty
		if(!generatedCode.endsWith("\n")) {
			LOGGER.warn("TypeHandler " + typeHandler.getClass().getName() + " generates code with missing \\n at the end. " +
					"Exceptionally i will fix this for you. But please fix your code. My patience is limited...");
			generatedCode = generatedCode + "\n";
		}
		
		return generatedCode;
	}
}
