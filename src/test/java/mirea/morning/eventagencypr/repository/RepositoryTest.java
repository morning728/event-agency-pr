package mirea.morning.eventagencypr.repository;

import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.Order1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class RepositoryTest {

    @Autowired
    private repositoryPostgres  underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindAllBy() {
        //given
        Order1 o1 = new Order1();
        o1.setCreationDate("123");
        underTest.saveAndFlush(o1);
//        Order1 o2 = new Order1();
//        o1.setCreationDate("321");
//        underTest.saveAndFlush(o2);


        //when
        List<Order1> expectedOne = underTest.findAllByCreationDate("123");


        //List<Order1> expectedTwo = underTest.findAllByCreationDate("321");


        //then
        assertThat(expectedOne).isNotNull();
        assertThat(expectedOne.size()).isEqualTo(1);
        assertThat(expectedOne.get(0).getCreationDate()).isEqualTo("123");

//        assertThat(expectedTwo).isNotNull();
//        assertThat(expectedTwo.size()).isEqualTo(1);
//        assertThat(expectedTwo.get(0).getCreationDate()).isEqualTo("321");
    }

    @Test
    void itShouldNotFindAllBy() {
        //given
        Order1 o1 = new Order1();
        o1.setCreationDate("123");
        underTest.saveAndFlush(o1);

        //when
        List<Order1> expectedOne = underTest.findAllByCreationDate("124");

        //then

        assertThat(expectedOne).isNotNull();
        assertThat(expectedOne.size()).isEqualTo(0);
    }
}
