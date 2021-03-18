# ToDo List Group Project
Fork a copy of [JavaGettingStarted_ToDoList](https://github.com/gartee-john-PFG/JavaGettingStarted_ToDoList) to your GitHub account and clone it to your local machine.

We will be working together to write a small program to manage a TODO list.  Using console-based menu techniques, create an application that works as follows:

* First, always clear the screen prior to displaying a menu
* Main Menu
    ```
                            Main Menu
    1. Open ToDo List
    2. Display ToDo Items
    3. Edit To Do Item
    4. Delete ToDo Item
    5. Save ToDo List
    6. Exit
    ```    
* Menu Item 1 (Open ToDo List)
    1. Prompt for a file path
    2. File is in JSON Format
    3. File Structure
        ```json
       {
            "Items" : [
                {
                    "Date" : "mm/dd/yyyy",
                    "Time" : "hh/mm",
                    "Description" : "Description of the item1",
                    "DurationHours" : 1.25,
                    "Complete" : false
                },                {
                    "Date" : "mm/dd/yyyy",
                    "Time" : "hh/mm",
                    "Description" : "Description of the item2",
                    "DurationHours" : 0.25,
                    "Complete" : true
                } 
            ]  
            
       }
       ```
        * notes:  Items is an array of 0 - n items
        * date/time are both strings that will need to be parsed into LocalDate and LocalTime objects.
        * DurationHours is a float, but should be only in increments of 1/4 hour (.25, .50, .75, 1.00, 1.25, etc.).  Maximum should be 24.00.
        * Complete is a boolean value, defaulting to false
    4. Display the following errors when appropriate
        * File not found at "file path"
        * File is not in Json format

* Menu Item 2 (Display ToDo Items)
    1.  Display the following menu
    ```
                Display To Items
        1.  Show all items
        2.  Show Completed Items
        3.  Show Incomplete Items
        4.  Return to previous menu
    ```    
    * Display in Date/Time/Description order.
    * Do not allow task time intervals to overlap
    * Display Format:
    ```
        Description: your description here
        Date: mm/dd/yyyy  Time: hh:mm 
    ```
* Menu Item 3: Edit ToDo Item
    1. Numbered list all items in single lines
    2. Allow for multiple pages of listed items (be creative in how you do this)
    3. Allow user to scroll the list up and down by 1 or more lines (be creative there, too)
    4. Display format example
    ```
              Date      Time   Completed     Description
        1. mm/dd/yyyy hh/mm/ss    true      Mow the grass
        2. mm/dd/yyyy hh/mm/ss    false     Feed the cat
        3. mm/dd/yyyy hh/mm/ss    false     Pluck the chicken
        4. mm/dd/yyyy hh/mm/ss    false     Debug my code
        5. mm/dd/yyyy hh/mm/ss    false     Pick up the kids
        6. mm/dd/yyyy hh/mm/ss    false     Clean the garage
  
    ```
    5.  Select the number of the item to edit (be creative how you display/edit/save/cancel)
    6.  How will you return to the previous menu?
* Menu Item 4: Delete ToDo Item
    1. Use same display mechanisms and you did it Item 3
* Menu Item 5 (Save ToDo List)
    1. Save in new or same place?
    2. Save a copy of your old file or not?
* Menu Item 6 (Exit)
    1. Exit the program