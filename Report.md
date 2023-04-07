# in the name of god
-------------------
# OXDCommod (online shop simulator)

# Introduction:
The rise of e-commerce has changed the way people shop, providing a convenient and accessible way to purchase products from their homes. In response to this trend, we have created an online store that offers a wide range of products and services to customers around the world. Focused on providing exceptional service and customer satisfaction, our online store provides a user-friendly and secure platform for customers to browse, purchase and receive products. In this report, we provide an overview of our online store, including its features and capabilities, as well as the strategies we have employed to attract and retain customers.
For this purpose, the OOP principles have been used to manage information and various functions more straightforwardly. which are mentioned throughout the report

# Design and Implementation:
For optimal storage of information, a store class was defined and all the information required to run the program was stored in it.
For easy access to information for each object, a UUID is assigned during construction, so that during the program, when needed, the desired object can be accessed with that key.
This point of view has been followed for the rest of the classes to avoid re-saving the information.
## Class diagrams and functions that need further explanation
  [Store diagram](diagrams/Store.pdf)
- `add(User/Seller/Admin)`: these functions get the required information to add new objects of that classes and constructs them.
- `login`: This function takes a username and password and checks that the entered information is correct for a person and outputs accordingly, it outputs `null` if it does not match.The output consists of two parts, the first part specifies the user's access level and the second part specifies the unique ID.
- `find(user/admin/seller/product/order)`:These functions return the object itself by getting UUID so that its information can be used.
- `sendOrders(user/seller/wallet/....)`:These functions send various user requests to the store admin, which the admin can view and respond to.Each function performs a specific task as needed.
- `(verify/cancel)(user/subscription/wallet/seller/...)`:These functions take the admin's response and perform various operations according to it.
- `ban`:This function cuts off the user's or seller's access to their user account so that the admin's doubts about the person can be removed
- `permit`:It removes the effect of ban and returns everything to the previous state 
- `findProductByInfo`:This function performs an approximate search for a product. For this purpose, it first separates all the sub-strings of the input string and then looks for the maximum matching of these strings with the names of the goods in all the products, and then sorts the goods based on the degree of matching and outputs.
  
[User diagram](diagrams/User.pdf)
- `hash password`:uses the **MD5** algorithm to hash passwords that is  a secure way to save passwords in the database.when the program wants to check the password, hashes an input password and then compares it with the hash string that was stored before.
- `lastseen`:It saves the user's past visits to different products to use for making suggestions later.
- `is order OK`:During the final confirmation of the order, it checks the absence of problems in the inventory of the purchased goods and outputs the problems.
- `order`:send an order request for admin.
- `verify order`:After admin approval, it will deduct the cost of the products from the wallet, and after calculating the shipping cost, it will send it
- `findLocation & calShippingCost`:This function takes the geographical coordinates of the user by querying the site api.weatherapi.com and calculates the shipping cost using mathematical functions.
  
[َSeller diagram](diagrams/Seller.pdf)
- `check level`:Based on the amount of sales, this function assigns a level to each seller that is effective on the user's trust

**abstract** [Product diagram](diagrams/Product.pdf)
- `(set/change/remove)Rate`:These functions perform calculations related to the score of a product
- `(set/get/remove)comment`:These functions perform operations related to the comments of a product
- `toString() & TOString()`:TOString gives more information about a product that is shown to admins but toString is prepared for users.
  
[Order diagram](diagrams/Order.pdf)
-`verify`:This function is called after the admin's approval and reduces the number of purchased goods and deposits the money into the seller's account.
-`refund`:On the contrary, it does all the work done in verify

**abstract** **extend product** [Category diagram](diagrams/Order.pdf)
**extend category** [SubCategory diagram](diagrams/Order.pdf)
- These two classes do not have special functions and only specify the product category.
### javafx controllers
[AdminPanel diagram](diagrams/AdminPanel.pdf)
[CartSeeProduct diagram](diagrams/CartSeeProduct.pdf)
[EditProductPanel diagram](diagrams/EditProductPanel.pdf)
[MainMenu diagram](diagrams/MainMenu.pdf)
[SearchTab diagram](diagrams/SearchTab.pdf)
[SeeProduct diagram](diagrams/SeeProduct.pdf)
[SellerPanel diagram](diagrams/SellerPanel.pdf)
[SignPanel diagram](diagrams/SignPanel.pdf)
[signUpPanel diagram](diagrams/signUpPanel.pdf)
[UserPanel diagram](diagrams/UserPanel.pdf)

# bonus tasks
1. approximately search
2. The user can search and view the products without having an account
3. Each user has a list of favorites to which he can save goods
4. Calculate the cost of sending goods using the user's address
5. Possibility of subscription for the user to send goods for free
6. There is a score for each user so that the admin can use it to measure the user's history
7. Suggesting products to the user based on her past visits
8. User,seller,admin all have a platform to see their notification
9. The user can return his goods if he is not satisfied with the approval of the administrator.
10. Every user can rate the goods or leave a comment for them and earn XP for doing this.
11. The seller can upload a photo of her product so that the user is willing to buy it.
12. Sellers also have ratings so that users can trust them more easily.
13. sellers have a ledger system that can see amount of products that they sell or was refunded
14. It is possible for sellers to add a specific product together , and the user can buy from any seller she wants
15. The program information is saved in a binary form after closing the program, and when the program is run again, it is restored and the program is run with the saved information.
16. When logging into the user account, captcha images are used to prevent bots from intruding, which are loaded randomly
17. The user interface is written using javafx, the functions of the controller and its fxml files can be seen in the program files, which can be checked if you need details.
18. Admin can ban a user or seller from activity to prevent their suspicious activities.And of course, it can give access to the deprived person to resume his activity
19. The store has a server log file that saves all the activities performed for the admin, who can use it if needed, and when there is a possible disruption in the storage of information, it is possible to recover it.
20. The admin sees the requests of users and sellers in his panel and either accepts them or rejects them by mentioning the reason that is displayed for that person.
21. and a number of other cases that are not mentioned due to lack of space

## challenges
- Define the product class as abstract or interface. If this class was defined as an interface, separate functions would have to be written for each category, but because the functions perform the same action, it would only cause similar codes to be written.
- Synchronization of information: so that when changing the information of an object, there is no need to change a lot of information, and so that the same object is not stored in several different places, stores all goods using a specific UUID in the store class so that these problems can be managed
- For this, all admins can see the requests and notifications, but they are only stored in one place, we design these attributes statically in that class.
  
# Test
Two methods were used to test the program.
1. unit test: for which a class called testing was written and most of the functions of the program that had the possibility of bugs were checked by fixing minor problems such as mathematical errors in the calculation of the profit of the store and the score of a product, it was successfully completed
2. In the second method, using complex scenarios, we have tried to execute the program and enter the information manually, and by checking the expected output, we have tried to fix possible errors.

# Conclusion
In conclusion, the development and implementation of our online store has been a resounding success. Through careful planning and attention to detail, we have created a seamless and user-friendly shopping experience for our customers. Our online store offers a range of features and functionality that make it easy for customers to browse and purchase products, including a comprehensive product catalog, user accounts, a shopping cart and a secure checkout process.
Despite the success of our online store, we understand the importance of continuous improvement and innovation. We will continue to collect feedback from customers and analyze data to identify areas for improvement and growth. This includes exploring new technologies, expanding our product offerings and enhancing the overall customer experience.