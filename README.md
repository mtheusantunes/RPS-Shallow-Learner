# Rock Paper Scissors - Shallow Learner

[Português](#português) | [English](#english)

<a name="english"></a>
## English Version
# Rock Paper Scissors Bot: Shallow Learning Strategy

This repository contains an intelligent Rock Paper Scissors (Jankenpon) player implemented in Java for an academic project. The bot employs a **Traditional Machine Learning (Shallow Model)** approach to predict and counter opponents' moves.

## 🧠 Strategy

The algorithm is not based on luck, but on **Conditional Probability** and the mapping of opponent reactions.

### How it works:
* **Exploration Phase:** For the first 30 rounds, the bot plays randomly to collect data without biasing the opponent's behavior.
* **Reaction Matrix:** The bot assumes the opponent reacts to my previous move. It stores these reactions in a frequency matrix (Markov Chain).
* **Probabilistic Calculation:** The model calculates the percentage of each response for every move I made. For example: *"If I played Scissors, what is the probability of them reacting with Rock?"*
* **Decision Making:** After the warm-up, the bot analyzes which move the opponent is **least** likely to make (based on history) and chooses the option that would lose to that least likely move — which is also the move that wins or ties against their most probable choices.
* **Continuous Learning:** Even after the initial 30 rounds, the matrices are constantly updated, allowing adaptation to an opponent's strategy shifts.

## 🛠️ Shallow Learning Concepts Applied

This project is a practical example of a **Shallow Model**:
* **Low Computational Cost:** Lightweight training and execution.
* **Structured Data:** Uses frequency matrices for prediction.
* **Interpretability (White Box):** You can clearly understand why the model made a decision by analyzing the calculated probabilities.
* **Efficiency:** Capable of identifying complex patterns with a relatively low volume of data (300 rounds).

## 📋 Prerequisites and Dependencies

To run and test this player, you must integrate it with the game engine provided in the following links:

1. [JanKenPonManager](https://github.com/guisso/JanKenPonManager) - Tournament and match manager.
2. [JanKenPon](https://github.com/guisso/JanKenPon) - Interface and base game classes.

## 🎮 The Tournament
* **Rounds:** 300 rounds per match.
* **Opponents:** Round-robin format (every player faces every other player).
* **Reset:** The bot's memory is cleared after each match, ensuring one opponent's patterns don't interfere with another's analysis.
* **Input:** The bot receives only the opponent's last move as a parameter.
* **Scoring System:** Stars indicate consecutive wins in a match:
  • First win (no stars): +1 point.
  • 1st consecutive win (one star): +2 points.
  • 2 or more consecutive wins (two stars): +3 points.
  • Any loss: -1 point for the loser.
  • Tie: 0 points.

## 🚀 Technologies
* Java
* Computational Statistics

---

<a name="português"></a>
## Versão em Português
# Pedra, Papel e Tesoura (Jokenpo) Bot: Estratégia Baseada em Shallow Learning

Este repositório contém a implementação de um player inteligente de Jokenpô (Pedra, Papel e Tesoura) desenvolvido em Java para uma disciplina acadêmica. O bot utiliza uma abordagem de **Machine Learning Tradicional (Shallow Model)** para prever e contra-atacar as jogadas dos oponentes.

## 🧠 A Estratégia

O algoritmo não se baseia em sorte, mas em **Probabilidade Condicional** e no mapeamento de reações do adversário.

### Como funciona:
* **Fase de Exploração:** Nas primeiras 30 rodadas, o bot joga de forma aleatória para coletar dados sem viciar o comportamento do oponente.
* **Matriz de Reações:** O bot assume que o oponente reage à jogada anterior que eu fiz. Ele armazena essas reações em uma matriz de frequências.
* **Cálculo Probabilístico:** O modelo calcula a porcentagem de cada resposta para cada jogada minha. Por exemplo: *"Se eu joguei Tesoura, qual a probabilidade de ele reagir com Pedra?"*
* **Tomada de Decisão:** Após o aquecimento, o bot analisa qual jogada o oponente tem **menos** chance de fazer (baseado no histórico) e escolhe a opção que perderia para ela, o que também é a opção que venceria ou empataria com as jogadas mais prováveis dele.
* **Aprendizado Contínuo:** Mesmo após as 30 rodadas iniciais, as matrizes continuam sendo atualizadas, permitindo adaptação a mudanças de estratégia do oponente.

## 🛠️ Conceitos de Shallow Learning Aplicados

Este projeto é um exemplo prático de um **Modelo Raso (Shallow Model)**:
* **Baixo custo computacional:** Treinamento e execução leves.
* **Dados Estruturados:** Utiliza matrizes de contagem para predição.
* **Interpretabilidade (Caixa Branca):** É possível entender exatamente por que o modelo tomou cada decisão analisando as probabilidades calculadas.
* **Eficiência:** Capaz de identificar padrões complexos com um volume relativamente baixo de dados (300 rodadas).

## 📋 Pré-requisitos e Dependências

Para executar e testar este player, é necessário integrar este código com o motor do jogo fornecido nos links abaixo:

1.  [JanKenPonManager](https://github.com/guisso/JanKenPonManager) - Gerenciador das partidas e torneios.
2.  [JanKenPon](https://github.com/guisso/JanKenPon) - Interface e classes base do jogo.

## 🎮 O Torneio
* **Rodadas:** 300 rodadas por confronto.
* **Oponentes:** Todos os players se enfrentam (Round-robin).
* **Reset:** A memória do bot é resetada a cada novo confronto, garantindo que o padrão de um aluno não interfira na análise de outro.
* **Entrada:** O bot recebe apenas a última jogada do oponente como parâmetro.
* **Pontuação:** As estrelas indicam o número de vitórias consecutivas na disputa entre dois jogadores, sendo:
  • primeira vitória (sem estrelas) gera ganho de um ponto;
  • 1 vitória consecutiva (uma estrela) gera ganho de dois pontos;
  • 2 ou mais vitórias consecutivas (duas estrelas) gera ganho de três pontos;
  • qualquer derrota retira um ponto do perdedor da rodada;
  • empate não gera pontuação.

## 🚀 Tecnologias
* Java
* Estatística Computacional
