# Module 1 - CSS3 Exercises - Answers

## Exercise 1: Why CSS? Inline vs. Internal vs. External

There are 3 ways to apply CSS:

**Inline** - directly on the element using `style` attribute:
```html
<h1 style="color: red;">This heading is red</h1>
```

**Internal** - inside a `<style>` tag in the `<head>`:
```html
<head>
    <style>
        body {
            background-color: #f0f0f0;
        }
    </style>
</head>
```

**External** - link a separate CSS file:
```html
<link rel="stylesheet" href="styles.css">
```

In `styles.css`:
```css
/* Header styles */
header {
    background-color: #333;
    color: white;
    padding: 15px;
}

/* Navigation styles */
nav a {
    color: white;
    text-decoration: none;
    margin-right: 15px;
}

/* Footer styles */
footer {
    text-align: center;
    padding: 10px;
    background-color: #333;
    color: white;
}
```

External is the best approach because you can reuse the same stylesheet across multiple pages. Inline is quick but messy. Internal is okay for single pages.

---

## Exercise 2: CSS Syntax and Comments

Good CSS should be clean and well-commented so other developers can understand it.

```css
/* ================================
   Community Portal - Main Styles
   ================================ */

/* Style for main CTA button */
.cta-button {
    background-color: #007BFF;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

/* Style for event cards */
.event-card {
    border: 1px solid #ddd;
    padding: 15px;
    margin: 10px;
    border-radius: 8px;
}

/* Style for section headings */
.section-title {
    font-size: 24px;
    color: #333;
    border-bottom: 2px solid #007BFF;
    padding-bottom: 5px;
}
```

The key thing is to keep consistent indentation and put comments above each section so you know what each rule does.

---

## Exercise 3: Selectors Playground

Different types of selectors you can use in CSS:

```css
/* Universal selector - resets margin and padding for everything */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Element selector - styles all h2 elements */
h2 {
    color: #2c3e50;
    font-size: 22px;
    margin-bottom: 10px;
}

/* ID selector - for the main banner (unique element) */
#mainHeader {
    background-color: #3498db;
    color: white;
    padding: 20px;
    text-align: center;
}

/* Class selector - for event card containers (reusable) */
.eventCard {
    border: 1px solid #ccc;
    padding: 15px;
    margin: 10px;
    border-radius: 8px;
    background-color: #fff;
}

/* Grouping selector - style h3 and p together */
h3, p {
    font-family: Arial, sans-serif;
    line-height: 1.6;
}
```

The difference between ID and class: ID is unique (only one element), class can be used on multiple elements.

---

## Exercise 4: Color & Background Styling

Using different color formats and background images.

```css
/* Text colors using HEX */
h1 {
    color: #2c3e50;
}

/* Background with RGBA (the 0.9 is opacity) */
.banner {
    background-color: rgba(52, 152, 219, 0.9);
    color: white;
    padding: 30px;
}

/* Background image with fallback color */
body {
    background-image: url('bg-pattern.jpg');
    background-color: #ecf0f1;
    background-size: cover;
    background-repeat: no-repeat;
}

/* Gradient on section headers */
.section-header {
    background: linear-gradient(to right, #3498db, #2ecc71);
    color: white;
    padding: 15px;
    border-radius: 5px;
}
```

The fallback color shows up if the image doesn't load.

---

## Exercise 5: Typography: Fonts and Text

Making text look better with Google Fonts and text properties.

```html
<!-- Add this in your HTML <head> to import Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
```

```css
/* Apply Google Font to the whole page */
body {
    font-family: 'Roboto', sans-serif;
    font-size: 16px;
    line-height: 1.6;
}

/* Different font styles for headings */
h1 {
    font-size: 32px;
    font-weight: 700;
    letter-spacing: 1px;
}

h2 {
    font-size: 24px;
    font-style: italic;
    font-weight: 400;
}

/* Text styling for descriptions */
.description {
    text-align: justify;
    text-transform: capitalize;
    letter-spacing: 0.5px;
    line-height: 1.8;
}

/* Uppercase for section labels */
.section-label {
    text-transform: uppercase;
    font-weight: 700;
    font-size: 14px;
    color: #7f8c8d;
}
```

---

## Exercise 6: Link and List Styling

Customizing links and lists to match the design.

