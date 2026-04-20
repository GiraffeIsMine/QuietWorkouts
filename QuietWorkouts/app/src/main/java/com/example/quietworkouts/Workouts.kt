package com.example.quietworkouts

object Workouts {

    data class Workout(val title: String, val description: String)

    val list: List<Workout> = listOf(
        Workout("Wall Sit", "Hold a wall sit for 45 seconds. Rest 15s. Repeat 3x."),
        Workout("Plank", "Hold a forearm plank for 45 seconds. Rest 20s. Repeat 3x."),
        Workout("Push-Ups", "3 sets of 12 push-ups. Rest 30s between sets."),
        Workout("Slow Squats", "3 sets of 15 slow bodyweight squats."),
        Workout("Reverse Lunges", "3 sets of 10 reverse lunges per leg."),
        Workout("Calf Raises", "3 sets of 20 slow calf raises."),
        Workout("Glute Bridges", "3 sets of 15 glute bridges."),
        Workout("Bird Dogs", "3 sets of 10 per side."),
        Workout("Dead Bugs", "3 sets of 10 per side."),
        Workout("Tricep Dips", "3 sets of 12 dips off a chair or couch."),
        Workout("Side Plank", "Hold each side for 30 seconds. Repeat 2x per side."),
        Workout("Leg Raises", "3 sets of 12 lying leg raises."),
        Workout("Russian Twists", "3 sets of 20 total."),
        Workout("Superman Hold", "3 sets of 20-second holds."),
        Workout("Wall Push-Ups", "3 sets of 15 wall push-ups."),
        Workout("No-Jump Burpees", "3 sets of 8. Step back, step in, stand."),
        Workout("Slow Mountain Climbers", "3 sets of 20 total."),
        Workout("Downward Dog Flow", "Downward Dog -> Plank -> Cobra, 10 rounds."),
        Workout("Warrior II Hold", "Hold each side for 30 seconds. Repeat 2x."),
        Workout("Chair Pose", "Hold for 30 seconds. Rest 15s. Repeat 4x."),
        Workout("Single-Leg Deadlift", "3 sets of 10 per leg."),
        Workout("Step-Back Lunge to Knee Drive", "3 sets of 10 per side."),
        Workout("Hollow Body Hold", "3 sets of 20-second holds."),
        Workout("Pike Push-Ups", "3 sets of 8."),
        Workout("Isometric Squat Hold", "Hold a low squat for 30 seconds. Repeat 4x.")
    )

    fun random(): Workout = list.random()
}
