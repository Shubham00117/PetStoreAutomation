package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.playload.User;
import io.restassured.response.Response;

public class UserTests2 {

    private static final Logger logger =
            LogManager.getLogger(UserTests2.class);

    Faker faker;
    User userpayload;

    // ================= SETUP =================
    @BeforeClass
    public void setUp() {

        faker = new Faker();
        userpayload = new User();

        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstname(faker.name().firstName());
        userpayload.setLastname(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassowrd(faker.internet().password());
        userpayload.setPhone(faker.phoneNumber().cellPhone());

        logger.info("Test data initialized");
    }

    // ================= POST =================
    @Test(priority = 1)
    public void testPostUser() {

        logger.info("POST user test started");

		Response response = UserEndPoints2.createUser(userpayload);

        logger.info("POST status code: {}", response.getStatusCode());
        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }

    // ================= GET =================
    @Test(priority = 2, dependsOnMethods = "testPostUser")
    public void testGetUserByName() {

        logger.info("GET user test started");

        Response response =
                UserEndPoints2.getUser(userpayload.getUsername());

        logger.info("GET status code: {}", response.getStatusCode());
        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }

    // ================= PUT =================
    @Test(priority = 3, dependsOnMethods = "testGetUserByName")
    public void testPutUser() {

        logger.info("PUT user test started");

        userpayload.setFirstname(faker.name().firstName());
        userpayload.setLastname(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());

        Response response =
                UserEndPoints2.updateUser(userpayload, userpayload.getUsername());

        logger.info("PUT status code: {}", response.getStatusCode());
        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }

    // ================= DELETE =================
    @Test(priority = 4, dependsOnMethods = "testPutUser")
    public void testDeleteUser() {

        logger.info("DELETE user test started");

        Response response =
                UserEndPoints2.deleteUser(userpayload.getUsername());

        logger.info("DELETE status code: {}", response.getStatusCode());
        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }
}