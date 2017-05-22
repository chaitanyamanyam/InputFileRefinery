package com.company;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public enum Delimiter {

    COMMA,
    SPACE,
    PIPE,
    UNSUPPORTED;

    static String valueOf(Delimiter delimiter)
    {

        switch (delimiter)
        {
            case PIPE:
                return "|";
            case COMMA:
                return ",";
            case SPACE:
                return " ";
            default:
                throw new UnsupportedOperationException("Unsupported Delimiter");
        }
    }
}
