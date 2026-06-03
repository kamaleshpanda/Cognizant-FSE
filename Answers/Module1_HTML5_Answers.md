# Module 1 - HTML5 Exercises - Answers

## Exercise 1: Create the HTML5 Base Template

We need to set up the basic document structure that every page will use.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Community Event Portal</title>
</head>
<body>
    <!-- Navigation -->
    <nav>
        <a href="#home">Home</a>
        <a href="#events">Events</a>
        <a href="#contact">Contact</a>
    </nav>

    <!-- Main Content -->
    <main>
        <h1>Welcome to the Community Event Portal</h1>
        <p>Your one stop place for all local events.</p>
    </main>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Community Portal. All rights reserved.</p>
    </footer>
</body>
</html>
```

To inspect in Chrome, just right-click on the page and select "Inspect" to open DevTools. You can see the DOM tree there.

---

## Exercise 2: Navigation and Linking

Here we add proper navigation with anchor links pointing to different sections on the same page. Also linking to an external page.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Community Portal - Navigation</title>
</head>
<body>
    <nav>
        <a href="#home">Home</a> |
        <a href="#events">Events</a> |
        <a href="#contact">Contact</a> |
        <a href="help.html" target="_blank">Help</a>
    </nav>

    <section id="home">
        <h2>Home</h2>
        <p>Welcome to our community portal. Find events near you!</p>
    </section>

    <section id="events">
        <h2>Events</h2>
        <p>Check out the upcoming events in your area.</p>
    </section>

    <section id="contact">
        <h2>Contact Us</h2>
        <p>Email us at community@portal.com</p>
    </section>
</body>
</html>
```

The `target="_blank"` makes the help page open in a new tab.

---

## Exercise 3: Welcome Message with Styling and ID/Class

Using id and class selectors along with inline and internal CSS.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome Banner</title>
    <style>
        #welcomeBanner {
            background-color: #3498db;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .highlight {
            background-color: yellow;
            color: black;
            padding: 2px 5px;
        }
    </style>
</head>
<body>
    <div id="welcomeBanner">
        <h1>Welcome back, User!</h1>
        <p>We have <span class="highlight">3 new events</span> for you.</p>
        <span style="color: red; font-weight: bold;">Special Offer: 50% off on premium events!</span>
    </div>
</body>
</html>
```

Here the `id="welcomeBanner"` is used for the unique banner, `class="highlight"` can be reused on multiple elements, and inline style is used for the special offer text.

---

## Exercise 4: Image Gallery for Community Events

Displaying images in a table layout with captions.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Event Gallery</title>
    <style>
        .event-img {
            width: 200px;
            height: 150px;
            border: 2px solid #333;
        }
        table {
            margin: 20px auto;
        }
        td {
            text-align: center;
            padding: 10px;
        }
    </style>
</head>
<body>
    <h2>Past Community Events</h2>
    <table>
        <tr>
            <td>
                <img src="event1.jpg" alt="Music Festival" title="Music Festival 2024" class="event-img">
                <p>Music Festival</p>
            </td>
            <td>
                <img src="event2.jpg" alt="Food Fair" title="Food Fair 2024" class="event-img">
                <p>Food Fair</p>
            </td>
            <td>
                <img src="event3.jpg" alt="Art Exhibition" title="Art Exhibition 2024" class="event-img">
                <p>Art Exhibition</p>
            </td>
        </tr>
        <tr>
            <td>
                <img src="event4.jpg" alt="Marathon" title="City Marathon 2024" class="event-img">
                <p>Marathon</p>
            </td>
            <td>
                <img src="event5.jpg" alt="Tech Meetup" title="Tech Meetup 2024" class="event-img">
                <p>Tech Meetup</p>
            </td>
            <td>
                <img src="event6.jpg" alt="Book Fair" title="Book Fair 2024" class="event-img">
                <p>Book Fair</p>
            </td>
        </tr>
    </table>
</body>
</html>
```

---

## Exercise 5: Event Registration Form

