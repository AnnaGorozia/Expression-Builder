public class ConstantExpression extends Expression {
	private Object value;
	private String type;

	public Object getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public ConstantExpression(Object value, String type) {
		this.value = value;
		this.type = type;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
}
