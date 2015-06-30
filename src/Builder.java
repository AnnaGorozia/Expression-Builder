
abstract class Builder {
	public abstract void generateParameterExpression(String parameter, String type);
	public abstract void generateUnaryExpression(String operation);
	public abstract void generateBinaryExpression(String operation, String type);
	public abstract void generateConstantExpression(Object value, String type);
	public abstract void openBrackets();
	public abstract void closeBrackets();
}
