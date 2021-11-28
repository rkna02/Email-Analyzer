# mp2 Feedback

## Grade: B

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
|YesYesYesYes|NoNoNoNo|183.89|

## Comments
Good work. Per the rules for using the extension, your grade is capped at a B.
Specifications are strong and well thought out. Good coverage of corner cases especially in UDWInteractionGraph.  RI is good, but may be a good idea to also enforce that no field is null. AF is very well written and easy to understand. Most variable names are clear. Variables with the name "xx", "Etext", "count", and "length" could be replaced with more descriptive names. Methods with the name "reader", and "helper" could also be made more descriptive. Nice checkRep method. Try to avoid directly returning the internal representation of your ArrayLists in the DWInteractionGraph class, this causes rep exposure. Any method longer than 60 lines should be broken up into helper methods. Comments look okay, but be careful in the future to not add too many comments. Descriptive variable names should help the reader understand most of the code.
## Test Results 
### cpen221.mp2.Task1DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|9|
|skipped|0|
|failures|1|
|errors|0|
#### Failed Tests
1. `testLargeUIDs() (java.lang.NegativeArraySizeException: -2147483648)`
### cpen221.mp2.Task1UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|10|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task2DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|15|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task2UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|11|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task3DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|12|
|skipped|0|
|failures|2|
|errors|0|
#### Failed Tests
1. `testBFSGraph2User6ToAllUsers() (org.opentest4j.AssertionFailedError: expected: <[6]> but was: <[6, 5, 4, 7, 2, 9, 10]>)`
1. `testDFSUserDoesNotExist() (java.lang.ArrayIndexOutOfBoundsException: Index 15 out of bounds for length 11)`
### cpen221.mp2.Task3UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|8|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task4DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|6|
|skipped|0|
|failures|4|
|errors|0|
#### Failed Tests
1. `testMaxedBreachedUserCount2() (org.opentest4j.AssertionFailedError: expected: <10> but was: <8>)`
1. `testMaxedBreachedUserCount3() (java.lang.NullPointerException: Cannot invoke "java.util.List.size()" because "CheckSize" is null)`
1. `testMaxedBreachedUserCount5() (org.opentest4j.AssertionFailedError: expected: <1> but was: <2>)`
1. `testMaxedBreachedUserCount6() (org.opentest4j.AssertionFailedError: execution timed out after 150000 ms)`

