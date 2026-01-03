# Advanced UI Development Quiz - Set 2

## Lectures 1-8: HTML, CSS, JavaScript, Tailwind & DOM

### 25 Advanced Questions

---

## Section A: CSS Box Model, Units & Positioning (Q1-7)

**1.** An element has the following CSS applied with the default `box-sizing: content-box`. What is the TOTAL horizontal space this element occupies?

```css
.box {
  width: 300px;
  padding: 20px;
  border: 5px solid black;
  margin: 15px;
}
```

A) 300px  
B) 350px  
C) 380px  
D) 380px (width) + 30px (margins) = total space affected is 410px

---

**2.** What is the key difference between `em` and `rem` units in CSS?

A) `em` is relative to the root element; `rem` is relative to the parent element  
B) `em` is relative to the parent element's font-size; `rem` is relative to the root (`<html>`) element's font-size  
C) They are identical and interchangeable  
D) `em` is an absolute unit; `rem` is a relative unit

---

**3.** A child element has `position: absolute` with `top: 50px`. The parent has NO positioning set. Where will the child be positioned?

```html
<div class="parent">
  <div class="child">Content</div>
</div>
```

```css
.parent { /* no position property */ }
.child { position: absolute; top: 50px; left: 20px; }
```

A) 50px from the top of its parent  
B) 50px from the top of the nearest positioned ancestor, or the viewport/initial containing block  
C) 50px from its normal flow position  
D) The element won't move without a positioned parent

---

**4.** What is the difference between `position: relative` and `position: absolute` regarding the element's original space in the document flow?

A) Both preserve the element's original space  
B) Both remove the element from the normal flow  
C) `relative` preserves original space; `absolute` removes it from the flow  
D) `relative` removes from flow; `absolute` preserves space

---

**5.** Which CSS property and value would you use to make a background image cover the entire element while maintaining its aspect ratio, potentially cropping parts of the image?

A) `background-size: contain`  
B) `background-size: cover`  
C) `background-size: 100% 100%`  
D) `background-repeat: no-repeat`

---

**6.** In the CSS shorthand `border: 2px solid red`, what is the correct order of values?

A) color, style, width  
B) width, style, color  
C) style, width, color  
D) The order doesn't matter for this shorthand

---

**7.** What does `background-attachment: fixed` do?

A) Prevents the background image from repeating  
B) Makes the background image stay in place when the page scrolls  
C) Fixes the background image size to the element dimensions  
D) Attaches the background to the border-box

---

## Section B: CSS Selectors & Specificity (Q8-12)

**8.** For the pseudo-class selectors on links, what is the CORRECT order they must appear in CSS to work properly (LVHA)?

A) `:hover`, `:link`, `:visited`, `:active`  
B) `:link`, `:visited`, `:hover`, `:active`  
C) `:active`, `:hover`, `:visited`, `:link`  
D) Order doesn't matter for pseudo-classes

---

**9.** What will the following descendant selector target?

```css
article p span { color: red; }
```

A) All `<span>` elements that are direct children of `<p>` inside `<article>`  
B) All `<span>` elements anywhere inside `<p>` elements that are anywhere inside `<article>` elements  
C) Only the first `<span>` inside each `<p>`  
D) `<span>` elements that are siblings of `<p>` and `<article>`

---

**10.** Given the following CSS, what color will the text be?

```html
<p id="note" class="highlight">Hello World</p>
```

```css
p { color: black; }
.highlight { color: green; }
#note { color: red; }
p { color: purple !important; }
```

A) black  
B) green  
C) red  
D) purple

---

**11.** What is the specificity value of the selector `ul#nav li.active a:hover`?

A) 0-1-1-3  
B) 0-1-2-3  
C) 0-1-3-2  
D) 0-1-2-2

---

**12.** Which statement about CSS inheritance is CORRECT?

A) All CSS properties are inherited by child elements  
B) Layout properties like `margin` and `padding` are typically inherited  
C) Text-related properties like `font-family` and `color` typically inherit; layout properties typically don't  
D) Inheritance can never be overridden

---

## Section C: JavaScript Arrays, Strings & Storage (Q13-18)

**13.** What is the output of this code?

```javascript
const arr = ['red', 'green', 'blue'];
arr.shift();
arr.unshift('yellow');
console.log(arr);
```

