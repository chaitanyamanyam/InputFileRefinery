package com.company;


/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public class Resolver {

    public DataParser resolve(String firstLine)
    {

        Delimiter delimiter = getDelimiter(firstLine); // get the delimiter

        switch (delimiter){
            case COMMA:
                return new CommaFileDataParser();
            case SPACE:
                return new SpaceFileDataParser();
            case PIPE:
                return new PipeFileDataParser();
            default:
                throw new UnsupportedOperationException("Unsupported Delimiter");
        }
    }

    private Delimiter getDelimiter(String firstLine)
    {
        for(Delimiter delimiter: Delimiter.values())
        {
            if(firstLine.contains(Delimiter.valueOf(delimiter))) return delimiter;
        }
        return Delimiter.UNSUPPORTED;
    }
}
