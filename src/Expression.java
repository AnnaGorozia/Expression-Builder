public abstract class Expression {

    /**
     * Accepts visitor
     * @param visitor
     */
    public abstract void accept(ExpressionVisitor visitor);
}
