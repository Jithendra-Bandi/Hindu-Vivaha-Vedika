const form = document.getElementById("form");
const male = document.getElementById("maleLabel");
const female = document.getElementById("femaleLabel")
const maleRadio = document.getElementById("male");
const femaleRadio = document.getElementById("female");
const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const fatherName = document.getElementById("fatherName");
const motherName = document.getElementById("motherName");
const date = document.getElementById("date");
const month = document.getElementById("month");
const year = document.getElementById("year");
const rasiNakshitram = document.getElementById("rasiNakshitram");
const feet = document.getElementById("feet");
const inch = document.getElementById("inch");
const casteGeneral= document.getElementById("casteGeneral");
const caste= document.getElementById("caste");
const education= document.getElementById("education");
const occupation= document.getElementById("occupation");
// const occupationRole = document.getElementById("occupationRole");
const india = document.getElementById("india");
const otherCountry = document.getElementById("otherCountry");
const occupationState = document.getElementById("occupationState");
const cityName = document.getElementById("cityName");
const countryName = document.getElementById("countryName");
const otherCountryCityName = document.getElementById("otherCountryCityName");
const salary = document.getElementById("salary");
const propertyValue = document.getElementById("propertyValue");
const brothers = document.getElementById("brothers");
const doorNo = document.getElementById("doorNo");
const streetName = document.getElementById("streetName");
const village = document.getElementById("village");
const nativeState = document.getElementById("nativeState");
const apChoose = document.getElementById("apChoose");
const nativeLocation= document.getElementById("nativeLocation");
const mobileNumber= document.getElementById("mobileNumber");
const alternateMobileNumber= document.getElementById("alternateMobileNumber");
const emailId= document.getElementById("emailId");
const image= document.getElementById("image");
const maritialStatus = document.getElementById("maritialStatus");
const noOfChildren = document.getElementById("noOfChildren");
const checkPassword = document.getElementById("checkPassword");
const password = document.getElementById("password");


// Dynamic Purpose created variables -----------------------------------------------------------------
const indiaDisplay = document.getElementById("indiaDisplay");
const otherCountryDisplay = document.getElementById("otherCountryDisplay");
const brothersList = document.getElementById("brothersList");
const sistersList = document.getElementById("sistersList");
const apDistrict = document.getElementById("apDistrict");
const noOfChildrenContainer = document.getElementById("noOfChildrenContainer");
const validationError = document.getElementById("validationErrorMessage");
const indiaLabel = document.getElementById("indiaLabel");
const otherCountryLabel = document.getElementById("otherCountryLabel");

// -=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=



function getLoginForm() {
    window.location.href = "http://localhost:8080/profile-display";
}


// Error Display Validation ============================
function errorDisplay(event) {
    if (event.target.value === "") event.target.classList.add("error-display");
    else event.target.classList.remove("error-display");
}



// Required Fields ----------------------------------------------------------
maleRadio.addEventListener("change", () => {
    male.style.color="white";
    female.style.color="white";
})
femaleRadio.addEventListener("change", () => {
    male.style.color="white";
    female.style.color="white";
})

firstName.addEventListener("change", errorDisplay);
lastName.addEventListener("change", errorDisplay);
date.addEventListener("change", errorDisplay);
month.addEventListener("change", errorDisplay);
year.addEventListener("change", errorDisplay);
rasiNakshitram.addEventListener("change", errorDisplay);
feet.addEventListener("change", errorDisplay);
inch.addEventListener("change", errorDisplay);
casteGeneral.addEventListener("change", errorDisplay);
caste.addEventListener("change", errorDisplay);
education.addEventListener("change", errorDisplay);
occupation.addEventListener("change", errorDisplay);
occupationState.addEventListener("change", errorDisplay);
cityName.addEventListener("change", errorDisplay);
countryName.addEventListener("change", errorDisplay);
otherCountryCityName.addEventListener("change", errorDisplay);
salary.addEventListener("change", errorDisplay);
brothers.addEventListener("change", errorDisplay);
sisters.addEventListener("change", errorDisplay);
doorNo.addEventListener("change", errorDisplay);
streetName.addEventListener("change", errorDisplay);
village.addEventListener("change", errorDisplay);
nativeState.addEventListener("change", errorDisplay);
mobileNumber.addEventListener("change", errorDisplay);
image.addEventListener("change", errorDisplay);
maritialStatus.addEventListener("change", errorDisplay);
// Password
checkPassword.addEventListener("change", errorDisplay);
password.addEventListener("change", event => {
    if (event.target.value === "") password.classList.add("error-display");
    else if (event.target.value !== checkPassword.value) {
        password.classList.add("error-display");
        validationError.textContent="Make Sure Password and Confirm Password are same!";
    }
    else if (event.target.value === checkPassword.value && event.target.value !== "") {
        password.classList.remove("error-display");
        validationError.textContent="";
    }
});