```css
/* Link styles with pseudo-classes */
a:link {
    color: #3498db;
    text-decoration: none;
}

a:visited {
    color: #8e44ad;
}

a:hover {
    color: #e74c3c;
    text-decoration: underline;
}

a:active {
    color: #2ecc71;
}

/* Remove bullets from navigation menu */
nav ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

nav ul li {
    display: inline;
    margin-right: 20px;
}

/* Styled list with custom bullets */
.event-list {
    list-style-type: square;
    list-style-position: inside;
    padding-left: 10px;
}

.event-list li {
    padding: 5px 0;
    margin: 3px 0;
}
```

---

## Exercise 7: Table Styling

Making the admin table look clean and professional.

```css
table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}

th, td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
}

th {
    background-color: #3498db;
    color: white;
    font-weight: bold;
}

/* Zebra striping for rows */
tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #ddd;
}
```

`border-collapse: collapse` removes the double borders between cells. `nth-child(even)` targets every even row for the alternating color effect.

---

## Exercise 8: Box Model & Layout Control

Understanding margin, padding, border, and outline.

```css
/* Event card with box model properties */
.eventCard {
    border: 2px solid #3498db;
    padding: 20px;
    margin: 15px;
    border-radius: 8px;
    background-color: white;
}

/* Form field outline on focus */
input:focus, textarea:focus {
    outline: 3px solid #3498db;
    outline-offset: 2px;
}

/* Demonstrating visibility vs display */
.hidden-visibility {
    visibility: hidden;
    /* element is invisible but still takes up space on the page */
}

.hidden-display {
    display: none;
    /* element is completely removed from the layout, no space taken */
}
```

The main difference: `visibility: hidden` hides the element but it still occupies space. `display: none` removes it completely from the page flow.

---

## Exercise 9: Multiple Columns in Text

Making a news section look like a newspaper with multiple columns.

```css
.news-article {
    column-count: 2;
    column-gap: 30px;
    column-rule: 1px solid gray;
    text-align: justify;
    padding: 20px;
}
```

```html
<div class="news-article">
    <h3>Community Bulletin</h3>
    <p>The city council is pleased to announce several upcoming events 
    for the community. This month we have a music festival, a food fair, 
    and a tech meetup all planned for different weekends. Residents are 
    encouraged to participate and bring their families along. Registration 
    is now open for all events through our online portal. Early bird 
    discounts are available for those who register before the end of this 
    week. We look forward to seeing everyone there and making these events 
    a great success for our community.</p>
</div>
```

The text automatically flows from one column to the next. `column-rule` adds a vertical line between columns.

---

## Exercise 10: Responsive Web Design with Media Queries

Making the portal work on different screen sizes.

```css
/* Default styles for desktop */
nav ul {
    display: flex;
    list-style: none;
    gap: 20px;
}

.event-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.eventCard {
    width: 30%;
}

img {
    max-width: 100%;
    height: auto;
}

/* Tablet styles */
@media screen and (max-width: 768px) {
    nav ul {
        flex-direction: column;
        gap: 10px;
    }

    .eventCard {
        width: 45%;
    }

    h1 {
        font-size: 24px;
    }

    body {
        font-size: 14px;
    }
}

/* Mobile styles */
@media screen and (max-width: 480px) {
    .eventCard {
        width: 100%;
    }

    .event-grid {
        flex-direction: column;
    }

    h1 {
        font-size: 20px;
    }
}
```

Using `%`, `vw`, `vh` units instead of fixed `px` values makes things flexible. Flexbox helps a lot with responsive layouts.

---

## Exercise 11: Debug and Test with Dev Tools and VS Code

This is about using browser tools to find and fix issues:

**Device Toolbar (Responsive Testing):**
- Open Chrome DevTools (F12)
- Click the device toggle icon (looks like a phone/tablet) at the top left of DevTools
- You can now pick different screen sizes like iPhone, iPad, etc.
- Drag the edges to test custom sizes
- Check if your layout breaks at any width

**Inspect Applied Styles:**
- Click on any element in the Elements tab
- The right panel shows all CSS rules applied to that element
- You can check/uncheck properties to see their effect
- You can click on values and change them to test new styles
- Changes are temporary and go away when you reload

**Network Tab:**
- Go to the Network tab and reload the page
- Look for your CSS file in the list
- If it shows a 200 status, the file loaded fine
- If it shows 404, the file path is wrong
- You can also see how long each file takes to load

This is basically the main way frontend developers debug their CSS problems. Get comfortable with it because you'll use it a lot.
