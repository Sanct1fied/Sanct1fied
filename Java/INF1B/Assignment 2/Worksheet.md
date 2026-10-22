- Name: Joshua Jones
- Student ID: s2662423
- Tutorial group: 1A
- Tutor: Thian Iskandar
- Date: 2026-03-11


# Superstitious Hotel Elevator #

# Target audience #

**My** target audience is people with **dyslexia**, as I have close friends 
with 
dyslexia.

# Prerequisite knowledge #

**You need** to have some basic knowledge of Java, how to create **for loops,
if 
statements, ArrayLists** as well as create additional methods to assist with 
the algorithm. You may also wish to learn more about Java methods e.g 
**toCharArray()**.

# Learning outcomes #

**Hopefully** you will learn how to create for loops, while loops, switches and 
if statements

# Introduction #
**In China**, the number 4 is considered unlucky since it's pronunciation is 
very similar to the word "Death".
Similarly, 13 is considered unlucky in the western world.

**In some** elevators in China omit the digit 4, and the number 13 from their 
elevator buttons.

**This** worksheet will walk you through how to create the buttons for a hotel 
with N floors.
This means avoiding all numbers with the digit 4, and the number 13.

## Getting Started ##
**In order** to get the buttons we first must know how many floors our hotel is!
To do this, we are going to use the **arguments**.

### Step 1: Creating our class <br> ###
**We start** how we would any other program. <br>
`public class`
<br> **Then** followed by our class name, which for this worksheet we will just 
call `Elevator()`
### Step 2: Creating our main method ###
**Like normal**, we just do `public static void main()` except to get our 
arguments and continue with our construction of the elevator we need to have 
`(String[] args)` in the brackets of `main()`, you may rename `args` to 
whatever you wish.
**Now** that we have the start of our file written we can begin with 
constructing our main method.
### Step 3: Writing the code <br> ###
**In order** to do what we want, we need an **Integer** that represents the 
number of floors in the elevator. Currently the arguments are a list of Strings.
**To fix this**, we need to **convert** this into an integer.
We know there will only be one argument, so we need `args[0]`
<br>
<br> We will need to convert `args[0]` into an integer, this is called 
**parsing**.
We'll create a new integer N: <br>
`int N = 0` <br>
then we can set `N = Integer.parseInt(args[0])`<br>
Now we have N, which we can use to figure out which floors need to be omitted.
### Step 4: Conditions ###
**To proceed**, we will lay out our rules for the elevator.<br>
There are 2:<br>
- No number can contain the digit 4
- No number = 13 <br>
<!---->
The second rule is very easy to sort out.
<br> We can set `int x` to be the floor we are currently on.
`x =! 13` or in an if statment this looks like: <br>
`if (x == 13) {...}` we can then write some code that skips over it but also 
maintains the correct number of total floors. We will come back to this.
<br>
<br> **For the first rule** it's a bit more complicated, as we have to check 
each number to ensure there are no 4s. 
Since we have to do this for *almost* all numbers, I think it's best if we 
write a **method** to do this for us.
`public static boolean numberChecker(int X){}` should do.
<br> **Then** we have to find a way to check each digit, we can do this by 
splitting the number up. Unfortunately we have to do this while it's a 
string: <br>
`String number = String.valueOf(X);` lets us store the number X as a 
**string**. 
<br>
**After that** `char[] result = number.toCharArray()` will give us each digit 
as a 
single character element in an **array** called **result**. For example if `X = 
256` 
then the resulting **array** would be `{"2","5","6"}` which is exactly what we 
want.
<br> we can then just check to see if each of these is a 4 with a simple for 
loop.
<br> <br> `for (int i = 0; i < result.length(); i++){
if (String.valueOf(result[i]) == 4) {return true;}}` This small piece of code 
will check each digit to see if it is a 4, and if it is then it will return 
**True** for the method. You can see this in the top right of the **flowchart**
<br> <br> **Going back** to our bit of code earlier `if (x==13){return true;}
` we can add in the curly brackets `return true`. Now that our conditions will have 
been checked, we can continue to write the rest of our code.
Since we don't want 0 either I'm also going to add a **statement** to avoid 
that 
`if (x==0){return true}`
### Wrtiting the rest of the code ###
**Now** that we have a way to omit the words we don't want, now we need a 
way to figure out which numbers we *do* want. To do this, a **for loop** with an **if 
statement** will suffice. <br>
`for (i = 1;i < N;i++){`<br>
`if (numberChecker(i)){`<br>
`N++; Continue;`<br>
this makes it so that if the number is 13 or contains a 4, it will add a 
floor and then skip the floor we dont want. After this is done what we can 
do is either print it out, or add it to an **ArrayList**. <br> <br> By 
adding it 
to an array list, we can later print it out in the format they are asking 
for in the challenge.
**to do this**, before the for loop I **initialise** it with this line: <br>
`ArrayList<Integer> floors = new ArrayList<Integer>();`
then inside the for loop but outside the if statement I add this line:<br>
`floors.add(i)` This will add the floor number to the arraylist **if** it is 
**not** caught by the method we wrote earlier.

