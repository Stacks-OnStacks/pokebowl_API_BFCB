
function displayAuthMember(){
const element = document.getElementById("authMemberFullName");
let decodedCookie = decodeURIComponent(document.cookie);
let ca = decodedCookie.split(';');
let cs = String(ca[1]);
let name=cs.substring(11);
element.innerHTML = name;
}