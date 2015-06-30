public class ExpressionTypeResolver implements ExpressionVisitor {
	private String type;

	public String getType() {
		return type;
	}

	@Override
	public void visit(ConstantExpression expression){
		this.type = expression.getType();
	}

	@Override
	public void visit(UnaryExpression expression) {
		expression.getOperand().accept(this);
		String type = this.getType();
		String operation = expression.getOperation();
		this.type = null;
		if(type == null) return;
		if (operation.equalsIgnoreCase("NOT")) {
			if (type.equalsIgnoreCase("boolean")) {
				this.type = type;
			}
		} else if (operation.equalsIgnoreCase("NEGATE")) {
			if (type.equalsIgnoreCase("int")) {
				this.type = type;
			}
		} else{
			System.out.println("Invalid unary operation");
			// throw new UnsupportedOperationException("Invalid unary operation");
		}
	}

	@Override
	public void visit(BinaryExpression expression) {
		expression.getLeft().accept(this);
		String leftType = this.getType();
		expression.getRight().accept(this);
		String rightType = this.getType();
		this.type = null;
		String operation = expression.getOperation();
		if(leftType == null || rightType == null) return;
		if (operation.equalsIgnoreCase("ADD")) {
			if ((leftType.equalsIgnoreCase("int") && rightType
					.equalsIgnoreCase("int"))
					|| (leftType.equalsIgnoreCase("String") && rightType
							.equalsIgnoreCase("String"))) {
				this.type = leftType;
			} 
		} else if (operation.equalsIgnoreCase("SUBTRACT")
				|| operation.equalsIgnoreCase("MULTIPLY")
				|| operation.equalsIgnoreCase("DIVIDE")
				|| operation.equalsIgnoreCase("MODULO")) {
			if ((leftType.equalsIgnoreCase("int") && rightType
					.equalsIgnoreCase("int"))) {
				this.type = leftType;
			}
		} else if (operation.equalsIgnoreCase("AND")
				|| operation.equalsIgnoreCase("OR")) {
			if ((leftType.equalsIgnoreCase("boolean") && rightType
					.equalsIgnoreCase("boolean"))) {
				this.type = leftType;
			} 
		} else if (operation.equalsIgnoreCase("EQUAL")
				|| operation.equalsIgnoreCase("NOT_EQUAL")) {
			if ((leftType.equalsIgnoreCase("int") && rightType
					.equalsIgnoreCase("int"))
					|| (leftType.equalsIgnoreCase("String") && rightType
							.equalsIgnoreCase("String"))
					|| (leftType.equalsIgnoreCase("boolean") && rightType
							.equalsIgnoreCase("boolean"))) {
				this.type = "boolean";
			} 
		}else if(operation.equalsIgnoreCase("LESS_THAN")
				|| operation.equalsIgnoreCase("LESS_THAN_OR_EQUAL")
				|| operation.equalsIgnoreCase("GREATER_THAN")
				|| operation.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")){
			if ((leftType.equalsIgnoreCase("int") && rightType
					.equalsIgnoreCase("int"))){
				this.type = "boolean";
			}
		} else{
			System.out.println("Invalid binary operation");
			 //throw new UnsupportedOperationException("Invalid binary operation");
		}

	}

	@Override
	public void visit(ParameterExpression expression) {
		// this.parameter = expression.getParameter();
		this.type = expression.getType();
	}
}
