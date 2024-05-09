package com.telusko.contest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;
import java.net.MalformedURLException;

import com.telusko.contest.entity.Url;
import com.telusko.contest.service.UrlService;

@RestController
@CrossOrigin(origins = "*")
public class UrlController {

	@Autowired
	UrlService service;
	
	@GetMapping("/getAllUrl")
	public List<Url> getAllUrl(){
		return service.getAllUrl();
	}

	@PostMapping("/originalUrl")
	public List<Url> getOriginalUrl(@RequestBody Map<String , String> requestBody){
		String shortUrl = requestBody.get("shortUrl");
		List<Url> urlsToReturn = new ArrayList<>();
		Url longUrl = service.getOriginalUrl(shortUrl);
		if(longUrl!=null)
		{
			urlsToReturn.add(longUrl);
		}
		return urlsToReturn;
	}

	@PostMapping("/stats")
	public List<Url> getStats(@RequestBody Map<String , String> requestBody){
		String shortUrl = requestBody.get("shortUrlForStats");
		List<Url> urlsToReturn = new ArrayList<>();
		Url longUrl = service.getOriginalUrl(shortUrl);
		if(longUrl!=null)
		{
			urlsToReturn.add(longUrl);
		}
		return urlsToReturn;
	}

	@PostMapping("/createUrl")
	public String createUrl(@RequestBody Map<String, String> requestBody) {
	    String inUrl = requestBody.get("inUrl");
		boolean validUrl = isValid(inUrl);
		if(!validUrl)
		{
			return "invalid url String please provide correct url";
		}
	    String temp = service.filterUrl(inUrl);
		List<Url> urls = service.getAllUrl();
        for (Url currentUrl : urls) {
            if (currentUrl.getLong_url().equalsIgnoreCase(inUrl)) {
                String shortUrlCreated = currentUrl.getShort_url();
                return "Url already present you can use\t\t" + "'" +shortUrlCreated+"'";
            }
        }
	    return service.createUrl(temp, inUrl);
	}


	public static boolean isValid(String urlString) {
		try {
			// Attempt to create a URL object from the given string
			URL url = new URL(urlString);
			// If no exception is thrown, the URL is valid
			return true;
		} catch (MalformedURLException e) {
			// MalformedURLException is thrown if the URL is not valid
			return false;
		}
	}
}
