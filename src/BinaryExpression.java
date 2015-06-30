
public class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;
    private final String operation;

   
    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperation() {
        return operation;
    }

    public BinaryExpression(Expression left, Expression right, String operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
