# TwentyFour7 

TwentyFour7 will provide a user the ability to create a planner for team tasks. This system is designed for team leads who are looking to allocate their resources effectively by prudently managing both task assignments and team members. 
I have taken ispiration from my a few of my favorite tools, Slack and Asana, and will be using this application as a brainstorming tool.

#### Basic Feature Include:
- creating a master task
- creating/removing tasks under master task
- assigning people to each task
- etc.

### Stage 1:
##### User Stories:
- as a User, should be able to create a master task
- as a User, should be able to input a master task's name
- as a User, should be able to create a subTask under a master task
- as a User, should be able to input a sub task's name
- as a User, should be able to create a person
- as a User, should be able to add a person to a sub task


### Stage 2:
- as a user, I want to be able to autosave my MasterTask to file
  -  when new mastertask/subtask is added
  -  upon quitting application
  
- as a user, I want to be able to reload my saved MasterTask into interface

### Stage 3:
- a functional gui with the exception of adding a new person, viewing added people

#### Instructions for Grader:
- Clicking the button "Create a new master task" generates a new master task from scratch
  - User is then prompted to enter a name for his/her master task 
  - pressing enter/clicking submit will save the master task automatically
  - User can add/remove subtasks to his master task and view the tasks assigned
  
- My application saves automatically to user input so there is no designated "save" button
- You can reload the state of my application by clicking the button "Reload a master task" 
  - if no previous JSON file is located, user will be prompted to return to home menu

- You can locate my visual component through out the app as the background and icons are set to images

### Phase 4: Task 2

- I decided to "Test and Design a class that is robust" and make edits to my MasterTask class. I added tests to the indexSubNames method and made MasterTask.getTitle() more robust by restricting masterTask title to the following characteristics: 
  - The master task title must not be fewer than 3 characters
  - The master task title must not consist of only numbers

### Phase 4: Task 3
- I had previously refactored most of my work as I was working along in my project. In my model package, the Subtask extends MasterTask and inherits its functions and fields. I did this in phase 1 to keep a hierarchy within the data structure which was implemented in phase 3. I also refactored my "assignedPerson" list to a HashSet in the SubTask Class in phase 1 to make use of a Set rather than a Collection to avoid duplication.

- I decided to place all data saving and reloading components into the UI section because with the added GSON library it was easier to control inside the TaskApp Class as the only saving and loading accessed would be for the Application itself. Creating a separate saveable interface and data/save classes would have been menial. Navigation within the TaskApp was displayed with updating the JFrame and Jpanel rather than creating new Panel's within each method so I chose to leave the pages inside the TaskApp class as "refactoring" would introduce much more coding and a different structure altogether.





