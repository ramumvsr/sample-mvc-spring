package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hibernatefiles.ResponseData;
import com.hibernatefiles.User;
import com.userdataserviceimpl.UserDataServiceImpl;

	
		@Controller
		public class ControllerWeb {

			@Autowired
			private UserDataServiceImpl userDataServiceImpl;

			@RequestMapping(value = { "/", "/login" })
			public ModelAndView login(){
					
				ModelAndView model = new ModelAndView();
				
			
				model.setViewName("mvclogin");

				return model;
			}
			
			
			
			@PostMapping(path = "/addUserData", consumes = "application/json", produces = "application/json")
			public ResponseData addUserData(@RequestBody User userData) {
				
 System.out.println(userData.getuId());
				ResponseData responseData = userDataServiceImpl
						.addUserData(userData);

				return responseData;

			}

			
			
			
			
			
			
			
			
		@RequestMapping(value={"/second"})
			public ModelAndView nextPage(){
					
				ModelAndView model = new ModelAndView();
				
			
				model.setViewName("Form");

				return model;
			}
		/*	@RequestMapping(value={"/third "})
			public ModelAndView thirdPage(){
					
				ModelAndView model = new ModelAndView();
				
			
				model.setViewName("ThirdWelcom");

				return model;
			}*/
			
			
			
	}



