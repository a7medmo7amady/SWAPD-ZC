# Advanced UI Development Quiz

## Lectures 1-8: HTML, CSS, JavaScript, Tailwind & DOM

### 50 Advanced Questions

---

## Section A: HTML & Semantic Elements (Questions 1-10)

**1.** Given the following HTML structure, which element would a screen reader identify as the primary navigation region WITHOUT requiring any ARIA attributes?

```html
<div class="menu">
  <a href="/">Home</a>
  <a href="/about">About</a>
</div>

<nav>
  <a href="/">Home</a>
  <a href="/about">About</a>
</nav>
```

A) Both elements are equivalent for screen readers  
B) The `<div class="menu">` element  
C) The `<nav>` element  
D) Neither - both require `role="navigation"`

---

**2.** A developer writes the following HTML5 form. What happens when a user tries to submit with the email field containing "john@" ?

```html
<form>
  <input type="email" required>
  <input type="submit">
</form>
```

A) The form submits successfully because "john@" contains an @ symbol  
B) The browser prevents submission and shows a validation error  
C) JavaScript must be added to validate the email  
D) The `required` attribute only checks if the field is empty

---

**3.** Which statement about HTML5 semantic elements' default layout behavior is CORRECT?

A) `<header>`, `<nav>`, `<main>`, `<aside>`, and `<footer>` automatically arrange themselves in a typical webpage layout  
B) `<aside>` has a default `float: right` property  
C) These semantic elements are block-level and stack vertically by default with no special positioning  
D) `<nav>` automatically creates horizontal navigation menus

---

**4.** A developer needs to provide accessibility for a custom search input that has no visible label. Which approach is MOST appropriate?

A) `<input type="search" placeholder="Search...">`  
B) `<input type="search" aria-label="Search the website">`  
C) `<input type="search" title="Search">`  
D) `<label style="display:none">Search</label><input type="search">`

---

**5.** Consider this HTML5 structure. How many times can `<header>` and `<footer>` elements appear in a valid HTML5 document?

```html
<body>
  <header>Site Header</header>
  <article>
    <header>Article Header</header>
    <p>Content...</p>
    <footer>Article Footer</footer>
  </article>
  <footer>Site Footer</footer>
</body>
```

A) Only once per document  
B) Multiple times - once per section, article, or for the main page  
C) `<header>` once, `<footer>` unlimited  
D) Only valid inside `<article>` elements

---

**6.** What is the PRIMARY difference between `<section>` and `<article>` in HTML5?

A) `<section>` is for grouping related content; `<article>` is for self-contained, independently distributable content  
B) `<section>` must contain headings; `<article>` does not  
C) `<article>` can only be used for blog posts  
D) There is no semantic difference; they are interchangeable

---

**7.** Which HTML5 input type combination provides BOTH a date picker interface AND validates that a value is entered before form submission?

A) `<input type="date" required>`  
B) `<input type="datetime" required>`  
C) `<input type="text" pattern="\d{4}-\d{2}-\d{2}" required>`  
D) `<input type="date" validate="true">`

---

**8.** A form has `<input type="number" min="1" max="10" step="2">`. Which value would be INVALID?

A) 1  
B) 3  
C) 4  
D) 9

---

**9.** What does the `autocomplete` attribute in HTML5 forms control?

A) Automatic form submission after all fields are filled  
B) Browser suggestions based on previously entered values  
C) Real-time validation as the user types  
D) Automatic completion of sentences in text fields

---

**10.** Given ARIA and HTML5 semantic elements, which statement is TRUE?

A) ARIA should always be used instead of semantic HTML5 elements  
B) Using `<nav>` is equivalent to using `<div role="navigation">`  
C) ARIA attributes override HTML5 semantic meanings  
D) HTML5 semantic elements are deprecated in favor of ARIA

---

## Section B: CSS Fundamentals & Specificity (Questions 11-20)

**11.** Given the following CSS rules, what color will the text "Hello World" be?

```html
<p id="note" class="highlight">Hello World</p>
```

```css
p { color: black; }
.highlight { color: green; }
#note { color: red; }
p#note.highlight { color: orange; }
```

A) black  
B) green  
C) red  
D) orange

---

**12.** Calculate the specificity value of the selector `div.container #main p.intro::first-line`:

A) 0-1-1-3  
B) 0-1-2-3  
C) 0-1-2-4  
D) 0-1-3-2

