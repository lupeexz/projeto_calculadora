# Projeto Calculadora — FinançApp

> Projeto acadêmico de Qualidade de Software, Testes, Refatoração, Documentação e Versionamento.

## 1. Identificação do Projeto

**Projeto:** projeto_calculadora  
**Contexto:** FinançApp  
**Aluno:** Gustavo Henrique Lupi Bignardi  
**RA:** 251310  
**Curso:** Análise e Desenvolvimento de Sistemas  
**Disciplina:** UX/UI  
**Professor:** Daniel Ohata  
**Semestre:** 2026.2

## 2. Visão Geral

Módulo Java de cálculos básicos para a startup fictícia FinançApp. Pode apoiar orçamento mensal, divisão de despesas e projeções de economia. O projeto combina implementação funcional, testes automatizados, tratamento de erros, refatoração e documentação.

## 3. Problema

A FinançApp precisa de um módulo matemático reutilizável, legível e fácil de manter. Resultados incorretos, divisão por zero e operadores desconhecidos devem ser detectados, e entradas inválidas não podem encerrar inesperadamente a interface.

## 4. Objetivo

### 4.1 Objetivo Geral

Construir, validar, manter e documentar uma calculadora em Java, aplicando práticas de qualidade de software.

### 4.2 Objetivos Técnicos

- Implementar quatro operações com o método `calc(int a, int b, String op)`.
- Validar erros e informar mensagens claras.
- Testar resultados e exceções com JUnit.
- Refatorar na branch `Refatoracao` preservando o comportamento.
- Documentar classes, atributos, métodos, parâmetros, retornos e exceções.
- Gerar JavaDoc HTML e registrar a evolução em commits.

## 5. Escopo Funcional

| ID | Funcionalidade | Exemplo | Resultado |
|---|---|---|---|
| RF01 | Soma `+` | `10 + 5` | `15.0` |
| RF02 | Subtração `-` | `10 - 5` | `5.0` |
| RF03 | Multiplicação `*` | `10 * 5` | `50.0` |
| RF04 | Divisão `/` | `5 / 2` | `2.5` |
| RF05 | Impedir divisão por zero | `10 / 0` | `ArithmeticException` com mensagem |
| RF06 | Rejeitar operador inválido | `10 % 5` | `IllegalArgumentException` com mensagem |

O retorno é `double`: a divisão preserva a parte decimal. Os operandos são inteiros de 32 bits. A conversão ocorre antes da aritmética, evitando overflow de `int`; produtos muito grandes continuam sujeitos à precisão de `double`. Para valores monetários reais com precisão decimal exata, seria necessário outro escopo usando `BigDecimal`.

A classe `Calculadora` comunica entradas inválidas por exceções. `Aplicacao` captura essas exceções e erros de digitação, mostra a mensagem e continua lendo operações. Operador nulo e vazio também são rejeitados. Não existe atributo na calculadora porque ela não mantém estado.

## 6. Requisitos Não Funcionais

| ID | Categoria | Atendimento |
|---|---|---|
| RNF01 | Qualidade | Pacote `calculadora`, indentação e nomes descritivos |
| RNF02 | Testabilidade | 12 testes JUnit com esperado e obtido |
| RNF03 | Manutenção | Extração de métodos e seleção por switch |
| RNF04 | Documentação | JavaDoc com validação `-Xdoclint:all` |
| RNF05 | Versionamento | Histórico Git, `main` e `Refatoracao` locais; publicação pendente |
| RNF06 | Confiabilidade | Interface continua após erros de entrada |

## 7. Tecnologias Utilizadas

| Tecnologia | Versão | Utilização |
|---|---|---|
| Java / Eclipse Temurin JDK | 21.0.13 | Compilar e executar |
| JUnit Jupiter | 5.11.4 | Testes unitários |
| JUnit Platform Console | 1.11.4 | Executar testes sem IDE |
| JavaDoc | JDK 21 | Documentação HTML |
| Git | 2.53.0.windows.1 | Commits e branches |
| PowerShell | 7 ou Windows PowerShell 5.1 | Script de execução |
| GitHub | — | Destino da publicação da entrega |

## 8. Estrutura do Projeto

```text
projeto_calculadora/
├── src/calculadora/
│   ├── Calculadora.java
│   └── Aplicacao.java
├── test/calculadora/TesteCalculadora.java
├── docs/index.html
├── images/
│   ├── testes/
│   └── javadoc/
├── evidencias/
├── executar.ps1
├── README.md
└── .gitignore
```

