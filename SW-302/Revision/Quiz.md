# Advanced React Quiz - Lectures 9 & 10 (HARD MODE)

## React Introduction & React Continued

### 20 Complex Questions with Tricky Scenarios

---

## Section A: React Core Concepts (Q1-5)

**1.** What will be logged to the console?

```jsx
function App() {
  console.log("1: App render");
  
  const [count, setCount] = useState(() => {
    console.log("2: useState initializer");
    return 0;
  });
  
  useEffect(() => {
    console.log("3: useEffect");
  }, []);
  
  return <div>{count}</div>;
}
```

A) 1, 2, 3  
B) 2, 1, 3  
C) 1, 3, 2  
D) 2, 1, 3, 1 (renders twice in StrictMode)

---

**2.** A component re-renders. Which of these will NOT cause a re-render?

A) Calling `setState` with a new value  
B) Parent component re-rendering  
C) Changing a `useRef` value  
D) Context value changing

---

**3.** What's wrong with this code?

```jsx
function UserList() {
  const [users, setUsers] = useState([]);
  
  useEffect(() => {
    fetch('/api/users')
      .then(res => res.json())
      .then(data => setUsers(data));
  });
  
  return <ul>{users.map(u => <li key={u.id}>{u.name}</li>)}</ul>;
}
```

A) Missing `key` prop on list items  
B) Missing dependency array causes infinite fetch loop  
C) `fetch` should be `await`ed  
D) `setUsers` should be inside a callback

---

**4.** What is the value of `count` displayed after clicking the button?

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(count + 1);
    setCount(prev => prev + 1);
    setCount(42);
  };
  
  return <button onClick={handleClick}>{count}</button>;
}
```

A) 2  
B) 42  
C) 43  
D) 1

---

**5.** Which statement about the Virtual DOM is FALSE?

A) React creates a new Virtual DOM tree on every render  
B) The Virtual DOM is a lightweight JavaScript object representation  
C) React updates the real DOM synchronously after every state change  
D) React batches multiple DOM updates for better performance

---

## Section B: useState Deep Dive (Q6-9)

**6.** What happens when this component renders?

```jsx
function BrokenCounter() {
  let count = 0;
  
  const increment = () => {
    count = count + 1;
    console.log(count);
  };
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={increment}>+1</button>
    </div>
  );
}
```

A) Clicking increments the displayed count normally  
B) Console shows incrementing values, but display stays 0  
C) Both console and display show incrementing values  
D) Error: cannot reassign const variable

---

**7.** What will `items` contain after clicking "Add" twice?

```jsx
function List() {
  const [items, setItems] = useState([1, 2, 3]);
  
  const addItem = () => {
    items.push(items.length + 1);
    setItems(items);
  };
  
  return (
    <div>
      <button onClick={addItem}>Add</button>
      <p>{items.join(', ')}</p>
    </div>
  );
}
```

A) [1, 2, 3, 4, 5]  
B) [1, 2, 3] (no change displayed)  
C) [1, 2, 3, 4]  
D) Error: cannot push to state

---

**8.** What's the correct way to update an object in state?

```jsx
const [user, setUser] = useState({ name: 'Ahmed', age: 25 });

// Update age to 26
```

A) `setUser({ age: 26 })`  
B) `user.age = 26; setUser(user)`  
C) `setUser({ ...user, age: 26 })`  
D) `setUser(prev => { prev.age = 26; return prev })`

---

**9.** What is logged when the button is clicked?

```jsx
function Example() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(1);
    setCount(2);
    setCount(3);
    console.log(count);
  };
  
  return <button onClick={handleClick}>Click</button>;
}
```

A) 3  
B) 0  
C) 1  
D) undefined

---

## Section C: Props & Component Communication (Q10-13)

**10.** What will be rendered?

```jsx
function Child({ data = [] }) {
  return <p>Length: {data.length}</p>;
}

function Parent() {
  return (
    <>
      <Child data={null} />
      <Child data={undefined} />
      <Child />
    </>
  );
}
```

A) Length: 0, Length: 0, Length: 0  
B) Error on first Child (null.length)  
C) Length: 0 for all, but first causes error  
D) Error: null, Length: 0, Length: 0

---

**11.** In this code, what value does the child receive for `onClick`?

```jsx
function Parent() {
  const handleClick = () => console.log("clicked");
  
  return <Child onClick={handleClick()} />;
}
```

A) A function that logs "clicked"  
B) `undefined` (function is called immediately, returns nothing)  
C) The string "clicked"  
D) An error is thrown

---

**12.** How can a child component update state that lives in a grandparent, skipping the parent?

A) Directly import and call the grandparent's setState  
B) Use useContext to share the setState function  
C) Modify props directly since they're objects  
D) This is impossible in React

---

**13.** What's the output?

```jsx
function Outer() {
  const [value, setValue] = useState("initial");
  
  return (
    <Inner 
      text={value} 
      onChange={(newVal) => setValue(newVal)} 
    />
  );
}

