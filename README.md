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
- You can generate the first required event (logging in) by running main and inputting the string "Gandalf" (with the capital "G") into both the username and password text fields and clicking on the login button
- Upon successful login, you should trigger my audio component (the mario theme song)
- You can generate the second required event by typing a task name and due date (both strings) into the appropriate fields and clicking on add task.
The task should appear in the scrollable task list panel.
- You can save the state of the task list by clicking on the save button at the bottom of the page. It should save to a file in the data folder.
- You can reload the state of the application by exiting, rerunning main, and logging in again. If you've previously saved the task list it should appear in the same state it was saved in upon relaunching the application.
- You can also edit a task in the task list by selecting a task, typing what you want the edited task to look in the appropriate text fields, and clicking on edit. The task should change in the task list panel below.
- You can also delete a task from the task list by selecting a task and clicking on the delete button.
- If you try to do something invalid (like add a task without text, edit/ delete a task without selecting a task, etc) you should trigger another audio component (and at times a dialogue box) signalling error