package com.example.demo.Profile.Model;

// import java.util.ArrayList;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name="profile_details")
public class ProfileDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="details_id")
    private long detailsId;

    @Column(name="profile_id")
    private long profileId;
    
    @Column(name="gender")
    private String gender;

    @Column(name="ap_district")
    private String apDistrict;

    @Column(name="caste")
    private String caste;

    @Column(name="caste_general")
    private String casteGeneral ;

    @Column(name="city_name")
    private String cityName ;

    @Column(name="country_name")
    private String countryName;

    @Column(name="other_country_city_name")
    private String otherCountryCityName;

    @Column(name="birth_date")
    private String birthDate;

    @Column(name="education")
    private String education ;

    @Column(name="father_name")
    private String fatherName;

    @Column(name="feet")
    private String feet;

    @Column(name="first_name")
    private String firstName ;

    @Column(name="inch")
    private String inch ;

    @Column(name="last_name")
    private String lastName ;

    @Column(name="maritial_status")
    private String maritialStatus ;

    @Column(name="maritial_status_brother_1")
    private String maritialStatusBrother1 ;

    @Column(name="maritial_status_brother_2")
    private String maritialStatusBrother2 ;

    @Column(name="maritial_status_brother_3")
    private String maritialStatusBrother3 ;

    @Column(name="Maritial_status_brother_4")
    private String maritialStatusBrother4 ;

    @Column(name="maritial_status_sister_1")
    private String maritialStatusSister1 ;

    @Column(name="maritial_status_sister_2")
    private String  maritialStatusSister2;

    @Column(name="maritial_status_sister_3")
    private String maritialStatusSister3 ;

    @Column(name="maritial_status_sister_4")
    private String  maritialStatusSister4;

    @Column(name="birth_month")
    private String birthMonth ;

    @Column(name="mother_name")
    private String  motherName;

    @Column(name="native_location")
    private String nativeLocation ;

    @Column(name="native_state")
    private String nativeState ;

    @Column(name="number_of_brothers")
    private String  numberOfBrothers;

    @Column(name="number_of_children")
    private String  numberOfChildren;

    @Column(name="number_of_sisters")
    private String numberOfSisters ;

    @Column(name="occupation")
    private String  occupation;

    @Column(name="occupation_place")
    private String occupationPlace ;

    @Column(name="occupation_state")
    private String occupationState ;

    @Column(name="occupation_role")
    private String  occupationRole;

    @Column(name="property_value")
    private String propertyValue ;

    @Column(name="rasi_nakshitram")
    private String  rasiNakshitram;

    @Column(name="salary")
    private String  salary;

    @Column(name="sub_caste")
    private String  subCaste;

    @Column(name="birth_year")
    private String  birthYear;

    @Column(name="younger_or_elder_brother_1")
    private String youngerOrElderBrother1 ;

    @Column(name="younger_or_elder_brother_2")
    private String youngerOrElderBrother2 ;

    @Column(name="younger_or_elder_brother_3")
    private String youngerOrElderBrother3 ;

    @Column(name="younger_or_elder_brother_4")
    private String  youngerOrElderBrother4;

    @Column(name="younger_or_elder_sister_1")
    private String youngerOrElderSister1 ;

    @Column(name="younger_or_elder_sister_2")
    private String youngerOrElderSister2 ;

    @Column(name="younger_or_elder_sister_3")
    private String youngerOrElderSister3 ;

    @Column(name="younger_or_elder_sister_4")
    private String youngerOrElderSister4 ;   

    @Column(name="tokens")
    private int tokens;

    @Column(name="liking")
    private HashSet<Long> liking;
    
    @Column(name="liked")
    private HashSet<Long> liked;

    @Lob
    @Column(name="image")
    private byte[] image;

    @Column(name="connected")
    private HashSet<Long> connected;

    
    public ProfileDetails(){

    }
    


    public long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(long detailsId) {
        this.detailsId = detailsId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getApDistrict() {
        return apDistrict;
    }

    public void setApDistrict(String apDistrict) {
        this.apDistrict = apDistrict;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getCasteGeneral() {
        return casteGeneral;
    }

    public void setCasteGeneral(String casteGeneral) {
        this.casteGeneral = casteGeneral;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFeet() {
        return feet;
    }

    public void setFeet(String feet) {
        this.feet = feet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInch() {
        return inch;
    }

    public void setInch(String inch) {
        this.inch = inch;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public String getMaritialStatusBrother1() {
        return maritialStatusBrother1;
    }

    public void setMaritialStatusBrother1(String maritialStatusBrother1) {
        this.maritialStatusBrother1 = maritialStatusBrother1;
    }

    public String getMaritialStatusBrother2() {
        return maritialStatusBrother2;
    }

    public void setMaritialStatusBrother2(String maritialStatusBrother2) {
        this.maritialStatusBrother2 = maritialStatusBrother2;
    }

    public String getMaritialStatusBrother3() {
        return maritialStatusBrother3;
    }

    public void setMaritialStatusBrother3(String maritialStatusBrother3) {
        this.maritialStatusBrother3 = maritialStatusBrother3;
    }

    public String getMaritialStatusBrother4() {
        return maritialStatusBrother4;
    }

    public void setMaritialStatusBrother4(String maritialStatusBrother4) {
        this.maritialStatusBrother4 = maritialStatusBrother4;
    }

    public String getMaritialStatusSister1() {
        return maritialStatusSister1;
    }

    public void setMaritialStatusSister1(String maritialStatusSister1) {
        this.maritialStatusSister1 = maritialStatusSister1;
    }

    public String getMaritialStatusSister2() {
        return maritialStatusSister2;
    }

    public void setMaritialStatusSister2(String maritialStatusSister2) {
        this.maritialStatusSister2 = maritialStatusSister2;
    }

    public String getMaritialStatusSister3() {
        return maritialStatusSister3;
    }

    public void setMaritialStatusSister3(String maritialStatusSister3) {
        this.maritialStatusSister3 = maritialStatusSister3;
    }

    public String getMaritialStatusSister4() {
        return maritialStatusSister4;
    }

    public void setMaritialStatusSister4(String maritialStatusSister4) {
        this.maritialStatusSister4 = maritialStatusSister4;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getNativeLocation() {
        return nativeLocation;
    }

    public void setNativeLocation(String nativeLocation) {
        this.nativeLocation = nativeLocation;
    }

    public String getNativeState() {
        return nativeState;
    }

    public void setNativeState(String nativeState) {
        this.nativeState = nativeState;
    }

    public String getNumberOfBrothers() {
        return numberOfBrothers;
    }

    public void setNumberOfBrothers(String numberOfBrothers) {
        this.numberOfBrothers = numberOfBrothers;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getNumberOfSisters() {
        return numberOfSisters;
    }

    public void setNumberOfSisters(String numberOfSisters) {
        this.numberOfSisters = numberOfSisters;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationPlace() {
        return occupationPlace;
    }

    public void setOccupationPlace(String occupationPlace) {
        this.occupationPlace = occupationPlace;
    }

    public String getOccupationState() {
        return occupationState;
    }

    public void setOccupationState(String occupationState) {
        this.occupationState = occupationState;
    }

    public String getOccupationRole() {
        return occupationRole;
    }

    public void setOccupationRole(String occupationRole) {
        this.occupationRole = occupationRole;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getRasiNakshitram() {
        return rasiNakshitram;
    }

    public void setRasiNakshitram(String rasiNakshitram) {
        this.rasiNakshitram = rasiNakshitram;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getYoungerOrElderBrother1() {
        return youngerOrElderBrother1;
    }

    public void setYoungerOrElderBrother1(String youngerOrElderBrother1) {
        this.youngerOrElderBrother1 = youngerOrElderBrother1;
    }

    public String getYoungerOrElderBrother2() {
        return youngerOrElderBrother2;
    }

    public void setYoungerOrElderBrother2(String youngerOrElderBrother2) {
        this.youngerOrElderBrother2 = youngerOrElderBrother2;
    }

    public String getYoungerOrElderBrother3() {
        return youngerOrElderBrother3;
    }

    public void setYoungerOrElderBrother3(String youngerOrElderBrother3) {
        this.youngerOrElderBrother3 = youngerOrElderBrother3;
    }

    public String getYoungerOrElderBrother4() {
        return youngerOrElderBrother4;
    }

    public void setYoungerOrElderBrother4(String youngerOrElderBrother4) {
        this.youngerOrElderBrother4 = youngerOrElderBrother4;
    }

    public String getYoungerOrElderSister1() {
        return youngerOrElderSister1;
    }

    public void setYoungerOrElderSister1(String youngerOrElderSister1) {
        this.youngerOrElderSister1 = youngerOrElderSister1;
    }

    public String getYoungerOrElderSister2() {
        return youngerOrElderSister2;
    }

    public void setYoungerOrElderSister2(String youngerOrElderSister2) {
        this.youngerOrElderSister2 = youngerOrElderSister2;
    }

    public String getYoungerOrElderSister3() {
        return youngerOrElderSister3;
    }

    public void setYoungerOrElderSister3(String youngerOrElderSister3) {
        this.youngerOrElderSister3 = youngerOrElderSister3;
    }

    public String getYoungerOrElderSister4() {
        return youngerOrElderSister4;
    }

    public void setYoungerOrElderSister4(String youngerOrElderSister4) {
        this.youngerOrElderSister4 = youngerOrElderSister4;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public HashSet<Long> getLiking() {
        return liking;
    }

    public void setLiking(HashSet<Long> liking) {
        this.liking = liking;
    }

    public HashSet<Long> getLiked() {
        return liked;
    }

    public void setLiked(HashSet<Long> liked) {
        this.liked = liked;
    }



    public long getProfileId() {
        return profileId;
    }



    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }



    public String getOtherCountryCityName() {
        return otherCountryCityName;
    }



    public void setOtherCountryCityName(String otherCountryCityName) {
        this.otherCountryCityName = otherCountryCityName;
    }



    public HashSet<Long> getConnected() {
        return connected;
    }



    public void setConnected(HashSet<Long> connected) {
        this.connected = connected;
    }




    
}


