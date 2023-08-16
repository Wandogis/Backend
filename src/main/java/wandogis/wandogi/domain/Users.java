package wandogis.wandogi.domain;//테이블정보

import lombok.Data;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;        // DB 자동 생성
    @Column(nullable = false)
    private String email;       // 가입되어있는 회원인지 확인함
    @Column(nullable = false)
    private String nickname;    // 사용자 닉네임
    @Column(nullable = false)
    private int socialType;     // 1: Naver, 2: Kakao
    @Column(nullable = false)
    private String photo;       // 사용자 프로필 이미지
    private String genre;       // 도서 추천 때 선택 입력(선호하는 장르)
    private int age;            // 도서 추천 때 선택 입력
    private String gender;      // 도서 추천 때 선택 입력
    private int ranking;        // 완료한 챌린지 개수에 따른 오늘의 순위
    private int numOfChallenge; // 완료한 챌린지 개수
    private List<ObjectId> books;      // 유저가 선호하는 책(Books와 One-to-Many)
    private List<ObjectId> challenges; // 유저가 진행한 챌린지(Challenges와 Many-to-Many)
    private List<ObjectId> calendars;  // 유저 달력(날짜)(Calendars와 One-to-Many)
}