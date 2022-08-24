function login() {
    var username = String(document.getElementById('username').value);
    var password = String(document.getElementById('password').value);
    const newMember = {username:username, password:password };
    var json = JSON.stringify(newMember);
    alert("Sending this information via POST HTTP Request to be received by ServletContext to be routed to servlet with the address auth, which will be proccessed by AuthServlet's doPost Method");
    var request = new XMLHttpRequest();
    request.open("POST", "/auth");
    request.send(json);
    event.preventDefault();
    let x = document.cookie;
    setTimeout(() => {  window.location.assign("index.html") }, 5000);
}
