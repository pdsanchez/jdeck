/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pdsanchez.deck;

/**
 * Playing card abstraction.
 * 
 * @author pdsanchez
 */
public class Card {
    enum Suit { SPADES, HEARTS, CLUBS, DIAMONDS };
    enum Value {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);
        
        private int value;
        
        private Value(int v) {
            this.value = v;
        }
        
        public int getValue() {
            return value;
        }
        
        public String getName() {
            return this.toString();
        }
    }
}
