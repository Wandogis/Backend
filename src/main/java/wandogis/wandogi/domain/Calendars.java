package wandogis.wandogi.domain;

import lombok.Data;

import java.sql.Array;
import java.sql.Date;

// 달력에 각 날짜에 어떤 책들을 읽었는지 확인 가능, 그날 총 읽은 책의 분량은 몇 페이지인지 색상의 진하기를 다르게 나타내어 표시
@Data
public class Calendars {
    private Date date;
    private Array title;    // 읽은 책이 여러 권일 수 있음
    private int page;   // 읽은 페이지 수, 여러 책이면 계속 더해서 하루에 얼마나 읽었는지 표시
}
