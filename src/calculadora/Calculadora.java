package calculadora;

/**
 * Módulo de operações básicas da FinançApp.
 * @author Gustavo Henrique Lupi Bignardi
 * @version 1.0
 */
public class Calculadora {
    /** Cria uma calculadora sem estado. */
    public Calculadora() { }

    /**
     * Calcula uma operação com dois inteiros e resultado decimal.
     * @param a primeiro operando
     * @param b segundo operando
     * @param op operador: +, -, * ou /
     * @return resultado da operação, sem truncamento na divisão
     * @throws ArithmeticException se o divisor for zero
     * @throws IllegalArgumentException se o operador for inválido ou nulo
     */
    public double calc(int a, int b, String op) {
        if (op == null) {
            throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
        if (op.equals("+")) {
            return (double) a + b;
        } else if (op.equals("-")) {
            return (double) a - b;
        } else if (op.equals("*")) {
            return (double) a * b;
        } else if (op.equals("/")) {
            if (b == 0) {
                throw new ArithmeticException("Não é permitido dividir por zero.");
            }
            return (double) a / b;
        }
        throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
    }
}
