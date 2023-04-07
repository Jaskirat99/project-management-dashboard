# Construction Project Management 

## Keeping track of your projects so you don't have to!

This project helps log and track costs of projects and then 
keeps track of all the analytics including estimated profits,
costs, tax, realtor commission and much more! You will be able
to start a project and then add cost entries as they occur. 
Then on your dashboard you will be presented with all the 
information you need to make sure you remain **informed** on the 
**analytics** of your **business**!

Personally, my family works in construction and we 
build homes for clients but also for ourselves and then sell after construction. For these personal projects, in order to keep 
track of all the properties and projects and their 
corresponding costs and calculations we have a complicated 
system of spreadsheets that I manage. These spreadsheets 
give us insight into our costs, profits, taxes etc. 
However, due to complicated formulas needed, it is prone 
to easily breaking, and is limited in its automation and 
user-friendliness. This project is meant to rework that idea
and build something that is much easier to scale, and much easier
to use 
## User Stories:

- As a user, I want to be able to create multiple projects
- As a user, I want to be able to add entry logs in any project
- As a user, I want to be able to see my total costs for a particular project
- As a user, I want to be able to see my cost breakdown on what was spent in cash,cheque or credit card
- As a user, I want to be able to store an expected sale price for any project.
- As a user, I want to be able to see an expected profit figure on any project
- As a user, I want to be able to see how much of my cost went towards taxes (GST/PST)
- As a user, I want to be able to make slight mistakes and the program correct me
- As a user, I want to be able to be presented the option to save the current state of the application and reminded to 
do so
- As a user, I want to be provided the choice to load existing data

# Instructions for Grader

- You can add an Entry to A Project by clicking the top right menu option labelled "Entry Options" and then clicking 
   "Add A Transaction"
- You can generate the first required action related to adding Entries to a Project by clicking the top right menu option 
labelled "Entry Options" and then clicking
  "Add A Transaction"
- You can generate the second required action related to adding Entries to a Project by clicking the top right menu option
labelled "Entry Options" and then clicking "View Transactions." Here you can view all entries for a particular Project 
and optionally filter (By clicking the top menu option labelled "Filter") for a subset of Entries. You can also view
a summary of the entries through "Project Options" and then "View Summaries"
- You can locate my visual component on the main screen. Any time you navigate "home", a picture is displayed
- You can save the state of my application by clicking the top menu option labelled "File Options" and then clicking 
"Save Data"
- You can reload the state of my application by clicking the top menu option labelled "File Options" and then clicking
  "Load Data"

# Phase 4: Task 2
Mon Apr 03 18:14:20 PDT 2023
Project: 123 Created.

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: 123

Mon Apr 03 18:14:20 PDT 2023
Project: 456 Street Created.

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: 456 Street

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: 456 Street

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: 456 Street

Mon Apr 03 18:14:20 PDT 2023
Project: 789 Rd Created.

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: 789 Rd

Mon Apr 03 18:14:20 PDT 2023
Project: Coachella Created.

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: Coachella

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: Coachella

Mon Apr 03 18:14:20 PDT 2023
Entry Constructed.

Mon Apr 03 18:14:20 PDT 2023
Total Cost Calculated

Mon Apr 03 18:14:20 PDT 2023
Entry Created For: Coachella

Mon Apr 03 18:14:20 PDT 2023
Project: 45678 Street Created.

Mon Apr 03 18:16:09 PDT 2023
Project: 686 Street Created.

Mon Apr 03 18:16:09 PDT 2023
Number Formatted For Display!.

## Phase 4: Task 3
Looking at my current code, I am happy with how the buttons are organized. Each custom button extends an abstract
Button class. That abstract class contains the standard information for all buttons, and the individual button classes
handle any specific behaviour. However, I do not have one point of change for these buttons. If I wanted to say change 
the color palette I am using, I would have to do so individually and that is bad. The reason I did this was because the 
menu buttons did not need these colors and borders. Reflecting on this, the better way to do this would be to have a 
method in the abstract class that specified the formatting (maybe something like "applyFormatting()"). This would be not
abstract and this method could be called in every button that needed the formatting and omitted in the rest. This would
provide just one point where the color and whatnot could be specified.

Next, while the Model package is well-designed and distributes responsibilities nicely, The same can not be said for the
UI package. This package only has 3 classes (plus the Button subpackage), main (which only calls either the TUI or GUI),
App (which does everything for the TUI) and VisualApp (which does everything for the GUI except for buttons). Ideally, 
the UI should have been split up more. This would make the program more modular and reduce coupling. For example, right 
now with the GUI everytime you go to a new screen, it clears the previous screen completely and if you want to see it 
again, you would have to reconstruct that screen. It would be possibly more effective to have each of these screens as 
their own class as this would allow for greater flexibility in creating more screens and maintaining the existing ones.
A possible downside with this approach would be that there would have to be careful consideration made into ensuring 
each screen has access to all the necessary information. This could possibly lead to a lot of repetition and needless 
passing around of fields.
