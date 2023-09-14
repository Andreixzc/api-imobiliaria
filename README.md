<h1 align="center">
  API - Real Estate
</h1>


## 💻 Project
Application for registration, viewing, updating, and removal of: users, properties, property categories, and property visit scheduling.

## 🔨 Implementations

- [X] Registration, viewing, and updating of all entities
- [X] Date/time validation, authentication, and administrator permission validation.

## ✨ Technologies

- [X] Bcrypt
- [X] Postgres
- [X] Spring Security



## 🌐 Business Rules

- Routes with administrator permission and/or authentication must be properly protected;
- It should not be possible to create more than one user with the same email;
- When deleting a user, their isActive state should be changed to false, and if it already is, return an error;
- When the user logs in, a JSON containing the token should be returned;
- Addresses and categories must be unique;
- Validate if the scheduled visit time is between 08:00 AM and 06:00 PM;
- Validate if the scheduled visit day falls between Monday to Friday;
- It should not be possible to schedule two visits at the same time and date on the same property.