---

**13.** Which CSS rule will WIN when applied to the same element?

```css
/* Rule A */ p { color: blue !important; }
/* Rule B */ #main p { color: red; }
/* Rule C */ .container .text p { color: green; }
/* Rule D */ <p style="color: yellow;">
```

A) Rule A  
B) Rule B  
C) Rule C  
D) Rule D (inline style)

---

**14.** Which CSS properties are typically INHERITED by child elements?

A) `margin`, `padding`, `border`  
B) `font-family`, `color`, `line-height`  
C) `width`, `height`, `display`  
D) `position`, `top`, `left`

---

**15.** In the CSS box model with `box-sizing: content-box`, if an element has `width: 200px`, `padding: 20px`, and `border: 5px solid`, what is the TOTAL width the element occupies?

A) 200px  
B) 240px  
C) 250px  
D) 250px (200 + 40 padding + 10 border)

---

**16.** What is the correct order of CSS cascade resolution when multiple rules have equal specificity?

A) External stylesheet → Embedded styles → Inline styles  
B) Source order (later rules win)  
C) Alphabetical order of property names  
D) Browser defaults always win

---

**17.** Given `position: absolute` on a child element, which ancestor does it position relative to?

A) Always the `<body>` element  
B) The nearest ancestor with `position: static`  
C) The nearest ancestor with `position: relative`, `absolute`, or `fixed`  
D) The viewport

---

**18.** What happens to the original space of an element when it has `position: absolute` applied?

A) The space is preserved (like `position: relative`)  
B) The space collapses and is closed up  
C) Other elements float around it  
D) It depends on the `display` property

---

**19.** Which selector type has the LOWEST specificity?

A) `#id`  
B) `.class`  
C) `element`  
D) `*` (universal selector)

---

**20.** A CSS rule uses `!important`. Under what circumstances can it be overridden?

A) It can never be overridden  
B) By another `!important` rule with equal or higher specificity  
C) By an inline style  
D) Only by JavaScript

---

## Section C: Flexbox & Layout (Questions 21-28)

**21.** Given a container with `flex-wrap: wrap` and three child items with `flex: 1 1 200px`, how will they behave if the container is 500px wide?

A) All three items fit on one row, each shrinking to ~166px  
B) Two items on the first row (each 250px), one wraps to the second row (500px)  
C) All items keep their 200px width and overflow the container  
D) Items wrap to three separate rows

---

**22.** In the `flex` shorthand `flex: 1 1 200px`, what do the three values represent in order?

A) `flex-basis`, `flex-grow`, `flex-shrink`  
B) `flex-grow`, `flex-shrink`, `flex-basis`  
C) `flex-shrink`, `flex-grow`, `flex-basis`  
D) `flex-grow`, `flex-basis`, `flex-shrink`

---

**23.** What does `flex-shrink: 0` do to a flex item?

A) Prevents the item from growing beyond its basis  
B) Prevents the item from shrinking below its basis  
C) Makes the item invisible  
D) Resets the item to default flex behavior

---

**24.** Which `flex-flow` value creates a column layout that wraps items in reverse order?

A) `flex-flow: column-reverse wrap`  
B) `flex-flow: column wrap-reverse`  
C) `flex-flow: reverse-column wrap`  
D) `flex-flow: column-reverse wrap-reverse`

---

**25.** A flex container has `justify-content: space-between` with 4 items. How is space distributed?

A) Equal space between and around all items  
B) Equal space between items, no space at the edges  
C) Items centered with equal space around them  
D) First item at start, last item at end, no space between

---

**26.** What is the default value of `flex-direction` in a flex container?

A) column  
B) row  
C) row-reverse  
D) inherit

---

**27.** When using `align-items` in a flex container with `flex-direction: row`, which axis is being controlled?

A) The main axis (horizontal)  
B) The cross axis (vertical)  
C) Both axes equally  
D) It depends on the `writing-mode`

---

**28.** What happens when `flex-wrap: nowrap` (default) is set and flex items exceed the container width?

A) Items automatically wrap to the next line  
B) Items shrink proportionally based on `flex-shrink` values  
C) Container expands to fit all items  
D) Items overflow and are clipped

---

## Section D: JavaScript Fundamentals (Questions 29-38)

**29.** What is the output of this code?