A) `['yellow', 'red', 'green', 'blue']`  
B) `['yellow', 'green', 'blue']`  
C) `['red', 'green', 'blue', 'yellow']`  
D) `['green', 'blue', 'yellow']`

---

**14.** What is the difference between `arr.slice(1, 3)` and `arr.splice(1, 3)` on an array `['a', 'b', 'c', 'd', 'e']`?

A) Both return `['b', 'c']` and don't modify the original array  
B) `slice` returns `['b', 'c']` without modifying; `splice` returns `['b', 'c', 'd']` AND removes them from original  
C) `slice` returns `['b', 'c', 'd']`; `splice` returns `['b', 'c']`  
D) They are identical methods

---

**15.** What does the following code output?

```javascript
const colors = ['orange', 'green', 'yellow'];
console.log(colors.join(' - '));
console.log(colors.toString());
```

A) `orange - green - yellow` then `orange - green - yellow`  
B) `orange - green - yellow` then `orange,green,yellow`  
C) `orange,green,yellow` then `orange - green - yellow`  
D) Both output `['orange', 'green', 'yellow']`

---

**16.** A developer wants to store a JavaScript object in `localStorage`. Which approach is CORRECT?

```javascript
const user = { name: 'Ahmed', age: 25 };
```

A) `localStorage.setItem('user', user)`  
B) `localStorage.setItem('user', JSON.stringify(user))`  
C) `localStorage.user = user`  
D) `localStorage.setItem('user', user.toString())`

---

**17.** What is the KEY difference between `localStorage` and `sessionStorage`?

A) `localStorage` is faster than `sessionStorage`  
B) `localStorage` persists until manually cleared; `sessionStorage` clears when the tab/session ends  
C) `sessionStorage` can store more data than `localStorage`  
D) `localStorage` is only accessible in the current tab

---

**18.** What will `JSON.parse()` and `JSON.stringify()` NOT be able to handle correctly?

A) Arrays and nested objects  
B) Strings and numbers  
C) Functions, `undefined`, and `Infinity`  
D) Booleans and `null`

---

## Section D: JavaScript DOM & Events Advanced (Q19-22)

**19.** What is the output when clicking the button?

```html
<div id="outer">
  <button id="inner">Click Me</button>
</div>
<script>
document.getElementById('outer').addEventListener('click', () => {
  console.log('Outer');
}, true);

document.getElementById('inner').addEventListener('click', () => {
  console.log('Inner');
});
</script>
```

A) `Inner` then `Outer`  
B) `Outer` then `Inner`  
C) Only `Inner`  
D) Only `Outer`

---

**20.** What does `element.classList.toggle('active')` do?

A) Always adds the 'active' class  
B) Always removes the 'active' class  
C) Adds the class if not present, removes it if present  
D) Returns whether the element has the 'active' class

---

**21.** What is the difference between `innerHTML` and `innerText`?

A) They are identical  
B) `innerHTML` returns/sets HTML markup; `innerText` returns/sets only visible text content  
C) `innerText` can include HTML tags; `innerHTML` cannot  
D) `innerHTML` is faster than `innerText`

---

**22.** A developer uses `querySelectorAll('.item')` and adds a new element with class 'item' to the DOM. What happens to the NodeList returned earlier?

A) It automatically includes the new element  
B) It does NOT include the new element (static NodeList)  
C) It throws an error  
D) It depends on the browser

---

## Section E: Tailwind CSS & Responsive Design (Q23-25)

**23.** In Tailwind CSS, what does the class `hover:bg-blue-700` do?

A) Always sets background to blue-700  
B) Sets background to blue-700 only when the element is hovered  
C) Sets a blue-700 hover cursor  
D) Removes blue background on hover

---

**24.** What is the correct interpretation of `text-base md:text-lg lg:text-xl` in Tailwind?

A) Apply `text-xl` on all screens, `text-lg` on medium, `text-base` on large  
B) Apply `text-base` by default, `text-lg` on medium screens and up, `text-xl` on large screens and up  
C) Apply all three font sizes simultaneously  
D) Apply `text-base` only on small screens

---

**25.** Why can't inline CSS styles achieve what Tailwind's `hover:bg-blue-500` does?

