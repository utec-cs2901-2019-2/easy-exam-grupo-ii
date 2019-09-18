### Software: EasyExam
### Actor: User
### Requirement: None
### Use Case: UC01 - Account creation

1. User enters EasyExam´s web page
2. User clicks on register
3. System redirects to Register page
4. User fills the registration form
5. User clicks Create Account
6. System creates the account and redirects the user to home

USE CASE ENDS.

### Extentions:
- 5a. System detects unfilled fields on the registration form
- 5a1.  System asks the user to fulfill the unfilled fileds
- 5a2. User fills the fields
- Repeat until user creates an account successfully 


- 5a. System detects that there is already a user registered with that information
- 5a1. Redirects back to the registration form and tells the user there is someone already registered with that information
- Repeat until user inputs different info


- *a User cancels registration
- *a1. System redirects user back to the home page

USE CASE ENDS

-----------------------------------------------------------------------------------

### Software: EasyExam
### Actor: User
### Requirement: User is logged
### Use Case: UC02 - Registering a new question/answer

1. Logged user clicks on Submit a question
2. User fills the fields for the question
3. User clicks Next
4. User fills the fields for the answer
5. User clicks Submit
6. User redirects to confirmation page
7. User clicks on finish button
8. User gets redirected to the dashboard

USE CASE ENDS

### Extentions: 
- 5a. System detects unfilled fields on the form
- 5a1.  System asks the user to fulfill the unfilled fileds
- 5a2. User fills the fields
- Repeat until user submits a question successfully

- *a User cancels Submition
- *a1. System redirects user back to the dashboard

USE CASE ENDS

-----------------------------------------------------------------------------------

### Software: EasyExam
### Actor: User
### Requirement: User is logged, user added problems (UC04)
### Use Case: UC03 - User downloads the exam

1. User clicks generate
2. System redirects user to the exam generation page
3. User clicks on the format he wants to download the exam in 
4. System downloads the exam into the user's PC

USE CASE ENDS

### Extentions: 
- *a User cancels Submition
- *a1. System redirects user back to the dashboard

USE CASE ENDS

-----------------------------------------------------------------------------------

### Software: EasyExam
### Actor: User
### Requirement: User is logged
### Use case: UC04 - User adds a question to the exam

1. User selects a problem from the dashboard
2. System pops up the problem's window
3. User Clicks on get problem 
4. System adds the problem to the exam the user is generating

USE CASE ENDS

### Extenitions:
- *a User Cancels Submition
- *a1. System redirects user back to the dashboard

USE CASE ENDS

-----------------------------------------------------------------------------------

### Software: EasyExam
### Actor: User
### Requirement: User is logged
### Use case: UC05 - Report a problem

1. User selects a problem from the dashboard
2. System pops up the problem's window
3. User Clicks on report
4. System pops up a report window
5. User fills on the fields to report the problem
6. User clicks on submit report

USE CASE ENDS

### Extenitions:
- 5a. System detects unfilled fields on the form
- 5a1.  System asks the user to fulfill the unfilled fields
- 5a2. User fills the fields
- Repeat until user creates a report successfully

- *a User Cancels Submition
- *a1. System redirects user back to the dashboard

USE CASE ENDS

-----------------------------------------------------------------------------------

### Software: EasyExam
### Actor: User
### Requirement: None
### Use case: UC06 - Account recovery

1. User enters EasyExam's web page
2. User selects forgot password
3. System redirects to recovery page
4. User fills the fields 
5. User clicks on submit
6. System sends an account recovery email to the user

USE CASE ENDS

### Extenitions:
- 5a. System detects unfilled fields on the form
- 5a1.  System asks the user to fulfill the unfilled fields
- 5a2. User fills the fields
- Repeat until user sends  

- *a User Cancels Submition
- *a1. System redirects user back to the dashboard

USE CASE ENDS