// Native State -------------------------------
nativeState.addEventListener("change", function(event){
    if (event.target.value === "Andhra Pradesh") {
        apChoose.classList.remove("d-none");
        apChoose.addEventListener("change", errorDisplay);
    }
    else {
        apChoose.classList.add("d-none");
        apDistrict.value="";
    } 
});

// Year ---------------------------------------------------------
for (let i = 1970; i <= 2005; i++) {
    let option = document.createElement("option");
    option.value = i;
    option.textContent = i;
    year.appendChild(option);
}


// Country ---------------------------------

india.addEventListener("change", function() {
    indiaDisplay.classList.remove("d-none");
    otherCountryDisplay.classList.add("d-none");
    countryName.value="";
    otherCountryCityName.value="";
    
});
otherCountry.addEventListener("change", function() {
    indiaDisplay.classList.add("d-none");
    otherCountryDisplay.classList.remove("d-none");
    occupationState.value="";
    cityName.value="";
});


// Siblings --------------------------noOfBrothers
function addSibling(id) {
    let marriedId = "married"+id;
    let unmarriedId = "unmarried"+id;
    let youngerId = "younger"+id;
    let elderId = "elder"+id;
    let maritialStatusSibling = "maritialStatus"+id;
    let youngerOrElder = "youngerOrElder"+id;

    let siblingItem = document.createElement("li");

    let siblingContainer = document.createElement("div");
    siblingItem.appendChild(siblingContainer);

    let marriedRadio = document.createElement("input");
    marriedRadio.id=marriedId;
    marriedRadio.value = "Married";
    marriedRadio.type="radio";
    marriedRadio.name=maritialStatusSibling;
    siblingContainer.appendChild(marriedRadio);
    let marriedLabel = document.createElement("label");
    marriedLabel.textContent="Married";
    marriedLabel.setAttribute("for", marriedId);
    siblingContainer.appendChild(marriedLabel);

    let lineBreak1 = document.createElement("br");
    siblingContainer.appendChild(lineBreak1);

    let unmarriedRadio = document.createElement("input");
    unmarriedRadio.id=unmarriedId;
    unmarriedRadio.type="radio";
    unmarriedRadio.value = "Unmarried";
    unmarriedRadio.name=maritialStatusSibling;
    siblingContainer.appendChild(unmarriedRadio);
    let unmarriedLabel = document.createElement("label");
    unmarriedLabel.textContent="Unmarried";
    unmarriedLabel.setAttribute("for", unmarriedId);
    siblingContainer.appendChild(unmarriedLabel);

    let siblingBreak = document.createElement("br");
    siblingContainer.appendChild(siblingBreak);

    let youngerRadio = document.createElement("input");
    youngerRadio.id=youngerId;
    youngerRadio.value="Younger";
    youngerRadio.type="radio";
    youngerRadio.name=youngerOrElder;
    siblingContainer.appendChild(youngerRadio);
    let youngerLabel = document.createElement("label");
    youngerLabel.textContent="Younger";
    youngerLabel.setAttribute("for", youngerId);
    siblingContainer.appendChild(youngerLabel);

    let lineBreak2 = document.createElement("br");
    siblingContainer.appendChild(lineBreak2);

    let elderRadio = document.createElement("input");
    elderRadio.id=elderId;
    elderRadio.value = "Elder";
    elderRadio.type="radio";
    elderRadio.name=youngerOrElder;
    siblingContainer.appendChild(elderRadio);
    let elderLabel = document.createElement("label");
    elderLabel.textContent="Elder";
    elderLabel.setAttribute("for", elderId);
    siblingContainer.appendChild(elderLabel);
    
    return siblingItem;
}

