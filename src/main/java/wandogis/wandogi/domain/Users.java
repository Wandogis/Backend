package wandogis.wandogi.domain;

import lombok.Data;

@Data
public class Users {
    private String name;

    @Override
    public String toString() {
        return "name is " + name;
    }
}

