package org.airs.datastruct.stack;

public class CalculatorDemo {
    public static void main(String[] args) {

        String expression = "(70+2) * (5-5)";
        expression = expression.replace(" ", ""); // 保证没有空格

        ArrayStack numStack = new ArrayStack(30);
        ArrayStack operStack = new ArrayStack(30);

        String str;
        char operTop;
        char operator;
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            str = expression.substring(i, i + 1);
            /*
             * 如果是数字
             * 向后找 直到找到不为数字的符号
             * 然后拼接数字 入栈
             */
            if (isNum(str)) {
                numStr.append(str);

                while (isNum(str)) {
                    if (i >= expression.length() - 1) {
                        break;
                    }

                    i++;
                    str = expression.substring(i, i + 1);
                    if (isNum(str)) {
                        numStr.append(str);
                        continue;
                    }

                    i--;
                    break;
                }

                numStack.push(Integer.parseInt(numStr.toString()));
                numStr.setLength(0);
                continue;
            }

            /*
             * 如果是符号
             * 判断操作符栈是否为空
             *  空直接入栈
             */
            operator = str.charAt(0);
            if (operStack.isEmpty()) {
                operStack.push(operator);
                continue;
            }

            /*
             * 插入小括号
             * 如果是 （ 直接入符号栈
             * 如果是 ） 进行计算 直到符号栈中第一次碰到的 （ 取出
             */
            if (operator == '(') {
                operStack.push(operator);
                continue;
            }
            if (operator == ')') {
                while (operStack.peek() != '(') {
                    calculationOnce(numStack, operStack);
                }

                operStack.pop();
                continue;
            }

            /*
             * 非空比较栈顶操作符和当前操作符优先级
             * 当前优先级高 直接进符号栈
             * 栈顶优先级高或相等 一次取出两个栈顶数字和栈顶操作符进行计算，将结果压入数字栈，将当前操作符压入符号栈
             */
            operTop = (char) operStack.peek();
            if (priority(operator) > priority(operTop)) {
                operStack.push(operator);
                continue;
            }

            calculationOnce(numStack, operStack);
            operStack.push(operator);
        }

        while (!operStack.isEmpty()) {
            calculationOnce(numStack, operStack);
        }

        System.out.println(numStack.pop());
    }

    // 执行一次计算 并入栈
    public static void calculationOnce(ArrayStack numStack, ArrayStack operStack) {
        int aValue = numStack.pop();
        int bValue = numStack.pop();
        char operTop = (char) operStack.pop();
        int result = operate(aValue, bValue, operTop);
        numStack.push(result);
    }

    // 判断字符串是数字还是操作符
    public static boolean isNum(String str) {
        return !(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("(") || str.equals(")"));
    }

    public static int operate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
            default:
                throw new RuntimeException("不支持的操作符！");
        }
    }

    // 数字越大，优先级越高
    public static int priority(char chr) {
        switch (chr) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

}


