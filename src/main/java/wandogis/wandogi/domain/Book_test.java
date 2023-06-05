package wandogis.wandogi.domain;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class Book_test {
    private String object;
    private Long created;
    private String model;
    private Array choices;
    private String bookTitle;
    private String isbn;
}
