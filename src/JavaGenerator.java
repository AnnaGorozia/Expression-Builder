public class JavaGenerator extends Builder {

	private String generatedCode;

	public String getResult() {
		return generatedCode;
	}

	public JavaGenerator() {
		this.generatedCode = "";
	}

	@Override
	public void generateParameterExpression(String parameter, String type) {

		if (type.equalsIgnoreCase("String")) {
			parameter = "(" + parameter + ")";
		}
		generatedCode += parameter;
	}

	@Override
	public void generateUnaryExpression(String operation) {
		openBrackets();
		if (operation.equalsIgnoreCase("NEGATE"))
			operation = "-";
		else if (operation.equalsIgnoreCase("NOT"))
			operation = "!";
		String res = generatedCode + operation;
		generatedCode = res;
	}

	@Override
	public void generateBinaryExpression(String operation, String type) {
		String op = "";
		if (operation.equalsIgnoreCase("ADD")) {
			op = " + ";
		} else if (operation.equalsIgnoreCase("SUBTRACT")) {
			op = " - ";
		} else if (operation.equalsIgnoreCase("MULTIPLY")) {
			op = " * ";
		} else if (operation.equalsIgnoreCase("DIVIDE")) {
			op = " / ";
		} else if (operation.equalsIgnoreCase("MODULO")) {
			op = " % ";
		} else if (operation.equalsIgnoreCase("LESS_THAN")) {
			op = " < ";
		} else if (operation.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
			op = " <= ";
		} else if (operation.equalsIgnoreCase("GREATER_THAN")) {
			op = " > ";
		} else if (operation.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
			op = " >= ";
		} else if (operation.equalsIgnoreCase("AND")) {
			op = " && ";
		} else if (operation.equalsIgnoreCase("OR")) {
			op = " || ";
		} else if (operation.equalsIgnoreCase("EQUAL")) {
			if (type.equalsIgnoreCase("String")) {
				op = ".equals";
			} else
				op = " == ";
		} else if (operation.equalsIgnoreCase("NOT_EQUAL")) {
			if (type.equalsIgnoreCase("String")) {
				op = ".equals";
			} else
				op = " != ";
		}
		generatedCode += op;
	}

	@Override
	public void generateConstantExpression(Object value, String type) {
		if (value.getClass().equals(String.class)) {
			value = "(\"" + value + "\")";
		}
		generatedCode += value;
	}

	public void openBrackets() {
		generatedCode += "(";
	}

	public void closeBrackets() {
		generatedCode += ")";
	}

}