function Inner({ text, onChange }) {
  useEffect(() => {
    onChange("updated");
  }, []);
  
  return <p>{text}</p>;
}
```

A) "initial" forever (missing onChange in deps)  
B) "updated" (after effect runs)  
C) Infinite loop  
D) "initial" then "updated"

---

## Section D: Lists, Keys & Conditional Rendering (Q14-16)

**14.** Which key strategy causes the MOST problems?

```jsx
// Option A
{items.map((item, i) => <Item key={i} data={item} />)}

// Option B  
{items.map(item => <Item key={item.id} data={item} />)}

// Option C
{items.map(item => <Item key={Math.random()} data={item} />)}

// Option D
{items.map(item => <Item key={item.name} data={item} />)}
```

A) Option A (index keys)  
B) Option B (id keys)  
C) Option C (random keys)  
D) Option D (name keys)

---

**15.** What renders when `count = 0`?

```jsx
function Display({ count }) {
  return (
    <div>
      {count && <p>Count: {count}</p>}
    </div>
  );
}
```

A) Nothing (empty div)  
B) `<p>Count: 0</p>`  
C) The number `0` is rendered (falsy but not false)  
D) Error: count is falsy

---

**16.** What's the issue with this conditional rendering?

```jsx
function UserProfile({ user }) {
  return (
    <div>
      <h1>{user.name}</h1>
      {user.isAdmin && (
        const adminBadge = "★";
        <span>{adminBadge} Admin</span>
      )}
    </div>
  );
}
```

A) Missing key prop  
B) Cannot declare variables inside JSX expressions  
C) `isAdmin` should use ternary operator  
D) Fragment is required for multiple elements

---

## Section E: useEffect & Lifecycle (Q17-19)

**17.** How many times does "Effect!" log when count goes from 0→1→2→1?

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    console.log("Effect!");
    return () => console.log("Cleanup!");
  }, [count]);
  
  // ... buttons to change count
}
```

A) 3 times (initial + 2 changes)  
B) 4 times (initial + 3 changes)  
C) 2 times (only when count changes)  
D) 1 time (empty dependency would run once)

---

**18.** What's the execution order when component mounts?

```jsx
function Example() {
  console.log("A: Render");
  
  useEffect(() => {
    console.log("B: Effect 1");
    return () => console.log("C: Cleanup 1");
  }, []);
  
  useEffect(() => {
    console.log("D: Effect 2");
  }, []);
  
  return <div>Hello</div>;
}
```

A) A, B, C, D  
B) A, B, D  
C) A, D, B  
D) B, D, A

---

**19.** This code has a bug. What is it?

```jsx
function Timer() {
  const [seconds, setSeconds] = useState(0);
  
  useEffect(() => {
    const id = setInterval(() => {
      setSeconds(seconds + 1);
    }, 1000);
    
    return () => clearInterval(id);
  }, []);
  
  return <p>Seconds: {seconds}</p>;
}
```

A) Missing `seconds` in dependency array  
B) `clearInterval` is in wrong place  
C) Closure captures stale `seconds` (always 0), should use `setSeconds(s => s + 1)`  
D) Both A and C describe the same bug

---

## Section F: useContext (Q20)

**20.** What happens when `<Profile />` renders?

```jsx
const UserContext = createContext({ name: "Guest" });

function App() {
  return (
    <div>
      <Profile />
      <UserContext.Provider value={{ name: "Ahmed" }}>
        <Dashboard />
      </UserContext.Provider>
    </div>
  );
}

function Profile() {
  const user = useContext(UserContext);
  return <h1>Hello, {user.name}</h1>;
}

function Dashboard() {
  const user = useContext(UserContext);
  return <h2>Welcome, {user.name}</h2>;
}
```

A) Profile: "Hello, Ahmed", Dashboard: "Welcome, Ahmed"  
B) Profile: "Hello, Guest", Dashboard: "Welcome, Ahmed"  
C) Both show "Guest" (Provider is below them)  
D) Error: Profile is outside Provider

---

# Answer Key

|Q#|Answer|Q#|Answer|
|---|---|---|---|
|1|A|11|B|
|2|C|12|B|
|3|B|13|D|
|4|B|14|C|
|5|C|15|C|
|6|B|16|B|
|7|B|17|B|
|8|C|18|B|
|9|B|19|D|
|10|D|20|B|

---

# Detailed Explanations

