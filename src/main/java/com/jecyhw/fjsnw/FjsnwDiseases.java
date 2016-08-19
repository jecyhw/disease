package com.jecyhw.fjsnw;

import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-19.
 */
@Component
public class FjsnwDiseases implements Iterable<FjsnwDisease>{
    int startPageNumber = 0;
    int endPageNumber = 61;

    @Override
    public Iterator<FjsnwDisease> iterator() {
        return new FjsnwDiseaseIterator();
    }

    class FjsnwDiseaseIterator implements Iterator<FjsnwDisease> {

        @Override
        public boolean hasNext() {
            return startPageNumber < endPageNumber;
        }

        @Override
        public FjsnwDisease next() {
            startPageNumber++;
            return new FjsnwDisease(startPageNumber);
        }
    }
}
