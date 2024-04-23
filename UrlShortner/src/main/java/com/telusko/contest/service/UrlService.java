package com.telusko.contest.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.contest.entity.Url;
import com.telusko.contest.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	UrlRepository url;

	public List<Url> getAllUrl() {
		List<Url> urls = url.findAll();
		return urls;
	}

	public String filterUrl(String oUrl) {
		// TODO Auto-generated method stub
		StringBuilder res = new StringBuilder();
		for (char ele : oUrl.toCharArray()) {
			if (((int) ele > (int) 'a' && (int) ele < (int) 'z') || ((int) ele > (int) 'A' && (int) ele < (int) 'Z'))
				res.append(ele);
		}
		return res.toString();
	}

	public Url getOriginalUrl(String shortUrl)
	{
		List<Url> allUrl = getAllUrl();
		for(int i=0;allUrl!=null && i<allUrl.size();i++)
		{
			Url currentUrl = allUrl.get(i);
			if(currentUrl.getShort_url().equals(shortUrl))
			{
				int accessCount = currentUrl.getAccess_count()+1;
				String shorty = currentUrl.getShort_url();
				String longUrl = currentUrl.getLong_url();
				url.deleteById(currentUrl.getId());
				url.save(new Url(shorty, longUrl, accessCount, currentUrl.getId()));
				//currentUrl.setAccess_count(accessCount);
				//url.save(currentUrl);
				return currentUrl;
			}
		}
		return null;
	}

	public String createUrl(String filterUrl, String originalUrl) {
		// TODO Auto-generated method stub
		List<Url> allUrl = getAllUrl(); // Get All UrlShortner
		Set<String> uniqueSet = new HashSet();
		allUrl.forEach(u -> uniqueSet.add(u.getLong_url())); // Create Set of Unique Url
		String res = "ushrt/";
		StringBuilder sb = new StringBuilder();
		while (true) {
			for (int i = 0; i < 6; i++) {
				sb.append(filterUrl.charAt(new Random().nextInt(filterUrl.length())));
			}
			res = res + sb;
			if (!uniqueSet.contains(sb)) { //if generated url is unique break the loop then only
				break;
			}else {
				res = "ushrt/";
			}
		}
		url.save(new Url(res, originalUrl));
		return res;
	}
}
