# CLI-RPG-maker

Anyone can make an RPG! Kind of. It’s more like interactive fiction.
This is a program that parses text files containing character dialogue into lines which can be progressively displayed 
in the CLI like a simple text-only RPG. Branching can also occur based on the collected user response.

To create your story, you have to provide .txt files for the program to read from, formatted in a particular way.

### Basic dialogue parsing
Modify the constant file path in Main.java to reflect the appropriate text file you want to start reading from.
When the application starts, the program reads the file line by line, displaying the next line when the user presses `Enter`/`Return`.

You can prefix each line with a character's name to indicate the speaker of a line of dialogue.
Example txt format:
```
[character A] Hello. How are you?
[character B] I am fine. What about you?
```
Program displays:
```
character A: Hello. How are you?
character B: I am fine. What about you?
```

### Saving speaker acronyms
This feature allows parsing of commonly used character names in an abbreviated form.
•	User can store commonly used character names in a separate file and map them to a single character (A,B,C,...) or a shortened acronym
•	This is to save typing time when scripting the dialogues
•	Also, in case a character’s name is changed, the user does not need to manually replace all the fields in all the text files

To use acronyms, modify the constant file path in Main.java to the .txt file containing speaker acronyms.
The acronyms should be in the following format:
```
A:Alice
B:Bob
```
Then, whenever [A] and [B] are used in the file dialogues, the program will instead print Alice: and Bob:.

### Multi-line text
To display multiple lines at once, indicate <lines to display at once> in a separate line above the lines to be displayed.
Example txt format:
```
<2>
line 1
line 2
```
Program displays both the lines at once, without user having to press `Enter`/`Return`:
```
line 1
line 2
```

### Branching
To branch to different lines in the txt file based on the collected user response, use the following format in the .txt file:
`(branch) choice_1 choice_2 choice_3 ...`
Example txt format:
```
(branch) yes no
yes
[A] some dialogue
some text
endbranch
no
some text
more text
endbranch
text after branch
```
If user enters `yes`, program displays:
```
A: some dialogue
some text
text after branch
```
Note: as of now, choices have to be single words separated by a single whitespace.

### Jumping to another file
If a choice results in a big branch, you can store the branch in another text file, and make the program jump to that file.
Example txt format:
```
(goto) branch1
```
This causes the program to start reading branch1.txt. When it reaches the end of the file, it will return to the original file and 
continue reading from where it left off.

Work-in-progress:
- Chapter selection menu
- Loading and saving game progress
- Start menu
- Build and file encryption issues
