package de.metacoder.generatorgenerator.typehandler;

public class NumberArrayTypeHandler extends TypeHandler {

	public NumberArrayTypeHandler(CodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public String generateCode(Object o, String varName) {
		String generated = o.getClass().getSimpleName() + " " + varName + " = new " + o.getClass().getSimpleName() + "{"; 
		

		if(o instanceof Object[]){
			Object[] os = (Object[]) o;
			for(Object number : os){
				generated = generated + number + ",";
			}
			
		} else if(o instanceof int[]){
			for(Object number : (int[]) o){
				generated = generated + number + ",";
			}
		} else if(o instanceof float[]){
			for(Object number : (float[]) o){
				generated = generated + number + ",";
			}
		} else if(o instanceof long[]){
			for(Object number : (long[]) o){
				generated = generated + number + ",";
			}
		} else if(o instanceof double[]){
			for(Object number : (double[]) o){
				generated = generated + number + ",";
			}
		} else if(o instanceof short[]){
			for(Object number : (short[]) o){
				generated = generated + number + ",";
			}
		}

		// Todo more comfortable way (string utils join? scala?)
		if(generated.endsWith(",")) {
			generated = generated.substring(0, generated.length()-1);
		}
		
		generated = generated + "};\n";
		return generated;
	}

	@Override
	public Class<?>[] getTypes() {
		return new Class<?>[] {
				Integer[].class,
				Float[].class,
				Long[].class,
				Double[].class,
				Short[].class,
				int[].class,
				float.class,
				long[].class,
				double[].class,
				short[].class
		};
	}


}
