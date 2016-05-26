
1. How did you structure your classes to allow multiple question types in your question array?

I created one abstract class, Question, with a single abstract method,
"isAnswerCorrect(Object answer)". Then I created three more classes, one for each
question type, all extending Question. In each of these classes, I
implemented the "isAnswerCorrect" method, casting answer to a different type
depending on what I needed for that Question type. Each Question type also
had a different constructor depending on what information needed to be stored
in the question object.

2. Did your choice for Question #1 affect how you set up your Controller?

Yes. I used introspection to tell what type of question was being displayed,
and modify how the view was shown.

3. What difficulties did you have with the Controller passing information from the Model to the View?

I wasn't sure how to get the String for a resource ID, but a Piazza post
answered this for me.
