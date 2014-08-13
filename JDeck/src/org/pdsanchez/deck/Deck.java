/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.deck;

import org.pdsanchez.deck.system.CardSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pdsanchez
 */
public class Deck {

    private static final Random RND = new Random();

    private static final int DECK_SIZE_52 = 52;

    private List<Card> cards;

    private Map<Integer, Card> mapByPos;
    private Map<Card, Integer> mapByCard;

    public Deck() {
        this(CardSystem.SYS_DEFAULT);
    }

    public Deck(CardSystem cardSys) {
        cards = new ArrayList<>(DECK_SIZE_52);

        mapByPos = new HashMap<>(DECK_SIZE_52);
        mapByCard = new HashMap<>(DECK_SIZE_52);

        loadSystem(cardSys);
    }

    public final void loadSystem(File cardSys) throws FileNotFoundException {
        loadSystem(new FileInputStream(cardSys));
    }

    public final void loadSystem(CardSystem cardSys) {
        loadSystem(this.getClass().getResourceAsStream(cardSys.getPath()));
    }

    private void loadSystem(InputStream is) {
        Scanner scanner = new Scanner(is).useDelimiter("\\s+");

        int idx = 1;
        while (scanner.hasNext()) {
            String valAbbr = scanner.next();
            String suiteAbbr = scanner.next();
            Card card = new Card(Card.getSuite(suiteAbbr), Card.getValue(valAbbr));
            cards.add(card);

            mapByCard.put(card, idx);
            mapByPos.put(idx, card);
        }
        scanner.close();
    }

    public void restartOrder() {
        cards = new ArrayList(mapByCard.keySet());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int cut(int pos) {
        Collections.rotate(cards, -pos);
        return pos;
    }

    public int cut() {
        int pos = RND.nextInt(45) + 5;
        return cut(pos);
    }

    public int getPosition(Card card) {
        return cards.indexOf(card);
    }

    public Card getCard(int position) {
        return cards.get(position);
    }

    public Card getRandomCard() {
        int pos = RND.nextInt(52);
        return cards.get(pos);
    }

    public int getSystemPosition(Card card) {
        return mapByCard.get(card);
    }

    public Card getSystemCard(int position) {
        return mapByPos.get(position);
    }

    public Iterator<Card> fwIterator() {
        return new Iterator<Card>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return (idx < DECK_SIZE_52);
            }

            @Override
            public Card next() {
                Card card = cards.get(idx);
                idx++;
                return card;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<Card> bwIterator() {
        return new Iterator<Card>() {
            private int idx = DECK_SIZE_52 - 1;

            @Override
            public boolean hasNext() {
                return (idx >= 0);
            }

            @Override
            public Card next() {
                Card card = cards.get(idx);
                idx--;
                return card;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Deck d = new Deck(CardSystem.SYS_SISTEBBINS);
        System.out.println(d);
        int pos = d.cut();
        System.out.println("CUT " + pos + ": " + d);

        Deck ssDeck = new Deck();

        System.out.println(ssDeck.getRandomCard());
        System.out.println(ssDeck.getRandomCard());
        System.out.println(ssDeck.getRandomCard());
        System.out.println(ssDeck.getRandomCard());
        System.out.println(ssDeck.getRandomCard());

        ssDeck.shuffle();

        int cut = ssDeck.cut();
        System.out.println("CUT " + cut);
//    for (int i=1; i<=52; i++) {
//      System.out.println(ssDeck.getPosition(ssDeck.getCard(i)) + " --> " + ssDeck.getCard(i));
//    }
        Iterator<Card> fwIt = ssDeck.fwIterator();
        while (fwIt.hasNext()) {
            Card c = fwIt.next();
            System.out.println(ssDeck.getPosition(c) + " --> " + c);
        }

        System.out.println();

        Iterator<Card> bwIt = ssDeck.bwIterator();
        while (bwIt.hasNext()) {
            Card c = bwIt.next();
            System.out.println(ssDeck.getPosition(c) + " --> " + c);
        }

        ssDeck.restartOrder();

    }
}
