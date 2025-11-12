package ch.heg.cours6341.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class Csv {
  public static <T> List<T> parse(String fileName, Class<? extends T> classToParse) {
    Reader reader = new InputStreamReader(Csv.class.getClassLoader().getResourceAsStream(fileName));

    CsvToBean<T> csvToBean =
        new CsvToBeanBuilder<T>(reader)
            .withType(classToParse)
            .withIgnoreLeadingWhiteSpace(true)
            .build();

    return csvToBean.parse();
  }
}
