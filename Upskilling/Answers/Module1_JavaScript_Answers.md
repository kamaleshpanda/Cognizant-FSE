# Module 1 - JavaScript Exercises - Answers

## Exercise 1: JavaScript Basics & Setup

Set up your portal to use JavaScript.

**index.html:**
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Community Portal</title>
</head>
<body>
    <h1>Community Event Portal</h1>
    <p>Check the console for a message!</p>

    <script src="main.js"></script>
</body>
</html>
```

**main.js:**
```javascript
console.log("Welcome to the Community Portal");

window.onload = function() {
    alert("Page is fully loaded! Welcome!");
};
```

---

## Exercise 2: Syntax, Data Types, and Operators

Storing event details with proper data types.

```javascript
const eventName = "Tech Meetup";
const eventDate = "2025-06-15";
let availableSeats = 50;

// using template literal to combine info
let eventInfo = `Event: ${eventName} | Date: ${eventDate} | Seats: ${availableSeats}`;
console.log(eventInfo);

// registering a user decreases seats
availableSeats--;
console.log("After 1 registration, seats left: " + availableSeats);

// another registration
availableSeats--;
console.log("After 2nd registration, seats left: " + availableSeats);
```

`const` is used for things that don't change (event name and date). `let` is for things that can change (seats go down when people register).

---

## Exercise 3: Conditionals, Loops, and Error Handling

Only show valid upcoming events that have seats available.

```javascript
let events = [
    { name: "Tech Meetup", date: "2025-08-10", seats: 30 },
    { name: "Music Night", date: "2024-01-15", seats: 0 },
    { name: "Art Workshop", date: "2025-09-20", seats: 15 },
    { name: "Food Fest", date: "2024-06-01", seats: 50 },
    { name: "Coding Bootcamp", date: "2025-12-05", seats: 0 }
];

let today = new Date();

console.log("Upcoming events with available seats:");

events.forEach(function(event) {
    let eventDate = new Date(event.date);

    if (eventDate > today && event.seats > 0) {
        console.log(`${event.name} - ${event.date} - ${event.seats} seats left`);
    } else {
        // skip past events or events with no seats
    }
});

// try-catch for registration
function registerForEvent(event) {
    try {
        if (event.seats <= 0) {
            throw new Error("No seats available for " + event.name);
        }
        event.seats--;
        console.log("Registered successfully for " + event.name);
    } catch (error) {
        console.log("Registration failed: " + error.message);
    }
}

registerForEvent(events[0]); // should work
registerForEvent(events[1]); // should fail - no seats
```

---

## Exercise 4: Functions, Scope, Closures, Higher-Order Functions

Creating reusable functions for event operations.

```javascript
let allEvents = [];

// function to add event
function addEvent(name, category, seats) {
    allEvents.push({ name: name, category: category, seats: seats });
    console.log("Added: " + name);
}

// function to register user
function registerUser(eventName) {
    let event = allEvents.find(e => e.name === eventName);
    if (event && event.seats > 0) {
        event.seats--;
        console.log("Registered for " + eventName + ". Seats left: " + event.seats);
    } else {
        console.log("Cannot register for " + eventName);
    }
}

// higher-order function with callback for filtering
function filterEventsByCategory(category) {
    return allEvents.filter(e => e.category === category);
}

// closure to track registrations per category
function createCategoryTracker(category) {
    let count = 0;
    return function() {
        count++;
        console.log(category + " registrations so far: " + count);
        return count;
    };
}

// usage
addEvent("Rock Concert", "music", 100);
addEvent("Jazz Night", "music", 50);
addEvent("Code Jam", "tech", 30);

registerUser("Rock Concert");

let musicEvents = filterEventsByCategory("music");
console.log("Music events:", musicEvents);

let trackMusic = createCategoryTracker("music");
trackMusic(); // 1
trackMusic(); // 2
```

---

## Exercise 5: Objects and Prototypes

Modeling events as objects.

```javascript
// Event constructor
function Event(name, category, seats, date) {
    this.name = name;
    this.category = category;
    this.seats = seats;
    this.date = date;
}

// add method to prototype
Event.prototype.checkAvailability = function() {
    if (this.seats > 0) {
        return this.name + " has " + this.seats + " seats available.";
    } else {
        return this.name + " is fully booked.";
    }
};

let event1 = new Event("Tech Summit", "tech", 25, "2025-07-10");
let event2 = new Event("Music Fest", "music", 0, "2025-08-15");

console.log(event1.checkAvailability());
console.log(event2.checkAvailability());

