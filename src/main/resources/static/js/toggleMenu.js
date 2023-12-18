const message = /*[(${'hideAuthPageText'})]*/ "default";
console.log(message);


const loginBar = document.querySelector(".login");
const signUpBar = document.querySelector(".signUp");
const logout = document.querySelector(".logout");
if(message){
    loginBar.parentNode.removeChild(loginBar);
    signUpBar.parentNode.removeChild(signUpBar);
    logout.style.display = "inline";
}




