# Running Plan Builder App

## An app to create a personalized running plan!

### App Description and Purpose


>
>This app is for anyone who is interested in running and wants to be able to build their own running plan. 
>
>I was inspired to build this app because I am passionate about running, and wanted to make it easy for anyone
>to create a running plan and achieve their goals!
>
>Here is a quick overview of how to use this app: 
>
>
>>**Step 1: Set the Date**
>>
>>Choose a date for your trainingWorkout.
>>
>>**Step 2: Choose Your Workout Type**
>>
>>Choose the type of trainingWorkout you want to complete.
>>Here are the types of workouts you can choose from: 
>> - **Speed Workout:** Intervals or fartlek run alternating between an easy and hard pace.
>> - **Long Run:** A long run at an easy pace
>> - **Medium Run:** A medium length run at an easy to moderate pace
>> - **Hill Workout:** A medium length hilly run, throw in a few hill sprints for fun!
>> - **Cross Training:** Try cycling, swimming, or a strength trainingWorkout on this day
>> - **Yoga or Stretch:** Get in some easy movement.
>> - **Rest:** Take it easy and thank your body for all the hard work! 
>>
>>**Step 3: How Far?**
>>
>>Choose a distance for your run:
>> - **Long Runs:** Should be based on your current longest run to date, increase this distance slightly every week
>> - **Medium runs:** Should be about 60% of the distance of your long run
>> - **Speed Workouts:** These can vary, but should be within 40-60% of your long run
>> - **Hills:** Should be about 40-60% of your long run
>>
>>**Step 5: Build Your Plan!**
>>
>>You should try to aim for at least 3 runs a week including 
>>a long run, a medium run (or hills), and a speed trainingWorkout. 
>>Fill in your other days with cross training, yoga, and at least one rest day. 
>>Don't forget to add a race, so you have something to work towards!
>>
>>**Step 5: Get To It!**
>>
>>You can now start to execute on your plan. 
>>Mark off your workouts as complete as you go along and leave a comment on how the run went.
>>These comments will be helpful to look back on as you evaluate the success of your plan. 
>>Pay attention to the details!
>>Any pains that persist the day after your run is your body telling you to take a break! 
>>
>>
>>**A Note on Pace:**
>>
>>Here is a little guide to choosing a pace for your runs:
>>
>> **Easy Pace:** This is a pace that is comfortable and easy to talk through.
>> This pace is ideal for long runs and your "down" intervals in speed workouts. 
>>
>> **Moderate Pace:** Sometimes called your "Tempo Pace", this should be more challenging
>> than your Easy pace, it should be getting more difficult to hold a conversation. 
>> Throw this pace into your medium runs and speed intervals. 
>>
>> **Hard Pace:** Sometimes called your "Race Pace". This should be pushing to your limits. 
>> It should be difficult to hold a conversation through this pace.
>> This pace is mainly for intervals in your speed workouts. Choose a time or distance that you can maintain. 
>>
>>**NOW GO CRUSH THOSE GOALS!!!!**


___

### User Stories

> - As a user I want to add multiple workouts or races to my running plan.
> - As a user I want to mark a trainingWorkout or race as complete. 
> - As a user I want to leave a comment on a trainingWorkout.
> - As a user I want to view a trainingWorkout.
> - As a user I want to view all the workouts on my running plan. 
> - As a user I want to be able to save my running plan. 
> - As a user I want to be able to load my running plan from a file. 
> - As a user I want to be prompted to save my plan before quitting. 
> - As a user I want to be prompted to load my plan upon entering the app. 


### Phase 4: Task 2
> **Option 2: Include a type hierarchy**
> My project includes a type hierarchy where the Workout class is an abstract class and both the TraningWorkout class
> and the RaceWorkout class extend Workout. TrainingWorkout and RaceWorkout both override and implement their own 
> versions of the workoutToString() method and the toJson() method. 
>
> **Option 3: Make appropriate use of the Map Interface**
> My project also includes an enumeration WorkoutType. Included in this class is a hashmap that maps the 
> WorkoutTypes in the enumeration to an integer key. This integer key is used in the main and ui classes to easily 
> retrieve the WorkoutType from the enumeration. 


### Phase 4: Task 3
> My UML class diagram indicates that there is some unnecessary associations between RunPlanGui, WorkoutTable, and 
> various classes in the model and persistence packages. If I had more time to work on the design of this project 
> I would implement the observer pattern to reduce these associates. The observers could listen for certain actions in 
> the gui and then implement the necessary changes within the appropriate classes. There is also a bi-directional 
> relationship between Welcome Panel and Main gui that could be eliminated by using the observer pattern. 
