#Commit Message Conventions #

> This page defines a convention for commit messages for the game3d project.
> 
> All commits pushed to the game3d repository must conform to that convention.

The contens of this page are partly based on the angular commit messages document.
This document can be accessed using the URL below:

[https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit?pli=1](https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit?pli=1 "Angular Git Commit Message Conventions")


## Purpose ##

The commit message is what describes your contribution. Its purpose must therefore be to document what a commit contributes to a project.

Its head line **should** be as meaningful as possible because it is always seen along with other commit messages.

Its body **should** provide information to comprehend the commit for people who care.

Its footer **may** contain references to external artifacts (issues it solves, related commits) as well as breaking change notes.

This applies to **all kind of projects**.

## Format ##
### Short form (only subject line) ###

    <type>(<scope>): <trigram> - <subject>

### Long form (with body) ###

    <type>(<scope>): <trigram> - <subject>
    
    <BLANK LINE>
    
    <body>
    
    <BLANK LINE>
    
    <footer>

First line cannot be longer than **70 characters**, second line is always blank and other lines should be wrapped at **80 characters**! This makes the message easier to read on github as well as in various git tools.

### Subject Line ###

The subject line contains succinct description of the change.

#### Trigram ####

- use three letters: 
 - first letter of your firsname
 - followed by the two first letters of your lastname
- capitalize all letters

#### Subject message ####

- use imperative, present tense: change not changed nor changes or changing
- do not capitalize first letter
- do not append dot (.) at the end

#### Allowed types ####

##### Main types #####

- **chore**(*build, release, ...*): Changes to the build process or auxiliary tools and libraries such as documentation generation. The updated file is the one which name is given in parenthesis
  - *e.g :* `chore(build): FLA - add project compilation target`
- **feat**(*feature name*): Changes made for implementing the specified feature
  - *e.g :* `feat(BALL VELOCITY): FLA - add method for computing the norm of the ball's velocity vector`
- **fix**(*feature name*): Changes made for fixing the specified feature
  - *e.g :* `fix(BALL VELOCITY): FLA - fix the formula used for computing the norm of the ball`
- **test**(*feature name*): Changes made for implementing unit tests or test cases or test scenarios defined for the specified feature
  - *e.g :* `test(BALL VELOCITY): FLA - implement test cases n° 1 to 5 of US-1`

##### Other types #####

- **docs**(*document subject*): Documentation only changes. This does not includes changes of JAVADOC comments within JAVA source files
- **improve**(*feature name*): A code change that improves performance for the specified feature
- **refactor**(*file name*): Code changes in the specified file that neither fixes a bug or adds a feature. This includes JAVADOC refactoring
- **style**(*file name*): Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)


### Message body ###

- just as in use imperative, present tense: change not changed nor changes or changing
- include motivation for the change and contrast it with previous behavior
- in case of code changes, mention the updated methods

### Message footer ###

#### Referencing issues ####

Closed bugs / feature requests / issues should be listed on a separate line in the footer prefixed with "Closes" keyword like this:


> Closes #234

or in case of multiple issues:

> Closes #123, #245, #992


# FAQ for Geeks #

---

**Why to use imperative form in commit messages?**

I.e. why to use *add test for #foo* versus *added test for #foo* or *adding test for foo*?

Makes commit logs way more readable. See the work you did during a commit as a work on an issue and the commit as solving that issue. Now write about for what issue the commit is a result rather than what you did or are currently doing.

**Example:** You write a test for the function #foo. You commit the test. You use the commit message add test for #foo. Why? Because that is what the commit solves.

**How to categorize commits which are direct follow ups to merges?**

Use `chore(merge): <what>`.

**I want to commit a micro change. What should I do?**

Ask yourself, why it is only a micro change. Use type = docs, style or chore depending on the change of your merge. Please see next question if you consider commiting work in progress.

**I want to commit work in progress. What should I do?**

Do not do it or do it (except for locally) or do it on a non public branch (ie. non master / develop ...) if you need to share the stuff you do.

When you finished your work, squash the changes to commits with reasonable commit messages and push them on a public branch.