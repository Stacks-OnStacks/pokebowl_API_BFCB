# Project 1 - Pokebowl Restaurant API
#### Created by: Byron Fedele & Caleb Beck

## SERVLET OUTLINE (FRONT-END):
### { /member }
- [ ] **<u>(doGet)</u>:**
  - **[ADMIN] -** Obtain all **members'** information or a single **member's** information *(?username=USERNAME)* {username,fullName,dob,isAdmin}
  - **[MEMBER] -** Obtain current **authMember's** information {username,fullname,dob}
  - **[ANON] -** *CANNOT READ* ***MEMBERS*** *FROM DATABASE*
- [ ] **<u>(doPost)</u>:**
  - **[ADMIN] -** Creates a new **member** from info in the request body {username,password,fullName,dob,isAdmin}
  - **[MEMBER] -** *CANNOT CREATE A NEW ***MEMBER*** WHILE LOGGED IN*
  - **[ANON] -** Creates a new **member** from info in the request body and logs in {username,password,fullName,dob}
- [ ] **<u>(doPut)</u>:**
  - **[ADMIN] -** Updates a **member** *(?username=USERNAME)* including the current **authMember** {username,password,fullName,isAdmin}
  - **[MEMBER] -** Updates current **authMember** {username,password,fullName}
  - **[ANON] -** *CANNOT UPDATE* ***MEMBERS*** *IN DATABASE*
- [ ] **<u>(doDelete)</u>:**
  - **[ADMIN] -** Deletes any **member** from the Database *{username}*
  - **[MEMBER] -** Deletes current **authMember** from the Database {password}
  - **[ANON] -** *CANNOT DELETE* ***MEMBERS*** *FROM DATABASE*

### { /dish }
- [ ] **<u>(doGet)</u>:**
    - **[ALL USERS] -** Obtains all **dishes'** information or a single **dish's** information *(?dishName=DISH_NAME)* {dishName,dishCost,description,isVegetarian}
- [ ] **<u>(doPost)</u>:**
    - **[ADMIN] -** Creates a new **dish** from info in the request body {dishName,dishCost,description,isVegetarian}
    - **[NOT ADMIN] -** *CANNOT CREATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doPut)</u>:**
    - **[ADMIN] -** Updates a **dish** *(?dishName=DISH_NAME)* using info in the request body {dishName,dishCost,description,isVegetarian}
    - **[NOT ADMIN] -** *CANNOT UPDATE* ***DISHES*** *IN DATABASE*
- [ ] **<u>(doDelete)</u>:**
    - **[ADMIN] -** Deletes any **dish** from the Database *{dishName}*
    - **[NOT ADMIN] -** *CANNOT DELETE* ***DISHES*** *FROM DATABASE*

### { /payment } WORK IN PROGRESS
- [ ] **<u>(doGet)</u>:**
    - **[ALL MEMBERS] -** Obtains all current **authMember's payments'** information or a single **payment's** information *(?paymentName=PAYMENT_NAME)* {paymentName,balance,expDate,ccv,zipCode,provider}
    - **[ANON] -** *CANNOT READ ANY* ***PAYMENTS***
- [ ] **<u>(doPost)</u>:**
    - **[ALL MEMBERS] -** Creates a new **payment** from info in the request body for current **authMember** {paymentName,balance,expDate,ccv,zipCode,provider}
    - **[ANON] -** *CANNOT CREATE ANY* ***PAYMENTS***
- [ ] **<u>(doPut)</u>:**
    - **[ALL MEMBERS] -** Updates the current **authMember's payment** *(?paymentName=PAYMENT_NAME)* using info in the request body {paymentName,balance,expDate,ccv,zipCode,provider}
    - **[ANON] -** *CANNOT UPDATE ANY* ***PAYMENTS***
- [ ] **<u>(doDelete)</u>:**
    - **[ALL MEMBERS] -** Deletes a **payment** from the Database for current **authMember** *{paymentName,password}*
    - **[ANON] -** *CANNOT DELETE ANY* ***PAYMENTS***

### { /order } WORK IN PROGRESS
- [ ] **<u>(doGet)</u>:**
  - **[ALL MEMBERS] -** Obtains all current **authMember's orders'** information, a single **order's** information *(?orderId=ORDER_ID)*, or the **currentOrder's** information *(?orderId=current)* {amount,orderDate,orderAddress,orderZip,paymentId}
  - **[ANON] -** *CANNOT READ ANY* ***ORDERS***
- [ ] **<u>(doPost)</u>:**
  - **[ALL MEMBERS] -** Creates an empty order and **currentOrder** cookie or posts the **currentOrder** to the database *(if valid)* {amount,orderDate,orderAddress,orderZip,paymentId}
  - **[ANON] -** *CANNOT CREATE ANY* ***ORDERS***
- [ ] **<u>(doPut)</u>:**
  - **[ALL MEMBERS] -** Updates the **currentOrder** using info from the request body {orderAddress,orderZip,paymentId}, or updates **currentOrder** amount from associated orderDetails *(?refresh=true)*
  - **[ANON] -** *CANNOT UPDATE ANY* ***ORDERS***
- [ ] **<u>(doDelete)</u>:**
  - **[ALL MEMBERS] -** invalidates the **currentOrder** cookie
  - **[ANON] -** *CANNOT DELETE ANY* ***ORDERS***

### { /orderdetails } WORK IN PROGRESS
- [ ] **<u>(doGet)</u>:**
  - **[ALL MEMBERS] -** Obtains all of the **currentOrder's orderDetails** information, an individual **orderDetails'** information from the **currentOrder** *(?orderDetailsId=ORDER_DETAILS_ID)*, all of an **authMember's order's orderDetails** *(?orderId=ORDER_ID)* information, OR an individual **orderDetail's** information from a single **order** *(?orderId=ORDER_ID&orderDetailsId=ORDER_DETAILS_ID)*, {quantity,comments,dishName,orderId}
  - **[ANON] -** *CANNOT READ ANY* ***ORDERDETAILS***
- [ ] **<u>(doPost)</u>:**
  - **[ALL MEMBERS] -** Creates a new **orderDetails** from info in the request body for the **currentOrder** {quantity,comments,dishName,orderId}
  - **[ANON] -** *CANNOT CREATE ANY* ***ORDERDETAILS***
- [ ] **<u>(doPut)</u>:**
  - **[ALL MEMBERS] -** Updates the **orderDetails** using info in the request body for the **currentOrder** {orderDetailsId,quantity,comments}
  - **[ANON] -** *CANNOT UPDATE ANY* ***ORDERDETAILS***
- [ ] **<u>(doDelete)</u>:**
  - **[ALL MEMBERS] -** Deletes the **orderDetails** info in the request body for the **currentOrder** {orderDetailsId}
  - **[ANON] -** *CANNOT DELETE ANY* ***ORDERDETAILS***