## Code Quality
```
DWInteractionGraph.java:8:	Possible God Class (WMC=123, ATFD=50, TCC=29.412%)
DWInteractionGraph.java:8:	The class 'DWInteractionGraph' has a Modified Cyclomatic Complexity of 5 (Highest = 23).
DWInteractionGraph.java:8:	The class 'DWInteractionGraph' has a Standard Cyclomatic Complexity of 5 (Highest = 23).
DWInteractionGraph.java:8:	The class 'DWInteractionGraph' has a total cyclomatic complexity of 123 (highest 25).
DWInteractionGraph.java:8:	This class has too many methods, consider refactoring it.
DWInteractionGraph.java:14:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:17:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:18:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:19:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:52:	Avoid throwing raw exception types.
DWInteractionGraph.java:56:	Avoid throwing raw exception types.
DWInteractionGraph.java:74:	Consider using varargs for methods or constructors which take an array the last parameter.
DWInteractionGraph.java:74:	The constructor 'DWInteractionGraph(String, int)' has a cyclomatic complexity of 10.
DWInteractionGraph.java:82:	The initializer for variable 'largestId' is never used (overwritten on lines 108 and 110)
DWInteractionGraph.java:101:	Substitute calls to size() == 0 (or size() != 0, size() > 0, size() < 1) with calls to isEmpty()
DWInteractionGraph.java:104:	Substitute calls to size() == 0 (or size() != 0, size() > 0, size() < 1) with calls to isEmpty()
DWInteractionGraph.java:143:	Ensure that resources like this FileReader object are closed after use
DWInteractionGraph.java:148:	System.out.println is used
DWInteractionGraph.java:157:	System.out.println is used
DWInteractionGraph.java:176:	Position literals first in String comparisons
DWInteractionGraph.java:176:	Position literals first in String comparisons
DWInteractionGraph.java:176:	Position literals first in String comparisons
DWInteractionGraph.java:176:	Position literals first in String comparisons
DWInteractionGraph.java:181:	Found 'DU'-anomaly for variable 'count' (lines '181'-'195').
DWInteractionGraph.java:183:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:185:	Found 'DU'-anomaly for variable 'count' (lines '185'-'195').
DWInteractionGraph.java:186:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:188:	Found 'DU'-anomaly for variable 'count' (lines '188'-'195').
DWInteractionGraph.java:189:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:191:	Found 'DU'-anomaly for variable 'count' (lines '191'-'195').
DWInteractionGraph.java:237:	Avoid unused local variables such as 'i'.
DWInteractionGraph.java:258:	Consider using varargs for methods or constructors which take an array the last parameter.
DWInteractionGraph.java:331:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:351:	Found 'DD'-anomaly for variable 'emailCount' (lines '351'-'354').
DWInteractionGraph.java:354:	Found 'DD'-anomaly for variable 'emailCount' (lines '354'-'354').
DWInteractionGraph.java:399:	Consider using varargs for methods or constructors which take an array the last parameter.
DWInteractionGraph.java:405:	Found 'DD'-anomaly for variable 'reportNum' (lines '405'-'416').
DWInteractionGraph.java:409:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:409:	These nested if statements could be combined
DWInteractionGraph.java:416:	Found 'DD'-anomaly for variable 'reportNum' (lines '416'-'417').
DWInteractionGraph.java:417:	Found 'DD'-anomaly for variable 'reportNum' (lines '417'-'418').
DWInteractionGraph.java:434:	Found 'DD'-anomaly for variable 'theOne' (lines '434'-'457').
DWInteractionGraph.java:437:	Found 'DD'-anomaly for variable 'count1' (lines '437'-'444').
DWInteractionGraph.java:438:	Found 'DD'-anomaly for variable 'count2' (lines '438'-'448').
DWInteractionGraph.java:442:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:444:	Found 'DD'-anomaly for variable 'count1' (lines '444'-'444').
DWInteractionGraph.java:448:	Found 'DD'-anomaly for variable 'count2' (lines '448'-'448').
DWInteractionGraph.java:457:	Found 'DD'-anomaly for variable 'theOne' (lines '457'-'458').
DWInteractionGraph.java:458:	Found 'DD'-anomaly for variable 'theOne' (lines '458'-'459').
DWInteractionGraph.java:475:	The method 'NthMostActiveUser' has a Modified Cyclomatic Complexity of 23.
DWInteractionGraph.java:475:	The method 'NthMostActiveUser' has a Standard Cyclomatic Complexity of 23.
DWInteractionGraph.java:475:	The method 'NthMostActiveUser(int, SendOrReceive)' has a cyclomatic complexity of 25.
DWInteractionGraph.java:475:	The method 'NthMostActiveUser(int, SendOrReceive)' has an NPath complexity of 469, current threshold is 200
DWInteractionGraph.java:477:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:478:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:481:	Found 'DU'-anomaly for variable 'userRanks' (lines '481'-'571').
DWInteractionGraph.java:484:	Found 'DU'-anomaly for variable 'mostSentEmails' (lines '484'-'571').
DWInteractionGraph.java:485:	Found 'DD'-anomaly for variable 'nextMostSentEmails' (lines '485'-'517').
DWInteractionGraph.java:485:	Found 'DU'-anomaly for variable 'nextMostSentEmails' (lines '485'-'571').
DWInteractionGraph.java:485:	The initializer for variable 'nextMostSentEmails' is never used (overwritten on line 517)
DWInteractionGraph.java:486:	Found 'DU'-anomaly for variable 'rankedUsers' (lines '486'-'571').
DWInteractionGraph.java:488:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:498:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:500:	Found 'DD'-anomaly for variable 'mostSentEmails' (lines '500'-'524').
DWInteractionGraph.java:500:	Found 'DU'-anomaly for variable 'mostSentEmails' (lines '500'-'571').
DWInteractionGraph.java:507:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:510:	Found 'DD'-anomaly for variable 'rankedUsers' (lines '510'-'510').
DWInteractionGraph.java:518:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:524:	Found 'DD'-anomaly for variable 'mostSentEmails' (lines '524'-'524').
DWInteractionGraph.java:524:	Found 'DU'-anomaly for variable 'mostSentEmails' (lines '524'-'571').
DWInteractionGraph.java:529:	The initializer for variable 'nextMostReceivedEmails' is never used (overwritten on line 559)
DWInteractionGraph.java:532:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:542:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:549:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:560:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:585:	The method 'BFS(int, int)' has a cyclomatic complexity of 11.
DWInteractionGraph.java:585:	The method 'BFS(int, int)' has an NPath complexity of 240, current threshold is 200
DWInteractionGraph.java:596:	Found 'DU'-anomaly for variable 'max' (lines '596'-'640').
DWInteractionGraph.java:600:	Potential violation of Law of Demeter (object not created locally)
DWInteractionGraph.java:600:	Potential violation of Law of Demeter (object not created locally)
DWInteractionGraph.java:606:	Found 'DU'-anomaly for variable 'Dgraph' (lines '606'-'640').
DWInteractionGraph.java:607:	Found 'DD'-anomaly for variable 'visited' (lines '607'-'612').
DWInteractionGraph.java:612:	Found 'DU'-anomaly for variable 'visited' (lines '612'-'640').
DWInteractionGraph.java:620:	Avoid assignments in operands
DWInteractionGraph.java:688:	Found 'DU'-anomaly for variable 'thereturnone' (lines '688'-'701').
DWInteractionGraph.java:689:	Found 'DD'-anomaly for variable 'havenorNot' (lines '689'-'693').
DWInteractionGraph.java:690:	This for loop can be replaced by a foreach loop
DWInteractionGraph.java:697:	Avoid unnecessary comparisons in boolean expressions
DWInteractionGraph.java:715:	Found 'DU'-anomaly for variable 'array2' (lines '715'-'719').
DWInteractionGraph.java:734:	Avoid using Literals in Conditional Statements
DWInteractionGraph.java:758:	Found 'DU'-anomaly for variable 'seconds' (lines '758'-'780').
DWInteractionGraph.java:760:	Found 'DD'-anomaly for variable 'temp' (lines '760'-'773').
DWInteractionGraph.java:760:	Found 'DU'-anomaly for variable 'temp' (lines '760'-'780').
DWInteractionGraph.java:760:	The initializer for variable 'temp' is never used (overwritten on line 773)
DWInteractionGraph.java:763:	Found 'DD'-anomaly for variable 'end' (lines '763'-'763').
DWInteractionGraph.java:763:	Found 'DU'-anomaly for variable 'end' (lines '763'-'780').
DWInteractionGraph.java:764:	Found 'DD'-anomaly for variable 'endUser' (lines '764'-'767').
DWInteractionGraph.java:767:	Found 'DD'-anomaly for variable 'endUser' (lines '767'-'767').
DWInteractionGraph.java:773:	Potential violation of Law of Demeter (object not created locally)
UDWInteractionGraph.java:4:	Avoid unused imports such as 'java.util.Arrays'
UDWInteractionGraph.java:16:	Possible God Class (WMC=108, ATFD=10, TCC=16.667%)
UDWInteractionGraph.java:16:	The class 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 7 (Highest = 14).
UDWInteractionGraph.java:16:	The class 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 7 (Highest = 14).
UDWInteractionGraph.java:16:	The class 'UDWInteractionGraph' has a total cyclomatic complexity of 108 (highest 15).
UDWInteractionGraph.java:21:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:24:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:25:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:26:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:31:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:34:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:69:	Avoid throwing raw exception types.
UDWInteractionGraph.java:73:	Avoid throwing raw exception types.
UDWInteractionGraph.java:86:	The constructor 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 13.
UDWInteractionGraph.java:86:	The constructor 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 13.
UDWInteractionGraph.java:86:	The constructor 'UDWInteractionGraph(String)' has a cyclomatic complexity of 13.
UDWInteractionGraph.java:86:	The constructor 'UDWInteractionGraph(String)' has an NPath complexity of 461, current threshold is 200
UDWInteractionGraph.java:89:	Ensure that resources like this Scanner object are closed after use
UDWInteractionGraph.java:145:	System.out.println is used
UDWInteractionGraph.java:146:	Avoid printStackTrace(); use a logger call instead.
UDWInteractionGraph.java:165:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:165:	The constructor 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 14.
UDWInteractionGraph.java:165:	The constructor 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 14.
UDWInteractionGraph.java:165:	The constructor 'UDWInteractionGraph(String, int)' has a cyclomatic complexity of 15.
UDWInteractionGraph.java:165:	The constructor 'UDWInteractionGraph(String, int)' has an NPath complexity of 665, current threshold is 200
UDWInteractionGraph.java:168:	Ensure that resources like this Scanner object are closed after use
UDWInteractionGraph.java:227:	System.out.println is used
UDWInteractionGraph.java:228:	Avoid printStackTrace(); use a logger call instead.
UDWInteractionGraph.java:248:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:248:	The constructor 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 11.
UDWInteractionGraph.java:248:	The constructor 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 11.
UDWInteractionGraph.java:248:	The constructor 'UDWInteractionGraph(UDWInteractionGraph, int)' has a cyclomatic complexity of 12.
UDWInteractionGraph.java:248:	The constructor 'UDWInteractionGraph(UDWInteractionGraph, int)' has an NPath complexity of 408, current threshold is 200
UDWInteractionGraph.java:267:	Substitute calls to size() == 0 (or size() != 0, size() > 0, size() < 1) with calls to isEmpty()
UDWInteractionGraph.java:311:	The constructor 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 11.
UDWInteractionGraph.java:311:	The constructor 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 11.
UDWInteractionGraph.java:311:	The constructor 'UDWInteractionGraph(UDWInteractionGraph, List)' has a cyclomatic complexity of 12.
UDWInteractionGraph.java:311:	The constructor 'UDWInteractionGraph(UDWInteractionGraph, List)' has an NPath complexity of 408, current threshold is 200
UDWInteractionGraph.java:330:	Substitute calls to size() == 0 (or size() != 0, size() > 0, size() < 1) with calls to isEmpty()
UDWInteractionGraph.java:366:	The constructor 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 10.
UDWInteractionGraph.java:366:	The constructor 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 10.
UDWInteractionGraph.java:366:	The constructor 'UDWInteractionGraph(DWInteractionGraph)' has a cyclomatic complexity of 10.
UDWInteractionGraph.java:366:	The constructor 'UDWInteractionGraph(DWInteractionGraph)' has an NPath complexity of 204, current threshold is 200
UDWInteractionGraph.java:381:	Substitute calls to size() == 0 (or size() != 0, size() > 0, size() < 1) with calls to isEmpty()
UDWInteractionGraph.java:436:	Found 'DD'-anomaly for variable 'count' (lines '436'-'441').
UDWInteractionGraph.java:441:	Found 'DD'-anomaly for variable 'count' (lines '441'-'441').
UDWInteractionGraph.java:458:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:460:	Found 'DD'-anomaly for variable 'data' (lines '460'-'464').
UDWInteractionGraph.java:461:	Potential violation of Law of Demeter (static property access)
UDWInteractionGraph.java:462:	Potential violation of Law of Demeter (static property access)
UDWInteractionGraph.java:464:	Found 'DD'-anomaly for variable 'data' (lines '464'-'465').
UDWInteractionGraph.java:482:	Found 'DU'-anomaly for variable 'numUsersSet' (lines '482'-'508').
UDWInteractionGraph.java:483:	Found 'DD'-anomaly for variable 'numEmails' (lines '483'-'492').
UDWInteractionGraph.java:483:	Found 'DD'-anomaly for variable 'numEmails' (lines '483'-'495').
UDWInteractionGraph.java:483:	Found 'DD'-anomaly for variable 'numEmails' (lines '483'-'498').
UDWInteractionGraph.java:483:	Found 'DU'-anomaly for variable 'numEmails' (lines '483'-'508').
UDWInteractionGraph.java:484:	Found 'DD'-anomaly for variable 'numUsers' (lines '484'-'503').
UDWInteractionGraph.java:484:	Found 'DU'-anomaly for variable 'numUsers' (lines '484'-'508').
UDWInteractionGraph.java:484:	The initializer for variable 'numUsers' is never used (overwritten on line 503)
UDWInteractionGraph.java:485:	Found 'DD'-anomaly for variable 'data' (lines '485'-'504').
UDWInteractionGraph.java:485:	Found 'DU'-anomaly for variable 'data' (lines '485'-'508').
UDWInteractionGraph.java:492:	Found 'DD'-anomaly for variable 'numEmails' (lines '492'-'492').
UDWInteractionGraph.java:492:	Found 'DD'-anomaly for variable 'numEmails' (lines '492'-'495').
UDWInteractionGraph.java:492:	Found 'DD'-anomaly for variable 'numEmails' (lines '492'-'498').
UDWInteractionGraph.java:495:	Found 'DD'-anomaly for variable 'numEmails' (lines '495'-'492').
UDWInteractionGraph.java:495:	Found 'DD'-anomaly for variable 'numEmails' (lines '495'-'495').
UDWInteractionGraph.java:495:	Found 'DD'-anomaly for variable 'numEmails' (lines '495'-'498').
UDWInteractionGraph.java:498:	Found 'DD'-anomaly for variable 'numEmails' (lines '498'-'492').
UDWInteractionGraph.java:498:	Found 'DD'-anomaly for variable 'numEmails' (lines '498'-'495').
UDWInteractionGraph.java:498:	Found 'DD'-anomaly for variable 'numEmails' (lines '498'-'498').
UDWInteractionGraph.java:504:	Found 'DD'-anomaly for variable 'data' (lines '504'-'505').
UDWInteractionGraph.java:522:	The method 'NthMostActiveUser(int)' has a cyclomatic complexity of 10.
UDWInteractionGraph.java:529:	Found 'DD'-anomaly for variable 'nextMostInteractions' (lines '529'-'553').
UDWInteractionGraph.java:529:	The initializer for variable 'nextMostInteractions' is never used (overwritten on line 553)
UDWInteractionGraph.java:544:	System.out.println is used
UDWInteractionGraph.java:546:	Found 'DD'-anomaly for variable 'rankedUsers' (lines '546'-'546').
UDWInteractionGraph.java:560:	Found 'DD'-anomaly for variable 'mostInteractions' (lines '560'-'560').
UDWInteractionGraph.java:560:	Found 'DU'-anomaly for variable 'mostInteractions' (lines '560'-'569').
UDWInteractionGraph.java:582:	Avoid reassigning parameters such as 'rootUser'
UDWInteractionGraph.java:585:	Found 'DD'-anomaly for variable 'visitedUser' (lines '585'-'591').
UDWInteractionGraph.java:591:	Found 'DU'-anomaly for variable 'visitedUser' (lines '591'-'611').
UDWInteractionGraph.java:602:	Found 'DU'-anomaly for variable 'visitedUser' (lines '602'-'611').
UDWInteractionGraph.java:626:	Found 'DU'-anomaly for variable 'searchedUsers' (lines '626'-'637').
UDWInteractionGraph.java:627:	Found 'DD'-anomaly for variable 'count' (lines '627'-'632').
UDWInteractionGraph.java:629:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:632:	Found 'DD'-anomaly for variable 'count' (lines '632'-'632').
UDWInteractionGraph.java:652:	Avoid unnecessary if..then..else statements when returning booleans
UDWInteractionGraph.java:652:	Potential violation of Law of Demeter (method chain calls)
LoosePackageCoupling	-	No packages or classes specified
```

