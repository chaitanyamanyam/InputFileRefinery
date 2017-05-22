package com.company;

import java.util.List;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public interface DataParser {

    List<Person> parse(List<String> plainData);
}
