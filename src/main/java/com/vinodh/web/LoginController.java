package com.vinodh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vinodh.entity.ApplicationUser;
import com.vinodh.util.custom.annotations.CurrentUser;

@Controller
public class LoginController {

	private static final String CUSTOM_LOGIN_SUCCESS = "customUser";

	@GetMapping(value = { "/", "/home" })
	public String homePage(ModelMap map) {
		map.addAttribute("greeting", "Hello welcome boss!!");
		return "welcome";
	}

	@GetMapping(value = "/admin")
	public String adminPage(ModelMap map) {
		map.addAttribute("user", getPrincipal());
		return "adminView";
	}

	@GetMapping(value = "/db")
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@GetMapping(value = "/Access_Denied")
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@GetMapping(value = "/login")
	public String loginPage() {
		return "loginView";
	}

	@PostMapping(value = "/loginSuccess")
	public String loginPageRedirect() {
		return "redirect:/" + CUSTOM_LOGIN_SUCCESS;
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	/*	@GetMapping(value = "/customUser_Secure")
	public String loggedInUserSecure(ModelMap model) {
		model.addAttribute("user", getUser());
		return CUSTOM_LOGIN_SUCCESS;
	}

	@GetMapping(value = "/customUser")
	public ModelAndView loggedInUser(@AuthenticationPrincipal ApplicationUser user) {
		ModelAndView modelAndView = new ModelAndView(CUSTOM_LOGIN_SUCCESS);
		modelAndView.addObject("user", getUser());
		return modelAndView;
	}

	@GetMapping(value = "/customUser_CurrentUser")
	public ModelAndView loggedInCurrentUser(@CurrentUser ApplicationUser currentUser) {
		ModelAndView modelAndView = new ModelAndView(CUSTOM_LOGIN_SUCCESS);
		currentUser.setFirstName("Vinodh Kumar");
		currentUser.setLastName("Thimmisetty");
		modelAndView.addObject(currentUser);
		return modelAndView;
	}

	private ApplicationUser getUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		UserDetails principal = (UserDetails) authentication.getPrincipal();
		ApplicationUser user = new ApplicationUser();
		user.setUserEmail(principal.getUsername());
		user.setFirstName("Vinodh Kumar Thimmisetty");
		return user;
	}
*/
	private String getPrincipal() {
		String userName = null;
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
