import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceTest {
    LocalizationService localSRV;

    @BeforeEach
    public void init() {
        localSRV = new LocalizationServiceImpl();
    }

    @Test
    public void localeTest() {
        Country rus = Country.RUSSIA;
        Country us = Country.USA;

        String expectedRus = "Добро пожаловать";
        String expectedUS = "Welcome";

        assertEquals(expectedUS, localSRV.locale(us));
        assertEquals(expectedRus, localSRV.locale(rus));
    }
}
