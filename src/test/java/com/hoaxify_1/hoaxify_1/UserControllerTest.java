package com.hoaxify_1.hoaxify_1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.hoaxify_1.hoaxify_1.shared.GenericResponce;
import com.hoaxify_1.hoaxify_1.user.User;
import com.hoaxify_1.hoaxify_1.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {
	private static final String API_1_0_USER = "/api/1.0/user";
	@Autowired
	TestRestTemplate testRestTemplate;// it is used for web services testing
	@Autowired
	UserRepository userRepository;

	@Before
	public void cleanUp() {
		userRepository.deleteAll();
	}

	@Test
	public void postUser_whenUserIsValid_recieveOk() {
		User user = createVaidUser();

		ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USER, user, Object.class);
		try {
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	private User createVaidUser() {// extracted into method
		User user = new User();
		user.setUserName("test-user");
		user.setDisplayName("test-display");
		user.setPassword("Password");
		return user;
	}

	@Test
	public void postUser_whenUserIsValid_userStoredtoDatabase() {
		User user = createVaidUser();
		testRestTemplate.postForEntity(API_1_0_USER, user, Object.class);
		try {
			assertThat(userRepository.count()).isEqualTo(1);
		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	@Test
	public void postUser_whenUserIsValid_recieveSuccessMessage() {
		User user = createVaidUser();

		ResponseEntity<GenericResponce> response = testRestTemplate.postForEntity(API_1_0_USER, user,
				GenericResponce.class);
		try {
			assertThat(response.getBody().getMessage()).isNotNull();
		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}
}
