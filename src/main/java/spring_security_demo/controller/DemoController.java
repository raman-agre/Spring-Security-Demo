package spring_security_demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @GetMapping("/public")
    public String publicApi(){
        return "Public endpoint - no login required";
    }

    @GetMapping("/admin")
    public String adminApi(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String userApi(Authentication authentication){
        return "Hello User: " + authentication.getName(); //user
    }

    @GetMapping("/dev")
    public String devApi(Authentication authentication){
        return "Hello Dev: " + authentication.getName();
    }

    @PreAuthorize("hasRole('DEV')")
    @GetMapping("/authentication")
    public String userApi2(Authentication authentication){
        return "No auth required";
        /*
        {
  "authenticated": true,
  "authorities": [
    {
      "authority": "FACTOR_PASSWORD",
      "issuedAt": "2025-12-25T16:53:57.280437300Z"
    }
  ],
  "credentials": null,
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": "08C27C31FCA516F8112EC5A72EC5B9DA"
  },
  "name": "user",
  "principal": {
    "accountNonExpired": true,
    "accountNonLocked": true,
    "authorities": [],
    "credentialsNonExpired": true,
    "enabled": true,
    "password": null,
    "username": "user"
  }
}
//         */
    }

    @GetMapping("/profile/read")
    public String readProfile(){
        return "Profile read!";
    }

    @PostMapping("/profile/write")
    public String writeProfile(){
        return "Profile updated!";
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(){
        return "Profile deleted!";
    }
//
//    @GetMapping("/principal")
//    public Principal userApi3(Authentication authentication){
//        return authentication;
//        /*
//        {
//  "authenticated": true,
//  "authorities": [
//    {
//      "authority": "FACTOR_PASSWORD",
//      "issuedAt": "2025-12-25T16:53:57.280437300Z"
//    }
//  ],
//  "credentials": null,
//  "details": {
//    "remoteAddress": "0:0:0:0:0:0:0:1",
//    "sessionId": "08C27C31FCA516F8112EC5A72EC5B9DA"
//  },
//  "name": "user",
//  "principal": {
//    "accountNonExpired": true,
//    "accountNonLocked": true,
//    "authorities": [],
//    "credentialsNonExpired": true,
//    "enabled": true,
//    "password": null,
//    "username": "user"
//  }
//}
//         */
//    }
//
//    @GetMapping("/credentials")
//    public Object userApi4(Authentication authentication){
//        return authentication.getCredentials();//blank
//    }
//
//    @GetMapping("/details")
//    public Object userApi5(Authentication authentication){
//        return authentication.getDetails();
//        /*
//        {
//  "remoteAddress": "0:0:0:0:0:0:0:1",
//  "sessionId": "08C27C31FCA516F8112EC5A72EC5B9DA"
//}
//         */
//    }
//
//    @GetMapping("/getprincipal")
//    public Object userApi6(Authentication authentication){
//        return authentication.getPrincipal();
//        /*
//        {
//  "accountNonExpired": true,
//  "accountNonLocked": true,
//  "authorities": [],
//  "credentialsNonExpired": true,
//  "enabled": true,
//  "password": null,
//  "username": "user"
//}
//         */
//    }

}
