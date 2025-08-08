/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.matheusaantunesf.jankenponplayer;

import io.github.guisso.jankenpon.AbstractPlayer;
import io.github.guisso.jankenpon.Move;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Matheus Antunes <maf6@aluno.ifnmg.edu.br>
 */
public class JankenponPlayer 
    extends AbstractPlayer{

    Integer rodada = 0;
    
    @Override
    public String getDeveloperName() {
        return "Matheus Antunes Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        if(opponentPreviousMove == Move.NONE){
            rodada = 0;
        }
        if(rodada <= 30){
            Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 4);
            if(numeroAleatorio == 1){
                return Move.ROCK;
            } else if(numeroAleatorio == 2){
                return Move.PAPER;
            } else {
                return Move.SCISSORS;
            }
        }
        return Move.NONE;
    }

}
