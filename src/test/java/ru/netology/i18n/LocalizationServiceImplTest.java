package ru.netology.i18n;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

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
        System.out.println("Test finished");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests finished");
    }
    @Test
    void testLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.RUSSIA);

        String expected = "Добро пожаловать";

        assertEquals(expected, message);
    }
}