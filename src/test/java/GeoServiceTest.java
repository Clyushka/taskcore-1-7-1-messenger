import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceTest {
    GeoService sut;

    @BeforeEach
    public void init() {
        sut = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("ipAndLocationFactory")
    //Проверить работу метода public Location byIp(String ip)
    public void byIpTest(String ip, Location expected) {
        assertEquals(expected,
                sut.byIp(ip));
    }

    public static Stream<Arguments> ipAndLocationFactory() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST,
                        new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP,
                        new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP,
                        new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.0.0",
                        new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("172.34.55.6",
                        new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.0.0.0",
                        new Location("New York", Country.USA, null,  0)),
                Arguments.of("96.123.45.98",
                        new Location("New York", Country.USA, null,  0)),
                Arguments.of("0.0.0.0",
                        null)
        );
    }

    @Test
    public void byCoordinatesTest() {
        assertThrows(
                RuntimeException.class,
                () -> sut.byCoordinates(0,0));
    }

    //    public Location byCoordinates(double latitude, double longitude) {
    //        throw new RuntimeException("Not implemented");
    //    }
}
