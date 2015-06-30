
public class SchemeGenerator extends Builder {

	private String generatedCode;
	private String expression;
	
	public String getResult() {
		return generatedCode;
	}

	public SchemeGenerator() {
		this.generatedCode = "";
	}

	@Override
	public void generateParameterExpression(String parameter, String type) {
		expression = parameter + " ";
	}
	
	@Override
	public void generateConstantExpression(Object value, String type) {
		if (value.getClass().equals(String.class)) {
			value = "\"" + value + "\"";
		} else if (value.getClass().equals(Boolean.class)) {
			boolean val = (Boolean) value;
			if (val == true)
				value = "#t";
			else
				value = "#f";
		}
		expression = value + " ";
	}
	
	@Override
	public void generateUnaryExpression(String operation) {
		openBrackets();
		if (operation.equalsIgnoreCase("NEGATE"))
			operation = "- ";
		else if (operation.equalsIgnoreCase("NOT"))
			operation = "not ";
		String res = generatedCode + operation;
		generatedCode = res;
	}

	@Override
	public void generateBinaryExpression(String operation, String type) {
		String op = "";
		if (operation.equalsIgnoreCase("ADD")) {
			if (type.equalsIgnoreCase("int"))
				op = "+ ";
			else if (type.equalsIgnoreCase("String"))
				op = "string-append ";
		} else if (operation.equalsIgnoreCase("SUBTRACT")) {
			op = "- ";
		} else if (operation.equalsIgnoreCase("MULTIPLY")) {
			op = "* ";
		} else if (operation.equalsIgnoreCase("DIVIDE")) {
			op = "/ ";
		} else if (operation.equalsIgnoreCase("MODULO")) {
			op = "mod ";
		} else if (operation.equalsIgnoreCase("LESS_THAN")) {
			op = "< ";
		} else if (operation.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
			op = "<= ";
		} else if (operation.equalsIgnoreCase("GREATER_THAN")) {
			op = "> ";
		} else if (operation.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
			op = ">= ";
		} else if (operation.equalsIgnoreCase("AND")) {
			op = "and ";
		} else if (operation.equalsIgnoreCase("OR")) {
			op = "or ";
		} else if (operation.equalsIgnoreCase("EQUAL")) {
			op = "equal? ";
		}
		generatedCode += op;
		generatedCode += expression;
		expression = "";
	}

	public void openBrackets() {
		generatedCode += "(";
	}

	public void closeBrackets() {
		generatedCode += expression;
		expression = "";
		generatedCode = generatedCode.substring(0, generatedCode.length() - 1)
				+ ") ";
	}

}
