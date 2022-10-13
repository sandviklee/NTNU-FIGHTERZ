# What's new in release 2

## Features
* Changed persistent layer to use json instead of csv files
* Make character selection screen and character information screen be dynamic
* Added gitpod support and gitpod tests
* Added tools for checking code quality such as jacoco, checkstyle and spotbugs
* Updated diagrams and architecture of project
* Added exit button
* Began implementing gameplay classes
* Better documentation of workflow and changes!

## Documentation
The documentation for this release consists of:
* **[Dev notes 2](dev-notes-2.md):** Reflection around how working with this release has been, what we have done this sprint and what can be improved for next sprint.
* **[Workflow and code](workflow-and-code.md):** Documents thoughts surrounding git, code, workflow and testing, as well as what has improved and can be improved surrounding workflow.
* **[Diagrams folder](diagrams):** Contains various diagrams that were created during sprint 2 and iterations of them.
    * **[PlantUML for newest class diagram for gameplay](diagrams/ClassDiagram.puml) and [photo of the class diagram](diagrams/ClassDiagramGameplayVer4.png):** Shows the planned classes for the gameplay module and the relations between them.
    * **[PlantUML for newest package diagram](diagrams/PackageDiagram.puml) and [photo of the package diagram](diagrams/PackageDiagramVer1.png):** Shows the planned modules, packages within them and external modules the project is dependent on, as well as the relationships and dependencies between these.
* **[json folder](json):** Contains the formats for json files used in sprint 2.
    * **[Character format](json/characterformat.json):** Contains json schema to verify how a new character is added to the game and what is required.
    * **[User format](json/userformat.json):** Contains json schema to verify how a new user is added to the database and what is required.
* **[Meetings folder](meetings):** Contains all meeting notes for the sprint, which includes division of labour, longer discussions surrounding design choices (summary of these in the [ADR](../current/ADR.md)) and the day-to-day planning of the project.
