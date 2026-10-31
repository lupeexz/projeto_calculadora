package calculadora;

import java.util.Scanner;

/**
 * Interface de terminal que mantém a execução após entradas inválidas.
 * @author Gustavo Henrique Lupi Bignardi
 * @version 1.0
 */
public final class Aplicacao {
    /** Impede criação da classe utilitária. */
    private Aplicacao() { }

    /**
     * Executa o menu até receber sair ou terminar a entrada.
     * @param args argumentos de linha de comando, não utilizados
     */
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        try (Scanner entrada = new Scanner(System.in, "UTF-8")) {
            System.out.println("FinançApp | Digite: inteiro operador inteiro (ex.: 5 / 2). Use sair para encerrar.");
            while (entrada.hasNextLine()) {
                String linha = entrada.nextLine().trim();
                if (linha.equalsIgnoreCase("sair")) {
                    break;
                }
                String[] partes = linha.split("\\s+");
                if (partes.length != 3) {
                    System.out.println("Erro: informe inteiro operador inteiro.");
                    continue;
                }
                try {
                    int primeiro = Integer.parseInt(partes[0]);
                    int segundo = Integer.parseInt(partes[2]);
                    System.out.println("Resultado: " + calculadora.calc(primeiro, segundo, partes[1]));
                } catch (NumberFormatException erro) {
                    System.out.println("Erro: use números inteiros de 32 bits.");
                } catch (IllegalArgumentException | ArithmeticException erro) {
                    System.out.println("Erro: " + erro.getMessage());
                }
            }
            System.out.println("Calculadora encerrada.");
        }
    }
}
