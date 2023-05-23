package wandogis.wandogi.domain;//테이블정보

import lombok.Data;

@Data
public class Users {
    private String loginid;
    private String password;
    private String name;
    private String photo;
    private String genre;
    private String age;
    private String challenge;

    @Override
    public String toString() {
        return "name is " + name;
    }
}

