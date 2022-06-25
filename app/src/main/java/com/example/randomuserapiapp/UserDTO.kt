package com.example.randomuserapiapp

data class UserDTO(
    val results: List<Results>
)

data class Results(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val phone: String,
    val picture: Picture
)

data class Name(
    val first: String,
    val last: String
)

data class Location(
    val city: String,
    val country: String,
    val street: Street
)

data class Street(
    val number: Int,
    val name: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)