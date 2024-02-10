package com.example.demo.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Profile.Model.MainProfile;
import com.example.demo.Profile.Model.ProfileDetails;




// @CrossOrigin("http://127.0.0.1:3000")
@Controller
public class ProfileController {
    @Autowired
    public ProfileService profileService;

    // @GetMapping("/sendOtp")
    // public void sendOtp() throws IOException {
    //     String otp = "1234";
    //     profileService.sendOtp("7306655710", otp);
    // }













    // HTML ==========================================================================
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
    // @GetMapping("/encoded")
    // public String encodedPassword() {
    //     return profileService.encodePassword("Hello");
    // }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/profile-display")
    public String profiles() {
        return "profile-display";
    }

    @GetMapping("/registration")
    public String display() {
        return "registration";
    }


   @PostMapping("/register-profile")
    @ResponseBody
    public String saveProfile(@RequestParam("file") MultipartFile file, @ModelAttribute MainProfile mainProfile, @ModelAttribute ProfileDetails profileDetails) throws IOException {
        return profileService.registerProfile(file, mainProfile, profileDetails);
    }
    @PostMapping("/profile/edit")
    @ResponseBody
    public String editProfile(Authentication authentication, @RequestParam("file") MultipartFile file, @ModelAttribute MainProfile mainProfile, @ModelAttribute ProfileDetails profileDetails) throws IOException {
        return profileService.editProfile(authentication, file, mainProfile, profileDetails);
    }

    // REST API =====================================================================================

    // Profile Display ---------------------------------------------------------------
    
    @GetMapping("/profile/{profileId}")
    @ResponseBody
    public ArrayList<ProfileDetails> getProfileDetails(@PathVariable("profileId") long profileId) {
        return profileService.getProfileDetails(profileId);
    }
    
    @GetMapping("/profiles")
    @ResponseBody
    public Set<ProfileDetails> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/profile/likedList")
    @ResponseBody
    public List<ProfileDetails> getLikedList(Authentication authentication) {
        return profileService.getLikedList(authentication);
    }

    @GetMapping("/profile/likerList")
    @ResponseBody
    public List<ProfileDetails> getLikerList(Authentication authentication) {
        return profileService.getLikerList(authentication);
    }

    @GetMapping("/profile/connectedList")
    @ResponseBody
    public List<ProfileDetails> getConnectedList(Authentication authentication) {
        return profileService.getConnectedList(authentication);
    }

    // Filter -------------------------------------------------------------------
    @ResponseBody
    @PostMapping("/profile/filter")
    public Set<ProfileDetails> getFilterProfiles(
        @RequestParam("gender") String gender,
        @RequestParam("casteGeneral") String casteGeneral,
        @RequestParam("occupationPlace") String occupationPlace,
        @RequestParam("nativeState") String nativeState,
        @RequestParam("maritialStatus") String maritialStatus
        ) {
            return profileService.getFilterProfiles(gender, casteGeneral, occupationPlace, nativeState, maritialStatus);
        }


    



    

    @ResponseBody
    @GetMapping("/profile/connect/{likedId}")
    public String addLiked(Authentication authentication, @PathVariable("likedId") long likedId) throws Exception {
        return profileService.addLiked(authentication, likedId);
    }
    @ResponseBody
    @GetMapping("/profile/cancel/{cancelId}")
    public String cancelProfile(Authentication authentication, @PathVariable("cancelId") long cancelId) throws Exception {
        return profileService.cancelProfile(authentication, cancelId);
    }
    @ResponseBody
    @GetMapping("/profile/accept/{requestId}")
    public String acceptProfile(Authentication authentication, @PathVariable("requestId") long requestId) throws Exception {
        return profileService.acceptProfile(authentication, requestId);
    }
    @ResponseBody
    @GetMapping("/profile/contact/{contactId}")
    public MainProfile contactProfile(Authentication authentication, @PathVariable("contactId") long contactId) throws Exception {
        return profileService.contactProfile(authentication, contactId);
    }
    @ResponseBody
    @GetMapping("/profile/myProfile")
    public ProfileDetails myProfile(Authentication authentication) {
        return profileService.myProfile(authentication);
    }
    @ResponseBody
    @GetMapping("/profile/delete/{profileId}")
    public String deleteProfile(Authentication authentication, @PathVariable("profileId") long profileId) throws Exception{
        return profileService.deleteProfile(authentication, profileId);
    }





    //  Admin Controller -----------------------

    @GetMapping("/admin")
    public String getAdminPage(Authentication authentication) {
        if (authentication.getName().equals("0000000000")) return "admin";
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @GetMapping("/admin/recharge/{profileId}")
    public String rechargeProfile(Authentication authentication, @PathVariable("profileId") long profileId) {
        System.out.println("hello");
        return profileService.rechargeProfile(authentication, profileId);
    }

    @ResponseBody
    @GetMapping("/admin/clear/{profileId}")
    public String clearProfile(Authentication authentication, @PathVariable("profileId") long profileId) {
        return profileService.clearProfile(authentication, profileId);
    }
    @ResponseBody
    @GetMapping("/admin/contact/{profileId}")
    public MainProfile contactDetailsProfile(Authentication authentication, @PathVariable("profileId") long profileId) {
        return profileService.contactDetailsProfile(authentication, profileId);
    }
    @ResponseBody
    @GetMapping("/admin/totalProfiles")
    public String totalProfiles(Authentication authentication) {
        return profileService.totalProfiles(authentication);
    }
    @ResponseBody
    @GetMapping("/admin/varuduProfiles")
    public String varuduProfiles(Authentication authentication) {
        return profileService.varuduProfiles(authentication);
    }
    @ResponseBody
    @GetMapping("/admin/vadhuvuProfiles")
    public String vadhuvuProfiles(Authentication authentication) {
        return profileService.vadhuvuProfiles(authentication);
    }
    @ResponseBody
    @GetMapping("/admin/likedList/{profileId}")
    public List<ProfileDetails> getLikedListByAdmin(Authentication authentication, @PathVariable("profileId") long profileId) {
        return profileService.getLikedListByAdmin(authentication, profileId);
    }
    @ResponseBody
    @GetMapping("/admin/likerList/{profileId}")
    public List<ProfileDetails> getLikerListByAdmin(Authentication authentication, @PathVariable("profileId") long profileId) {
        return profileService.getLikerListByAdmin(authentication, profileId);
    }
    @ResponseBody
    @GetMapping("/admin/connectedList/{profileId}")
    public List<ProfileDetails> getConnectedListByAdmin(Authentication authentication, @PathVariable("profileId") long profileId) {
        return profileService.getConnectedListByAdmin(authentication, profileId);
    }



    // @ResponseBody
    // @GetMapping("/clear")
    // public String clear() {
    //     return profileService.clear();
    // }

   
   
}
