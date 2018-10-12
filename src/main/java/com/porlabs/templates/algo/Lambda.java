package com.porlabs.templates.algo;

public class Lambda {
    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        MathOperation adittion = (int a, int b) -> a + b;
        System.out.println(lambda.operate(1, 2 ,adittion));
    }


    int operate(int a, int b, MathOperation operation) {
        return operation.operation(a, b);
    }
}

interface MathOperation {
    int operation(int a, int b);
}
