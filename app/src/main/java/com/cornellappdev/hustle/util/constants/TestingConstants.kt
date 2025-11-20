package com.cornellappdev.hustle.util.constants

import com.cornellappdev.hustle.data.model.services.Service
import com.cornellappdev.hustle.data.model.user.User

val TEST_RECENT_SEARCHES = listOf(
    "nails",
    "photos",
    "haircuts",
    "tutors",
    "resumes",
    "art",
    "programming",
    "moving",
    "cleaning",
    "cooking"
)

val TEST_SERVICES = listOf(
    Service(
        id = 1,
        name = "Dreamy fall grad photo session",
        category = "Photo",
        minimumPrice = 67.0,
        rating = 4.1,
        isFavorited = false,
        user = User(
            firebaseUid = "",
            email = "",
            displayName = "Jane Doe",
            photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocKJrWoJxoOC0CoGv76ocYAULrRz9dAlfxMOiTb78E5dXH1VVo_j=s576-c-no"
        ),
        displayImageUrl = "https://news.cornell.edu/sites/default/files/styles/full_size/public/06_2023_1114_sh_005-n_1.jpg?itok=E3ecxgYl",
        priceUnit = "/hour",
    ),
    Service(
        id = 2,
        name = "Pretty Nail Painting Service",
        category = "Beauty",
        minimumPrice = 10.50,
        rating = 4.5,
        isFavorited = false,
        user = User(
            firebaseUid = "",
            email = "",
            displayName = "Lauren Ah-Hot",
            photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocKJrWoJxoOC0CoGv76ocYAULrRz9dAlfxMOiTb78E5dXH1VVo_j=s576-c-no"
        ),
        displayImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhJqCvqqrdsOc9pS4HZiHvoA-gtow5VeUF0g&s",
        priceUnit = "/hour",
    ),
    Service(
        id = 3,
        name = "Cheap Haircuts for Males",
        category = "Beauty",
        minimumPrice = 20.0,
        rating = 4.2,
        isFavorited = false,
        user = User(
            firebaseUid = "",
            email = "",
            displayName = "Joshua Dirga",
            photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocKJrWoJxoOC0CoGv76ocYAULrRz9dAlfxMOiTb78E5dXH1VVo_j=s576-c-no"
        ),
        displayImageUrl = "https://s3-media0.fl.yelpcdn.com/bphoto/1KwtwltxdEYVz4TIHAzaow/1000s.jpg"
    ),
    Service(
        id = 4,
        name = "Computer Science Tutoring",
        category = "Lessons",
        minimumPrice = 15.0,
        rating = 3.5,
        isFavorited = false,
        user = User(
            firebaseUid = "",
            email = "",
            displayName = "Andrew Cheung",
            photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocKJrWoJxoOC0CoGv76ocYAULrRz9dAlfxMOiTb78E5dXH1VVo_j=s576-c-no"
        ),
        displayImageUrl = "https://www.engineering.cornell.edu/wp-content/uploads/2025/03/Duffield-Atrium-Students-Header-02.jpg",
        priceUnit = "/hour",
    ),
    Service(
        id = 5,
        name = "Really Awesome Resume Review and Editing Session",
        category = "Professional",
        minimumPrice = 35.0,
        rating = 4.1,
        isFavorited = false,
        user = User(
            firebaseUid = "",
            email = "",
            displayName = "Jane Doe",
            photoUrl = "https://lh3.googleusercontent.com/a/ACg8ocKJrWoJxoOC0CoGv76ocYAULrRz9dAlfxMOiTb78E5dXH1VVo_j=s576-c-no"
        ),
        displayImageUrl = "https://www.engineering.cornell.edu/wp-content/uploads/2024/10/AU-CornellEngineering-March082023-181.jpg",
        priceUnit = "/hour",
    ),

)