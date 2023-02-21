package cu.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cu.assignment.model.AudioItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/audio")
@Tag(name="apiaudio")
public class AudioController extends HttpServlet {

	AudioItem auidoObject1 = new AudioItem("1", "Varisu", "Thee Thalapathy", "34", "2022", "3000000", "5000000",
			"Thaman");
	AudioItem auidoObject2 = new AudioItem("2", "Thunivu", "Gangster", "34", "2022", "3000000", "5000000", "Anirudh");
	AudioItem auidoObject3 = new AudioItem("3", "Tillu", "DJ Tillu", "34", "2022", "3000000", "5000000", "Tillu");

	ConcurrentHashMap<String, AudioItem> artistMap = new ConcurrentHashMap<String, AudioItem>();
	{
		artistMap.put("id_1", auidoObject1);
		artistMap.put("id_2", auidoObject2);
		artistMap.put("id_3", auidoObject3);
	}

	@GetMapping("/artists/{id}")
	@Operation(summary = "Get a artist by ID", description = "Returns artist by ID")
	public void findArtistsbyId(@PathVariable(required = true) String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		getArtistbyId(id, response);

	}

	@GetMapping("/all/artists")
	@Operation(summary = "Get all artists", description = "Returns all artists")
	public String findAllArtists(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArrayList<AudioItem> audioItemList = new ArrayList<>();
		for (AudioItem audioItem : artistMap.values()) {
			audioItemList.add(audioItem);
		}
		Gson gson = new Gson();
		String json = gson.toJson(audioItemList);
		return json;
	}

	@GetMapping("/artist/{name}")
	@Operation(summary = "Get artists by name", description = "Returns artists by name")
	public void findArtistsbyName(@PathVariable(required = true) String name, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		getArtistbyName(name, response);

	}

	@GetMapping("/artist/{name}/{property}")
	@Operation(summary = "Get artists by name and a property", description = "Returns artists by name and a property")
	public String findArtistsByNameByProperty(@PathVariable(required = true) String name,
			@PathVariable(required = true) String property, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		for (Entry<String, AudioItem> map : artistMap.entrySet()) {
			if (map.getValue() instanceof AudioItem) {
				AudioItem artistInfo = (AudioItem) map.getValue();
				if (artistInfo.getArtistName().equals(name)) {

					String key = map.getKey();
					if (artistMap.containsKey(key)) {
						return switch (property) {
						case "artistName" -> artistInfo.getArtistName().toString();

						case "trackTitle" -> artistInfo.getTrackTitle().toString();

						case "albumTitle" -> artistInfo.getAlbumTitle().toString();

						case "trackNumber" -> artistInfo.getTrackNumber().toString();

						case "year" -> artistInfo.getYear().toString();

						case "numberOfReviews" -> artistInfo.getNumberOfReviews().toString();

						case "noOfCopiesSold" -> artistInfo.getNoOfCopiesSold().toString();

						default -> "Unexpected value: " + property;

						};

					}
				}
			}
		}
		return property;
	}

	@GetMapping("/copies-sold")
	@Operation(summary = "Get number of copies sold", description = "Returns number of copies sold")
	public void numberofCopiesSold(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		long sum = 0;
		for (Map.Entry<String, AudioItem> map : artistMap.entrySet()) {

			sum += Long.parseLong(map.getValue().getNoOfCopiesSold());
		}
		out.println("GET RESPONSE ::: NUMBER OF COPIES SOLD : " + sum);

	}

	private void getArtistbyName(String name, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		for (Entry<String, AudioItem> map : artistMap.entrySet()) {
			if (map.getValue() instanceof AudioItem) {
				AudioItem artistInfo = (AudioItem) map.getValue();
				if (artistInfo.getArtistName().equals(name)) {

					String key = map.getKey();
					if (artistMap.containsKey(key)) {
						out.println(artistInfo);
					}
				}
			}
		}

	}

	@PostMapping("/create")
	@Operation(summary = "Creates an audio item", description = "Creates an audio item")
	public void createAudioItem(@RequestBody() AudioItem audioItem, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (audioItem != null) {
			artistMap.put("id_4", audioItem);
		}
		artistMap.put("id_4", audioItem);
		PrintWriter out = response.getWriter();
		out.println("CREATED ::: " + artistMap.get("id_4").toString());

	}

	private void getArtistbyId(String id, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		out.println("ID ::: " + id);
		if (artistMap.isEmpty()) {
			out.println("DB IS EMPTY UPDATING THE DB");
			init();
		}

		AudioItem audioItem = artistMap.get(id);

		if (audioItem != null) {
			out.println("GET RESPONSE ::: " + audioItem.toString());
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

}
