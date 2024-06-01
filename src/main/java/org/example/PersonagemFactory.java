package org.example;


/**
 * 
 */
public class PersonagemFactory {

    int level = 0;

    public PersonagemFactory(int level){
        this.level = level;
    }

    public PlayerClass makeGuerreiro() {
        return new Warrior();
    }

    /**
     * 
     */
    public PlayerClass makeMago() {
        return new Mage();
    }

    /**
     * 
     */
    public PlayerClass makeArqueiro() {
        return new Archer();
    }
}