Form with different input types, validation, and output element.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Event Registration</title>
    <style>
        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        label { display: block; margin-top: 10px; }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        button {
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Register for an Event</h2>
    <form onsubmit="document.getElementById('result').value='Registration submitted successfully!'; return false;">
        <label>Name:
            <input type="text" placeholder="Enter your name" required autofocus>
        </label>

        <label>Email:
            <input type="email" placeholder="Enter your email" required>
        </label>

        <label>Date:
            <input type="date" required>
        </label>

        <label>Event Type:
            <select required>
                <option value="">-- Select --</option>
                <option value="music">Music</option>
                <option value="tech">Tech</option>
                <option value="sports">Sports</option>
                <option value="food">Food</option>
            </select>
        </label>

        <label>Message:
            <textarea placeholder="Any message for us?" rows="3"></textarea>
        </label>

        <button type="submit">Register</button>
        <br><br>
        <output id="result"></output>
    </form>
</body>
</html>
```

---

## Exercise 6: Event Feedback with Events Handling

Handling different events like blur, change, click, dblclick, and keyboard events.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Event Feedback</title>
</head>
<body>
    <h2>Event Feedback Form</h2>

    <label>Phone Number:
        <input type="text" id="phone" onblur="validatePhone()">
    </label>
    <span id="phoneError" style="color: red;"></span>
    <br><br>

    <label>Select Event:
        <select id="eventSelect" onchange="showFee()">
            <option value="">-- Choose --</option>
            <option value="500">Music Night - Rs.500</option>
            <option value="1000">Tech Summit - Rs.1000</option>
            <option value="300">Food Walk - Rs.300</option>
        </select>
    </label>
    <p id="feeDisplay"></p>

    <button onclick="alert('Feedback submitted! Thank you.')">Submit Feedback</button>
    <br><br>

    <img src="event.jpg" alt="Event" width="200" ondblclick="this.width=400" style="cursor:pointer;">
    <p><small>Double-click image to enlarge</small></p>

    <label>Your Feedback:
        <textarea id="feedback" rows="4" cols="40" onkeyup="countChars()"></textarea>
    </label>
    <p id="charCount">Characters: 0</p>

    <script>
        function validatePhone() {
            var phone = document.getElementById('phone').value;
            var error = document.getElementById('phoneError');
            if (phone.length !== 10 || isNaN(phone)) {
                error.textContent = 'Please enter a valid 10-digit phone number';
            } else {
                error.textContent = '';
            }
        }

        function showFee() {
            var fee = document.getElementById('eventSelect').value;
            document.getElementById('feeDisplay').textContent = fee ? 'Fee: Rs.' + fee : '';
        }

        function countChars() {
            var text = document.getElementById('feedback').value;
            document.getElementById('charCount').textContent = 'Characters: ' + text.length;
        }
    </script>
</body>
</html>
```

---

## Exercise 7: Video Invite with Media Events

Adding a video element with oncanplay and onbeforeunload events.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Event Video Invite</title>
</head>
<body>
    <h2>Watch Our Event Promo</h2>

    <video width="400" controls oncanplay="document.getElementById('videoStatus').textContent='Video ready to play!'">
        <source src="promo.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>
    <p id="videoStatus" style="color: green;"></p>

    <script>
        window.onbeforeunload = function() {
            return "You have unsaved changes. Are you sure you want to leave?";
        };
    </script>
</body>
</html>
```

The `oncanplay` event fires when the browser has loaded enough of the video to start playing. `onbeforeunload` shows a warning when the user tries to close or leave the page.

---

## Exercise 8: Saving User Preferences

Using localStorage and sessionStorage to save and retrieve user preferences.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Preferences</title>
</head>
<body>
    <h2>Set Your Preferences</h2>

    <label>Preferred Event Type:
        <select id="eventType" onchange="savePreference()">
            <option value="">-- Select --</option>
            <option value="music">Music</option>
            <option value="tech">Tech</option>
            <option value="sports">Sports</option>
            <option value="food">Food</option>
        </select>
    </label>
    <br><br>
    <button onclick="clearPreferences()">Clear Preferences</button>
    <p id="status"></p>

    <script>
        // on page load, check if there's a saved preference
        window.onload = function() {
            var saved = localStorage.getItem('preferredEvent');
            if (saved) {
                document.getElementById('eventType').value = saved;
                document.getElementById('status').textContent = 'Loaded saved preference: ' + saved;
            }
        };

        function savePreference() {
            var selected = document.getElementById('eventType').value;
            if (selected) {
                localStorage.setItem('preferredEvent', selected);
                sessionStorage.setItem('lastSelected', selected);
                document.getElementById('status').textContent = 'Preference saved: ' + selected;
            }
        }

        function clearPreferences() {
            localStorage.clear();
            sessionStorage.clear();
            document.getElementById('eventType').value = '';
            document.getElementById('status').textContent = 'All preferences cleared!';
        }
    </script>
</body>
</html>
```

---

## Exercise 9: Geolocation for Event Mapping

Using the Geolocation API to get user coordinates.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find Nearby Events</title>
</head>
<body>
    <h2>Find Events Near You</h2>
    <button onclick="findLocation()">Find Nearby Events</button>
    <p id="location"></p>

    <script>
        function findLocation() {
            var output = document.getElementById('location');

            if (!navigator.geolocation) {
                output.textContent = 'Geolocation is not supported by your browser.';
                return;
            }

            output.textContent = 'Finding your location...';

            var options = {
                enableHighAccuracy: true,
                timeout: 10000,
                maximumAge: 0
            };

            navigator.geolocation.getCurrentPosition(
                function(position) {
                    var lat = position.coords.latitude;
                    var lon = position.coords.longitude;
                    output.textContent = 'Your location: Latitude ' + lat + ', Longitude ' + lon;
                },
                function(error) {
                    switch(error.code) {
                        case error.PERMISSION_DENIED:
                            output.textContent = 'You denied the location request.';
                            break;
                        case error.POSITION_UNAVAILABLE:
                            output.textContent = 'Location information is unavailable.';
                            break;
                        case error.TIMEOUT:
                            output.textContent = 'The request to get location timed out.';
                            break;
                        default:
                            output.textContent = 'An unknown error occurred.';
                    }
                },
                options
            );
        }
    </script>
</body>
</html>
```

---

## Exercise 10: Debugging with Chrome DevTools

This exercise is more about using tools rather than writing code. Here's what you do:

**Inspect Element:**
- Right-click on any element on your page and click "Inspect"
- This opens the Elements tab where you can see the HTML
- You can click on any style property and change it live to see how it looks
- For example change background-color of the banner and see it change instantly

**Console Tab:**
- Open DevTools (F12) and go to Console tab
- Any `console.log()` messages from your script will show up here
- You can also type JavaScript directly in the console to test things
- Try typing `document.title` and press Enter to see the page title

**Breakpoints:**
- Go to the Sources tab in DevTools
- Click on your JS file on the left
- Click on a line number to add a breakpoint (blue marker will appear)
- Reload the page, and execution will pause at that line
- You can hover over variables to see their values
- Use the step buttons to go through code line by line

This is very useful for finding bugs when something doesn't work as expected.
