import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.Objects;
import java.util.stream.Stream;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTests {
    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void initEachTest() {
        localizationService = new LocalizationServiceImpl();
    }

    @MethodSource("source")
    @ParameterizedTest
    public void testMethodLocale(Country country, String expected) {
        //act
        String result = localizationService.locale(country);

        //assert
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(BRAZIL, "Welcome")
        );


    }
}