```javascript
console.log(x);
var x = 10;
console.log(x);
```

A) `ReferenceError`, then `10`  
B) `undefined`, then `10`  
C) `10`, then `10`  
D) `null`, then `10`

---

**30.** What is the output of this code?

```javascript
console.log(y);
let y = 20;
```

A) `undefined`  
B) `20`  
C) `ReferenceError: Cannot access 'y' before initialization`  
D) `null`

---

**31.** Which statement about `const` in JavaScript is CORRECT?

A) `const` variables cannot be modified in any way  
B) `const` prevents reassignment but objects/arrays declared with `const` can have their contents modified  
C) `const` is function-scoped like `var`  
D) `const` must be declared without an initial value

---

**32.** What is the key difference between `var` and `let` scoping?

A) `var` is block-scoped; `let` is function-scoped  
B) `var` is function-scoped; `let` is block-scoped  
C) Both have identical scoping rules  
D) `let` cannot be used inside functions

---

**33.** Which syntax correctly defines an arrow function that takes two parameters and returns their sum?

A) `const add = (a, b) => { a + b }`  
B) `const add = (a, b) => return a + b`  
C) `const add = (a, b) => a + b`  
D) `const add = a, b => a + b`

---

**34.** What does `event.stopPropagation()` do?

A) Prevents the default browser action for the event  
B) Stops the event from bubbling up to parent elements  
C) Removes all event listeners from the element  
D) Cancels the event entirely including the target handler

---

**35.** In event delegation, why is it advantageous to attach a single listener to a parent element?

A) It makes the code more difficult to maintain  
B) It works for dynamically added child elements and improves performance  
C) Parent listeners execute faster than child listeners  
D) It's required by the DOM specification

---

**36.** What is the correct order of event propagation phases?

A) Bubbling → Target → Capturing  
B) Target → Bubbling → Capturing  
C) Capturing → Target → Bubbling  
D) Target → Capturing → Bubbling

---

**37.** What does `event.target` refer to?

A) The element to which the event handler is attached  
B) The element that originally triggered the event  
C) The parent element of the clicked element  
D) The `window` object

---

**38.** What is the difference between `getElementsByClassName()` and `querySelectorAll()`?

A) `getElementsByClassName()` returns a live HTMLCollection; `querySelectorAll()` returns a static NodeList  
B) They return identical types of collections  
C) `querySelectorAll()` is faster for class selection  
D) `getElementsByClassName()` uses CSS selector syntax

---

## Section E: JavaScript Modules & Async (Questions 39-44)

**39.** What is the CORRECT syntax for importing a default export?

A) `import { MyComponent } from './component.js'`  
B) `import MyComponent from './component.js'`  
C) `import default MyComponent from './component.js'`  
D) `require('./component.js')`

---

**40.** A module has both named and default exports. Which import statement is CORRECT?

```javascript
// utils.js
export const PI = 3.14159;
export default function log(msg) { console.log(msg); }
```

A) `import log, { PI } from './utils.js'`  
B) `import { log, PI } from './utils.js'`  
C) `import { default as log, PI } from './utils.js'`  
D) Both A and C are correct

---

**41.** What does the `fetch()` API return?

A) The response data directly  
B) A Promise that resolves to a Response object  
C) An XMLHttpRequest object  
D) A callback function

---

**42.** In the following code, what is the correct way to extract JSON data from a fetch response?

```javascript
fetch('https://api.example.com/data')
  .then(response => ???)
  .then(data => console.log(data));
```

A) `response.data`  
B) `response.json()`  
C) `JSON.parse(response)`  
D) `response.text()`

---

**43.** What is the purpose of `.catch()` in a Promise chain?

A) To execute code when the Promise resolves successfully  
B) To handle errors when the Promise rejects  
C) To cancel the Promise  
D) To convert the Promise to a synchronous operation

---

**44.** Which statement about `localStorage` vs `sessionStorage` is TRUE?

A) Both persist indefinitely until manually cleared  
B) `localStorage` persists permanently; `sessionStorage` clears when the tab closes  
C) `sessionStorage` is accessible across all tabs; `localStorage` is not  
D) `localStorage` has a larger storage limit than `sessionStorage`

---

## Section F: Tailwind CSS & DOM (Questions 45-50)

**45.** How does Tailwind CSS differ from Bootstrap in its core philosophy?

