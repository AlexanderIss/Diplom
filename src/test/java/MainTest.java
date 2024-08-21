import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;


public class MainTest {

    @Test
    void test() {
        open("http://localhost:8080/");
    }
}
