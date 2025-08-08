/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.matheusaantunesf.jankenponplayer;

import io.github.guisso.jankenpon.AbstractPlayer;
import io.github.guisso.jankenpon.Move;

/**
 *
 * @author Matheus Antunes <maf6@aluno.ifnmg.edu.br>
 */
public class JankenponPlayer 
    extends AbstractPlayer{

    @Override
    public String getDeveloperName() {
        return "Matheus Antunes Freire";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        return Move.NONE;
    }

}
