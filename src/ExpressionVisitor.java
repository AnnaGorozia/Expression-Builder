
/**
 * Represents abstract visitor (operation)
 */
public interface ExpressionVisitor {

    /**
     * Visits constant expression
     * @param expression
     */
    public void visit(ConstantExpression expression);

    /**
     * Visits binary expression
     * @param expression
     */
    public void visit(BinaryExpression expression);

    /**
     * Visits Unary expression
     * @param expression
     */
    public void visit(UnaryExpression expression);
    
    /**
     * Visits Unary expression
     * @param expression
     */
    public void visit(ParameterExpression expression);
}
