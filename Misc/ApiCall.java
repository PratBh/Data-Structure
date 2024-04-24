package Misc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCall {

	public static void main(String[] args) {
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create("https://jsonplaceholder.typicode.com/users"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse resp = null;
		try {
			resp = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofInputStream());
					//send(req, HttpResponse.BodyHandlers.ofString());
//			BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) resp));
//			String inputLine;
//            StringBuilder response = new StringBuilder();
//			while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }

            //System.out.println(response.toString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String result = resp.toString();
		
		//System.out.println(resp.body());
		String[] test = resp.body().toString().split(",");
		for(String st:test)
			System.out.println(st);
	}

}