## 9. Como Executar

Instale um **JDK 21 ou superior**, configure `JAVA_HOME` apontando para sua pasta e abra o PowerShell na raiz do projeto. O script baixa o JUnit do Maven Central na primeira execução; a pasta `lib/` é ignorada pelo Git.

```powershell
.\executar.ps1 testar
.\executar.ps1 executar
.\executar.ps1 documentar
```

Na aplicação, informe uma operação por linha, separada por espaços:

```text
10 + 5
5 / 2
10 / 0
10 * 5
sair
```

## 10. Testes e Evidências

Os mesmos 12 testes passaram antes e depois da refatoração: **12 executados, 12 aprovados, 0 falhas**. Cada caso imprime entrada, esperado e obtido; erros validam também o tipo da exceção e sua mensagem.

| Caso | Entrada | Esperado |
|---|---|---|
| Soma | 10 + 5 | 15.0 |
| Subtração | 10 - 5 | 5.0 |
| Multiplicação | 10 * 5 | 50.0 |
| Divisão | 10 / 5 | 2.0 |
| Divisão decimal | 5 / 2 | 2.5 |
| Negativos | -8 / 2 | -4.0 |
| Zero válido | 0 * 3 | 0.0 |
| Limite de int | 2147483647 + 1 | 2147483648.0 |
| Divisão por zero | 10 / 0 | ArithmeticException |
| Operador inválido | 10 % 5 | IllegalArgumentException |
| Operador nulo | calc(10, 5, null) | IllegalArgumentException |
| Operador vazio | calc(10, 5, "") | IllegalArgumentException |

O [relatório HTML dos testes](evidencias/testes.html) apresenta a saída real da execução. **Print pendente:** abra o relatório ou execute os testes e salve uma captura em `images/testes/testes.png` antes da entrega.

Registros completos: [antes](evidencias/testes-antes.txt), [depois](evidencias/testes-depois.txt), [relatório JUnit XML](evidencias/junit/TEST-junit-jupiter.xml).

O [teste funcional](evidencias/teste-funcional.txt) enviou divisão por zero, operador inválido, texto no lugar de inteiro e expressão incompleta; em seguida, `5 / 2` e `10 + 5` retornaram `2.5` e `15.0`, comprovando a continuidade da execução.

## 11. Processo de Refatoração

1. A implementação inicial foi registrada na `main` com condicionais `if/else` e testes aprovados.
2. A branch **Refatoracao** foi criada a partir desse commit.
3. As operações foram extraídas para `somar`, `subtrair`, `multiplicar` e `dividir`.
4. Os parâmetros dos métodos internos receberam nomes descritivos; a assinatura exigida de `calc` foi preservada.
5. A cadeia de condicionais foi substituída por `switch`; a validação do divisor ficou no método de divisão.
6. A mesma suíte passou novamente. Após a documentação, a versão refatorada foi integrada à `main` por merge, preservando a branch e o histórico inicial.

Para consultar a evolução:

```text
git log --oneline --graph --all
git diff 475b449 Refatoracao -- src/calculadora/Calculadora.java
```

## 12. Documentação JavaDoc

Documentação gerada pelo JavaDoc, incluindo membros privados para evidenciar os métodos extraídos e o atributo da suíte. Contém `@author`, `@version`, `@param`, `@return` e `@throws` quando aplicáveis.

Abra [docs/index.html](docs/index.html) no navegador. Selecione o pacote `calculadora` e a classe desejada. A documentação é local e não depende de hospedagem.

**Print pendente:** abra `docs/calculadora/Calculadora.html` no navegador e salve uma captura em `images/javadoc/calculadora.png` antes da entrega.

O [registro de geração](evidencias/javadoc.txt) contém a saída do comando JavaDoc.

## 13. Repositório e Entrega

**Publicação no GitHub pendente.** O repositório local contém as branches `main` e `Refatoracao` e os commits da atividade. O arquivo `repositorio.bundle` distribuído junto ao projeto preserva esse histórico e pode ser clonado:

```text
git clone repositorio.bundle projeto_calculadora
cd projeto_calculadora
git switch -c Refatoracao origin/Refatoracao
git switch main
```

O link do repositório da entrega será preenchido após sua criação e publicação na conta do aluno. O link abaixo é somente a referência do professor, não o repositório desta entrega:

[Modelo README utilizado — dr-ohata/calculadora_refatoracao](https://github.com/dr-ohata/calculadora_refatoracao/blob/main/README.md).