brothers.addEventListener("change", function(event){
    brothersList.textContent="";
    let brotherCount = (parseInt(event.target.value));
    for (let i=1; i<=brotherCount; i++) {
        let brotherItem = addSibling("Brother"+i);
        brothersList.appendChild(brotherItem);
        let lineBreak3 = document.createElement("br");
        brothersList.appendChild(lineBreak3);
    }
});

sisters.addEventListener("change", function(event){
    sistersList.textContent="";
    let sisterCount = (parseInt(event.target.value));
    for (let i=1; i<=sisterCount; i++) {
        let sisterItem = addSibling("Sister"+i);
        sistersList.appendChild(sisterItem);
        let lineBreak4 = document.createElement("br");
        sistersList.appendChild(lineBreak4);
    }
});

// No of Children---------------------------------
maritialStatus.addEventListener("change", function(event) {
    if (event.target.value === "Second Marraige") {
        noOfChildrenContainer.classList.remove("d-none");
        noOfChildren.addEventListener("change", errorDisplay);
    }
    else {
        noOfChildrenContainer.classList.add("d-none");
        noOfChildren.value="";
    }
});


// Validation -----------------------------------
function validate() {
    const formData = new FormData(form);
    const obj = Object.fromEntries(formData);
    console.log(obj);

    if (obj.gender === undefined) {
        male.style.color="red";
        female.style.color="red";
        validationError.textContent="Select Varudu or Vadhuvu";
        return false;
    }
    if (obj.firstName === "") {
        firstName.classList.add("error-display");
        validationError.textContent="Enter First Name";
        return false;
    }
    if (obj.lastName === "") {
        lastName.classList.add("error-display");
        validationError.textContent="Enter Last Name";
        return false;
    }
    if (obj.birthDate === "") {
        date.classList.add("error-display");
        validationError.textContent="Select Birth Date";
        return false;
    }
    if (obj.birthMonth === "") {
        month.classList.add("error-display");
        validationError.textContent="Select Birth Month";
        return false;
    }
    if (obj.birthYear === "") {
        year.classList.add("error-display");
        validationError.textContent="Select Birth Year";
        return false;
    }
    if (obj.feet === "") {
        feet.classList.add("error-display");
        validationError.textContent="Select Height(Feet)";
        return false;
    }
    if (obj.inch === "") {
        inch.classList.add("error-display");
        validationError.textContent="Select Height(Inch)";
        return false;
    }
    if (obj.casteGeneral === "") {
        casteGeneral.classList.add("error-display");
        validationError.textContent="Select Caste Type";
        return false;
    }
    if (obj.caste === "") {
        caste.classList.add("error-display");
        validationError.textContent="Enter Caste Name";
        return false;
    }
    if (obj.education === "") {
        education.classList.add("error-display");
        validationError.textContent="Enter Education";
        return false;
    }
    if (obj.occupation === "") {
        occupation.classList.add("error-display");
        validationError.textContent="Select Occupation Type";
        return false;
    }
    if (obj.occupationPlace ==="India") {
        if (obj.occupationState==="") {
            occupationState.classList.add("error-display");
            validationError.textContent="Select Occupation State";
            return false;
        }
        if (obj.cityName==="") {
            cityName.classList.add("error-display");
            validationError.textContent="Enter City Name";
            return false;
        }
    }if (obj.occupationPlace === "Other Country") {
        if (obj.countryName === "") {
            countryName.classList.add("error-display");
            validationError.textContent="Enter Country Name";
            return false;
        }
        if (obj.otherCountryCityName === "") {
            otherCountryCityName.classList.add("error-display");
            validationError.textContent="Enter Occupation State/City Name in Other Country";
            return false;
        }
    }
    if (obj.salary === "") {
        salary.classList.add("error-display");
        validationError.textContent="Select Salary Range in LPA";
        return false;
    }
    if (obj.numberOfBrothers === "") {
        brothers.classList.add("error-display");
        validationError.textContent="Select Number of Brothers";
        return false;
    }
    if (obj.numberOfSisters === "") {
        sisters.classList.add("error-display");
        validationError.textContent="Select Number of Sisters";
        return false;
    }
    if (obj.doorNo === "") {
        doorNo.classList.add("error-display");
        validationError.textContent="Enter Door Number";
        return false;
    }
    if (obj.streetName === "") {
        streetName.classList.add("error-display");
        validationError.textContent="Enter Street Name";
        return false;
    }
    if (obj.village === "") {
        village.classList.add("error-display");
        validationError.textContent="Enter Village/Town Name";
        return false;
    }
    if (obj.nativeState === "") {
        nativeState.classList.add("error-display");
        validationError.textContent="Select Your Native State";
        return false;
    }
    if (obj.nativeState === "Andhra Pradesh"){
        if (obj.apDistrict === "") {
            apDistrict.classList.add("error-display");
            validationError.textContent="Select District";
            return false;
        }
    }
    if (obj.mobileNumber === "") {
        mobileNumber.classList.add("error-display");
        validationError.textContent="Enter Mobile Number";
        return false;
    }
    if (!(/^\d+$/.test(obj.mobileNumber)) || obj.mobileNumber.length!== 10) {
        mobileNumber.classList.add("error-display");
        validationError.textContent = "Enter Valid Number";
        return false;
    }
    if (obj.alternateMobileNumber !== "") {
        if (!(/^\d+$/.test(obj.alternateMobileNumber)) || obj.alternateMobileNumber.length !== 10) {
            alternateMobileNumber.classList.add("error-display");
            validationError.textContent = "Enter Valid Alternate Number";
            return false;
        }else {
            alternateMobileNumber.classList.remove("error-display");
            validationError.textContent = "";
        }
    }
    if (obj.file.size === 0) {
        image.classList.add("error-display");
        validationError.textContent="Please Upload a Valid Picture";
        return false;
    }
    if (obj.file.size/1024 > 1024) {
        image.classList.add("error-display");
        validationError.textContent="File is too large. Please upload below 1 MB";
        return false;
    }
    if (obj.maritialStatus === "") {
        maritialStatus.classList.add("error-display");
        validationError.textContent="Select Maritial Status";
        return false;
    }
    if (obj.maritialStatus==="Second Marraige") {
        if (obj.numberOfChildren === "") {
            noOfChildren.classList.add("error-display");
            validationError.textContent="Select Number Of Children";
            return false;
        }
    }
    if (obj.password === "") {
        password.classList.add("error-display");
        validationError.textContent="Please provide Password";
        return false;
    }
    if (obj.password !== checkPassword.value) {
        password.classList.add("error-display");
        validationError.textContent="Make sure password and confirm password are same!";
        return false;
    }
    return true;
}

// Posting Fetch Method -------------------------------------------
function saveData() {
    const formData = new FormData(form);
    // console.log(Object.fromEntries(formData));
    const options = {
        method:"POST",
        body:formData
    };
    // console.log("Fetch Starting...")
    fetch("http://localhost:8080/register-profile", options)
    .then(res => res.text())
    .then(data => {
        if (data === "Registration Completed") {
            validationError.textContent = "Registration Completed";
            setTimeout(() => {
                window.location.replace("/login");
            },2000);
        }
        else if (data === "This Mobile Number already Registered") validationError.textContent=data;
    })
    .catch(err => validationError.textContent = "Something Went Wrong");
}


// Form ---------------------------------------------------------
form.addEventListener("submit", function(event){
    event.preventDefault();
    let isValid = validate();
    if (!isValid) {
        return;
    }
    validationError.textContent="Checking";
    saveData();
});
