package service;

import java.io.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import vue.Ajout;
import vue.Home;
import vue.WarningAdd;

import javax.swing.DefaultListModel;

public class Requests {
	
	public Requests() {
		
	}
	
	/**
	 * Get all tasks
	 * @throws IOException
	 */
	public DefaultListModel getTasks() throws IOException {
		URL url = new URL("");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setInstanceFollowRedirects(false);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		DefaultListModel <String> content = new DefaultListModel<>();
		while ((inputLine = in.readLine()) != null) {
			content.addElement(inputLine);
		}
		in.close();
		System.out.println(content);
		return content;
	}
	
	
	/**
	 * Get task by name
	 * @throws IOException
	 */
	public DefaultListModel<String> getTaskByName(String name) throws IOException {
		URL url = new URL("");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setInstanceFollowRedirects(false);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		DefaultListModel <String> content = new DefaultListModel<>();
		while ((inputLine = in.readLine()) != null) {
			content.addElement(inputLine);
		}
		in.close();
		
		System.out.println(content);
		return content;
	}
	
	
	/**
	 * Get task by state
	 * @throws IOException
	 * @throws ParseException 
	 */
	public DefaultListModel<String> getTaskByState(int state) throws IOException, ParseException {
		
		/*String stringURL = String.format("https://java-schedule-moc.herokuapp.com/tasks/status/%d", state);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(stringURL)).build();
		client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println).join(); */
		
		/*String stringURL = String.format("https://java-schedule-moc.herokuapp.com/tasks/status/%d", state);
		URL url = new URL(stringURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setInstanceFollowRedirects(false);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		DefaultListModel <String> content = new DefaultListModel<>();
		while ((inputLine = in.readLine()) != null) {
			exampleArray.put(inputLine);
			content.addElement(inputLine);
		}
		in.close();*/
		
		
		DefaultListModel <String> content = new DefaultListModel<>();
		if(state == 1) {
			content.addElement("App Android");
			content.addElement("App iOS");
		}
		else if(state == 2) {
			content.addElement("App Java");
		}
		else if(state == 3) {
			content.addElement("App Flutter");
		}
		
		//System.out.println(content.get(0));
		return content;
	}

	
	/**
	 * Add task to db
	 * @param name
	 * @param desc
	 * @param date
	 * @param state
	 * @throws IOException
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 */
	public void addTask(String name, String desc, String date, String state, String contributor) throws IOException, URISyntaxException, InterruptedException {
		int idState = 0;
		if(state == "A faire") {idState = 1;}
		else if(state == "En cours") {idState = 2;}
		else if(state == "Terminé") {idState = 3;}
		
		String jsonInputString = String.format("{ \"name\":\"%s\", \"description\":\"%s\", \"dateEnded\":\"%S\", \"person\":\"%s\", \"idStatus\":%d}", name, desc, date, contributor, idState);
        String url = "https://java-schedule-moc.herokuapp.com/tasks";
        
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();
     
        var client = HttpClient.newHttpClient();
        
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
 
        System.out.println(response.statusCode());
        /*if(response.statusCode() == 500) {
        	Ajout frm1 = new Ajout();
			WarningAdd frm2 = new WarningAdd("Remplissez les champs obligatoires");
			frm2.setVisible(true);
			frm1.setVisible(false);
        }*/
        //System.out.println(response.body());
	}
	 
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void updateTask(String name, String desc, String date, String state, String contributor, String _id) throws IOException, InterruptedException {
		int idState = 0;
		if(state == "A faire") {idState = 1;}
		else if(state == "En cours") {idState = 2;}
		else if(state == "Terminé") {idState = 3;}
	
		
		String data = String.format("{ \"name\":\"%s\", \"description\":\"%s\", \"dateEnded\":\"%S\", \"person\":\"%s\", \"_id\":\"%S\", \"idStatus\":%d}", name, desc, date, contributor, _id, idState);
		System.out.println(data);
		String url = "https://java-schedule-moc.herokuapp.com/tasks/";
        
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(data))
                .build();
     
        var client = HttpClient.newHttpClient();
        
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
 
        System.out.println(response.statusCode());
	}
	 
	public void deleteTask(String id) throws IOException, InterruptedException {
		String stringURL = String.format("https://java-schedule-moc.herokuapp.com/tasks/%s", id);
		String url = stringURL;
		
		var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json").DELETE().build();
     
        var client = HttpClient.newHttpClient();
        
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
 
        System.out.println(response.statusCode());
	}
}
