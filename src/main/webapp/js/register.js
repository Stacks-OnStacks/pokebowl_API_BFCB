function register() {
    var username = String(document.getElementById('username').value);
    var fullname = document.getElementById('fullname').value;
    var password = document.getElementById('password').value;
    var dob = document.getElementById('dob').value;
    const newMember = {username:username, fullName:fullname, password:password, dob: dob};
    var json = JSON.stringify(newMember);
    alert("Sending this information to ServletContext via POST HTTP Request which will route it to servlet with address member, there it will be processed by MemberServlet's doPost Method");
    var request = new XMLHttpRequest();
    request.open("POST", "http://44.201.202.225:8080/member");
    request.send(json);
    event.preventDefault();
}