# Workflow and code
This document describes the workflow, code standard and tools used in the project.
## Git
### Issues and tasks
* Replacing the former system of "subtasks", [linked issues](https://docs.gitlab.com/ee/user/project/issues/related_issues.html) will replace the task of linking issues together. As some subtasks are sometimes too big to be a subtask and should rather be an issue, this will make it easy to still see the dependencies between different issues.
### Merge requests
* Do not create too big merge requests, if a feature is very massive, rather divide it into subparts and several merge requests so that the code is easier to look through and so that it is easier to know what part of the code might create errors.
* Do NOT approve or merge a request without checking that it works!
* Preferably review the code along with the owner of the merge request, but where this is not possible, write comments with specific details on what should be changed and where.
* Preferably, you should not merge your own request, but rather someone else should do it after review and changes have been done. It falls upon whoever merges to close the issue related to the merge request.
### Branches
* Branch name: Begin with issue number and call it after branch name, separated by dashes, for example:
    `2-create-signup-page-controller`
* Subbranches: Branches that relate to a particularly big issue where multiple branches are required to solve the issue, will have a bigger branch in the middle that will act as a bridge between the dev branch and many small branches. All the smaller branches will first be merged into the middle branch and tested together, such that features that should work together can be tested together without potentially breaking the dev branch. This branch can relate to multiple issues, relating to both implementing features and testing. It will be linked to the difference subbranches through linked issues (described above), and follow the naming pattern described above, but starting with the "implement" to indicate that it will be a branch for testing and merging to dev. Example of name:
    `4-implement-signup-page`
### Commits
* In commit messages, write the issue (and subtask number where applicable) and what has been added to this issue/feature, examples:

    `1: Bugfix MainMenuController` (Issue 1)

    `2: Style changes CharacterSelection` (Issue 2)

    `5: Implement new loading screen in MainMenu` (Issue 5)

## Code
* **JavaDocs:** All methods should be described using JavaDocs prior to implementation and coding.
* **Variable names**:
    * All variable and method names should use camelCase.
    * Noun VS verb: Objects and fields should have noun names, while methods should have verb names.'
    * Boolean names should begin with "is".
    * Name should be as specific as possible, and variables that are vague enough to be used for more than one purpose should be avoided.
    * Prioritize read-convenience over write-time-convenience, and making code as easy to read as possible.
    * Avoid abbreviations.
* **Exception handling**: Exceptions shall be thrown in the model/logic layer, while the controller will catch and handle them. This is to avoid not handling/handling multiple times and to easier test errors in the logic layer. If a part of the logic layer requires a try catch to work, the logic layer shall throw the exception further in the catch block.
* **Controller FXML tags**: For variables the tag should be on the same line as the variable, while as for methods, the tag should be one line above the method name and definition.
* Code should ONLY be written after a common agreement and final decision has been made on the architecture, design and methods of this part of the application, to avoid confusion and having to change major concepts later in the design process. These decisions should preferably be made for smaller portions of the application at a time, to support agile development and faster iterations, but should also keep the bigger picture and future features of the app in mind.

## Workflow
There will be two weekly meetings on Monday and Thursday, where we have scrum, planning and division of labour. Where relevant the meetings will also be used to discuss obstacles in the project and design decisions. The first meeting of a sprint will go to planning the sprint, such as deciding on user stories and creating issues. Issues assigned and decided during meetings will be worked on during each member's own spare time, however this is preferably done together, to be able to pair program and review code continiously. To promote pair programming and 1-on-1 in-depth code reviews, there will also be hosted hackathons once a week, where we will work together to solve design problems, code and review each other's code. When a member has finished a feature, they will notify another member or the group and request a review and a merge.

## Testing
* The testing will try to follow somewhat test driven development, though not necessarily by creating *all* tests before implementing code. Before writing code, the feature should already be thoroughly documented through diagrams, JavaDocs and other documentation, so that whoever implements the feature, knows what to do. The test driven development will add a new step to this process: planning of tests. Before implementing the classes, the member should plan how they will test the class, what additional fields or methods are needed to support this, and outline edge cases. This should be done creating JavaDocs for all tests necessary for the class. This makes it so that if the implementation of the method changes, no change will have to be done to the tests, while still ensuring a test driven development and securing all edge cases and test scenarios.
* To further check and validate code coverage, jacoco will be used to check test coverage. This plugin is checks what percentage of the application's lines are tested, and provides detailed reports for each class. However, it does not account for or check edge cases, and testing of such cases relies upon good planning ahead of testing and implementing.
* Code should be tested both in vscode and gitpod, as well as on numerous computers and OSs to ensure that it works universally.

## Tools
* This section contains other tools used in the development process that should be used and run when implementing new code to ensure code quality and robustness.
* Jacoco: Provides detailed reports about test coverage (however does not account for edge cases, only checks whether lines of code are tested or not.)
* Spotbugs: Finds blocks of code that contain logical fallacies.
* Checkstyle: Standardizes the style of the code and checks for inconsistencies.
