package bootmaven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Repository;


/**
 * https://api.github.com/users/
 * @author PRO2884
 *
 */

@Controller
public class WelcomeController {
	/**
	 * THE MAIN CONTROLLER AND AJAX REQ CONTROLLER
	 */
	
	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) throws IOException { 
		return "welcome";
	}
 
	/**
	 * OUR CONTROLLER AS GITHUB API IMPLEMENTER
	 */
	
	@RequestMapping(value = "/ajaxJsonRequest", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<Repository> ajaxJsonRequest(HttpServletRequest req)
			throws IOException {
		String inputFromAjaxCall = req.getParameter("uName"); 
		GitHub github = GitHub.connectAnonymously(); 
	    GHUser gU = github.getUser(inputFromAjaxCall);
	    Map<String, GHRepository> reps=  gU.getRepositories();  
	    List<Repository> shops = new ArrayList<Repository>();
		Repository rep; 
		Map<String, Long> langMap = null ;
		String  langs[]=null;
		int idx=0;
		for (GHRepository repos : reps.values()) {
			rep= new Repository();
			rep.setName(repos.getName()); 
			langMap = repos.listLanguages();
			langs= new String[langMap.keySet().size()];
			idx=0;
			for (String ele : langMap.keySet()) { 
				langs[idx]=ele;
				idx++;
			}
			rep.setStaffName(langs);
			shops.add(rep);
			langMap.clear();
		} 
		//returning as JSON
		return shops;
	}

	 
}