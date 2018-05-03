package io;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import logic.Flag;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

public class CsvInputReader {

    public List<Flag> parseFlags(){
        return getCsvToBean().parse();
    }

    private CsvToBean<Flag> getCsvToBean(){
        try {
            return new CsvToBeanBuilder(new InputStreamReader(getClass().getResourceAsStream("/flagi_csv.csv")))
                    .withType(Flag.class)
                    .withSeparator(',')
                    .build();
        } catch (Exception e) {
            //throw new UncheckedIOException(e);
        }
        throw new UncheckedIOException(new IOException("Failed to read from CSV"));
    }

}