## Question 1: Execution Order

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  EXECUTION ORDER: A, B, C → Answer: A (1, 2, 3)                  │
│                                                                  │
│  Timeline:                                                       │
│  ─────────────────────────────────────────────────────────────   │
│                                                                  │
│  1. Component function called                                    │
│     │                                                            │
│     ├──→ console.log("1: App render")     ← FIRST               │
│     │                                                            │
│     ├──→ useState initializer runs                               │
│     │    └──→ console.log("2: useState")  ← SECOND              │
│     │                                                            │
│     └──→ Return JSX (component renders to DOM)                   │
│                                                                  │
│  2. After DOM update, effects run                                │
│     │                                                            │
│     └──→ console.log("3: useEffect")      ← THIRD               │
│                                                                  │
│  Note: useState initializer only runs on FIRST render            │
│  Note: In StrictMode (dev), may see double render                │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 4: Multiple setState Calls

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  const handleClick = () => {                                     │
│    setCount(count + 1);      // Queue: set to 0+1 = 1            │
│    setCount(prev => prev+1); // Queue: set to 1+1 = 2            │
│    setCount(42);             // Queue: set to 42     ← WINS!     │
│  };                                                              │
│                                                                  │
│  React processes the queue:                                      │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │  Initial: count = 0                                        │  │
│  │                                                            │  │
│  │  Update 1: setCount(count + 1)                             │  │
│  │            count + 1 = 0 + 1 = 1                           │  │
│  │            Pending: 1                                      │  │
│  │                                                            │  │
│  │  Update 2: setCount(prev => prev + 1)                      │  │
│  │            prev = 1 (from pending)                         │  │
│  │            prev + 1 = 2                                    │  │
│  │            Pending: 2                                      │  │
│  │                                                            │  │
│  │  Update 3: setCount(42)                                    │  │
│  │            Direct value replaces everything                │  │
│  │            Pending: 42                                     │  │
│  │                                                            │  │
│  │  Final: count = 42                                         │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ANSWER: B (42)                                                  │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 7: Mutating State Directly

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  THE BUG:                                                        │
│                                                                  │
│  const addItem = () => {                                         │
│    items.push(items.length + 1);  // ❌ Mutates the array        │
│    setItems(items);               // ❌ Same reference!          │
│  };                                                              │
│                                                                  │
│  WHAT HAPPENS:                                                   │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  items = [1, 2, 3]  (reference: 0x001)                     │  │
│  │                                                            │  │
│  │  items.push(4)                                             │  │
│  │  items = [1, 2, 3, 4]  (still reference: 0x001)            │  │
│  │                                                            │  │
│  │  setItems(items)                                           │  │
│  │  React compares: 0x001 === 0x001  → TRUE                   │  │
│  │  React thinks: "Same reference, no change, skip render!"   │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  The array IS modified internally, but React doesn't see it      │
│  Display stays [1, 2, 3] even though items = [1,2,3,4,5]         │
│                                                                  │
│  ANSWER: B                                                       │
│                                                                  │
│  FIX: setItems([...items, items.length + 1])                     │
│       Creates NEW array reference                                │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 9: Logging State After setState

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  const handleClick = () => {                                     │
│    setCount(1);                                                  │
│    setCount(2);                                                  │
│    setCount(3);                                                  │
│    console.log(count);  // What is logged?                       │
│  };                                                              │
│                                                                  │
│  KEY CONCEPT: setState is ASYNCHRONOUS                           │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  When handleClick runs:                                    │  │
│  │                                                            │  │
│  │  • count = 0 (captured in closure at start)                │  │
│  │  • setCount(1) → queued                                    │  │
│  │  • setCount(2) → queued                                    │  │
│  │  • setCount(3) → queued                                    │  │
│  │  • console.log(count) → logs 0 (closure value!)            │  │
│  │                                                            │  │
│  │  AFTER function completes:                                 │  │
│  │  • React processes queue                                   │  │
│  │  • count becomes 3                                         │  │
│  │  • Component re-renders                                    │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ANSWER: B (0)                                                   │
│                                                                  │
│  The console.log runs BEFORE React updates state                 │
│  It sees the OLD value from the closure                          │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 10: Default Props with null vs undefined

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  function Child({ data = [] }) { ... }                           │
│                                                                  │
│  DEFAULT PARAMETERS ONLY APPLY TO undefined, NOT null!           │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  <Child data={null} />                                     │  │
│  │  • data = null (explicit value, not undefined)             │  │
│  │  • Default [] is NOT applied                               │  │
│  │  • null.length → ERROR! 💥                                 │  │
│  │                                                            │  │
│  │  <Child data={undefined} />                                │  │
│  │  • data = undefined                                        │  │
│  │  • Default [] IS applied → data = []                       │  │
│  │  • [].length = 0 ✓                                         │  │
│  │                                                            │  │
│  │  <Child />                                                 │  │
│  │  • data = undefined (not passed)                           │  │
│  │  • Default [] IS applied → data = []                       │  │
│  │  • [].length = 0 ✓                                         │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ANSWER: D (Error on first, then Length: 0, Length: 0)           │
│                                                                  │
│  LESSON: null !== undefined for default parameters               │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 14: Key Strategy Problems

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  RANKING KEY STRATEGIES (worst to best):                         │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  WORST: Option C - Math.random()                                 │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  {items.map(item => <Item key={Math.random()} />)}               │
│                                                                  │
│  Problems:                                                       │
│  • NEW key every render → React destroys/recreates ALL items     │
│  • Component state is lost on every render                       │
│  • Animations break                                              │
│  • Performance is terrible                                       │
│  • Input focus is lost                                           │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  BAD: Option A - Index keys (problematic when reordering)        │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  RISKY: Option D - name keys (not guaranteed unique)             │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  ══════════════════════════════════════════════════════════════  │
│  BEST: Option B - Unique ID keys ✓                               │
│  ══════════════════════════════════════════════════════════════  │
│                                                                  │
│  ANSWER: C (random keys are worst)                               │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 15: Falsy Value Gotcha with &&

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  {count && <p>Count: {count}</p>}                                │
│                                                                  │
│  When count = 0:                                                 │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  JavaScript && operator:                                   │  │
│  │  • If left side is falsy, RETURN left side                 │  │
│  │  • If left side is truthy, RETURN right side               │  │
│  │                                                            │  │
│  │  0 && <p>Count: 0</p>                                      │  │
│  │  │                                                         │  │
│  │  └→ 0 is falsy, so && returns 0                            │  │
│  │                                                            │  │
│  │  React renders: 0 (the number!)                            │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ANSWER: C (the number 0 is rendered)                            │
│                                                                  │
│  React renders:                                                  │
│  • false, null, undefined → nothing                              │
│  • 0, NaN → THE ACTUAL VALUE (renders "0")                       │
│                                                                  │
│  FIX: {count > 0 && <p>Count: {count}</p>}                       │
│       or: {count ? <p>Count: {count}</p> : null}                 │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 19: Stale Closure in useEffect

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  useEffect(() => {                                               │
│    const id = setInterval(() => {                                │
│      setSeconds(seconds + 1);  // ❌ BUG HERE                    │
│    }, 1000);                                                     │
│    return () => clearInterval(id);                               │
│  }, []);  // Empty deps = effect runs once                       │
│                                                                  │
│  THE PROBLEM: STALE CLOSURE                                      │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  Initial render: seconds = 0                               │  │
│  │                                                            │  │
│  │  useEffect runs (once, because []):                        │  │
│  │  • Creates interval                                        │  │
│  │  • Callback captures seconds = 0 in closure                │  │
│  │                                                            │  │
│  │  Every second:                                             │  │
│  │  • setSeconds(seconds + 1)                                 │  │
│  │  • setSeconds(0 + 1) = 1                                   │  │
│  │  • ALWAYS sets to 1! seconds is forever 0 in closure       │  │
│  │                                                            │  │
│  │  Display: 0 → 1 → 1 → 1 → 1 → 1...                         │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  FIX: Use functional updater                                     │
│  setSeconds(s => s + 1)  // s is always current value            │
│                                                                  │
│  ANSWER: D (Both A and C describe the same underlying bug)       │
│  - Missing dep AND stale closure are the same root cause         │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Question 20: Context Provider Scope

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                  │
│  const UserContext = createContext({ name: "Guest" });           │
│                      ← Default value                             │
│                                                                  │
│  <div>                                                           │
│    <Profile />  ← OUTSIDE Provider                               │
│    <UserContext.Provider value={{ name: "Ahmed" }}>              │
│      <Dashboard />  ← INSIDE Provider                            │
│    </UserContext.Provider>                                       │
│  </div>                                                          │
│                                                                  │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │  Profile:                                                  │  │
│  │  • useContext(UserContext)                                 │  │
│  │  • No Provider above it in tree                            │  │
│  │  • Gets DEFAULT value: { name: "Guest" }                   │  │
│  │  • Renders: "Hello, Guest"                                 │  │
│  │                                                            │  │
│  │  Dashboard:                                                │  │
│  │  • useContext(UserContext)                                 │  │
│  │  • Provider IS above it in tree                            │  │
│  │  • Gets Provider value: { name: "Ahmed" }                  │  │
│  │  • Renders: "Welcome, Ahmed"                               │  │
│  │                                                            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ANSWER: B                                                       │
│  Profile: "Hello, Guest" (uses default)                          │
│  Dashboard: "Welcome, Ahmed" (uses Provider)                     │
│                                                                  │
│  KEY INSIGHT: Context only flows DOWN from Provider              │
│  Siblings outside Provider use default value                     │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```