### Printing the buttons ###
**Now that** we've got all the floor numbers, we need to print the buttons.
To do this, guess what? a for loop will do. However it's going to be a 
little different to normal since we want to print them in the correct order. 
<br> To do this, we will initialise the loop like so: <br>
`for (int j = floors.size()-1; j > 0; j=j-2){}`<br>
**This means** that we are starting at the top, but minus one so we aren't 
adding an extra floor, then we go until 0. <br> <br> **You might** have caught 
that 
we're 
decrementing by -2 instead of 1 like usual. This is because we are going to 
print 2 buttons per line, with a horizontal tab in between. like so:
`System.out.print(floors.get(j-1));`<br>
`System.out.print("    ");`<br>
`System.out.println(floors.get(j));`<br> This can also be seen at the bottom 
of the **flowchart**.
This prints the floor, the horizontal tab, then the next floor.
Then the loop repeats and prints the rest of the floors.

### Final Touches ###
**Since** we only want the number of floors, we definitely don't want them 
inputting anything that's not a number. To do this we will use try and catch.
<br> <br> by doing `try { Integer.parseint(args[0])}` it will try to **parse** 
to integer and **if** it succeeds, we can continue as normal. if it fails we 
**catch** it and **throw** an error like so: `catch (Exception e){System.println
("Invalid input")}
`<br> we can give any reason by changing the print statement or we can even 
change the error that is thrown, for example in our case it would be a 
`NumberFormatException` as we are trying to parse an integer from something that 
is not an integer.
<br> Addtionally we should handle if the number is **negative** or less than 2. 
as our program currently does not work in these cases. for input 1, we can 
just add an if statement that prints -1 as the first floor. like so: `if 
(floors.size()<2){System.out.println(-1);}`<br> <br> 
Similarly if the input is negative, then we can just add a little bit after the 
try/catch `if (N < 0){throw new Exception("Number of floors cannot be 
negative.");}` This immediately stops the code and outputs an **exception**, to 
do this we have to add `throws Exception` to the end of our method 
definition like so: `public static void main(String[] args) throws Exception {`


# Original challenge question from CodeGolf #

[Short link to CodeGolf challenge](https://codegolf.stackexchange.com/q/68866/superstitious-hotel-elevator "tooltip text")

<STYLE>
* { /* Don't leave any empty lines or IntelliJ might not render correctly */
  /* Text size */
  font-size:   1.1rem;
  /*font-size:   1.2rem;*/
  /* Zenburn dark theme */
  background-color: #2A252A;
  color:            #D5DAD5;
  /* One Dark theme */
  /*background-color: #282C34;
  color:            #ABB2BF;*/
  /* white-ish on dull blue-ish */
  /*background-color: DarkSlateGray;
    color:            AntiqueWhite;*/
  /* white on black */
  /*background-color: black;
  color: white;*/
  /* black on white */
  /*background-color: white;
  color: black;*/
  /* nearly black on bright yellow */
  /*background-color: #FFFFAA;
  color:            #080808;*/
  /* black on bright blue */  
  /*background-color: #99CCFF;
  color:            black;*/
}
body {
  /* width of the text column */
  width: 80%;
  /* line spacing */
  line-height: 250%;
  /*line-height: 200%;*/
  /* Font styles: */
  /* Default sans serif */
  /*font-family: sans-serif;*/
  /* Default serif */
  font-family: serif;
  /* Specific font with generic fall-back */
  /* font-family: "Calibri Light", sans-serif; */
  /*font-family: "OpenDyslexic", sans-serif;*/
}
pre,
code,
pre code {
  /* line spacing */
  line-height: 150%;
  /* Default monospace */
  font-family: monospace;
  /* Specific fixed-width font with generic fall-back */
  /*font-family: "Consolas", monospace;*/
  /*font-family: "OpenDyslexicMono", monospace;*/
}
ol,
ol ol,
ol ol ol { /* Nested lists all use decimal numbering */
  list-style-type: decimal;
}
em {
  /* if you want underlining instead of italics */
  /*font-style: normal;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  padding-bottom:      2px;*/
  text-decoration-skip-ink: auto;
}
h2 { /* Put a horizontal line above major headings to assist screen viewing */
  border-top:  1px solid #D5DAD5;
  margin-top:  80px;
  padding-top: 20px;
  }
</STYLE>
