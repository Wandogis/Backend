package wandogis.wandogi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wandogis.wandogi.domain.Users;
import wandogis.wandogi.repository.UsersMongoDBRepository;

import java.util.List;

@SpringBootTest()
public class SpringBootMongoDBTest {
    @Autowired
    private UsersMongoDBRepository mongoDBRepository;

    @Test
    public void printMongoDB() {
        System.out.println(mongoDBRepository.findByName("이완득").getName());
        List<Users> result = mongoDBRepository.findAll();
        for(Users users : result) {
            System.out.print(users.getName() + ", ");
        }
    }
}
