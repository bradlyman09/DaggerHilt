# DaggerHilt
Android application using Dagger Hilt, MVVM architecture and ROOM library. 

## MVVM design pattern
I chose MVVM design pattern because it seperates UI and Application logic. By having these advantages it would be ideal to implement this in large scope applications.
Seperating these two parts ease mainting the application as it will be possible to add/update components. For example we can modify the application logic and immediately
apply its changes to the UI since its abstracted.


## Room library implementation
I implemented room library here by saving the api response to its local database. I added the feature favorites to make the user select their favorite movies and save it in the database.
