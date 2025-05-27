package com.example.wanderlustwheel

data class DestinationWrapper(
    val vibe: String,
    val mood: String,
    val destination: Destination
)

data class Destination(
    val name: String,
    val image: String,
    val description: String
)
