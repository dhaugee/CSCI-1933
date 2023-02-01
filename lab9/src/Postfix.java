public class Postfix {
    static double evaluate(String[] expression) {
        try {
            Stack s = new Stack(expression.length);
            for (int i = 0; i < expression.length; i++) {
                if (expression[i].equals("+")) {
                    double num1 = Double.parseDouble((String)s.pop());
                    double num2 = Double.parseDouble((String)s.pop());
                    s.push(Double.toString(num1 + num2));
                } else if (expression[i].equals("-")) {
                    double num1 = Double.parseDouble((String)s.pop());
                    double num2 = Double.parseDouble((String)s.pop());
                    s.push(Double.toString(num1 - num2));
                } else if (expression[i].equals("*")) {
                    double num1 = Double.parseDouble((String)s.pop());
                    double num2 = Double.parseDouble((String)s.pop());
                    s.push(Double.toString(num1 * num2));
                } else if (expression[i].equals("/")) {
                    double num1 = Double.parseDouble((String)s.pop());
                    double num2 = Double.parseDouble((String)s.pop());
                    s.push(Double.toString(num1 / num2));
                }
                else {
                    s.push(expression[i]);
            }
        }
        return Double.parseDouble((String)s.pop());
    } catch (StackException e){
        System.out.println("error");
        return 0.0;
    }
    }
}


