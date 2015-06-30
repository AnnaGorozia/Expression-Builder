public class CodeGenerator implements ExpressionVisitor {
	private Builder builder;
	private String type;
	private String parameter;
	private Object value;

	public String getType() {
		return type;
	}

	public String getParameter() {
		return parameter;
	}

	public Object getValue() {
		return value;
	}

	public CodeGenerator(Builder builder) {
		this.builder = builder;
	}

	@Override
	public void visit(ConstantExpression expression) {
		String type = expression.getType();
		Object value = expression.getValue();
		this.type = type;
		this.value = value;
		builder.generateConstantExpression(value, type);
	}

	@Override
	public void visit(BinaryExpression expression) {
		if (expression.getOperation().equalsIgnoreCase("NOT_EQUAL")) {
			Expression be = new BinaryExpression(expression.getLeft(),
					expression.getRight(), "EQUAL");
			Expression ue = new UnaryExpression(be, "NOT");
			ue.accept(this);
		} else {
			builder.openBrackets();
			expression.getLeft().accept(this);
			String leftType = this.getType();
			this.type = leftType;
			builder.generateBinaryExpression(expression.getOperation(),
					this.type);
			expression.getRight().accept(this);
			String rightType = this.getType();
			this.type = rightType;
			builder.closeBrackets();
		}
	}

	@Override
	public void visit(UnaryExpression expression) {
		ExpressionTypeResolver evaluator = new ExpressionTypeResolver();
		expression.accept(evaluator);
		if (expression.getOperand() instanceof ConstantExpression) {
			ConstantExpression constant = (ConstantExpression) expression
					.getOperand();
			builder.generateUnaryExpression(expression.getOperation());
			constant.accept(this);
			this.type = this.getType();
			builder.closeBrackets();
			return;
		}
		builder.generateUnaryExpression(expression.getOperation());
		expression.getOperand().accept(this);
		this.type = this.getType();
		builder.closeBrackets();
		// builder.putBrackets();

	}

	@Override
	public void visit(ParameterExpression expression) {
		String type = expression.getType();
		String parameter = expression.getParameter();
		this.type = type;
		this.parameter = parameter;
		builder.generateParameterExpression(parameter, type);
	}

}
