/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package io.github.matheusaantunesf.FrioCalculista;

import io.github.guisso.jankenpon.AbstractPlayer;
import io.github.guisso.jankenpon.Move;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Comparator;

/**
 *
 * @author Matheus Antunes <maf6@aluno.ifnmg.edu.br>
 */
public class FrioCalculista
        extends AbstractPlayer {

    Integer rodada = 0, ultimoMovimento = -1, penultimoMovimento = -1;
    ArrayList<ArrayList<Move>> amostras = new ArrayList<>();

    @Override
    public String getDeveloperName() {
        return "Matheus Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        rodada++;

        // Inicio da partida
        if (opponentPreviousMove == Move.NONE) {
            rodada = 1;
            amostras.clear();
            ultimoMovimento = 0;
            penultimoMovimento = -1;
            return Move.ROCK;
        }

        // Coleta de amostras iniciais para analise estatistica
        if (rodada < 32) {
            // Jogada aleatoria
            Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 3);

            // Insercao das amostras coletadas
            if (penultimoMovimento > -1) {
                while (amostras.size() <= penultimoMovimento) {
                    amostras.add(new ArrayList<>());
                }
                amostras.get(penultimoMovimento).add(opponentPreviousMove);
            }
            penultimoMovimento = ultimoMovimento;
            // Tomada de decisao
            ultimoMovimento = numeroAleatorio;
            if (numeroAleatorio == 0) {
                return Move.ROCK;
            } else if (numeroAleatorio == 1) {
                return Move.PAPER;
            } else {
                return Move.SCISSORS;
            }
        } else {
            // Constroi a tabela de proporcoes dinamica 
            ArrayList<ArrayList<ProporcaoIndice>> proporcoes = new ArrayList<>();
            Integer quantidadeRock, quantidadePaper, quantidadeScissors;

            amostras.get(ultimoMovimento).add(opponentPreviousMove);

            for (int i = 0; i < amostras.size(); i++) {
                quantidadeRock = quantidadePaper = quantidadeScissors = 0;
                for (Move movimentoOponente : amostras.get(i)) {
                    if (movimentoOponente == Move.ROCK) {
                        quantidadeRock++;
                    } else if (movimentoOponente == Move.PAPER) {
                        quantidadePaper++;

                    } else {
                        quantidadeScissors++;
                    }
                }
                while (proporcoes.size() < amostras.size()) {
                    proporcoes.add(new ArrayList<>());
                }
                Float proporcaoRock = (float) quantidadeRock / amostras.get(i).size();
                Float proporcaoPaper = (float) quantidadePaper / amostras.get(i).size();
                Float proporcaoScissors = (float) quantidadeScissors / amostras.get(i).size();

                proporcoes.get(i).add(new ProporcaoIndice(proporcaoRock, 0));
                proporcoes.get(i).add(new ProporcaoIndice(proporcaoPaper, 1));
                proporcoes.get(i).add(new ProporcaoIndice(proporcaoScissors, 2));

                proporcoes.get(i).sort(Comparator.comparing(ProporcaoIndice::proporcao));
            }

            // Faz a previsao da jogada atual menos provavel do oponente
            Integer previsao = proporcoes.get(ultimoMovimento).get(0).indice();
            // Realiza a jogada que perderia para a menos provavel
            if (previsao == 0) {
                ultimoMovimento = 2;
                return Move.SCISSORS;
            } else if (previsao == 1) {
                ultimoMovimento = 0;
                return Move.ROCK;
            } else {
                ultimoMovimento = 1;
                return Move.PAPER;
            }
        }
    }

    // record auxiliar para nao perder os indices das proporcoes ao ordenar
    public record ProporcaoIndice(Float proporcao, Integer indice) {

    }
}
