# Project 1 - Pokebowl Restaurant API
#### Created by: Byron Fedele & Caleb Beck

## SERVLET OUTLINE:
### { /member }
- [ ] **<u>(doGet)</u>:**
  - **[ADMIN] -** Obtain all Member's Information or a single member's information *(?username=USERNAME)* {username, fullName, dob, isAdmin}
  - **[MEMBER] -** Obtain current authMember's information {username, fullname, dob}
  - **[ANON] -** *CANNOT READ* ***MEMBERS*** *FROM DATABASE*
- [ ] **<u>(doPost)</u>:**
  - **[ADMIN] -** Creates a new member from info in the request body {username, password, fullName, dob, isAdmin}
  - **[MEMBER] -** *CANNOT CREATE A NEW MEMBER WHILE LOGGED IN*
  - **[ANON] -** Creates a new member from info in the request body {username, password, fullName, dob}
- [ ] **<u>(doPut)</u>:**
  - **[ADMIN] -** Updates a **member** *(?username=UPDATED_USERNAME)* including the current **authMember** {username, password, fullName, isAdmin}
  - **[MEMBER] -** Updates current **authMember** {username, password, fullName}
  - **[ANON] -** *CANNOT UPDATE* ***MEMBERS*** *IN DATABASE*
- [ ] **<u>(doDelete)</u>:**
  - **[ADMIN] -** Deletes any **member** from the Database *{username}*
  - **[MEMBER] -** Deletes current **authMember** from the Database {password}
  - **[ANON] -** *CANNOT DELETE* ***MEMBERS*** *FROM DATABASE*

### { /dish }
- [ ] **<u>(doGet)</u>:**
    - **[ADMIN] -**
    - **[MEMBER] -**
    - **[ANON] -**
- [ ] **<u>(doPost)</u>:**
    - **[ADMIN] -**
    - **[MEMBER] -**
    - **[ANON] -**
- [ ] **<u>(doPut)</u>:**
    - **[ADMIN] -**
    - **[MEMBER] -**
    - **[ANON] -**
- [ ] **<u>(doDelete)</u>:**
    - **[ADMIN] -**
    - **[MEMBER] -** 
    - **[ANON] -**
