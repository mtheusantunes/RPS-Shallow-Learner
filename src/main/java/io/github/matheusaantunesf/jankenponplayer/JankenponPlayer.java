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

    Integer rodada = 0, ultimoMovimento = -1,
            quantidadeRock = 0, quantidadePaper = 0, quantidadeScissors = 0;
    ArrayList<ArrayList<Move>> amostras = new ArrayList<>();
    Integer[][] menosProvaveis = new Integer[3][3];
    ArrayList<ArrayList<ProporcaoIndice>> proporcoes = new ArrayList<>();

    @Override
    public String getDeveloperName() {
        return "Matheus Antunes Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        rodada++;
        if (opponentPreviousMove == Move.NONE) {
            rodada = 1;
            quantidadeRock = quantidadePaper = quantidadeScissors = 0;
            proporcoes.clear();
            amostras.clear();
            ultimoMovimento = 0;;
            return Move.ROCK;
        }
        if (rodada < 32) {
            Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 3);
            if (ultimoMovimento > -1) {
                while (amostras.size() <= ultimoMovimento) {
                    amostras.add(new ArrayList<>());
                }
                amostras.get(ultimoMovimento).add(opponentPreviousMove);
            }
            ultimoMovimento = numeroAleatorio;
            if (numeroAleatorio == 0) {
                return Move.ROCK;
            } else if (numeroAleatorio == 1) {
                return Move.PAPER;
            } else {
                return Move.SCISSORS;
            }
        } else {
            if (rodada == 32) {
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
            }

            Integer previsao = proporcoes.get(ultimoMovimento).get(2).indice();

            if (previsao == 0) {
                ultimoMovimento = 1;
                return Move.PAPER;
            } else if (previsao == 1) {
                ultimoMovimento = 2;
                return Move.SCISSORS;
            } else {
                ultimoMovimento = 0;
                return Move.ROCK;
            }
        }
    }

    public record ProporcaoIndice(Float proporcao, Integer indice) {

    }
}