// list all keys and values
console.log("\nEvent 1 details:");
for (let [key, value] of Object.entries(event1)) {
    console.log(key + ": " + value);
}
```

---

## Exercise 6: Arrays and Methods

Managing the events array with array methods.

```javascript
let events = [
    { name: "Jazz Night", category: "music", seats: 40 },
    { name: "Code Workshop", category: "tech", seats: 20 }
];

// add new event using push
events.push({ name: "Rock Show", category: "music", seats: 100 });
events.push({ name: "Baking Workshop", category: "food", seats: 15 });
console.log("Total events: " + events.length);

// filter only music events
let musicEvents = events.filter(e => e.category === "music");
console.log("Music events:", musicEvents);

// map to create display cards
let displayCards = events.map(e => `${e.category.toUpperCase()}: ${e.name} (${e.seats} seats)`);
console.log("Display cards:");
displayCards.forEach(card => console.log("  " + card));
```

---

## Exercise 7: DOM Manipulation

Displaying events dynamically on the webpage.

```html
<div id="eventContainer"></div>

<script>
    let events = [
        { name: "Tech Meetup", date: "2025-07-10", seats: 25 },
        { name: "Music Night", date: "2025-08-15", seats: 50 },
        { name: "Art Workshop", date: "2025-09-20", seats: 15 }
    ];

    let container = document.querySelector("#eventContainer");

    events.forEach(function(event) {
        let card = document.createElement("div");
        card.className = "event-card";
        card.innerHTML = `
            <h3>${event.name}</h3>
            <p>Date: ${event.date}</p>
            <p>Seats: <span class="seat-count">${event.seats}</span></p>
            <button onclick="register(this, '${event.name}')">Register</button>
        `;
        container.appendChild(card);
    });

    function register(button, eventName) {
        let seatSpan = button.parentElement.querySelector(".seat-count");
        let seats = parseInt(seatSpan.textContent);
        if (seats > 0) {
            seatSpan.textContent = seats - 1;
            alert("Registered for " + eventName + "!");
        } else {
            alert("No seats left!");
        }
    }
</script>
```

---

## Exercise 8: Event Handling

Adding interactive buttons and filters.

```html
<input type="text" id="searchBox" placeholder="Search events..." onkeydown="searchEvent(event)">

<select id="categoryFilter" onchange="filterByCategory()">
    <option value="all">All Categories</option>
    <option value="music">Music</option>
    <option value="tech">Tech</option>
    <option value="food">Food</option>
</select>

<div id="eventList"></div>

<script>
    let events = [
        { name: "Rock Concert", category: "music" },
        { name: "Code Jam", category: "tech" },
        { name: "Food Fest", category: "food" },
        { name: "Jazz Night", category: "music" }
    ];

    function displayEvents(list) {
        let container = document.getElementById("eventList");
        container.innerHTML = "";
        list.forEach(e => {
            let div = document.createElement("div");
            div.textContent = e.name + " (" + e.category + ")";
            let btn = document.createElement("button");
            btn.textContent = "Register";
            btn.onclick = function() {
                alert("Registered for " + e.name);
            };
            div.appendChild(btn);
            container.appendChild(div);
        });
    }

    function filterByCategory() {
        let selected = document.getElementById("categoryFilter").value;
        if (selected === "all") {
            displayEvents(events);
        } else {
            displayEvents(events.filter(e => e.category === selected));
        }
    }

    function searchEvent(e) {
        if (e.key === "Enter") {
            let query = document.getElementById("searchBox").value.toLowerCase();
            let results = events.filter(ev => ev.name.toLowerCase().includes(query));
            displayEvents(results);
        }
    }

    displayEvents(events);
</script>
```

---

## Exercise 9: Async JS, Promises, Async/Await

Fetching event data from a mock API.

```javascript
// Using .then() and .catch()
function fetchEventsWithPromise() {
    fetch("https://jsonplaceholder.typicode.com/posts?_limit=5")
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log("Events loaded:");
            data.forEach(item => console.log("- " + item.title));
        })
        .catch(function(error) {
            console.log("Error fetching events: " + error);
        });
}

// Same thing with async/await
async function fetchEventsAsync() {
    let spinner = document.getElementById("spinner");
    spinner.style.display = "block"; // show loading

    try {
        let response = await fetch("https://jsonplaceholder.typicode.com/posts?_limit=5");
        let data = await response.json();

        console.log("Events loaded with async/await:");
        data.forEach(item => console.log("- " + item.title));
    } catch (error) {
        console.log("Error: " + error);
    } finally {
        spinner.style.display = "none"; // hide loading
    }
}
```

The async/await version is cleaner and easier to read. Both do the same thing though.

---

## Exercise 10: Modern JavaScript Features

Refactoring code with ES6+ features.

```javascript
// let and const instead of var
const portalName = "Community Event Portal";
let eventCount = 0;

// default parameters
function createEvent(name, category = "general", seats = 50) {
    return { name, category, seats };
}

