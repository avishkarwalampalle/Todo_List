function openLoginForm() {
   document.getElementById("login").style.display = "block";
   document.getElementById("forgot-password").style.display = "none";
}

function closeLoginForm() {
   document.getElementById("login").style.display = "none";
}

function openForgetPasswordForm(){
   document.getElementById("login").style.display = "none";
   document.getElementById("forgot-password").style.display = "block";
}

function closeForgetPasswordForm(){
   document.getElementById("forgot-password").style.display = "none";
   document.getElementById("login").style.display = "block";
}

function openSignupForm(){
   document.getElementById("login").style.display = "none";
   document.getElementById("register").style.display = "block";
}

function closeSignupForm(){
   document.getElementById("register").style.display = "none";
   document.getElementById("login").style.display = "block";
}

