package cu.assignment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cu.assignment.model.AudioItem;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DssAssignment1ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	String domain = "localhost"; // localhost - 155.248.235.48

//	@Test
	public void testAudioControllerGet(CountDownLatch latch) {
		try {
			long getRequestStartTime = System.currentTimeMillis();

			ResponseEntity<String> response = restTemplate
					.getForEntity("http://" + domain + ":8080/api/audio/artist/Anirudh/year", String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());

			long getRequestEndTime = System.currentTimeMillis();
			long totalDuration = getRequestEndTime - getRequestStartTime;
			System.out.println("Get Response Time ::: " + Long.toString(totalDuration));

		} catch (Exception e) {
			System.out.println("GET request failed: " + e.getMessage());

		}
	}

//	@Test
	public void testAudioControllerPost(CountDownLatch latch) {

		try {
			long postRequestStartTime = System.currentTimeMillis();

			AudioItem audioItem = new AudioItem(Long.toString(postRequestStartTime), "Vikram", "Pablo Sandhanam", "2",
					"2021", "2000000", "23000000", "Anirudh");

			HttpEntity<AudioItem> request = new HttpEntity<>(audioItem);
			ResponseEntity<String> response = restTemplate.postForEntity("http://" + domain + ":8080/api/audio/create",
					request, String.class);

			assertEquals(HttpStatus.OK, response.getStatusCode());

			long postRequestEndTime = System.currentTimeMillis();
			long totalDuration = postRequestEndTime - postRequestStartTime;
			System.out.println("Post Response Time ::: " + Long.toString(totalDuration));
		} catch (Exception e) {
			System.out.println("GET request failed: " + e.getMessage());

		}
	}

	@Test
	public void testConcurrentClients() throws InterruptedException {
		int totalClients = 150;
		int numGetRequests = 5;
		int numPostRequests = 1;

		long startTime = System.currentTimeMillis();

		ExecutorService executor = Executors.newFixedThreadPool(totalClients);
		CountDownLatch latch = new CountDownLatch(totalClients);

		for (int i = 0; i < totalClients; i++) {
			executor.submit(() -> {
				for (int j = 0; j < numGetRequests; j++) {

					testAudioControllerGet(latch);
				}
				for (int j = 0; j < numPostRequests; j++) {
					testAudioControllerPost(latch);
				}
				latch.countDown();
			});
		}

		latch.await();
		executor.shutdown();

		long endTime = System.currentTimeMillis();
		long totalDuration = endTime - startTime;
		System.out.println("totalDuration ::: " + totalDuration);
	}

}