A) Inline styles are slower  
B) Inline styles cannot define pseudo-class states like `:hover`  
C) Inline styles have lower specificity  
D) Inline styles don't support colors

---

# Answer Key

|Q#|Answer|Q#|Answer|Q#|Answer|Q#|Answer|Q#|Answer|
|---|---|---|---|---|---|---|---|---|---|
|1|D|6|B|11|B|16|B|21|B|
|2|B|7|B|12|C|17|B|22|B|
|3|B|8|B|13|B|18|C|23|B|
|4|C|9|B|14|B|19|B|24|B|
|5|B|10|D|15|B|20|C|25|B|

---

# Detailed Explanations

## Question 1: Box Model Calculation

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  box-sizing: content-box (DEFAULT)                               │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │  margin: 15px                                            │    │
│  │  ┌────────────────────────────────────────────────────┐  │    │
│  │  │  border: 5px                                       │  │    │
│  │  │  ┌──────────────────────────────────────────────┐  │  │    │
│  │  │  │  padding: 20px                               │  │  │    │
│  │  │  │  ┌────────────────────────────────────────┐  │  │  │    │
│  │  │  │  │                                        │  │  │  │    │
│  │  │  │  │         content: 300px                 │  │  │  │    │
│  │  │  │  │                                        │  │  │  │    │
│  │  │  │  └────────────────────────────────────────┘  │  │  │    │
│  │  │  └──────────────────────────────────────────────┘  │  │    │
│  │  └────────────────────────────────────────────────────┘  │    │
│  └──────────────────────────────────────────────────────────┘    │
│                                                                  │
│  CALCULATION:                                                    │
│  Content width:     300px                                        │
│  + Padding (both):  40px  (20px × 2)                             │
│  + Border (both):   10px  (5px × 2)                              │
│  ─────────────────────────                                       │
│  Element width:     350px                                        │
│                                                                  │
│  + Margin (both):   30px  (15px × 2)                             │
│  ─────────────────────────                                       │
│  Total space:       380px (element) + 30px (margins) = 410px     │
│                                                                  │
│  Answer D: The element is 380px wide; total affected space is    │
│            410px including margins.                              │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 2: `em` vs `rem` Units

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  em - Relative to PARENT element's font-size                     │
│  rem - Relative to ROOT (<html>) element's font-size             │
│                                                                  │
│  html { font-size: 16px; }     /* 1rem = 16px everywhere */      │
│                                                                  │
│  .parent {                                                       │
│    font-size: 20px;            /* 1em = 20px for children */     │
│  }                                                               │
│                                                                  │
│  .child {                                                        │
│    font-size: 1.5em;           /* 1.5 × 20px = 30px */           │
│    padding: 1rem;              /* 1 × 16px = 16px */             │
│  }                                                               │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐     │
│  │  USE CASES:                                             │     │
│  │                                                         │     │
│  │  rem: Consistent spacing/sizing across entire site      │     │
│  │       Easy to scale entire site by changing root size   │     │
│  │                                                         │     │
│  │  em:  Component-relative sizing                         │     │
│  │       Padding that scales with text size               │     │
│  └─────────────────────────────────────────────────────────┘     │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 3: Absolute Positioning Without Positioned Parent

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  When an element has position: absolute:                         │
│                                                                  │
│  1. Browser looks for nearest ancestor with:                     │
│     position: relative | absolute | fixed                        │
│                                                                  │
│  2. If NO positioned ancestor exists:                            │
│     → Element positions relative to the INITIAL CONTAINING BLOCK │
│     → (the viewport/document)                                    │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐     │
│  │  Viewport/Document                                      │     │
│  │  ┌────────────────────────────────────────────────┐     │     │
│  │  │                                                │     │     │
│  │  │  .parent (no position)                         │     │     │
│  │  │  ┌────────────────────────────────────┐        │     │     │
│  │  │  │  (parent content)                  │        │     │     │
│  │  │  └────────────────────────────────────┘        │     │     │
│  │  │                                                │     │     │
│  │  └────────────────────────────────────────────────┘     │     │
│  │                                                         │     │
│  │  ┌─────────────────┐ ← .child (position: absolute)      │     │
│  │  │ top: 50px       │    Positioned from VIEWPORT,       │     │
│  │  │ left: 20px      │    NOT from parent!                │     │
│  │  └─────────────────┘                                    │     │
│  │                                                         │     │
│  └─────────────────────────────────────────────────────────┘     │
│                                                                  │
│  FIX: Add position: relative to parent to contain the child      │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 8: LVHA Order for Link Pseudo-classes

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  The order matters because of CSS specificity and cascade:       │
│                                                                  │
│  L - :link    (unvisited links)                                  │
│  V - :visited (visited links)                                    │
│  H - :hover   (mouse over)                                       │
│  A - :active  (being clicked)                                    │
│                                                                  │
│  WHY THIS ORDER?                                                 │
│  ─────────────────                                               │
│  All four have EQUAL specificity (0-0-1-1 for a:pseudo)          │
│  So the LAST matching rule wins.                                 │
│                                                                  │
│  If :hover comes before :link/:visited:                          │
│  a:hover { color: red; }                                         │
│  a:link { color: blue; }   ← This wins! Hover broken             │
│                                                                  │
│  CORRECT ORDER:                                                  │
│  a:link { color: blue; }                                         │
│  a:visited { color: purple; }                                    │
│  a:hover { color: red; }   ← Overrides link/visited on hover     │
│  a:active { color: orange; } ← Overrides hover while clicking    │
│                                                                  │
│  Memory trick: "LoVe HAte" or "Las Vegas? Hell, Awesome!"        │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 14: `slice()` vs `splice()`

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  const arr = ['a', 'b', 'c', 'd', 'e'];                          │
│              [0]  [1]  [2]  [3]  [4]                             │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  SLICE(start, end) - "Slices" out a copy, doesn't modify         │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  arr.slice(1, 3)                                                 │
│  │         │  │                                                  │
│  │         │  └── end (exclusive): stop before index 3           │
│  │         └───── start: begin at index 1                        │
│  │                                                               │
│  Returns: ['b', 'c']  (indices 1 and 2)                          │
│  Original arr: ['a', 'b', 'c', 'd', 'e'] (UNCHANGED)             │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  SPLICE(start, deleteCount) - "Splices" out, MODIFIES original   │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  arr.splice(1, 3)                                                │
│  │          │  │                                                 │
│  │          │  └── deleteCount: remove 3 elements                │
│  │          └───── start: begin at index 1                       │
│  │                                                               │
│  Returns: ['b', 'c', 'd']  (3 elements starting from index 1)    │
│  Original arr: ['a', 'e'] (MODIFIED!)                            │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │  SUMMARY:                                                │    │
│  │  slice = "safe copy" (doesn't mutate)                    │    │
│  │  splice = "surgery" (mutates the array)                  │    │
│  └──────────────────────────────────────────────────────────┘    │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 16: Storing Objects in localStorage

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  localStorage can ONLY store STRINGS                             │
│                                                                  │
│  const user = { name: 'Ahmed', age: 25 };                        │
│                                                                  │
│  ═══════════════════════════════════════════════════════════     │
│  WRONG APPROACHES:                                               │
│  ═══════════════════════════════════════════════════════════     │
│                                                                  │
│  localStorage.setItem('user', user);                             │
│  // Stores: "[object Object]" ← useless string representation    │
│                                                                  │
│  localStorage.user = user;                                       │
│  // Same problem: "[object Object]"                              │
│                                                                  │
│  ═══════════════════════════════════════════════════════════     │
│  CORRECT APPROACH:                                               │
│  ═══════════════════════════════════════════════════════════     │
│                                                                  │
│  // STORING:                                                     │
│  localStorage.setItem('user', JSON.stringify(user));             │
│  // Stores: '{"name":"Ahmed","age":25}'                          │
│                                                                  │
│  // RETRIEVING:                                                  │
│  const retrieved = JSON.parse(localStorage.getItem('user'));     │
│  // Returns: { name: 'Ahmed', age: 25 } ← proper object!         │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │  Pattern:                                                │    │
│  │  Save:    JSON.stringify(object) → localStorage          │    │
│  │  Load:    localStorage → JSON.parse(string)              │    │
│  └──────────────────────────────────────────────────────────┘    │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 19: Event Capturing vs Bubbling

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  The outer listener has { capture: true } - CAPTURING PHASE      │
│  The inner listener has no third param - BUBBLING PHASE (default)│
│                                                                  │
│  When button is clicked:                                         │
│                                                                  │
│  PHASE 1: CAPTURING (top → down)                                 │
│  ┌───────────────────────────────────────────────────┐           │
│  │  document                                         │           │
│  │       │                                           │           │
│  │       ↓ ①                                         │           │
│  │  ┌─────────────────────────────────────────────┐  │           │
│  │  │  #outer (capture: true)                     │  │           │
│  │  │  ══════════════════════                     │  │           │
│  │  │  console.log('Outer')  ← EXECUTES HERE!     │  │           │
│  │  │       │                                     │  │           │
│  │  │       ↓ ②                                   │  │           │
│  │  │  ┌─────────────────────────────────────┐    │  │           │
│  │  │  │  #inner (button) - TARGET           │    │  │           │
│  │  │  └─────────────────────────────────────┘    │  │           │
│  │  └─────────────────────────────────────────────┘  │           │
│  └───────────────────────────────────────────────────┘           │
│                                                                  │
│  PHASE 2: TARGET                                                 │
│  ┌─────────────────────────────────────┐                         │
│  │  #inner (button)                    │                         │
│  │  console.log('Inner') ← EXECUTES!   │                         │
│  └─────────────────────────────────────┘                         │
│                                                                  │
│  OUTPUT: "Outer" then "Inner"                                    │
│                                                                  │
│  (If both were bubbling: would be "Inner" then "Outer")          │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 24: Tailwind Responsive Breakpoints

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  class="text-base md:text-lg lg:text-xl"                         │
│                                                                  │
│  Tailwind uses MOBILE-FIRST approach:                            │
│  Breakpoint prefixes mean "at this size AND ABOVE"               │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  Screen Size:    0px ─────────────────────────────→ ∞      │  │
│  │                                                            │  │
│  │                  │         │              │                │  │
│  │                  │   md    │     lg       │                │  │
│  │                  │  768px  │   1024px     │                │  │
│  │                  │         │              │                │  │
│  │  Applied:        │         │              │                │  │
│  │                  │         │              │                │  │
│  │  text-base ──────┼─────────┼──────────────┼────────────    │  │
│  │  (default)       │         │              │                │  │
│  │                  │         │              │                │  │
│  │           md:text-lg ──────┼──────────────┼────────────    │  │
│  │           (overrides)      │              │                │  │
│  │                            │              │                │  │
│  │                     lg:text-xl ───────────┼────────────    │  │
│  │                     (overrides)           │                │  │
│  │                                           │                │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  RESULT:                                                         │
│  • Small screens (< 768px): text-base                            │
│  • Medium screens (≥ 768px): text-lg                             │
│  • Large screens (≥ 1024px): text-xl                             │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 25: Why Inline Styles Can't Do Hover

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  INLINE STYLES LIMITATION:                                       │
│                                                                  │
│  <!-- This is ALL you can do with inline -->                     │
│  <p style="color: red;">Text</p>                                 │
│                                                                  │
│  <!-- THIS IS IMPOSSIBLE with inline: -->                        │
│  <p style="color: red; :hover { color: blue; }">Text</p>         │
│  <!-- ❌ INVALID SYNTAX! -->                                     │
│                                                                  │
│  WHY?                                                            │
│  ─────                                                           │
│  Inline styles only accept property:value pairs.                 │
│  They CANNOT define:                                             │
│  • Pseudo-classes (:hover, :focus, :active)                      │
│  • Pseudo-elements (::before, ::after)                           │
│  • Media queries (@media)                                        │
│  • Keyframe animations (@keyframes)                              │
│                                                                  │
│  ═══════════════════════════════════════════════════════════     │
│  TAILWIND SOLUTION:                                              │
│  ═══════════════════════════════════════════════════════════     │
│                                                                  │
│  <p class="text-red-500 hover:text-blue-500">Text</p>            │
│                                                                  │
│  Tailwind generates actual CSS:                                  │
│  .text-red-500 { color: #ef4444; }                               │
│  .hover\:text-blue-500:hover { color: #3b82f6; }                 │
│                                                                  │
│  This IS real CSS that can use pseudo-classes!                   │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```