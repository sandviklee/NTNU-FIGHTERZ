# Dev notes

## How it was working on this sprint
### Simon
It was a bit stressful in the beginning, because there was a lot that wasn't done from sprint 1 and a lot we had to do for sprint 2 as well. Was a bit worried we wouldn't be able to finish. Became less stressful as things started working and it felt possible to finish this sprint :) We've had good meetings and hackathons. The addition of JavaDocs was very nice and makes developing classes easier. Some of the JavaDocs were however finished, and as such, new additions and solutions to coding the classes still had to be implemented. Using JavaDocs also helped with the discussions around the architecture, methods and fields. It was easy to see if methods made sense and it helped a lot with planning the road ahead for the project.

### Kristoffer
Much less stressful than sprint 1 as we always had a viewable trajectory and reachable goal in mind. Made the experience more engaging as you felt you could actually make progress. I think this was a result of better planning, documentation and setting a more realistic timeline. We were mostly able to complete our assignments on time. This is also due to increased knowledge around the Maven framework and no more problems with the pom files. Planning and execution of plans went very well this sprint.

### Sverre
A great improvement from the last sprint was the introduction of hackathons. Not only did it make it easier to pair program, but it also made problemsolving much easier as we were could easier communicate physically. It was much easier to take care of problems as they arised. The work also became more engaging as we had a great work environment, with good food and good friends which made work easier. This also made it easier to practice agile development, as when problems were found, we could change the design immediately, or when we had sub-optimal solutions, we could learn together and make better solutions to improve performance and robustness of the code. Code reviews were also much easier in person, and this led to them being more thorough. The hackathons increased morale in the group and were just all in all a great addition (thanks Elin, great idea). Lastly, JavaDocs and dummy classes made it much easier to develop new classes since one could easily know what methods were at one's disposal.

### Elin
This sprint worked much better than the last one. We still had a lot to do from the last sprint, which might've halted progress this sprint. However, learning from past experiences from sprint 1, the planning and documentation for the project severely improved. This was a godsent and helped the development so much. In addition, we added new elements to our workflow, such as pair programming, code reviews together, hackathons, JavaDocs and documentation before implementation. The process was much more painless than sprint 1, and for the most part everyone could independently work and know what they can do at all times. The group really came together and solved a lot of issues together, especially regarding the design, and everyone contributed many different ideas. The design improved as a result of planning with everyone instead of just implementing on your own. All in all, it is incredible that we got as far as we did, including all unforeseen obstacles.

## What were our goals and did we reach them?
* **Goal 1: This**

    helo
* **Goal 2: That**

    helo

## What can we improve for next sprint?
### Simon
Remember to close your issues! A lot of clutter, issues are for some reason not closed automatically when merging, and as such need to be closed manually. Might have to look into what the issue is, but for now, please, remember to close your issues.

### Kristoffer
We need to be better to work on updated branches. A lot of merge conflicts occurred because branches were not updated with the latest changes. This could be fixed by making sure tasks only need itself or update branches such that multiple people can work on different tasks that are not dependent on each other. Another nice addition, would be more "middleman" branches so that we don't need to merge every feature into dev all at once. For example, all tests for fxui could go into a tests-fxui branch, with subbranches consisting of the different individual tests. This can be done by having tests-fxui as the issue, and the individual tests as subtasks, or just creating a "supertask" for the tests. This is further discussed in the new and improved "Workflow and code" document, which is continuation and improvement of the "Code and git standard document" from Sprint 1. Lastly, set realistic goals and don't let this project affect your sleep too much!

### Elin
There were some issues during the beginning of the sprint that we have learned from and improved. No one is merging directly to dev anymore or forgetting to create issues or branches, as the merge conflicts this caused for sure has left a mark on all of us. We sometimes forget to close issues or merge important features that impact all branches however, which should be done faster. Once you're done with a feature that will fundamentally change the project, push it immediately so that others can review and use it as fast as possible! And don't wait until you have a billion features to add, divide them into smaller merge requests. The planning for the sprint was done early this time and for the most part good, but could be more detailed. Lastly, we should also remember what we talked about last sprint: ask for help! We have become much better at it, but let people know even earlier that you are struggling and need help, and if you can't fix an issue, work together to get it done.

### Sverre
Stale branches and branches no longer in longer, such as backup branches, should be closed and deleted in timely manner to avoid clutter and confusion on which branch is up to date. Should maybe try more test driven development and write more tests before we implement code? However, there are some drawbacks with test driven development, especially when using agile development with quick iterations, as tests are very subject to change as new iterations provide new solutions. To accommodate for this, one could write general tests and then specify further as the features are implemented, but how to do this needs to be described further. As such it is discussed in the "Workflow and code" document of this sprint. More detailed planning will also make it easier to create tests, but as we lack knowledge in a lot of what is to come, it might be hard to predict certain things.

## Dos and don'ts next sprint!
* Do: keep having hackathons and weekly meetings!
* Do: keep writing JavaDocs, even earlier and for even more classes! This should be done before implementing any code and should ideally be done for all new classes before starting work.
* Do: Close issues when you merge something new!
* Do: Follow the new standard decided for git and code in the new "Workflow and code" document.
* Do: Delete branches that are not in use.
* Do: Ask for help.
* Do: Plan testing for classes ahead of implementing them.
* Do: Define clearer goals for the sprint, specify exactly what features are needed for the sprint to be a success.
* Don't: Merge directly to dev.
* Don't: Approve/merge merge requests without testing that they work.
* Don't: Create too big merge requests, divide into smaller requests, as it is easier to catch errors and review code, as well as understand what parts of the code create errors.
* Don't: Change sprint goals at the end of the sprint. This might be hard at times, as things end up taking more time than expected.

