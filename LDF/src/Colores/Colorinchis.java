package Colores;

public class Colorinchis {

    // ROJO
    public static String red (String palabra) {
        return "\u001B[31m"+palabra+"\u001B[0m";
    }

    public static String red (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[31m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[31m"+palabra+"\u001B[0m";
        }
    }

    public static String red (char caracter) {
        return "\u001B[31m"+caracter+"\u001B[0m";
    }

    public static String red (int numero) {
        return "\u001B[31m"+numero+"\u001B[0m";
    }

    public static String red (double numero) {
        return "\u001B[31m"+numero+"\u001B[0m";
    }

    //NEGRO
    public static String black (String palabra) {
        return "\u001B[30m"+palabra+"\u001B[0m";
    }

    public static String black (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[30m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[30m"+palabra+"\u001B[0m";
        }
    }

    public static String black (char caracter) {
        return "\u001B[30m"+caracter+"\u001B[0m";
    }

    public static String black (int numero) {
        return "\u001B[30m"+numero+"\u001B[0m";
    }

    public static String black (double numero) {
        return "\u001B[30m"+numero+"\u001B[0m";
    }

    // VERDE
    public static String green (String palabra) {
        return "\u001B[32m"+palabra+"\u001B[0m";
    }

    public static String green (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[32m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[32m"+palabra+"\u001B[0m";
        }
    }

    public static String green (char caracter) {
        return "\u001B[32m"+caracter+"\u001B[0m";
    }

    public static String green (int numero) {
        return "\u001B[32m"+numero+"\u001B[0m";
    }

    public static String green (double numero) {
        return "\u001B[32m"+numero+"\u001B[0m";
    }

    //AMARILLO
    public static String yellow (String palabra) {
        return "\u001B[33m"+palabra+"\u001B[0m";
    }

    public static String yellow (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[33m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[33m"+palabra+"\u001B[0m";
        }
    }
    public static String yellow (char caracter) {
        return "\u001B[33m"+caracter+"\u001B[0m";
    }

    public static String yellow (int numero) {
        return "\u001B[33m"+numero+"\u001B[0m";
    }

    public static String yellow (double numero) {
        return "\u001B[33m"+numero+"\u001B[0m";
    }

    //AZUL
    public static String blue (String palabra) {
        return "\u001B[34m"+palabra+"\u001B[0m";
    }

    public static String blue (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[34m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[34m"+palabra+"\u001B[0m";
        }
    }

    public static String blue (char caracter) {
        return "\u001B[34m"+caracter+"\u001B[0m";
    }

    public static String blue (int numero) {
        return "\u001B[34m"+numero+"\u001B[0m";
    }

    public static String blue (double numero) {
        return "\u001B[34m"+numero+"\u001B[0m";
    }

    // MORADO
    public static String purple (String palabra) {
        return "\u001B[35m"+palabra+"\u001B[0m";
    }

    public static String purple (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[35m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[35m"+palabra+"\u001B[0m";
        }
    }

    public static String purple (char caracter) {
        return "\u001B[35m"+caracter+"\u001B[0m";
    }

    public static String purple (int numero) {
        return "\u001B[35m"+numero+"\u001B[0m";
    }

    public static String purple (double numero) {
        return "\u001B[35m"+numero+"\u001B[0m";
    }

    //CYAN
    public static String cyan (String palabra) {
        return "\u001B[36m"+palabra+"\u001B[0m";
    }

    public static String cyan (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[36m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[36m"+palabra+"\u001B[0m";
        }
    }

    public static String cyan (char caracter) {
        return "\u001B[36m"+caracter+"\u001B[0m";
    }

    public static String cyan (int numero) {
        return "\u001B[36m"+numero+"\u001B[0m";
    }

    public static String cyan (double numero) {
        return "\u001B[36m"+numero+"\u001B[0m";
    }

    // BLANCO
    public static String white (String palabra) {
        return "\u001B[37m"+palabra+"\u001B[0m";
    }

    public static String white (String palabra, boolean modificador) {
        if (modificador) {
            return "\u001B[1m\u001B[37m"+palabra+"\u001B[0m";
        } else {
            return "\033[3m\u001B[37m"+palabra+"\u001B[0m";
        }
    }

    public static String white (char caracter) {
        return "\u001B[37m"+caracter+"\u001B[0m";
    }

    public static String white (int numero) {
        return "\u001B[37m"+numero+"\u001B[0m";
    }

    public static String white (double numero) {
        return "\u001B[37m"+numero+"\u001B[0m";
    }
}
