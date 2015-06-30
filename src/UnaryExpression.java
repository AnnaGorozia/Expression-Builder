
public class UnaryExpression extends Expression {
    private final Expression operand;
    private final String operation;

   
    public Expression getOperand() {
        return operand;
    }

    public String getOperation() {
        return operation;
    }

    public UnaryExpression(Expression operand, String operation) {
        this.operand = operand;
        this.operation = operation;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
