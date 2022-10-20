import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.lang.reflect.Executable;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.startsWith;

public class GeoServiceImplTests {

    GeoServiceImpl geoService;

    @BeforeEach
    public void initEachTest() {
        geoService = new GeoServiceImpl();
    }


    @MethodSource("source")
    @ParameterizedTest
    public void testMethodByIp(String ip, Location expected) {
        //act
        Location result = geoService.byIp(ip);

        //assert
        Objects.equals(expected, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of(startsWith("172."), new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of(startsWith("96."), new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    public void testMethodByCoordinates() {
        //assert
        double latitude = 55.45, longitude = 37.37;

        //assert
        assertThrowsExactly(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
    }


}
