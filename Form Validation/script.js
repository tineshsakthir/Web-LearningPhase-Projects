//Email validation own

const regexEmail = /^([a-zA-Z0-9\._]+)@([a-zA-Z0-9]+).([a-zA-Z]{2,6})$/;
const regexage = /^\d{2,3}$/;
const regexPhoneNumber = /^\d{10}$/;

const mainForm = document.querySelector("#mainForm");

mainForm.addEventListener("submit", (evt) => {
  evt.preventDefault();
  validate();
});

let validate = () => {
  
  const phoneNo = document.querySelector("#phoneno");
  const age = document.querySelector("#age");
  const email = document.querySelector("#email");

  const phoneNoValidationText = document.querySelector("#phoneNoValidationText");
  const ageValidationText = document.querySelector("#ageValidationText");
  const emailValidationText = document.querySelector("#emailValidationText");

  const name = document.querySelector("#name");

  if (name.value.trim() == "" || age.value.trim() == "" || phoneNo.value.trim() == "" || email.value.trim() == "") {
    alert("Please Enter all the fields before continue.....")

  }
  phoneNoValidationText.style.display = "block";
  ageValidationText.style.display = "block";
  emailValidationText.style.display = "block";

  //  if(regexPhoneNumber.test(phoneNo.value)){
  //     phoneNoValidationText.textContent = ""
  //  }
  let regpho = regexPhoneNumber.test(phoneNo.value.trim());
  let regage = regexage.test(age.value.trim())
  let regeme = regexEmail.test(email.value.trim())

  { regpho ? (phoneNoValidationText.textContent = "Valid Phone Number !!!") : phoneNoValidationText.textContent = "Invalid Phone Number :(" }
  { regpho ? phoneNoValidationText.style.color = "green" : phoneNoValidationText.style.color = "red" }
  { regage ? ageValidationText.textContent = "Valid Age !!! " : ageValidationText.textContent = "Invalid Age :(" }
  { regage ? ageValidationText.style.color = "green" : ageValidationText.style.color = "red" }
  { regeme ? emailValidationText.textContent = "Valid Email Addres !!!" : emailValidationText.textContent = "Invalid Email Address :(" }
  { regeme ? emailValidationText.style.color = "green" : emailValidationText.style.color = "red" }

}