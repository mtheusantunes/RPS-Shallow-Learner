/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package io.github.matheusaantunesf.jankenponplayer;

import io.github.guisso.jankenpon.AbstractPlayer;
import io.github.guisso.jankenpon.Move;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Comparator;

/**
 *
 * @author Matheus Antunes <maf6@aluno.ifnmg.edu.br>
 */
public class JankenponPlayer
        extends AbstractPlayer {

    Integer rodada = 1, ultimoMovimento, quantidadeRock = 0, quantidadePaper = 0,
            quantidadeScissors = 0;
    ArrayList<ArrayList<Move>> amostras;
    Integer[][] menosProvaveis = new Integer[3][3];
    ArrayList<ArrayList<ProporcaoIndice>> proporcoes;

    @Override
    public String getDeveloperName() {
        return "Matheus Antunes Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        if (opponentPreviousMove == Move.NONE) {
            rodada = 1;
            quantidadeRock = quantidadePaper = quantidadeScissors = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    proporcoes.clear();
                    amostras.clear();
                    ultimoMovimento = 0;
                    return Move.ROCK;
                }
            }
        } else if (rodada < 31) {
            Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 3);
            amostras.get(ultimoMovimento).add(opponentPreviousMove);
            ultimoMovimento = numeroAleatorio;
            if (numeroAleatorio == 0) {
                return Move.ROCK;
            } else if (numeroAleatorio == 1) {
                return Move.PAPER;
            } else {
                return Move.SCISSORS;
            }
        } else {
            if (rodada == 31) {
                for (int i = 0; i < amostras.size(); i++) {
                    for (Move movimentoOponente : amostras.get(i)) {
                        if (movimentoOponente == Move.ROCK) {
                            quantidadeRock++;
                        } else if (movimentoOponente == Move.PAPER) {
                            quantidadePaper++;
                        } else {
                            quantidadeScissors++;
                        }
                    }
                    proporcoes.get(i).add(new ProporcaoIndice((float) 
                            quantidadeRock / amostras.get(i).size(), 0));
                    proporcoes.get(i).add(new ProporcaoIndice((float) 
                            quantidadePaper / amostras.get(i).size(), 1));
                    proporcoes.get(i).add(new ProporcaoIndice((float) 
                            quantidadeScissors / amostras.get(i).size(), 2));
                    proporcoes.get(i).sort(Comparator.comparing
                            (ProporcaoIndice::proporcao));
                }
            }

        }
        return Move.NONE;
    }

    public record ProporcaoIndice(Float proporcao, Integer indice) {

    }

}
