package wandogis.wandogi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wandogis.wandogi.gpt.Choice;

import java.lang.reflect.Array;
import java.util.List;

@Data
@Document(collection = "wandogi.book_test")
public class Book_test {
    @Id
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;
    private String title;
    private String isbn;
}


