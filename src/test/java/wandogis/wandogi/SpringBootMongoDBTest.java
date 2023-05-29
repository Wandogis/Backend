package wandogis.wandogi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Users;
import wandogis.wandogi.repository.ChallengeRepository;
import wandogis.wandogi.repository.PostsMongoDBRepository;
import wandogis.wandogi.repository.UsersMongoDBRepository;
import wandogis.wandogi.service.ChallengeService;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest()
public class SpringBootMongoDBTest {
    @Autowired
    private UsersMongoDBRepository mongoDBRepository;
    @Autowired
    private PostsMongoDBRepository postsMongoDBRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeService challengeService;

    @Test
    public void printMongoDB() {
        System.out.println(mongoDBRepository.findByName("park").getName());
        List<Users> result = mongoDBRepository.findAll();
        for(Users users : result) {
            System.out.print(users.getName() + ", ");
        }
    }

//    @Test
//    public void printPostsDB() {
//        System.out.println(postsMongoDBRepository.findByTitle("완득이 후기").getTitle());
//    }

    @Test
    public void printExpectedViewChallengesDB() {
        List<Challenges> list = challengeService.getChallengeListsByStartDate();
        System.out.println(challengeService.getChallengeListsByView(list));
    }

    @Test
    public void printExpectedLatestChallengesDB() {
        List<Challenges> list = challengeService.getChallengeListsByStartDate();
        System.out.println(challengeService.getChallengeListsByDate(list));
    }

    @Test
    public void printLocalDate() {
        System.out.println(challengeService.getChallengeListsByStartDate());
    }
}
