const allProfilesUrl = "http://localhost:8080/profiles";
const showCase = document.getElementById("showCase");
const filterBtn = document.getElementById("filterBtn");

function registerPage() {
    window.location.href="/registration";
}

function getRequest(url) {
    fetch(url)
    .then(response => response.json())
    .then(profiles => {
        let profile = "";
        profiles.forEach(data => {
            profile += `
            <div class="profile-container row mt-3 mb-5 p-2">
                <div class="col-lg-7 mr-3 text-center">
                    <img src="data:image;base64,${data.image}" class="profile-image">
                </div>

                <div class="details col-lg-4">
                    <span>Profile No: <b>${data.profileId}</b></span><br>
                    <p>${data.firstName} ${data.lastName}</p>


                    <div class="d-flex">
                        <div>
                            <span><i class="fa-solid fa-cake-candles"></i></span>${data.birthDate}-${data.birthMonth}-${data.birthYear}<span>
                        </div>
                        <div class="mr-1 ml-1">
                            <li>${data.feet}'${data.inch}"</li>
                        </div>
                        <div>
                            <li>${data.rasiNakshitram}</span></li> 
                        </div>
                    </div>
                    <span><i class="fa-solid fa-graduation-cap"></i></span> ${data.education}<br/>
                    <span><i class="fa-solid fa-briefcase"></i></span> ${data.occupation} ${data.occupationRole}<br/>
                    <span><i class="fa-solid fa-location-dot"></i></span> Place of Job :- ${data.cityName} ${data.occupationState} ${data.otherCountryCityName} ${data.countryName}<br/>
                    <span><i class="fa-solid fa-sack-dollar"></i></span> ${data.salary} LPA<br/>
                    <span><i class="fa-solid fa-people-roof"></i> </span>Brother :-${data.numberOfBrothers},  Sisters :-${data.numberOfSisters}<br/>
                    <span><i class="fa-solid fa-user"></i></span>${data.maritialStatus}<br/>
                    <span><i class="fa-solid fa-house"></i></i></span> ${data.apDistrict} ${data.nativeState}<br/>
                    <li>Caste :- ${data.casteGeneral}- ${data.caste} ${data.subCaste}</li>
                    <div class="text-center mt-3">
                        <button id="profile${data.profileId}" onclick="registerPage()" class="btn btn-success">Connect</button>
                        <p id="error${data.profileId}" class="text-danger"></p>
                    </div>
                </div>
        
            </div>`;
            });
            showCase.innerHTML=profile;
        })
    .catch(err=> {
        showCase.innerHTML= "Error: Something went Wrong!";
    }); 
}
getRequest(allProfilesUrl);

filterBtn.addEventListener("click", (event) =>{
    filter.classList.toggle("d-none");
    event.target.classList.toggle("btn-primary");
    event.target.classList.toggle("btn-danger");
})