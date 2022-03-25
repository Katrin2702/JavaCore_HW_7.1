package ru.netology.geo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

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
    void testByIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country expected = Country.RUSSIA;

        Country country = geoService.byIp("172.2.36.488").getCountry();

        assertEquals(expected, country);
    }
}