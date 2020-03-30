# Get It Done!

## An all-in-one productivity manager for everyone!

This productivity manager allows its users to *login* and *schedule and keep track of tasks* to maximize their workflow efficiency. 
The goal of the application is to provide its users ease and reduce the added stress 
of keeping up and managing everything they need to do. The application is designed for 
students, working professionals, creatives -- anyone who has work they need to do! <br>

I decided to create **Get It Done** because I see a need for it in my own life. It's said 
that necessity is the mother of invention and that holds very true for this project. Ultimately, I'd like to 
see the application have a positive impact in the lives of all its users.

Productivity Manager Features:
- Login + User Accounts for Users :computer:
- Task tracker :memo:

## User Stories

- As a user, I want to be able to add tasks to the task tracker (and be able to edit them after adding them)
- As a user, I want to see the tasks added to the task tracker
- As a user, I want to be able to delete completed tasks from the task tracker
- As a user, I want to be able to login to the application from the menu
- As a user, I want to be able to save my task tracker to file
- As a user, I want to be able to see old tasks that were previously added when I relaunch the application.

## Instructions for Grader
- You can generate the first required event (logging in) by running main and inputting the string "Gandalf"  (with the capital "G") into both the username and password text fields and clicking on the login button :squirrel:
- Upon successful login, you should trigger my audio component (the mario theme song) :notes:
- You can generate the second required event by typing a task name and due date (both strings) into the appropriate fields and clicking on add task.
The task should appear in the scrollable task list panel. :computer:	
- You can save the state of the task list by clicking on the save button at the bottom of the page. It should save to a file in the data folder. :floppy_disk:
- You can reload the state of the application by exiting, rerunning main, and logging in again. If you've previously saved the task list it should appear in the same state it was saved in upon relaunching the application. :file_folder:
- You can also edit a task in the task list by selecting a task, typing what you want the edited task to look in the appropriate text fields, and clicking on edit. The task should change in the task list panel below. :black_nib:
- You can also delete a task from the task list by selecting a task and clicking on the delete button. :x:
- If you try to do something invalid (like add a task without text, edit/ delete a task without selecting a task, etc) you should trigger another audio component (and at times a dialogue box) signalling error :warning: 
- You should only be able to add 10 tasks to the task list at a time. If you try adding an 11th for example, you should get a dialog box error. :weary:

## Image/ Audio Sources
- The Zelda "treasure found" sound effect obtained from: https://www.youtube.com/watch?v=LqkRghWjD0Y
- The Mario theme music sound effect obtained from: https://www.youtube.com/watch?v=VH8mQRXemuo
- Check mark image used as logo obtained from: https://www.iconfinder.com/icons/1398912/check_circle_correct_mark_success_tick_yes_icon

## Phase 4: Task 2
- I chose to make my Task class (in the model package) robust 
- I made the Task constructor as well as the setTaskName and setDueDate methods throw the EmptyStringException that I added to the exceptions package. 
- This exception gets thrown whenever a user attempts to add a task with an unspecified (empty) name or due date or if they try to edit an already existing task without specifying the name of due date.
- Note: I had to surround Task constructor calls in the Reader, TaskListTest, and WriterTest classes in order for the program to compile.
- The exceptions are handled appropriately in both the ConsoleUI and TaskManagerGUI.