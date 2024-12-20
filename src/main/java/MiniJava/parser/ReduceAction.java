package MiniJava.parser;

import MiniJava.Log.Log;

public class ReduceAction extends Action {
    public ReduceAction(int number) {
        super(number);
    }

    @Override
    public void act(Parser parser) {
        Rule rule = parser.getRules().get(this.number);
        for (int i = 0; i < rule.RHS.size(); i++) {
            parser.popStack();
        }
        Log.print(/* "state : " + */ parser.headStack() + "\t" + rule.LHS);
        // Log.print("LHS : "+rule.LHS);
        parser.pushStack(parser.getParseTable().getGotoTable(parser.headStack(), rule.LHS));
        Log.print(/* "new State : " + */parser.headStack() + "");
        // Log.print("");
        try {
            parser.getCg().semanticFunction(rule.semanticAction, parser.getLookAhead());
        } catch (Exception e) {
            Log.print("Code Genetator Error");
        }
    }

    @Override
    public String toString() {
        return "r" + number;
    }
}