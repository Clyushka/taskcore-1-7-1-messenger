import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderTest {
    MessageSender sut;

    @ParameterizedTest
    @MethodSource("messageSenderFactory")
    public void sendTest(String ipAddress, String expectedMSG) {
        GeoServiceImpl gsi = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gsi.byIp(ArgumentMatchers.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(gsi.byIp(ArgumentMatchers.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationServiceImpl lsi = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(lsi.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(lsi.locale(Country.USA))
                .thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        sut = new MessageSenderImpl(gsi, lsi);
        String resultMSG = sut.send(headers);

        assertEquals(resultMSG, expectedMSG);
    }

    public static Stream<Arguments> messageSenderFactory() {
        return Stream.of(
                Arguments.of(
                        GeoServiceImpl.MOSCOW_IP,
                        "Добро пожаловать"
                ),
                Arguments.of(
                        "172.55.55.55",
                        "Добро пожаловать"
                ),
                Arguments.of(
                        GeoServiceImpl.NEW_YORK_IP,
                        "Welcome"
                ),
                Arguments.of(
                        "96.77.77.77",
                        "Welcome")
        );
    }
}
