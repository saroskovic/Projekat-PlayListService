package projekat.playList.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projekat.playList.entities.PlayListVideo;
import projekat.playList.entities.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayListVideoRepositoryTest {


    @Autowired
    private PlayListVideoRepository testing;
/*
    @Test
    void itShouldFindAllVideosByPlayListIdForGivenUser() {
        //given
        User user = new User(1, "Zoran", 2);
        PlayListVideo video1 = new PlayListVideo(1,
                                                2,
                                                3,
                                                1);
        PlayListVideo video2 = new PlayListVideo(2,
                                                1,
                                                2,
                                                1);
        PlayListVideo video3 = new PlayListVideo(3,
                                                2,
                                                4,
                                                2);
        testing.save(video1);
        testing.save(video2);
        testing.save(video3);

        //when
        testing.findAllByPlayListId(user.getId());

        //then

    }
*/
    @Test
    void itShouldFindAllVideosByPlayListIdOrderByOrderNoAscForGivenUser() {

    }
}