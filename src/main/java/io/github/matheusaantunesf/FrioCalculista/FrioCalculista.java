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

    Integer rodada = 0, ultimoMovimento = -1,
            quantidadeRock = 0, quantidadePaper = 0, quantidadeScissors = 0;
    ArrayList<ArrayList<Move>> amostras = new ArrayList<>();
    ArrayList<ArrayList<ProporcaoIndice>> proporcoes = new ArrayList<>();

    @Override
    public String getDeveloperName() {
        return "Matheus Antunes Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        rodada++;

        // Inicio da partida
        if (opponentPreviousMove == Move.NONE) {
            rodada = 1;
            amostras.clear();
            ultimoMovimento = 0;
            return Move.ROCK;
        }

        // Coleta de amostras iniciais para analise estatistica
        if (rodada < 32) {
            // Jogada aleatoria
            Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 3);
            
            // Insercao das amostras coletadas
            while (amostras.size() <= ultimoMovimento) {
                amostras.add(new ArrayList<>());
            }
            amostras.get(ultimoMovimento).add(opponentPreviousMove);
            
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
            proporcoes.clear();
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
                proporcoes.get(i).add(new ProporcaoIndice((float) quantidadeRock / amostras.get(i).size(), 0));
                proporcoes.get(i).add(new ProporcaoIndice((float) quantidadePaper / amostras.get(i).size(), 1));
                proporcoes.get(i).add(new ProporcaoIndice((float) quantidadeScissors / amostras.get(i).size(), 2));
                proporcoes.get(i).sort(Comparator.comparing(ProporcaoIndice::proporcao));
            }
            
            // Faz a previsao e realiza a jogada com menos chance de ser vencida
            Integer previsao = proporcoes.get(ultimoMovimento).get(0).indice();
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