## Test Coverage
### UDWInteractionGraph
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|10|
|LINE_COVERED|263|
|BRANCH_MISSED|9|
|BRANCH_COVERED|171|
### DWInteractionGraph
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|5|
|LINE_COVERED|323|
|BRANCH_MISSED|13|
|BRANCH_COVERED|185|

## Checkstyle Results
### `DWInteractionGraph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 6 | 17 | `Using the '.*' form of import should be avoided - java.util.*.` |
| 14 | 21 | `Name 'DWInteractions' must match pattern '^_?[a-z][a-zA-Z0-9]*$|_?[A-Z]$'.` |
| 27 |  | `Line is longer than 100 characters (found 103).` |
| 28 |  | `Line is longer than 100 characters (found 106).` |
| 30 |  | `Line is longer than 100 characters (found 116).` |
| 46 | 8 | `File contains tab characters (this is the first instance).` |
| 49 | 54 | `'||' should be on a new line.` |
| 50 | 52 | `'||' should be on a new line.` |
| 79 | 27 | `Name 'Etext' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 115 | 22 | `Name 'i_1' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 116 | 26 | `Name 'j_1' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 140 | 56 | `Name 'Etext' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 170 | 78 | `Name 'Etext' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 176 | 13 | `'if' construct must use '{}'s.` |
| 209 | 27 | `Name 'Etext' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 269 |  | `Line is longer than 100 characters (found 111).` |
| 270 |  | `Line is longer than 100 characters (found 104).` |
| 275 |  | `Line is longer than 100 characters (found 104).` |
| 305 |  | `Line is longer than 100 characters (found 127).` |
| 399 | 18 | `Name 'ReportActivityInTimeWindow' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 433 | 18 | `Name 'ReportOnUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 475 | 5 | `Method NthMostActiveUser length is 97 lines (max allowed is 80).` |
| 475 | 16 | `Name 'NthMostActiveUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 506 | 31 | `Name 'NthRankUsers' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 519 | 78 | `'&&' should be on a new line.` |
| 548 | 31 | `Name 'NthRankUsers' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 561 | 82 | `'&&' should be on a new line.` |
| 585 | 26 | `Name 'BFS' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 598 | 22 | `Name 'IDs' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 604 | 23 | `Name 'BFS' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 606 | 17 | `Name 'Dgraph' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 614 | 9 | `'while' is not followed by whitespace.` |
| 614 | 32 | `'{' is not preceded with whitespace.` |
| 620 |  | `Line is longer than 100 characters (found 115).` |
| 620 | 13 | `'while' is not followed by whitespace.` |
| 620 | 115 | `'{' is not preceded with whitespace.` |
| 626 |  | `Line is longer than 100 characters (found 116).` |
| 628 | 17 | `'while' is not followed by whitespace.` |
| 628 | 40 | `'{' is not preceded with whitespace.` |
| 652 | 59 | `Array brackets at illegal position.` |
| 652 | 61 | `Array brackets at illegal position.` |
| 652 | 76 | `Array brackets at illegal position.` |
| 652 | 90 | `'{' is not preceded with whitespace.` |
| 654 | 9 | `'for' is not followed by whitespace.` |
| 654 | 58 | `'{' is not preceded with whitespace.` |
| 655 | 13 | `'if' is not followed by whitespace.` |
| 655 | 70 | `'{' is not preceded with whitespace.` |
| 659 | 13 | `'if' is not followed by whitespace.` |
| 676 | 26 | `Name 'DFS' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 697 | 24 | `Expression can be simplified.` |
| 713 | 10 | `Name 'DFS1' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 716 | 24 | `Array brackets at illegal position.` |
| 729 | 10 | `Name 'DFSUtil' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 757 | 16 | `Name 'MaxBreachedUserCount' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 758 | 23 | `'3600' is a magic number.` |
| 758 | 27 | `'*' is not followed by whitespace.` |
| 758 | 27 | `'*' is not preceded with whitespace.` |
| 761 | 9 | `'for' is not followed by whitespace.` |
| 768 | 19 | `'else' construct must use '{}'s.` |
| 772 | 27 | `Name 'CheckSize' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
### `SendOrReceive.java`
| Line | Column | Message |
| ---- | ------ | ------- |
### `UDWInteractionGraph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 4 | 8 | `Unused import - java.util.Arrays.` |
| 21 | 35 | `Name 'UDWInteractions' must match pattern '^_?[a-z][a-zA-Z0-9]*$|_?[A-Z]$'.` |
| 42 |  | `Line is longer than 100 characters (found 104).` |
| 44 |  | `Line is longer than 100 characters (found 104).` |
| 63 | 8 | `File contains tab characters (this is the first instance).` |
| 66 | 54 | `'||' should be on a new line.` |
| 67 | 56 | `'||' should be on a new line.` |
| 122 | 13 | `'for' is not followed by whitespace.` |
| 129 | 17 | `'if' is not followed by whitespace.` |
| 204 | 13 | `'for' is not followed by whitespace.` |
| 211 | 17 | `'if' is not followed by whitespace.` |
| 257 | 65 | `'&&' should be on a new line.` |
| 274 | 9 | `'for' is not followed by whitespace.` |
| 281 | 13 | `'if' is not followed by whitespace.` |
| 320 | 67 | `'||' should be on a new line.` |
| 337 | 9 | `'for' is not followed by whitespace.` |
| 344 | 13 | `'if' is not followed by whitespace.` |
| 388 | 9 | `'for' is not followed by whitespace.` |
| 395 | 13 | `'if' is not followed by whitespace.` |
| 439 | 86 | `'||' should be on a new line.` |
| 458 | 18 | `Name 'ReportActivityInTimeWindow' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 481 | 18 | `Name 'ReportOnUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 522 | 16 | `Name 'NthMostActiveUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 541 | 27 | `Name 'NthRankUsers' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 555 | 73 | `'&&' should be on a new line.` |
| 582 | 27 | `Name 'UDWGraphBFS' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 621 | 16 | `Name 'NumberOfComponents' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 629 | 13 | `'for' has incorrect indentation level 12, expected level should be 8.` |
| 630 | 17 | `'if' has incorrect indentation level 16, expected level should be 12.` |
| 631 | 21 | `'if' child has incorrect indentation level 20, expected level should be 16.` |
| 632 | 21 | `'if' child has incorrect indentation level 20, expected level should be 16.` |
| 633 | 17 | `'if rcurly' has incorrect indentation level 16, expected level should be 12.` |
| 634 | 13 | `'for rcurly' has incorrect indentation level 12, expected level should be 8.` |
| 648 | 20 | `Name 'PathExists' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 652 | 13 | `Conditional logic can be removed.` |

