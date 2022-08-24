
function displayAuthMember(){
const element = document.getElementById("authMemberFullName");
let decodedCookie = decodeURIComponent(document.cookie[1]);
element.innerHTML = decodedCookie;
}