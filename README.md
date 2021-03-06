# Visulog

_Tool for analysis and visualization of git logs - A UP Project_

## Presentation

Visulog a tool for analyzing contributions from the members of a team working a a same given project hosted on a git repository. Its goal is to assist teachers for individual grading of students working as a team.

This tool can:

-   compute a couple of relevant indicators such as:
    -   number of lines or characters added/deleted/changed
    -   number of commits
    -   number of merge commits
-   analyze the variations of these indicators in time: for instance sum then in a week, compute a daily average or an average in a sliding window, ...
-   visualize the indicators as charts (histograms, pie charts, etc.) embedded in a generated web page.

## Already existing similar tools

-   [gitstats](https://pypi.org/project/gitstats/)

## Technical means

-   The charts are generated by a third party library (maybe a Java library generating pictures, or a javascript library which dynamically interprets the data (CanvasJS)).
-   The data to analyze can be obtained using calls to the git CLI. For instance ./gradlew run --args='https://git_project typeofgraph branchename(or all for all branch)'

## Architecture

Visulog contains the following modules:

-   data types for storing raw data directly extracted from git history, with relevant parsers
-   a generator of numerical series (for the indicators mentioned above)
-   a graphical app
-   a generator of web pages
-   a command line program that calls the other modules using the provided command line parameters
-   a shared module for configuration object definitions

## How to run the program

-   ./gradlew run Launch the desktop app
-   ./gradlew run --args='https://github.com/0xRyN/visulog' Get all commits of the project with default graph
-   circle / bar / default with div HTML
-   ./gradlew run --args='https://github.com/0xRyN/visulog circle' Get commits of the project with a "circle" graph
-   ./gradlew run --args='https://github.com/0xRyN/visulog bar' Get commits of the project with a "bar" graph
-   ./gradlew run --args='https://github.com/0xRyN/visulog circle all' Get all commits of repository
-   ./gradlew run --args='https://github.com/0xRyN/visulog circle runBugFix' here the second argument is for the branch
