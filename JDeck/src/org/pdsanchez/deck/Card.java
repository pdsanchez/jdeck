/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.deck;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Playing card abstraction.
 *
 * @author pdsanchez
 */
public class Card {
    private static ResourceBundle i18n = 
            ResourceBundle.getBundle("org.pdsanchez.deck.i18n.MessagesBundle");

    public static enum Suit {

        SPADES("suit.spades"),
        HEARTS("suit.hearts"),
        CLUBS("suit.clubs"),
        DIAMONDS("suit.diamonds");

        private final String name;

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
        KING("val.king", "K", 13),
        JOKER("val.joker", "JOKER", 0);

        private final String name;
        private final String abbr;
        private final int index;

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
    
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, int idx) {
        this.suit = suit;
        this.value = getValue(idx);
    }
    
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }
    
    public String getSuitName() {
        return suit.getName();
    }

    public Value getValue() {
        return value;
    }
    
    public String getValueAbbr() {
        return value.getAbbr();
    }

    public int getValueIndex() {
        return value.getIndex();
    }

    public String getValueName() {
        return value.getName();
    }
    
    public static Value getValue(int idx) {
        switch (idx) {
            case 1:
                return Value.ACE;
            case 2:
                return Value.TWO;
            case 3:
                return Value.THREE;
            case 4:
                return Value.FOUR;
            case 5:
                return Value.FIVE;
            case 6:
                return Value.SIX;
            case 7:
                return Value.SEVEN;
            case 8:
                return Value.EIGHT;
            case 9:
                return Value.NINE;
            case 10:
                return Value.TEN;
            case 11:
                return Value.JACK;
            case 12:
                return Value.QUEEN;
            case 13:
                return Value.KING;
            default:
                return Value.JOKER;
        }
    }
    
    public static Value getValue(String abbr) {
        switch (abbr) {
            case "A":
                return Value.ACE;
            case "2":
                return Value.TWO;
            case "3":
                return Value.THREE;
            case "4":
                return Value.FOUR;
            case "5":
                return Value.FIVE;
            case "6":
                return Value.SIX;
            case "7":
                return Value.SEVEN;
            case "8":
                return Value.EIGHT;
            case "9":
                return Value.NINE;
            case "10":
                return Value.TEN;
            case "J":
                return Value.JACK;
            case "Q":
                return Value.QUEEN;
            case "K":
                return Value.KING;
            default:
                return Value.JOKER;
        }
    }
    
    public static Suit getSuite(String abbr) {
        switch (abbr) {
            case "S":
                return Suit.SPADES;
            case "H":
                return Suit.HEARTS;
            case "C":
                return Suit.CLUBS;
            case "D":
                return Suit.DIAMONDS;
            default:
                throw new AssertionError();
        }
    }
    
    public static void setLocale(String language, String country) {
        i18n = ResourceBundle.getBundle("org.pdsanchez.deck.i18n.MessagesBundle", 
                new Locale(language, country));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.suit);
        hash = 71 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String v = i18n.getString(value.getName());
        String s = i18n.getString(suit.getName());
        return v + "-" + s;
    }

    public static void main(String[] args) {
        Card c = new Card(Suit.CLUBS, Value.ACE);
        System.out.println("A: " + c.toString());
        Card.setLocale("en", "US");
        System.out.println("A: " + c.toString());
        //c.setLocale("esdn", "daUS");
        System.out.println("A: " + c.toString());
        
        Card c1 = new Card(Suit.SPADES, 10);
        System.out.println("A: " + c1.toString());
        //c1.setLocale("en", "US");
        System.out.println("A: " + c1.toString());
        Card.setLocale("esdn", "daUS");
        System.out.println("A: " + c1.toString());

        Card c2 = new Card(Suit.CLUBS, 1);
        System.out.println("EQ: " + c.equals(c2));
        System.out.println("EQ: " + c.equals(c1));
    }
}
