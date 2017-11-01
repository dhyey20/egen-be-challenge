# egen-be-challenge

# Egen Coding Challenge – Level 1

## Create a restful web service for User Management application using 
- Spark Java http://sparkjava.com/ and
- MongoDB

### Assumptions
- User has the following properties
```
{  
   "id":"1630215c-2608-44b9-aad4-9d56d8aafd4c",
   "firstName":"Dorris",
   "lastName":"Keeling",
   "email":"Darby_Leffler68@gmail.com",
   "address":{  
      "street":"193 Talon Valley",
      "city":"South Tate furt",
      "zip":"47069",
      "state":"IA",
      "country":"US"
   },
   "dateCreated":"2016-03-15T07:02:40.896Z",
   "company":{  
      "name":"Denesik Group",
      "website":"http://jodie.org"
   },
   "profilePic":"http://lorempixel.com/640/480/people"
}
```
- Use MongoDB as your data store.
- The User's ID uniquely identifies a user.
- Feel free to use any framework or library in your code. But make sure you use Spark Java and Mongo.


## Questions
- Develop the below 4 services using Spark Java
  * createUser
    - Takes input as JSON. 
    - Creates the user if he is not already available in the data store.
  * getAllUsers
    - Gives the list of all users that are in the data store
  * updateUser
    - Takes input as JSON
    - Finds the user from the data store
    - if found, updates the required fields
    - if not found, sends back a 404 saying user not found
  * Unit tests using any unit testing library


# API Usage
## Create User: /add_user
To create a user, hit the Spark service with the path as /add_user and set the body of the request as the json for the new user object to be created with all the fields except the id and the date created fields. To make sure that the users are unique, a check for a unique email has been made. Returns a json object of the new user created.

## Get All Users: /users
To get all the users stored in the mongodb collection, just hit the spark service with the path as /users with no other arguments. The list of json objects of users is returned as a response.

## Update User: /update_user
To update the fields of the user, hit the Spark service with the path as /update_user wiht the user json object as body of the request. If the user with the given objectId is found in mongodb then the user is updated except the email id, and the changed user json is returned as response. If not found then an error message is sent with a 404 as header status.
