package Model;

import com.sun.org.apache.regexp.internal.RE;

public enum Forme_Sommet {
    Rectangle, Cercle, Triangle, Losange;

    public static Forme_Sommet getFormeByText(String text) {
        if (text.equals("Rectangle")) {
            return Rectangle;
        } else if (text.equals("Cercle")) {
            return Cercle;
        } else if (text.equals("Triangle")) {
            return Triangle;
        } else if (text.equals("Losange")) {
            return Losange;
        } else {
            return Cercle;
        }
    }

}
