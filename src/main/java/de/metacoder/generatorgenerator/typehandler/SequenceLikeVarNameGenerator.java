package de.metacoder.generatorgenerator.typehandler;

import java.util.HashSet;

import org.springframework.stereotype.Component;

@Component
public class SequenceLikeVarNameGenerator {
	
	private final HashSet<String> generatedVarNames = new HashSet<String>();
	
	public String takeVarName(String fieldName){
		String varName = fieldName;
		int i = 1;
		while(generatedVarNames.contains(varName)){
			varName = fieldName+i;
			i++;
		}
		generatedVarNames.add(varName);
		return varName;
	}
}
