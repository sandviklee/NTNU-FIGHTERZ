# Code and Git standard
This document is a work in progress documenting style and structure of code and git related things. This document will be thoroughly worked on during Sprint 2, along with the diagrams section of the docs.
## Git
* Branch name: Begin with issue number and call it after branch name, separated by dashes, for example:
    `2-create-signup-page`
* Commit messages: Name it after issue and subtask number, for example if you fix issue 2 subtask 3, then commit messages should look like this:

    `2.3: Bugfix`
    `2.3: Style changes`
* Merging: Create a merge request to the `dev` branch. You can have 1-on-1 code-reviews or add comments in merge request. Only need one other person to approve to push, unless the proposed changes provide big changes/structural changes to the project. Then it shall be discussed during a meeting, or if this proves difficult, worst case discussed in the merge request. Preferably discuss changes like this on meetings before implementing them.
* Code-reviews: Merge request comments and/or 1-on-1 if you prefer that.

## Code
* camelCase
* JavaDocs
* Exception handling: throw expcetions in model, catch them in controller
* Controller FXML tag: same line for variables, line above for functions
