/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.deck;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Playing card abstraction.
 *
 * @author pdsanchez
 */
public class Card {

    public static enum Suit {

        SPADES("suit.spades"),
        HEARTS("suit.hearts"),
        CLUBS("suit.clubs"),
        DIAMONDS("suit.diamonds");

        private String name;

        private Suit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static enum Value {

        ACE("val.ace", "A", 1),
        TWO("val.two", "2", 2),
        THREE("val.three", "3", 3),
        FOUR("val.four", "4", 4),
        FIVE("val.five", "5", 5),
        SIX("val.six", "6", 6),
        SEVEN("val.seven", "7", 7),
        EIGHT("val.eight", "8", 8),
        NINE("val.nine", "9", 9),
        TEN("val.ten", "10", 10),
        JACK("val.jack", "J", 11),
        QUEEN("val.queen", "Q", 12),
        KING("val.king", "K", 13);

        private String name;
        private String abbr;
        private int index;

        private Value(String name, String abbr, int index) {
            this.name = name;
            this.abbr = abbr;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public String getAbbr() {
            return abbr;
        }

        public int getIndex() {
            return index;
        }
    }
    
    private Suit suit;
    private Value value;
    
    private ResourceBundle i18n;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        
        i18n = ResourceBundle.getBundle("org.pdsanchez.deck.i18n.MessagesBundle");
    }
    
    @Override
    public String toString() {
        String v = i18n.getString(value.getName());
        String s = i18n.getString(suit.getName());
        return v + " - " + s;
    }
    
    public void setLocale(String language, String country) {
        i18n = ResourceBundle.getBundle("org.pdsanchez.deck.i18n.MessagesBundle", 
                new Locale(language, country));
    }

    public static void main(String[] args) {
        Card c = new Card(Suit.CLUBS, Value.ACE);
        System.out.println("A: " + c.toString());
        c.setLocale("en", "US");
        System.out.println("A: " + c.toString());
        c.setLocale("esdn", "daUS");
        System.out.println("A: " + c.toString());
    }
}
