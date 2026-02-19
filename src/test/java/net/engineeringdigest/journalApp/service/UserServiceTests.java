package net.engineeringdigest.journalApp.service;

<<<<<<< HEAD
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
=======
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
>>>>>>> 1af1c12 (Mockito Spring Boot Sample test)
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;

=======
>>>>>>> 1af1c12 (Mockito Spring Boot Sample test)
import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTests {

<<<<<<< HEAD
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @BeforeEach
    public void setUp() {
        JournalEntry entry = new JournalEntry();
        entry.setTitle("Test Entry");
        entry.setContent("Test Content");
        journalEntryRepository.save(entry);

        User testUser = User.builder()
                .userName("ram")
                .password("testpassword")
                .roles(Arrays.asList("USER"))
                .journalEntries(new ArrayList<>())
                .build();
        testUser.getJournalEntries().add(entry);
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        User user = userRepository.findByUserName("ram");
        if (user != null && user.getJournalEntries() != null) {
            for (JournalEntry entry : user.getJournalEntries()) {
                if (entry != null && entry.getId() != null) {
                    journalEntryRepository.deleteById(entry.getId());
                }
            }
        }
        userRepository.deleteByUserName("ram");
    }

=======
>>>>>>> 1af1c12 (Mockito Spring Boot Sample test)
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
    }

<<<<<<< HEAD
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("ram");
        assertNotNull(user);
=======
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("ram"));
>>>>>>> 1af1c12 (Mockito Spring Boot Sample test)
    }

    @Test
    public void testFindByUsernameUserEntry(){
        User user = userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
