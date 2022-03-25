package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MessageSenderImplTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests started");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("\nTest finished");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests finished");
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testSend(Country country, String expected) {

        HashMap <String, String> map = new HashMap<String, String>();
        GeoServiceImpl geoServiceImplMock = Mockito.mock(GeoServiceImpl.class);
        LocalizationServiceImpl localizationServiceImplMock = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoServiceImplMock.byIp(Mockito.<String>any())).
                thenReturn(new Location(null, country, null, 0));
        Mockito.when(localizationServiceImplMock.locale(Mockito.<Country>any())).thenReturn(expected);

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceImplMock, localizationServiceImplMock);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "ip");
        String message = messageSenderImpl.send(map);

        assertEquals(expected, message);
    }

    public static Stream<Arguments> source(){
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"), Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"));
    }

}