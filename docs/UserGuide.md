---
layout: page
title: User Guide
---
* Table of Contents
{:toc}

## Introduction

HelloFile is a desktop app for managing files, optimised for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). By tagging frequently used files/folders with a short nickname, you
will be able to manage and access your files with ease.

--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Installation
1. Ensure you have [Java 11](https://www.java.com/en/download/) or above installed in your computer. 
2. Download the latest HelloFile.jar [here](https://github.com/AY2021S1-CS2103T-F12-1/tp/releases/tag/v1.2).

### Quick start
1. Move HelloFile.jar to the folder you intend to use as the home folder for HelloFile.
2. Double-click the HelloFile.jar icon to start the application. Alternatively, run the command `java -jar HelloFile.jar` in the command line.
3. Type `help` into the command box, followed by pressing the `Enter` key to view the supported features. Alternatively, click the help button in the menu bar to access our webpage.

### User Interface
![Illustration](images/Illustrations.PNG)

### Basic workflow
1. Tag important files with the `tag` command for ease of access.
2. When trying to access tagged files, instead of navigating to the file location, simply use the `open` command to access the required files.
3. To find the location of tagged files, use the `show` command to get the file path of the file.
4. To exit the application, either close the application window, or use the `exit` command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add t/TAG`, `TAG` is a parameter, such as in the case `add t/Myfile`.

* Items in square brackets are optional.<br>
  e.g `t/TAG [f/FILE_PATH]` can be used as `t/Myfile f/C:\Users` or as `t/Myfile`.

* Parameters can be in any order.<br>
  e.g. if the command specify `t/TAG f/FILE_PATH`, `f/FILE_PATH t/TAG` is also an acceptable command.

* Every tag name must be unique, but a file can have multiple tags.

* Every tag name is case-sensitive. e.g tag name `notes` is different from tag name `Notes`.

* Only the `cd` and `tag` command accepts relative file path, all other commands require absolute file path.

</div>

### Viewing help : `help`

Displays help for all commands.

Format: `help`

### Adding a tag with filepath : `tag`

Tags a file with a unique tag name.

Format: `tag t/TAG_NAME f/FILE_PATH [l/LABEL]`

Examples:
* (Without label) `tag t/Users f/C:\Users`
* (With label) `tag t/Users f/C:\Users l/Important`
* (With relative path) `tag t/Users f/.\Users`

### Displaying information of a tagged file : `show`

Displays the information of the tagged file.

Format: `show t/TAG_NAME`

Examples:
* `show t/my_research`
* `show t/notes`

### Accessing a tagged file : `open`

Opens the file specified by the unique tag name.

Format: `open t/TAG_NAME`

Examples:
* `open t/my_research`
* `open t/notes`

### Removing a tag : `untag`

Removes the tag from the list of tags.

Format: `untag t/TAG_NAME`

Examples:
* `untag t/notes`

### Renaming a tag : `retag`

Renames a tag.

Format: `retag o/OLD_TAG_NAME t/NEW_TAG_NAME`

Examples:
* `retag o/notes t/secret`

### Finding a tag : `find`

Finds a tag by its keyword (can be tag name and/or label).

Format: `find KEYWORD`
        
Examples:
* `find notes`

### Changing current directory : `cd`

Changes the current directory of the HelloFile internal File Explorer.

Format 1: `cd f/ABSOLUTE_FILE_PATH`

Format 2: `cd ./RELATIVE_FILE_PATH`

Format 3: `cd ../`

Examples:
* `cd f/C:\Users`
* `cd ./tp`
* `cd ../`

### Listing all tags : `ls`

Lists all managed tags.

Format: `ls`

### Clearing all tags : `clear`

Clears the list of all tags.
**Warning: All tags will be deleted!**

Format: `clear`

### Exiting the application : `exit`

Exits the application.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ
**Q**: What if the name or the directory of the file I tagged is changed? Can I still access the file using HelloFile?<br>
**A**: No. HelloFile is currently unable to track a file if its name or directory is changed. You will need to delete the old tag and retag the file.

**Q**: What is the structure of the application?<br>
**A**: You can find it in the Developer Guide [here](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/docs/DeveloperGuide.md)

**Q**: How do I report a bug?<br>
**A**: Please create a new issue on [this](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues) webpage.

**Q**: Can I contribute to the project?<br>
**A**: Sorry, as this is a school project, we are not accepting any contributors at this period.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Tag** | `tag t/TAG_NAME f/FILE_PATH , [l/LABEL]` <br> e.g., `tag t/newTag f/c:/myfolder/file.jpg l/MyFile` or `tag t/newTag f/c:/myfolder/file.jpg` 
**Show** | `show t/TAG_NAME`
**Untag** | `untag t/TAG_NAME`
**Retag** | `retag o/OLD_TAG_NAME t/NEW_TAG_NAME` <br> e.g., `retag o/mytag t/newtag`
**Find** | `find KEYWORD` <br> e.g., `find newtag`
**Open** | `open t/TAG_NAME`
**List** | `ls`
**Cd to an absolute file Path**| `cd f/ABSOLUTE_FILE_PATH` <br> e.g., `cd f/C:\Users`
**Cd to a relative file Path**| `cd ./RELATIVE_FILE_PATH` <br> e.g., `cd ./project01`
**Cd to the parent file Path**| `cd ../`
**Clear** | `clear`
**Help** | `help`
**Exit** | `exit`
