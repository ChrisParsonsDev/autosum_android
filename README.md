# AutoSum Android Application	
This basic Android application follows the following specification: 

1. 6 Number fields laid out in two columns
2. 7th field displaying the result of the sum of the numbers
3. Editing a number updates the sum
4. Tapping on the sum toggles flashing (500ms hide/show)

## Assumptions

The following assumptions (in no particular order) have been made about the requirements:

1. Double type will be acceptable for user input from the number field
2. Minimum Android SDK Level 24


## Design Choices

While I've made every effort to use comments to thoroughly document the code, I hope the discussion below will help to further illustrate some of the design choices that I've made. 

1. The number variables / computation is done by the NumberData object class. This modular implementation follows the MVVM pattern and increases extensibility to support additional functionality in the future (other operations/datatypes etc.) 
2. The parameters of each of the 6 numbers are stored as separate variables. Using the NumberData object to store the parameter values separately ensures that the user session is retrieved if the application is closed. This also reduces the latency on computation should network calls have to be made to retrieve/update the totals as only the changed value is amended and the total adjusted without the need to look up the values from other cells. 
3. GridView vs TableView - I have elected to use a TableView to create the User Interface for the application. Whilst it would be possible to use a GridView to create the EditText fields in two columns, I felt that TableView provided the necessary functionality for our use case (columns, spacing etc.) without the need for a Java implementation of child views. 
4. The result of the summation of the EditText fields is computed on each update. Whilst this means that there are more calls to computeResult() than other possible implementations - getResult() is computed in BigOh(1) time irrespective of the rest of the implementation. This means that if the application were to be extended and include things like network calls etc. the user experience would not be impacted and they would see the result on screen without delay.  
5. The application uses a custom TextWatcher to implement the onTextChanged listener for EditText views. This means that we avoid unnecessary code bloat from implementing a separate onTextChanged for each of the 6 EditTexts as they can all have an instance of the TextWatcher assigned. This will make it easier to extend the application to have additional functionality in the future and, I hope you'll agree, also makes the code more readable. :)
