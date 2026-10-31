package calculadora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários com entradas, resultados esperados e obtidos.
 * @author Gustavo Henrique Lupi Bignardi
 * @version 1.0
 */
public class TesteCalculadora {
    /** Instância sem estado usada pelos testes. */
    private final Calculadora calculadora = new Calculadora();

    /** Cria a suíte de testes. */
    public TesteCalculadora() { }

    /** Verifica soma. */
    @Test public void soma() { verificar(10, 5, "+", 15); }
    /** Verifica subtração. */
    @Test public void subtracao() { verificar(10, 5, "-", 5); }
    /** Verifica multiplicação. */
    @Test public void multiplicacao() { verificar(10, 5, "*", 50); }
    /** Verifica divisão exata. */
    @Test public void divisao() { verificar(10, 5, "/", 2); }
    /** Verifica preservação da parte decimal. */
    @Test public void divisaoDecimal() { verificar(5, 2, "/", 2.5); }
    /** Verifica operandos negativos. */
    @Test public void negativos() { verificar(-8, 2, "/", -4); }
    /** Verifica zero como operando válido. */
    @Test public void zero() { verificar(0, 3, "*", 0); }
    /** Verifica que a soma não estoura o intervalo de int. */
    @Test public void limiteInteiro() { verificar(Integer.MAX_VALUE, 1, "+", 2147483648.0); }
    /** Verifica divisão por zero e mensagem. */
    @Test public void divisaoPorZero() {
        ArithmeticException erro = assertThrows(ArithmeticException.class,
                () -> calculadora.calc(10, 0, "/"));
        verificarErro("10 / 0", "Não é permitido dividir por zero.", erro);
    }
    /** Verifica operador desconhecido. */
    @Test public void operacaoInvalida() { verificarOperador("%"); }
    /** Verifica operador nulo. */
    @Test public void operacaoNula() { verificarOperador(null); }
    /** Verifica operador vazio. */
    @Test public void operacaoVazia() { verificarOperador(""); }

    /**
     * Compara e registra um resultado numérico.
     * @param a primeiro operando
     * @param b segundo operando
     * @param op operador
     * @param esperado resultado esperado
     */
    private void verificar(int a, int b, String op, double esperado) {
        double obtido = calculadora.calc(a, b, op);
        System.out.printf("Entrada: %d %s %d | Esperado: %s | Obtido: %s%n", a, op, b, esperado, obtido);
        assertEquals(esperado, obtido, 0.000000001);
    }

    /**
     * Confirma rejeição de operador inválido.
     * @param operador operador sob teste
     */
    private void verificarOperador(String operador) {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calc(10, 5, operador));
        verificarErro("10 " + operador + " 5", "Operação inválida. Use +, -, * ou /.", erro);
    }

    /**
     * Compara e registra a mensagem de erro.
     * @param entrada entrada utilizada
     * @param esperado mensagem esperada
     * @param obtido exceção produzida
     */
    private void verificarErro(String entrada, String esperado, RuntimeException obtido) {
        System.out.printf("Entrada: %s | Esperado: %s | Obtido: %s%n", entrada, esperado, obtido.getMessage());
        assertEquals(esperado, obtido.getMessage());
    }
}