A) Tailwind provides component classes like `.btn`; Bootstrap provides utility classes  
B) Tailwind provides utility classes that can be combined; Bootstrap provides predefined components  
C) They are identical in approach  
D) Tailwind only works with React; Bootstrap works with any framework

---

**46.** What does the Tailwind class `md:text-lg` do?

A) Applies `text-lg` on all screen sizes  
B) Applies `text-lg` only on medium screens and larger  
C) Applies `text-lg` only on medium screens (not larger or smaller)  
D) Applies `text-lg` only on screens smaller than medium

---

**47.** In Tailwind's build process, what does the `content` configuration in `tailwind.config.js` specify?

A) The content to display on the webpage  
B) Files to scan for used class names (to remove unused CSS)  
C) The default text content for components  
D) API endpoints for dynamic content

---

**48.** Why can't inline styles handle responsive design effectively?

A) Inline styles don't support media queries or breakpoint conditions  
B) Inline styles are slower to render  
C) Browsers ignore inline styles on mobile devices  
D) Inline styles have lower specificity than external stylesheets

---

**49.** What capability does the DOM interface `addEventListener()` provide that inline event handlers (like `onclick`) do NOT?

A) The ability to respond to click events  
B) The ability to attach multiple handlers and remove them later  
C) Faster execution  
D) Access to the event object

---

**50.** In the DOM, what is the difference between `event.target` and `event.currentTarget`?

A) They are always the same element  
B) `target` is the element that triggered the event; `currentTarget` is the element with the attached listener  
C) `currentTarget` is the element that triggered the event; `target` is the element with the attached listener  
D) `currentTarget` only exists during the capturing phase

---

# Answer Key

|Q#|Answer|Q#|Answer|Q#|Answer|Q#|Answer|Q#|Answer|
|---|---|---|---|---|---|---|---|---|---|
|1|C|11|D|21|B|31|B|41|B|
|2|B|12|B|22|B|32|B|42|B|
|3|C|13|A|23|B|33|C|43|B|
|4|B|14|B|24|A|34|B|44|B|
|5|B|15|D|25|B|35|B|45|B|
|6|A|16|B|26|B|36|C|46|B|
|7|A|17|C|27|B|37|B|47|B|
|8|C|18|B|28|B|38|A|48|A|
|9|B|19|D|29|B|39|B|49|B|
|10|B|20|B|30|C|40|D|50|B|

---

# Detailed Explanations

## Question 21 Explanation (Your Example Question)

**Container: 500px wide** **Items: `flex: 1 1 200px`** (flex-grow: 1, flex-shrink: 1, flex-basis: 200px) **Wrap: enabled**

**Calculation:**

- Total basis of 3 items = 3 × 200px = 600px
- Container width = 500px
- Since 600px > 500px and `flex-wrap: wrap` is enabled, items wrap

**Row 1:** Two items fit (2 × 200px = 400px < 500px)

- Extra space = 500px - 400px = 100px
- Both items have flex-grow: 1, so they share the extra space
- Each item becomes 250px

**Row 2:** One item alone

- It has flex-grow: 1 and can expand to fill the row
- This item becomes 500px

**Answer: B**

---

## Question 11 Explanation (Specificity)

```css
p { color: black; }           /* Specificity: 0-0-0-1 */
.highlight { color: green; }  /* Specificity: 0-0-1-0 */
#note { color: red; }         /* Specificity: 0-1-0-0 */
p#note.highlight { color: orange; } /* Specificity: 0-1-1-1 */
```

The selector `p#note.highlight` has the highest specificity (111 when simplified), so the text will be **orange**.

---

## Question 30 Explanation (Temporal Dead Zone)

Variables declared with `let` and `const` are hoisted but NOT initialized. Accessing them before their declaration results in a `ReferenceError` due to the "Temporal Dead Zone" (TDZ).

With `var`, the variable is hoisted AND initialized to `undefined`, so accessing it before the declaration returns `undefined`.

---

## Question 36 Explanation (Event Propagation)

The correct order is:

1. **Capturing Phase**: Event travels DOWN from `document` → target's ancestors → target
2. **Target Phase**: Event reaches the element that triggered it
3. **Bubbling Phase**: Event travels UP from target → ancestors → `document`

By default, event listeners are attached to the bubbling phase. Use `{ capture: true }` to attach to the capturing phase.