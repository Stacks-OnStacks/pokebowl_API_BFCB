# Project 1 - Pokebowl Restaurant API
#### Created by: Byron Fedele & Caleb Beck

## SERVLET OUTLINE (FRONT-END):
### { /member }
- [ ] **<u>(doGet)</u>:**
  - **[ADMIN] -** Obtain all **members'** information or a single **member's** information *(?username=USERNAME)* {username, fullName, dob, isAdmin}
  - **[MEMBER] -** Obtain current **authMember's** information {username, fullname, dob}
  - **[ANON] -** *CANNOT READ* ***MEMBERS*** *FROM DATABASE*
- [ ] **<u>(doPost)</u>:**
  - **[ADMIN] -** Creates a new **member** from info in the request body {username, password, fullName, dob, isAdmin}
  - **[MEMBER] -** *CANNOT CREATE A NEW ***MEMBER*** WHILE LOGGED IN*
  - **[ANON] -** Creates a new **member** from info in the request body and logs in {username, password, fullName, dob}
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
    - **[ADMIN] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
    - **[ANON] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
- [ ] **<u>(doPost)</u>:**
    - **[ADMIN] -** Creates a new **dish** from info in the request body {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** *CANNOT CREATE* ***DISHES*** *IN DATABASE*
    - **[ANON] -** *CANNOT CREATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doPut)</u>:**
    - **[ADMIN] -** Updates a **dish** *(?dishName=UPDATED_DISH_NAME)* using info in the request body {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** *CANNOT UPDATE* ***DISHES*** *IN DATABASE*
    - **[ANON] -** *CANNOT UPDATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doDelete)</u>:**
    - **[ADMIN] -** Deletes any **dish** from the Database *{dishName}*
    - **[MEMBER] -** *CANNOT DELETE* ***DISHES*** *FROM DATABASE*
    - **[ANON] -** *CANNOT DELETE* ***DISHES*** *FROM DATABASE*

### { /payment }
- [ ] **<u>(doGet)</u>:**
    - **[ADMIN] -** Obtains all current **authMember's payments'** information or a single **payment's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
    - **[ANON] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName, dishCost, description, isVegetarian}
- [ ] **<u>(doPost)</u>:**
    - **[ADMIN] -** Creates a new **dish** from info in the request body {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** *CANNOT CREATE* ***DISHES*** *IN DATABASE*
    - **[ANON] -** *CANNOT CREATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doPut)</u>:**
    - **[ADMIN] -** Updates a **dish** *(?dishName=UPDATED_DISH_NAME)* using info in the request body {dishName, dishCost, description, isVegetarian}
    - **[MEMBER] -** *CANNOT UPDATE* ***DISHES*** *IN DATABASE*
    - **[ANON] -** *CANNOT UPDATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doDelete)</u>:**
    - **[ADMIN] -** Deletes any **dish** from the Database *{dishName}*
    - **[MEMBER] -** *CANNOT DELETE* ***DISHES*** *FROM DATABASE*
    - **[ANON] -** *CANNOT DELETE* ***DISHES*** *FROM DATABASE*
