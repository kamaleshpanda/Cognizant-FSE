# Module 1 - Bootstrap 5 Exercises - Answers

## Exercise 1.1: Bootstrap 5 via CDN

Create a basic page and link Bootstrap 5 using the CDN.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap 5 Setup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-primary">Hello Bootstrap 5!</h1>
        <p class="lead">This page is using Bootstrap via CDN.</p>
        <button class="btn btn-success">Click Me</button>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

## Exercise 1.2: Bootstrap via npm/downloaded files

After downloading Bootstrap or installing via `npm install bootstrap`, you include the files like this:

```html
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.bundle.min.js"></script>
```

Same thing, just local files instead of CDN links.

---

## Exercise 2.1: Bootstrap Directory Structure

When you download Bootstrap, you'll see these folders:

- **css/** - Contains `bootstrap.min.css` (the main stylesheet) and other CSS files like grid-only, reboot, utilities
- **js/** - Contains `bootstrap.bundle.min.js` (JS + Popper.js bundled together) and individual plugin files
- **icons/** - If you install Bootstrap Icons separately, this has SVG icon files

## Exercise 2.2: Including JS Plugins

```html
<!-- This includes both Bootstrap JS and Popper.js for dropdowns, tooltips etc -->
<script src="js/bootstrap.bundle.min.js"></script>
```

Using `bootstrap.bundle.min.js` is recommended because it already includes Popper.js which is needed for tooltips, popovers and dropdowns.

---

## Exercise 3.1: Responsive Three Columns

```html
<div class="container mt-4">
    <div class="row">
        <div class="col-12 col-md-6 col-lg-4">
            <div class="p-3 bg-primary text-white">Column 1</div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="p-3 bg-success text-white">Column 2</div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="p-3 bg-danger text-white">Column 3</div>
        </div>
    </div>
</div>
```

On mobile (`col-12`) they stack vertically, on tablet (`col-md-6`) two per row, on desktop (`col-lg-4`) three per row.

## Exercise 3.2: Container, Row, Col

```html
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4">Box 1</div>
        <div class="col-sm-6 col-md-4">Box 2</div>
        <div class="col-sm-12 col-md-4">Box 3</div>
    </div>
</div>
```

`.container` gives a centered fixed-width wrapper. `.row` creates a horizontal group. `.col-*` defines how wide each column is.

---

## Exercise 4.1: Two-Column Layout (Sidebar + Content)

```html
<div class="container mt-4">
    <div class="row">
        <div class="col-md-3 bg-light p-3">
            <h4>Sidebar</h4>
            <ul class="list-group">
                <li class="list-group-item">Dashboard</li>
                <li class="list-group-item">Events</li>
                <li class="list-group-item">Settings</li>
            </ul>
        </div>
        <div class="col-md-9 p-3">
            <h4>Main Content</h4>
            <p>This is the main content area of the page.</p>
        </div>
    </div>
</div>
```

## Exercise 4.2: Four Equal Columns

```html
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-3"><div class="p-3 border">Col 1</div></div>
        <div class="col-sm-3"><div class="p-3 border">Col 2</div></div>
        <div class="col-sm-3"><div class="p-3 border">Col 3</div></div>
        <div class="col-sm-3"><div class="p-3 border">Col 4</div></div>
    </div>
</div>
```

---

## Exercise 5.1: Alignment

```html
<div class="container mt-4">
    <div class="row justify-content-center align-items-center" style="height: 300px; background-color: #f0f0f0;">
        <div class="col-4 text-center">
            <h3>Centered Content</h3>
            <p>This is horizontally and vertically centered.</p>
        </div>
    </div>
</div>
```

## Exercise 5.2: Reordering Columns

```html
<div class="container mt-4">
    <div class="row">
        <div class="col-md-6 order-md-2 bg-info p-3">This shows second on desktop</div>
        <div class="col-md-6 order-md-1 bg-warning p-3">This shows first on desktop</div>
    </div>
</div>
```

On mobile they appear in normal order, but on desktop they swap positions.

---

## Exercise 6: Responsive Flexbox Utilities

### Exercise 7.1 (labeled 7.1 in PDF but is exercise 6):

```html
<nav class="d-flex flex-column flex-md-row bg-dark p-3">
    <a href="#" class="text-white p-2">Home</a>
    <a href="#" class="text-white p-2">Events</a>
    <a href="#" class="text-white p-2">About</a>
    <a href="#" class="text-white p-2">Contact</a>
</nav>
```

### Exercise 7.2:

```html
<div class="d-flex justify-content-between align-items-center p-3 border">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Event 1</h5>
            <p class="card-text">Tech meetup this weekend.</p>
        </div>
    </div>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Event 2</h5>
            <p class="card-text">Music night on Friday.</p>
        </div>
    </div>
</div>
```

---

## Exercise 7: Typography

### Exercise 7.1:

```html
<div class="container mt-4">
    <h1 class="display-1">Display 1 Heading</h1>
    <h2 class="display-4">Display 4 Heading</h2>
    <p class="lead">This is a lead paragraph - slightly bigger and lighter.</p>
    <p class="text-muted">This text is muted (gray color).</p>
    <p class="fw-bold">This is bold text.</p>
    <p class="fst-italic">This is italic text.</p>
</div>
```

### Exercise 7.2:

```html
<p class="text-uppercase">this will be all uppercase</p>
<p class="text-lowercase">THIS WILL BE ALL LOWERCASE</p>
<p class="text-capitalize">this will capitalize each word</p>
```

---

## Exercise 8: Forms

### Exercise 8.1: Registration Form

```html
<div class="container mt-4" style="max-width: 500px;">
    <h3>Register</h3>
    <form>
        <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" class="form-control" placeholder="Enter name" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <div class="input-group">
                <span class="input-group-text">🔒</span>
                <input type="password" class="form-control" placeholder="Password">
            </div>
        </div>
        <div class="form-check mb-3">
            <input type="checkbox" class="form-check-input" id="terms">
            <label class="form-check-label" for="terms">I agree to the terms</label>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
```

### Exercise 8.2: Floating Labels Login

```html
<div class="container mt-4" style="max-width: 400px;">
    <h3>Login</h3>
    <form>
        <div class="form-floating mb-3">
            <input type="email" class="form-control" id="email" placeholder="Email">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="password" placeholder="Password">
            <label for="password">Password</label>
        </div>
        <button class="btn btn-primary w-100">Sign In</button>
    </form>
</div>
```

---

## Exercise 9: Buttons

### Exercise 9.1:

```html
<div class="container mt-4">
    <button class="btn btn-primary">Primary</button>
    <button class="btn btn-secondary">Secondary</button>
    <button class="btn btn-success">Success</button>
    <button class="btn btn-danger">Danger</button>
    <button class="btn btn-warning">Warning</button>
    <button class="btn btn-info">Info</button>
    <br><br>
    <button class="btn btn-outline-primary">Outline Primary</button>
    <button class="btn btn-outline-danger">Outline Danger</button>
    <button class="btn btn-outline-success">Outline Success</button>
</div>
```

### Exercise 9.2: Button Groups

```html
<div class="btn-group" role="group">
    <button class="btn btn-primary">Left</button>
    <button class="btn btn-primary">Middle</button>
    <button class="btn btn-primary">Right</button>
</div>
<br><br>
<div class="btn-group" role="group">
    <input type="checkbox" class="btn-check" id="check1" autocomplete="off">
    <label class="btn btn-outline-primary" for="check1">Option 1</label>
    <input type="checkbox" class="btn-check" id="check2" autocomplete="off">
    <label class="btn btn-outline-primary" for="check2">Option 2</label>
</div>
```

---

## Exercise 10: Navbars and Navigation

### Exercise 10.1: Responsive Navbar

```html
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">MyPortal</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Events</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About</a></li>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
```

### Exercise 10.2: Tabs and Pills

```html
<ul class="nav nav-tabs">
    <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
    <li class="nav-item"><a class="nav-link" href="#">Events</a></li>
    <li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
</ul>

<br>

<ul class="nav nav-pills">
    <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
    <li class="nav-item"><a class="nav-link" href="#">Events</a></li>
    <li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
</ul>
```

---

## Exercise 11: Cards and Media Objects

### Exercise 11.1: Profile Card

```html
<div class="card" style="width: 18rem;">
    <img src="profile.jpg" class="card-img-top" alt="Profile">
    <div class="card-body">
        <h5 class="card-title">John Doe</h5>
        <p class="card-text">Full stack developer with 3 years of experience.</p>
        <a href="#" class="btn btn-primary">View Profile</a>
    </div>
</div>
```

### Exercise 11.2: Media Object Layout

```html
<div class="d-flex align-items-start p-3 border">
    <img src="event.jpg" alt="Event" width="80" class="me-3 rounded">
    <div>
        <h5>Tech Meetup 2025</h5>
        <p>Join us for a day of tech talks and networking. Open to all developers.</p>
    </div>
</div>
```

---

## Exercise 12: Spacing Utilities

### Exercise 12.1:

```html
<div class="m-3 p-2 bg-light">Margin 3, Padding 2</div>
<div class="mt-4 py-5 bg-info text-white">Margin-top 4, Padding Y-axis 5</div>
<div class="mx-auto p-3 bg-warning" style="width: 300px;">Centered with mx-auto</div>
```

### Exercise 12.2: Pricing Section

```html
<div class="container mt-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card p-4 text-center">
                <h4 class="mb-3">Basic</h4>
                <h2 class="mb-4">$9/mo</h2>
                <p class="mb-4">5 Events</p>
                <button class="btn btn-outline-primary mt-2">Choose</button>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-4 text-center border-primary">
                <h4 class="mb-3">Pro</h4>
                <h2 class="mb-4">$19/mo</h2>
                <p class="mb-4">20 Events</p>
                <button class="btn btn-primary mt-2">Choose</button>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-4 text-center">
                <h4 class="mb-3">Enterprise</h4>
                <h2 class="mb-4">$49/mo</h2>
                <p class="mb-4">Unlimited</p>
                <button class="btn btn-outline-primary mt-2">Choose</button>
            </div>
        </div>
    </div>
</div>
```

---

## Exercise 13: Colors and Backgrounds

### Exercise 13.1: Dashboard

```html
<div class="container mt-4">
    <div class="row g-3">
        <div class="col-md-3"><div class="p-4 bg-primary text-white rounded">Users: 1200</div></div>
        <div class="col-md-3"><div class="p-4 bg-success text-white rounded">Events: 45</div></div>
        <div class="col-md-3"><div class="p-4 bg-warning text-dark rounded">Pending: 12</div></div>
        <div class="col-md-3"><div class="p-4 bg-danger text-white rounded">Cancelled: 3</div></div>
    </div>
</div>
```

### Exercise 13.2: Gradient Background

```html
<div class="p-5 bg-dark bg-gradient text-white text-center">
    <h1>Welcome to the Portal</h1>
    <p>Dark gradient background with white text</p>
</div>
```

---

## Exercise 14: Display and Visibility

### Exercise 14.1:

```html
<div class="d-none d-md-block bg-info p-3">Only visible on tablets and above</div>
<div class="d-block d-lg-flex bg-warning p-3">Block on small screens, flex on large</div>
<div class="d-md-none bg-danger text-white p-3">Only visible on mobile</div>
```

### Exercise 14.2: Responsive Sidebar

```html
<div class="container-fluid">
    <div class="row">
        <div class="d-none d-md-block col-md-3 bg-light p-3" style="min-height: 400px;">
            <h5>Sidebar</h5>
            <p>This sidebar only shows on tablet and above.</p>
        </div>
        <div class="col-12 col-md-9 p-3">
            <h5>Main Content</h5>
            <p>This is always visible.</p>
        </div>
    </div>
</div>
```

---

## Exercise 15: Borders, Shadows, and Rounded Corners

### Exercise 15.1:

```html
<img src="profile.jpg" alt="Profile" class="border border-primary border-3 rounded-circle" width="150">
```

### Exercise 15.2:

```html
<div class="card shadow-lg rounded-pill p-4 text-center" style="max-width: 300px;">
    <h5>Premium Card</h5>
    <p>With large shadow and pill shape</p>
</div>
```

---

## Exercise 16: Positioning Utilities

### Exercise 16.1: Fixed Footer

```html
<footer class="position-fixed bottom-0 w-100 bg-dark text-white text-center p-3">
    &copy; 2025 Community Portal
</footer>
```

### Exercise 16.2: Badge Over Image

```html
<div class="position-relative d-inline-block">
    <img src="event.jpg" alt="Event" width="200" class="rounded">
    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
        NEW
    </span>
</div>
```

---

## Exercise 17: Bootstrap Icons

### Exercise 17.1: Social Media Icons in Footer

First add the Bootstrap Icons CSS:
```html
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
```

```html
<footer class="bg-dark text-white text-center p-4">
    <p>Follow us</p>
    <a href="#" class="text-white me-3"><i class="bi bi-facebook fs-4"></i></a>
    <a href="#" class="text-white me-3"><i class="bi bi-twitter fs-4"></i></a>
    <a href="#" class="text-white me-3"><i class="bi bi-instagram fs-4"></i></a>
    <a href="#" class="text-white"><i class="bi bi-linkedin fs-4"></i></a>
</footer>
```

### Exercise 17.2: Icon-Only Buttons

```html
<button class="btn btn-primary"><i class="bi bi-house"></i></button>
<button class="btn btn-success"><i class="bi bi-search"></i></button>
<button class="btn btn-danger"><i class="bi bi-trash"></i></button>
<button class="btn btn-warning"><i class="bi bi-pencil"></i></button>
```

---

## Exercise 18: Bootstrap 5 JavaScript Plugins

### Exercise 18.1: Modal Popup

```html
<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">Open Modal</button>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Event Details</h5>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Tech Meetup on June 10, 2025. Don't miss it!</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary">Register</button>
            </div>
        </div>
    </div>
</div>
```

### Exercise 18.2: Accordion

```html
<div class="accordion" id="faqAccordion">
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button" data-bs-toggle="collapse" data-bs-target="#q1">
                How do I register for an event?
            </button>
        </h2>
        <div id="q1" class="accordion-collapse collapse show" data-bs-parent="#faqAccordion">
            <div class="accordion-body">Click the Register button on the event page and fill in your details.</div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#q2">
                Can I cancel my registration?
            </button>
        </h2>
        <div id="q2" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
            <div class="accordion-body">Yes, go to your profile and click Cancel on the event.</div>
        </div>
    </div>
</div>
```

---

## Exercise 19: Customization with Sass

### Exercise 19.1: Setup with npm

```bash
npm init -y
npm install bootstrap sass
```

Create a file `src/custom.scss`:
```scss
// Import Bootstrap source
@import "../node_modules/bootstrap/scss/bootstrap";
```

Compile it:
```bash
npx sass src/custom.scss dist/css/custom.css
```

Then use `dist/css/custom.css` in your HTML instead of the CDN link.

### Exercise 19.2: Customize Variables

Create `src/custom.scss`:
```scss
// Override variables BEFORE importing Bootstrap
$primary: #e74c3c;
$border-radius: 12px;
$font-family-base: 'Segoe UI', sans-serif;

// Then import Bootstrap
@import "../node_modules/bootstrap/scss/bootstrap";
```

Compile again:
```bash
npx sass src/custom.scss dist/css/custom.css
```

Now all `.btn-primary`, `.bg-primary`, `.text-primary` etc will use your custom red color instead of the default blue. And all rounded corners will be 12px. This is the proper way to customize Bootstrap instead of overriding things in a separate CSS file.
