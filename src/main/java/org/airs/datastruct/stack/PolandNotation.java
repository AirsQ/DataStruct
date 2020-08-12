package org.airs.datastruct.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String suffixExpression = "3 4 + 5 * 6 -";
        calculate(getListString(suffixExpression));

        String infixExpr = "1+((2+3)*4)-5";
//        System.out.println(getInfixList(infixExpr));
//        convertToSufExpression(getInfixList(infixExpr));
        int value = calculate(convertToSufExpression(getInfixList(infixExpr)));
        System.out.println(value);
    }

    public static List<String> getListString(String suffixExp) {
        String[] strArr = suffixExp.split(" ");
        return Arrays.asList(strArr);
    }

    // 后缀表达式计算
    public static int calculate(List<String> list) {
        Stack<String> numStack = new Stack<>();

        list.forEach((String ele) -> {
            if (ele.matches("\\d+")) { // \d+\.?[\d+]*
                numStack.push(ele);
                return;
            }

            int a = Integer.parseInt(numStack.pop());
            int b = Integer.parseInt(numStack.pop());
            int res = CalculatorDemo.operate(a, b, ele.charAt(0));
            numStack.push(String.valueOf(res));
        });

        return Integer.parseInt(numStack.pop());
    }

    public static List<String> getInfixList(String infixExpression) {
        infixExpression = infixExpression.replace(" ", "");

        List<String> infixList = new ArrayList<>();
        String str;
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < infixExpression.length(); i++) {
            str = infixExpression.substring(i, i + 1);

            // 数字
            if (CalculatorDemo.isNum(str)) {
                numStr.append(str);

                while (CalculatorDemo.isNum(str)) {
                    if (i >= infixExpression.length() - 1) {
                        break;
                    }

                    i++;
                    str = infixExpression.substring(i, i + 1);
                    if (CalculatorDemo.isNum(str)) {
                        numStr.append(str);
                        continue;
                    }

                    i--;
                    break;
                }

                infixList.add(numStr.toString());
                numStr.setLength(0);
                continue;
            }

            // 操作符 包括了 （ ）
            infixList.add(str);
        }

        return infixList;
    }

    public static List<String> convertToSufExpression(List<String> infixList) {
        Stack<String> middleStack = new Stack<>();
        Stack<String> operStack = new Stack<>();

        infixList.forEach((String ele) -> {
            /*
             * 数字 直接压入中间结果栈
             * 符号
             *  如果符号栈为空 直接压入
             *  如果当前符号为 （ 直接压入
             *  如果栈顶为 （ 直接压入
             *  如果是 ）依次弹出符号栈栈顶元素，直到找到 ） 并丢弃这对（）
             *  拿当前符号和栈顶符号优先级比较
             *      如果当前优先级高 直接压入
             *      如果当前优先级低 弹出栈顶符号，将弹出的符号压入中间栈，继续拿当前符号和栈顶符号优先级比较
             */
            if (ele.matches("\\d+")) {
                middleStack.push(ele);
                return;
            }

            if (operStack.isEmpty() || ele.equals("(") || operStack.peek().equals("(")) {
                operStack.push(ele);
                return;
            }

            String operTop;
            if (ele.equals(")")) {
                while (!operStack.peek().equals("(")) {
                    operTop = operStack.pop();
                    middleStack.push(operTop);
                }
                operStack.pop();

                return;
            }

            while (true) {
                if (operStack.isEmpty() ||
                        CalculatorDemo.priority(ele.charAt(0)) > CalculatorDemo.priority(operStack.peek().charAt(0))) {
                    operStack.push(ele);
                    break;
                }

                operTop = operStack.pop();
                middleStack.push(operTop);
            }
        });
        operStack.forEach(middleStack::push);

        return new ArrayList<>(middleStack);
    }

}
