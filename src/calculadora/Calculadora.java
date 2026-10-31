package calculadora;

/**
 * Módulo de operações básicas da FinançApp.
 * @author Gustavo Henrique Lupi Bignardi
 * @version 2.0
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
        validarOperador(op);
        // Converte antes da operação para evitar overflow de int e divisão truncada.
        return switch (op) {
            case "+" -> somar(a, b);
            case "-" -> subtrair(a, b);
            case "*" -> multiplicar(a, b);
            case "/" -> dividir(a, b);
            default -> throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        };
    }

    /**
     * Valida a presença do operador antes do switch.
     * @param operador operador recebido
     * @throws IllegalArgumentException se o operador for nulo
     */
    private void validarOperador(String operador) {
        if (operador == null) {
            throw new IllegalArgumentException("Operação inválida. Use +, -, * ou /.");
        }
    }

    /**
     * Soma dois operandos.
     * @param primeiro primeiro operando
     * @param segundo segundo operando
     * @return soma em double
     */
    private double somar(int primeiro, int segundo) { return (double) primeiro + segundo; }

    /**
     * Subtrai o segundo operando do primeiro.
     * @param primeiro primeiro operando
     * @param segundo segundo operando
     * @return diferença em double
     */
    private double subtrair(int primeiro, int segundo) { return (double) primeiro - segundo; }

    /**
     * Multiplica dois operandos.
     * @param primeiro primeiro operando
     * @param segundo segundo operando
     * @return produto em double, sujeito à precisão de ponto flutuante
     */
    private double multiplicar(int primeiro, int segundo) { return (double) primeiro * segundo; }

    /**
     * Divide preservando a parte decimal.
     * @param dividendo valor a dividir
     * @param divisor valor pelo qual dividir
     * @return quociente em double
     * @throws ArithmeticException se o divisor for zero
     */
    private double dividir(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Não é permitido dividir por zero.");
        }
        return (double) dividendo / divisor;
    }
}
