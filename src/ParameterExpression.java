
public class ParameterExpression extends Expression {
    private String parameter;
    private String type;
    
    public String getParameter() {
        return parameter;
    }

    public String getType(){
    	return type;
    }
    public ParameterExpression(String parameter, String type) {
        this.parameter = parameter;
        this.type = type;
    }

   
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
