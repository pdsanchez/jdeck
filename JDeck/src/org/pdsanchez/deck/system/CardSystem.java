/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdsanchez.deck.system;

/**
 *
 * @author pdsanchez
 */
public enum CardSystem {

    SYS_DEFAULT("system/default.txt"),
    SYS_TAMARIZ("system/tamariz.txt"),
    SYS_SISTEBBINS("system/siStebbins.txt");

    private String path;

    private CardSystem(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