// destructuring
let event = createEvent("Tech Talk", "tech", 30);
let { name, category, seats } = event;
console.log(`${name} is a ${category} event with ${seats} seats`);

// spread operator to clone before filtering
let events = [
    { name: "Music Night", category: "music" },
    { name: "Code Jam", category: "tech" },
    { name: "Food Walk", category: "food" }
];

let eventsCopy = [...events]; // clone the array
let techOnly = eventsCopy.filter(e => e.category === "tech");
console.log("Filtered (tech):", techOnly);
console.log("Original still intact:", events);
```

---

## Exercise 11: Working with Forms

Connecting form inputs to JavaScript.

```html
<form id="registrationForm">
    <input type="text" name="username" placeholder="Your name" required>
    <input type="email" name="useremail" placeholder="Your email" required>
    <select name="event">
        <option value="">Select event</option>
        <option value="Tech Meetup">Tech Meetup</option>
        <option value="Music Night">Music Night</option>
    </select>
    <button type="submit">Register</button>
    <div id="errors" style="color: red;"></div>
</form>

<script>
    document.getElementById("registrationForm").addEventListener("submit", function(event) {
        event.preventDefault();
        let errors = document.getElementById("errors");
        errors.textContent = "";

        let name = this.elements.username.value;
        let email = this.elements.useremail.value;
        let selectedEvent = this.elements.event.value;

        if (!name) {
            errors.textContent = "Name is required!";
            return;
        }
        if (!email) {
            errors.textContent = "Email is required!";
            return;
        }
        if (!selectedEvent) {
            errors.textContent = "Please select an event!";
            return;
        }

        alert("Registered " + name + " for " + selectedEvent);
    });
</script>
```

`event.preventDefault()` stops the form from refreshing the page on submit.

---

## Exercise 12: AJAX & Fetch API

Sending data to a mock server.

```javascript
function submitRegistration(name, email) {
    let userData = {
        name: name,
        email: email,
        registered: true
    };

    fetch("https://jsonplaceholder.typicode.com/posts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })
    .then(response => response.json())
    .then(data => {
        console.log("Server response:", data);
        document.getElementById("message").textContent = "Registration successful!";
    })
    .catch(error => {
        document.getElementById("message").textContent = "Registration failed!";
    });
}

// simulating a delayed response with setTimeout
function simulateSubmission() {
    document.getElementById("message").textContent = "Submitting...";

    setTimeout(function() {
        document.getElementById("message").textContent = "Done! Your registration is confirmed.";
    }, 2000); // 2 second delay
}
```

---

## Exercise 13: Debugging and Testing

This is about using browser tools to debug. Here's what to do:

**Console Tab:**
- Open DevTools with F12
- Go to Console tab
- Add `console.log()` at each step of your code to track what's happening
```javascript
console.log("Step 1: Form submitted");
console.log("Step 2: Data collected:", formData);
console.log("Step 3: Sending to server...");
```

**Network Tab:**
- Go to Network tab, then submit your form
- Look for the POST request in the list
- Click on it to see the request payload (what data was sent)
- Check the response to see what the server returned
- If status is 200/201 then it worked, if 400/500 then something went wrong

**Breakpoints:**
- Go to Sources tab
- Find your JavaScript file
- Click on a line number to set a breakpoint
- When the code runs and hits that line, it pauses
- You can hover over variables to see their current values
- Use Step Over (F10) to go to the next line
- Use Continue (F8) to let it run until the next breakpoint

This helps a lot when you can't figure out why something isn't working.

---

## Exercise 14: jQuery and JS Frameworks

Using jQuery for simpler DOM operations.

```html
<!-- Include jQuery from CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<button id="registerBtn">Register</button>
<div id="eventCard" style="display:none; border:1px solid #ccc; padding:15px; margin:10px;">
    <h3>Tech Meetup</h3>
    <p>Join us for an amazing tech event!</p>
</div>

<button id="showBtn">Show Event</button>
<button id="hideBtn">Hide Event</button>

<script>
    // handle click with jQuery
    $('#registerBtn').click(function() {
        alert('Registration successful!');
    });

    // fade in the event card
    $('#showBtn').click(function() {
        $('#eventCard').fadeIn(500);
    });

    // fade out the event card
    $('#hideBtn').click(function() {
        $('#eventCard').fadeOut(500);
    });
</script>
```

**One benefit of moving to React or Vue:**
These frameworks let you build your UI as reusable components. So instead of writing separate HTML, CSS and JS, each component has everything it needs in one place. This makes it much easier to manage big applications because you can break the whole app into small, independent pieces. Also they handle updating the page automatically when data changes, so you don't have to manually update the DOM like we do with jQuery.
