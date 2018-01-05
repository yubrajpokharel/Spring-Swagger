package com.yubrajpokharel.controller;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

import com.yubrajpokharel.model.User;
import com.yubrajpokharel.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author yubrajpokharel
 */
@Api(value = "User Controller API's")
@Controller
public class HelloWorldController {

   @Autowired
   UserService userService;
   
   @RequestMapping(path={"/"},method=RequestMethod.GET)
   public String sayHello(Model model) {
      model.addAttribute("message","Hello Spring MVC!");
      DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
      LocalDate date=LocalDate.now();
      model.addAttribute("date", date.format(formatter));
      
      return "index";
   }

   @ApiOperation(value = "retrive users list")
   @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
   public @ResponseBody
   Map<Integer, User> getAllUser(){
      return userService.getAllUser();
   }

   @ApiOperation(value = "Get user by user Id")
   @ApiResponses(value = {
           @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain user"),
           @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
           @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
           @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
   })
   @RequestMapping(value = "user/{id}", produces="application/json", method = RequestMethod.GET)
   public @ResponseBody User getUserById(@PathVariable int id){
      return userService.getUserById(id);
   }

   @ApiOperation(value = "Create new user")
   @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
   public @ResponseBody Resource<User> addUser(@RequestBody  User user){
      userService.add(user);
      Resource<User> resource = new Resource<User>(user);
      ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUser());
      resource.add(linkTo.withRel("user/"+user.getId()));
      return resource;
   }

}
