
# Hotel Management System

In this project, we look at how the hotel management system conducts business and deliver a java desktop application. The project has some requirements:   
* There are three types of accounts: receptionist, guest, and manager.
* The rooms have different styles: standard, deluxe, family, and business.
* Guests can search for available rooms based on their preferred room style, date, and duration of stay.
* Guests can book any available rooms directly from the application.
* When a room is booked, a notification is generated and stored in the system.
* The system maintains a comprehensive database of all room bookings.

More specifically, the following base functions are available to stakeholders/users:
* Register account
* Edit account
* Delete account
* Input the customer data for booking
* Search for available rooms based on room style, date, and duration
* Book available rooms
* Generate booking notifications
* View and manage room bookings
 
The Java OOP programming paradigm is used to design classes. So we apply the principle of OOP such as abstraction, encapsulation, inheritance. Furthermore, the project is based on three-tier architecture which are presentation, application and data tier. 

![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/3bb80449-8fe0-408d-a607-5eba42c36e63)
## Class Diagram
![image34](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/41ff9614-2a90-4d12-a07b-833f3c5407da)
![image27](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/baa9c1fc-729e-4435-9423-54ce3ff30ebf)
![image19](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/d95229fc-5d08-4e90-878f-dbd065ec28b0)

## Use-Case Diagram
![UC Diagram drawio(2)](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/8ba3671a-9cd3-4a7b-b9f1-8720344ef175)

## Relational Schema
![image25](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/3ebf71f1-c50f-4f95-8559-28689da0339a)


## Graphical User Interface
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/ce07572f-a818-4212-ace3-de96b0384332)
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/b1a676b7-b386-4554-b1b9-7111f96a89f1)
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/699279b2-2fae-43f9-9641-4faab68dfff9)
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/a53a2ad2-a85e-4fee-bd88-c5a92ff4994a)
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/259834a0-cbec-4373-b253-7d1de3a553b4)
![image](https://github.com/anhtuansggd/Hotel_Jana/assets/122171727/484d9030-9158-46be-be77-5cb26ae74338)

## Run Locally
**Requirement Programs:**
* Netbeans
* MySQL Community Server
* MySQL Workbench

1. Clone the project
```bash
git clone https://github.com/anhtuansggd/Hotel_Jana.git
```
2. Create a config.properties file in the "Hotel_Jana" folder,
copy below configuration then put username and password of your MySQL after "="
```config
db.url=jdbc:mysql://localhost:3306/hotel_dbms
db.username=
db.password=
```
3. Setting up database<br>
    1\. Create database (schema)
    ```SQL
    CREATE DATABASE hotel_dbms;
    ```
    2\. Let SQL know what database to use
    ```SQL
    USE hotel_dbms;
    ```
    3\. Import database
    - On the menu row, find "Server" -> "Data import".
    - A new data import tab appear, then choose "Import from Dump  Project folder". <br>
    - Using "..." to choose the "dump" folder inside the "Hotel_Jana" folder.<br>
    - Under frame "Select Dabase Objects to Import", choose hotel_dbms as schema, then select all four tables.
    - Press start import.
4. Project loading<br>
    1\. Open Netbeans, on menu row, find "File" -> "Open Project"<br>
    2\. For easy importing, Maven is used here. Locate to project folder then choose "Hotel_Jana" and press "Open Project"<br>
5. Run<br>
    1\. Click "Source Packages" -> "GUI"<br>
    2\. Right click on "LoginGUI", press "Run File"<br>
    3\. (Optional) Using account "123" with password "123" to test.<br>

## References
* Hotel Management System using Object-Oriented Design, Educative <br/>
[https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/getting-ready-the-hotel-management-system] <br/>
* Three-tier Architecture Definition, IBM <br/>
[https://www.ibm.com/topics/three-tier-architecture] <br/>
* Polymorphism Diagram	<br/>
[https://metamug.com/article/java/polymorphism-java-tutorial.html] <br/>
