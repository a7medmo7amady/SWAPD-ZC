# Lecture 10: React Continued - Comprehensive Deep Dive

## Table of Contents
1. [Props Deep Dive](#section-1)
2. [Props vs State](#section-2)
3. [Immutability in React](#section-3)
4. [Re-Rendering Mechanism](#section-4)
5. [Component Lifecycle](#section-5)
6. [React Router](#section-6)
7. [React Hooks Deep Dive](#section-7)
8. [useEffect Hook](#section-8)
9. [useContext Hook](#section-9)
10. [Advanced Patterns](#section-10)

---

## Section 1: Props Deep Dive {#section-1}

### Understanding Props

**Props (Properties)** are the mechanism for passing data from parent components to child components in React. They are the primary way components communicate.

**Key Characteristics of Props:**
- **Read-only/Immutable** - Cannot be modified by the receiving component
- **Unidirectional** - Flow from parent to child only
- **Any data type** - Can pass strings, numbers, objects, arrays, functions
- **Make components reusable** - Same component, different data

### Passing Data via Props

**Basic Example:**

```jsx
// Parent Component (App)
function App() {
  const fruits = ['Apple', 'Banana', 'Orange', 'Mango'];
  const heading = 'Fruit Selection';
  
  return (
    <div>
      <ListGroup items={fruits} heading={heading} />
    </div>
  );
}

// Child Component (ListGroup)
function ListGroup({ items, heading }) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  
  return (
    <div>
      <h1>{heading}</h1>
      {items.length === 0 && <p>No items available</p>}
      
      <ul>
        {items.map((item, index) => (
          <li
            key={index}
            className={selectedIndex === index ? 'active' : ''}
            onClick={() => setSelectedIndex(index)}
          >
            {item}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

**What's Happening:**
1. Parent (`App`) has the data (fruits array, heading)
2. Parent passes data as props: `items={fruits}` and `heading={heading}`
3. Child (`ListGroup`) receives props as function parameters
4. Child uses destructuring: `{ items, heading }` to extract props
5. Child renders the data dynamically

### Passing Functions via Props

**Why Pass Functions?**
To allow child components to notify parents about events or state changes.

**Complete Example:**

```jsx
// Parent Component (App)
function App() {
  const cities = ['Cairo', 'Alexandria', 'Giza', 'Luxor'];
  
  // Handler function to be passed to child
  const handleSelectItem = (item) => {
    console.log('Selected city:', item);
    alert(`You selected: ${item}`);
  };
  
  return (
    <div className="container">
      <ListGroup 
        items={cities} 
        heading="Cities" 
        onSelectItem={handleSelectItem}  // Pass function as prop
      />
    </div>
  );
}

// Child Component (ListGroup)
function ListGroup({ items, heading, onSelectItem }) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  
  const handleClick = (item, index) => {
    setSelectedIndex(index);
    
    // Call the parent's function
    if (onSelectItem) {
      onSelectItem(item);
    }
  };
  
  return (
    <div>
      <h1>{heading}</h1>
      <ul className="list-group">
        {items.map((item, index) => (
          <li
            key={index}
            className={selectedIndex === index ? 'active' : ''}
            onClick={() => handleClick(item, index)}
          >
            {item}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

**Communication Flow:**
```
Parent (App)
    ↓ (passes function via props)
Child (ListGroup)
    ↑ (calls function when event occurs)
Parent (receives notification)
```

### Props Naming Conventions

**Best Practices:**

```jsx
// ✅ GOOD - Clear, descriptive names
<Button onClick={handleClick} text="Submit" variant="primary" />

// ✅ GOOD - Event handlers prefixed with 'on'
<Form onSubmit={handleSubmit} onChange={handleChange} />

// ✅ GOOD - Boolean props prefixed with 'is' or 'has'
<Modal isOpen={true} hasCloseButton={false} />

// ❌ BAD - Unclear names
<Button click={handleClick} t="Submit" v="primary" />

// ❌ BAD - Inconsistent naming
<Form submit={handleSubmit} changeHandler={handleChange} />
```

### Default Props

**Providing Default Values:**

```jsx
// Method 1: Default parameters
function Greeting({ name = 'Guest', age = 0 }) {
  return (
    <div>
      <h1>Hello, {name}!</h1>
      <p>Age: {age}</p>
    </div>
  );
}

// Method 2: Destructuring with defaults
function Button({ text = 'Click Me', variant = 'primary' }) {
  return <button className={`btn-${variant}`}>{text}</button>;
}

// Usage
<Greeting />  // Uses defaults: Guest, 0
<Greeting name="Ahmed" />  // name="Ahmed", age=0 (default)
<Button text="Submit" />  // text="Submit", variant="primary" (default)
```

### Props Validation (Optional but Recommended)

**Using TypeScript:**

```typescript
interface ButtonProps {
  text: string;
  onClick: () => void;
  variant?: 'primary' | 'secondary' | 'danger';
  disabled?: boolean;
}

function Button({ text, onClick, variant = 'primary', disabled = false }: ButtonProps) {
  return (
    <button 
      className={`btn-${variant}`}
      onClick={onClick}
      disabled={disabled}
    >
      {text}
    </button>
  );
}
```

---

## Section 2: Props vs State {#section-2}

### Fundamental Differences

**Design Pattern: When to Use What**

| Aspect | Props | State |
|--------|-------|-------|
| **Ownership** | Passed from parent | Owned by component |
| **Mutability** | Immutable (read-only) | Mutable (can change) |
| **Source** | External (parent component) | Internal (component itself) |
| **Purpose** | Configure component | Track changing data |
| **Updates** | Parent re-renders | setState triggers re-render |
| **Scope** | Can be passed to children | Private to component |

### Visual Comparison

```jsx
// PROPS Example - Data flows IN
function UserCard({ name, email, avatar }) {
  // Cannot modify: name, email, avatar
  // These come from parent
  
  return (
    <div className="card">
      <img src={avatar} alt={name} />
      <h3>{name}</h3>
      <p>{email}</p>
    </div>
  );
}

// Usage:
<UserCard 
  name="Ahmed" 
  email="ahmed@example.com" 
  avatar="photo.jpg" 
/>
```

```jsx
// STATE Example - Data changes WITHIN component
function Counter() {
  // Component owns and controls this data
  const [count, setCount] = useState(0);
  
  // Can modify state
  const increment = () => setCount(count + 1);
  
  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={increment}>Increment</button>
    </div>
  );
}
```

### Combined Usage

**Props + State Working Together:**

```jsx
// Parent Component
function App() {
  const [score, setScore] = useState(0);
  
  const handleScoreIncrease = (points) => {
    setScore(score + points);
  };
  
  return (
    <div>
      <h1>Total Score: {score}</h1>
      <GamePanel onScore={handleScoreIncrease} />
    </div>
  );
}

// Child Component
function GamePanel({ onScore }) {
  const [level, setLevel] = useState(1);
  
  const completeLevel = () => {
    onScore(level * 10);  // Notify parent (use prop)
    setLevel(level + 1);   // Update own state
  };
  
  return (
    <div>
      <h2>Level: {level}</h2>
      <button onClick={completeLevel}>Complete Level</button>
    </div>
  );
}
```

**What's Happening:**
1. Parent has **state** for total score
2. Parent passes **function prop** to child
3. Child has its own **state** for level
4. Child calls **parent function** to update parent's state
5. Both components re-render with new values

### Decision Tree: Props or State?

```
Does the data come from parent?
    ├─ YES → Use Props
    └─ NO → Does the data change over time?
          ├─ YES → Use State
          └─ NO → Use regular variable/constant
```

**Examples:**

```jsx
// User's name from parent → PROPS
function Profile({ userName }) {
  return <h1>{userName}</h1>;
}

// Toggle visibility → STATE
function Sidebar() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div>
      <button onClick={() => setIsOpen(!isOpen)}>Toggle</button>
      {isOpen && <div>Sidebar content</div>}
    </div>
  );
}

// API endpoint URL → CONSTANT (doesn't change)
const API_URL = 'https://api.example.com';

// Fetched data → STATE (changes when loaded)
const [users, setUsers] = useState([]);
```

---

## Section 3: Immutability in React {#section-3}

### What is Immutability?

**Immutability** means not modifying existing data structures directly. Instead, create new copies with the desired changes.

**Why Immutability Matters in React:**
1. **React detects changes** by comparing object references
2. **Prevents bugs** from unexpected mutations
3. **Enables performance optimizations** (React.memo, PureComponent)
4. **Makes debugging easier** - state history is preserved
5. **Enables features like undo/redo**

### The Problem with Direct Mutation

```jsx
// ❌ WRONG - Direct mutation
function TodoList() {
  const [todos, setTodos] = useState(['Task 1', 'Task 2']);
  
  const addTodo = () => {
    todos.push('Task 3');  // MUTATING the array directly
    setTodos(todos);       // React might not detect the change!
  };
  
  return (
    <div>
      <ul>
        {todos.map((todo, i) => <li key={i}>{todo}</li>)}
      </ul>
      <button onClick={addTodo}>Add</button>
    </div>
  );
}
```

**Why This Fails:**
- `todos.push()` modifies the existing array
- The array reference stays the same
- React compares references: `oldArray === newArray` → true
- React thinks nothing changed, skips re-render
- UI doesn't update!

### Immutable Patterns

#### Arrays

**Adding Items:**

```jsx
// ✅ CORRECT - Create new array
const [items, setItems] = useState(['A', 'B']);

// Add to end
setItems([...items, 'C']);  // Spread operator

// Add to beginning
setItems(['New', ...items]);

// Add at specific position
const index = 1;
setItems([
  ...items.slice(0, index),
  'Inserted',
  ...items.slice(index)
]);
```

**Removing Items:**

```jsx
// Remove by index
const removeItem = (index) => {
  setItems(items.filter((_, i) => i !== index));
};

// Remove by value
const removeValue = (value) => {
  setItems(items.filter(item => item !== value));
};
```

**Updating Items:**

```jsx
// Update item at specific index
const updateItem = (index, newValue) => {
  setItems(items.map((item, i) => 
    i === index ? newValue : item
  ));
};

// Update based on condition
const toggleComplete = (id) => {
  setTodos(todos.map(todo =>
    todo.id === id 
      ? { ...todo, completed: !todo.completed }
      : todo
  ));
};
```

#### Objects

**Updating Properties:**

```jsx
const [user, setUser] = useState({
  name: 'Ahmed',
  age: 22,
  address: {
    city: 'Cairo',
    country: 'Egypt'
  }
});

// ❌ WRONG - Direct mutation
user.age = 23;
setUser(user);

// ✅ CORRECT - Create new object
setUser({
  ...user,
  age: 23
});

// ✅ CORRECT - Update nested property
setUser({
  ...user,
  address: {
    ...user.address,
    city: 'Alexandria'
  }
});
```

**Complete Example:**

```jsx
function UserProfile() {
  const [user, setUser] = useState({
    name: 'Ahmed',
    email: 'ahmed@example.com',
    preferences: {
      theme: 'light',
      notifications: true
    }
  });
  
  const updateEmail = (newEmail) => {
    setUser({
      ...user,
      email: newEmail
    });
  };
  
  const toggleTheme = () => {
    setUser({
      ...user,
      preferences: {
        ...user.preferences,
        theme: user.preferences.theme === 'light' ? 'dark' : 'light'
      }
    });
  };
  
  return (
    <div>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
      <p>Theme: {user.preferences.theme}</p>
      <button onClick={toggleTheme}>Toggle Theme</button>
    </div>
  );
}
```

### Complex State Updates

**Using Functional Updates:**

```jsx
// When new state depends on previous state
const [count, setCount] = useState(0);

// ❌ RISKY - May use stale state
const increment = () => {
  setCount(count + 1);
};

// ✅ SAFE - Always uses latest state
const increment = () => {
  setCount(prevCount => prevCount + 1);
};

// Array example
const addItem = (item) => {
  setItems(prevItems => [...prevItems, item]);
};

// Object example
const updateUser = (field, value) => {
  setUser(prevUser => ({
    ...prevUser,
    [field]: value
  }));
};
```

**Why Functional Updates?**
- React batches state updates
- Multiple updates might use same "current" value
- Functional form guarantees latest state

---

## Section 4: Re-Rendering Mechanism {#section-4}

### When Do Components Re-Render?

**React re-renders a component when:**

1. **State changes** (via setState)
2. **Props change** (parent passed new props)
3. **Parent re-renders** (even if props didn't change)
4. **Context value changes** (useContext)
5. **Force update** (rarely used, not recommended)

### Understanding Re-Render Behavior

```jsx
function Parent() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('Ahmed');
  
  console.log('Parent rendered');
  
  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <Child name={name} />
    </div>
  );
}

function Child({ name }) {
  console.log('Child rendered');
  return <p>Hello, {name}</p>;
}
```

**What Happens:**
1. Click increment button
2. Parent state (`count`) changes
3. Parent re-renders → logs "Parent rendered"
4. Child also re-renders → logs "Child rendered"
5. **Even though Child's props (`name`) didn't change!**

### Predicting Re-Renders

**Rule 1: State Change → Component Re-renders**

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  // Every click triggers re-render
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

**Rule 2: Parent Re-renders → All Children Re-render**

```jsx
function App() {
  const [color, setColor] = useState('red');
  
  return (
    <div>
      <Header />  {/* Re-renders even if no props */}
      <Sidebar />  {/* Re-renders even if no props */}
      <Content color={color} />  {/* Re-renders because props changed */}
    </div>
  );
}
```

**Rule 3: Same State Value → Still Re-renders**

```jsx
function Component() {
  const [count, setCount] = useState(0);
  
  // Clicking still triggers re-render, even though count stays 0
  return <button onClick={() => setCount(0)}>{count}</button>;
}
```

### Re-Render Optimization

**React.memo - Prevent Unnecessary Re-renders:**

```jsx
// Without memo - re-renders every time parent re-renders
function ExpensiveChild({ data }) {
  console.log('Expensive render');
  // Complex calculations...
  return <div>{data}</div>;
}

// With memo - only re-renders if props actually change
const ExpensiveChild = React.memo(function ExpensiveChild({ data }) {
  console.log('Expensive render');
  return <div>{data}</div>;
});
```

**When to Use React.memo:**
- Component renders often
- With same props
- Rendering is expensive
- No internal state changes

**Example with Comparison:**

```jsx
import { useState, memo } from 'react';

// Parent component
function App() {
  const [count, setCount] = useState(0);
  const [color, setColor] = useState('blue');
  
  return (
    <div>
      <h1 style={{ color }}>Count: {count}</h1>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <button onClick={() => setColor(color === 'blue' ? 'red' : 'blue')}>
        Change Color
      </button>
      
      {/* This re-renders unnecessarily */}
      <ExpensiveList items={['A', 'B', 'C']} />
      
      {/* This only re-renders when items prop changes */}
      <MemoizedList items={['A', 'B', 'C']} />
    </div>
  );
}

// Regular component - re-renders on every parent update
function ExpensiveList({ items }) {
  console.log('ExpensiveList rendered');
  return (
    <ul>
      {items.map((item, i) => <li key={i}>{item}</li>)}
    </ul>
  );
}

// Memoized component - only re-renders if items prop changes
const MemoizedList = memo(function MemoizedList({ items }) {
  console.log('MemoizedList rendered');
  return (
    <ul>
      {items.map((item, i) => <li key={i}>{item}</li>)}
    </ul>
  );
});
```

### Re-Render Performance Visualization

```
State Update
    ↓
Component Function Runs
    ↓
New Virtual DOM Created
    ↓
Diff with Old Virtual DOM
    ↓
Calculate Changes
    ↓
Update Only Changed DOM Nodes
    ↓
Browser Repaints
```

**Time Breakdown:**
```
Component render: ~5ms
Virtual DOM diff: ~2ms
DOM update: ~15ms
Browser repaint: ~20ms
Total: ~42ms (typical)
```

---

## Section 5: Component Lifecycle {#section-5}

### Three Lifecycle Phases

Every React component goes through three main phases:

1. **Mounting** - Component is created and inserted into DOM
2. **Updating** - Component re-renders due to state/props changes
3. **Unmounting** - Component is removed from DOM

### Class Component Lifecycle Methods

**Note:** Modern React uses **function components with hooks**, but understanding class lifecycle helps grasp component behavior.

#### Phase 1: Mounting (Birth)

**Methods Called (in order):**

**1. constructor()**
```javascript
class MyComponent extends React.Component {
  constructor(props) {
    super(props);
    // Initialize state
    this.state = { count: 0 };
    // Bind methods
    this.handleClick = this.handleClick.bind(this);
  }
}
```
- Runs **before** component mounts
- Initialize state
- Bind event handlers

**2. getDerivedStateFromProps()**
```javascript
static getDerivedStateFromProps(props, state) {
  // Sync state with props
  if (props.initialValue !== state.value) {
    return { value: props.initialValue };
  }
  return null;
}
```
- **Rarely used**
- Sync state with incoming props
- Runs before every render

**3. render()**
```javascript
render() {
  return <div>{this.state.count}</div>;
}
```
- **Must be pure** - no side effects
- Returns JSX
- Called every time component updates

**4. componentDidMount()**
```javascript
componentDidMount() {
  // Fetch data
  fetch('/api/data')
    .then(res => res.json())
    .then(data => this.setState({ data }));
  
  // Set up subscriptions
  window.addEventListener('resize', this.handleResize);
  
  // Start timers
  this.timer = setInterval(() => {
    this.setState({ time: new Date() });
  }, 1000);
}
```
- Runs **after** component inserted into DOM
- **Perfect for:**
  - API calls
  - Event subscriptions
  - DOM manipulation
  - Starting timers
- **Runs only once**

#### Phase 2: Updating (Growth)

**Triggered by:**
- State changes (setState)
- Props changes (parent re-rendered)
- forceUpdate() (avoid this!)

**Methods Called (in order):**

**1. getDerivedStateFromProps()**
- Same as in mounting

**2. shouldComponentUpdate()**
```javascript
shouldComponentUpdate(nextProps, nextState) {
  // Only re-render if count changed
  if (this.state.count !== nextState.count) {
    return true;
  }
  return false;
}
```
- **Performance optimization**
- Return `true` → proceed with update
- Return `false` → skip update
- Default: always returns `true`

**3. render()**
- Re-generate UI

**4. getSnapshotBeforeUpdate()**
```javascript
getSnapshotBeforeUpdate(prevProps, prevState) {
  // Capture scroll position before DOM updates
  if (prevProps.list.length < this.props.list.length) {
    const list = this.listRef.current;
    return list.scrollHeight - list.scrollTop;
  }
  return null;
}
```
- Runs **just before** DOM updates
- Capture information (scroll position, cursor, etc.)
- Value returned passed to `componentDidUpdate`

**5. componentDidUpdate()**
```javascript
componentDidUpdate(prevProps, prevState, snapshot) {
  // Fetch new data if ID changed
  if (this.props.userId !== prevProps.userId) {
    this.fetchUserData(this.props.userId);
  }
  
  // Restore scroll position
  if (snapshot !== null) {
    const list = this.listRef.current;
    list.scrollTop = list.scrollHeight - snapshot;
  }
  
  // Update DOM based on state
  if (this.state.color !== prevState.color) {
    document.body.style.backgroundColor = this.state.color;
  }
}
```
- Runs **after** DOM updates
- **Good for:**
  - Fetch data based on prop changes
  - Update DOM manually
  - Compare prev/current values
- **Avoid infinite loops** - don't setState unconditionally!

#### Phase 3: Unmounting (Death)

**componentWillUnmount()**
```javascript
componentWillUnmount() {
  // Remove event listeners
  window.removeEventListener('resize', this.handleResize);
  
  // Clear timers
  clearInterval(this.timer);
  clearTimeout(this.timeout);
  
  // Cancel API calls
  this.abortController.abort();
  
  // Unsubscribe
  this.subscription.unsubscribe();
}
```
- Runs **just before** component is removed
- **Critical for cleanup:**
  - Remove event listeners
  - Clear timers
  - Cancel pending requests
  - Unsubscribe from services
- **Prevents memory leaks**

### Complete Lifecycle Example

```javascript
class UserProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true,
      error: null
    };
  }
  
  componentDidMount() {
    console.log('Component mounted - fetching data');
    this.fetchUser(this.props.userId);
  }
  
  componentDidUpdate(prevProps) {
    // Fetch new user if ID changed
    if (this.props.userId !== prevProps.userId) {
      console.log('User ID changed - fetching new data');
      this.fetchUser(this.props.userId);
    }
  }
  
  componentWillUnmount() {
    console.log('Component unmounting - cleanup');
    // Cancel any pending requests
  }
  
  fetchUser = async (userId) => {
    this.setState({ loading: true });
    try {
      const response = await fetch(`/api/users/${userId}`);
      const user = await response.json();
      this.setState({ user, loading: false });
    } catch (error) {
      this.setState({ error: error.message, loading: false });
    }
  }
  
  render() {
    const { user, loading, error } = this.state;
    
    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!user) return null;
    
    return (
      <div>
        <h1>{user.name}</h1>
        <p>{user.email}</p>
      </div>
    );
  }
}
```

### Lifecycle in Function Components (Modern)

**Hook Equivalents:**

```jsx
import { useState, useEffect } from 'react';

function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  
  // Equivalent to componentDidMount + componentDidUpdate
  useEffect(() => {
    console.log('Fetching user');
    
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(data => {
        setUser(data);
        setLoading(false);
      });
    
    // Equivalent to componentWillUnmount
    return () => {
      console.log('Cleanup');
    };
  }, [userId]); // Re-run if userId changes
  
  if (loading) return <div>Loading...</div>;
  return <div>{user.name}</div>;
}
```

**Lifecycle Comparison Table:**

| Class Component | Function Component (Hooks) |
|----------------|---------------------------|
| constructor() | useState() |
| componentDidMount() | useEffect(() => {}, []) |
| componentDidUpdate() | useEffect(() => {}, [deps]) |
| componentWillUnmount() | useEffect(() => { return cleanup }, []) |
| shouldComponentUpdate() | React.memo() |

---

## Section 6: React Router {#section-6}

### What is React Router?

**React Router** is a client-side routing library that enables navigation between different "pages" in a single-page application (SPA) without full browser reloads.

**Key Concept:** React Router changes which component is displayed based on the URL, without requesting a new HTML page from the server.

### Why React Router?

**Problem:**
- React by default shows one component/view
- Real applications need multiple pages:
  - `/login`
  - `/dashboard`
  - `/products/123`
  - `/settings`

**Solution:**
- React Router enables navigation between views
- Maintains SPA benefits: fast, no reloads, preserves state
- URL-based navigation (bookmarkable, shareable)

### Benefits of React Router

1. **Single-Page Application (SPA)**
   - Multiple "pages" in one HTML file
   - No full page reloads
   - Smoother, faster experience

2. **JavaScript-Based Navigation**
   - Updates view using JavaScript
   - Only changed components re-render
   - Browser history maintained

3. **Cleaner Code Organization**
   - Logical page structure
   - Separate concerns
   - Easier maintenance

4. **Dynamic URLs**
   - Route parameters: `/user/:id`
   - Query strings: `/search?q=react`
   - Extract and use in components

5. **Essential for Real Applications**
   - Professional web apps need multiple pages
   - Industry standard approach

### Installing React Router

```bash
npm install react-router-dom
```

### Basic Setup

**1. Wrap App in BrowserRouter:**

```jsx
// main.jsx or index.jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
```

**2. Define Routes:**

```jsx
// App.jsx
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';

function App() {
  return (
    <div>
      <h1>My App</h1>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </div>
  );
}

export default App;
```

**3. Create Page Components:**

```jsx
// pages/Home.jsx
function Home() {
  return (
    <div>
      <h2>Home Page</h2>
      <p>Welcome to the home page!</p>
    </div>
  );
}

export default Home;

// pages/About.jsx
function About() {
  return (
    <div>
      <h2>About Page</h2>
      <p>Learn more about us.</p>
    </div>
  );
}

export default About;
```

### Navigation with Link

**Don't use `<a>` tags - use `<Link>`:**

```jsx
import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav>
      {/* ❌ WRONG - causes full page reload */}
      <a href="/">Home</a>
      
      {/* ✅ CORRECT - SPA navigation */}
      <Link to="/">Home</Link>
      <Link to="/about">About</Link>
      <Link to="/contact">Contact</Link>
    </nav>
  );
}
```

**Why Link over `<a>`?**
- `<a>` triggers browser navigation (full reload)
- `<Link>` uses JavaScript routing (no reload)
- Preserves application state
- Much faster

### Complete Example

```jsx
// App.jsx
import { Routes, Route, Link } from 'react-router-dom';
import './App.css';

// Page Components
function Home() {
  return (
    <div>
      <h2>Home Page</h2>
      <p>Welcome to our website!</p>
    </div>
  );
}

function About() {
  return (
    <div>
      <h2>About Us</h2>
      <p>We are a company building great products.</p>
    </div>
  );
}

function Products() {
  return (
    <div>
      <h2>Our Products</h2>
      <ul>
        <li>Product A</li>
        <li>Product B</li>
        <li>Product C</li>
      </ul>
    </div>
  );
}

function Contact() {
  return (
    <div>
      <h2>Contact Us</h2>
      <p>Email: info@example.com</p>
    </div>
  );
}

// Main App
function App() {
  return (
    <div className="app">
      {/* Navigation */}
      <nav className="navbar">
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/products">Products</Link>
        <Link to="/contact">Contact</Link>
      </nav>
      
      {/* Routes */}
      <main className="content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/products" element={<Products />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
```

### Dynamic Routes

**Route Parameters:**

```jsx
import { useParams } from 'react-router-dom';

// Define route with parameter
<Route path="/user/:id" element={<UserProfile />} />

// Access parameter in component
function UserProfile() {
  const { id } = useParams();
  
  return (
    <div>
      <h2>User Profile</h2>
      <p>Viewing user: {id}</p>
    </div>
  );
}

// Navigate to: /user/123
// Component displays: Viewing user: 123
```

### Nested Routes

```jsx
function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="products" element={<Products />} />
      </Route>
    </Routes>
  );
}

function Layout() {
  return (
    <div>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
      </nav>
      <Outlet />  {/* Child routes render here */}
    </div>
  );
}
```

### 404 Not Found Page

```jsx
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/about" element={<About />} />
  <Route path="*" element={<NotFound />} />  {/* Catch-all route */}
</Routes>

function NotFound() {
  return (
    <div>
      <h2>404 - Page Not Found</h2>
      <Link to="/">Go Home</Link>
    </div>
  );
}
```

---

## Section 7: React Hooks Deep Dive {#section-7}

### Classification of React Hooks

**Basic Hooks (Most Common):**

1. **useState** - Manage state
2. **useEffect** - Side effects (API calls, subscriptions, timers)
3. **useContext** - Access context values

**Additional Hooks (Advanced):**

4. **useReducer** - Complex state logic (alternative to useState)
5. **useCallback** - Memoize functions
6. **useMemo** - Memoize computed values
7. **useRef** - DOM access and persistent values
8. **useImperativeHandle** - Customize ref behavior
9. **useLayoutEffect** - Synchronous effects before paint
10. **useDebugValue** - Custom labels in React DevTools

### Quick Overview

```jsx
// useState - State management
const [count, setCount] = useState(0);

// useEffect - Side effects
useEffect(() => {
  document.title = `Count: ${count}`;
}, [count]);

// useContext - Global data
const theme = useContext(ThemeContext);

// useReducer - Complex state
const [state, dispatch] = useReducer(reducer, initialState);

// useCallback - Memoize functions
const memoizedCallback = useCallback(() => {
  doSomething(a, b);
}, [a, b]);

// useMemo - Memoize values
const memoizedValue = useMemo(() => computeExpensiveValue(a, b), [a, b]);

// useRef - DOM reference
const inputRef = useRef(null);

// useLayoutEffect - Synchronous effects
useLayoutEffect(() => {
  // Measure DOM before paint
}, []);
```

We'll explore **useEffect** and **useContext** in detail next.

---

## Section 8: useEffect Hook {#section-8}

### What is useEffect?

**useEffect** lets you run side effects in function components.

**Side Effect:** Anything that affects something outside the component or needs to run after rendering.

**Common Side Effects:**
- Fetching data from APIs
- Updating document title
- Subscribing to events
- Setting up timers
- Storing data in localStorage
- DOM manipulations

### Why useEffect?

React renders UI first, **then** you often need to do something after:

```
Render Component → Display UI → Run Side Effects
```

**Without useEffect:**
```jsx
function Component() {
  // ❌ This runs during render - BAD!
  fetch('/api/data');  // Causes problems
  
  return <div>Content</div>;
}
```

**With useEffect:**
```jsx
function Component() {
  useEffect(() => {
    // ✅ This runs after render - GOOD!
    fetch('/api/data');
  }, []);
  
  return <div>Content</div>;
}
```

### useEffect Syntax

```jsx
useEffect(() => {
  // Effect code here
  
  return () => {
    // Cleanup code (optional)
  };
}, [dependencies]);
```

**Three Parts:**
1. **Effect function** - What to run
2. **Cleanup function** (optional) - How to clean up
3. **Dependency array** - When to run

### Controlling When Effects Run

#### A) Run Once (on Mount)

```jsx
useEffect(() => {
  console.log('Component mounted');
  // Fetch initial data
  fetch('/api/users')
    .then(res => res.json())
    .then(data => setUsers(data));
}, []); // Empty array = run once
```

**Equivalent to:** `componentDidMount()`

#### B) Run When Dependencies Change

```jsx
const [userId, setUserId] = useState(1);

useEffect(() => {
  console.log('userId changed:', userId);
  fetch(`/api/users/${userId}`)
    .then(res => res.json())
    .then(data => setUser(data));
}, [userId]); // Re-run when userId changes
```

**Equivalent to:** `componentDidMount()` + `componentDidUpdate()` for userId

#### C) Run After Every Render

```jsx
useEffect(() => {
  console.log('Component rendered');
  // This runs after EVERY render
}); // No dependency array = run always
```

**Rare use case** - usually you want dependencies

### Cleanup Functions

**Why Cleanup?**
- Remove event listeners
- Cancel subscriptions
- Clear timers
- Abort API calls

**When Cleanup Runs:**
- Before re-running effect (if dependencies changed)
- Before component unmounts

```jsx
useEffect(() => {
  // Setup
  const timer = setInterval(() => {
    console.log('Tick');
  }, 1000);
  
  // Cleanup
  return () => {
    clearInterval(timer);
    console.log('Timer cleared');
  };
}, []);
```

**Flow:**
```
1. Component mounts → Effect runs → Timer starts
2. Component unmounts → Cleanup runs → Timer cleared
```

### Practical Examples

#### Example 1: Update Document Title

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    document.title = `Count: ${count}`;
  }, [count]); // Update when count changes
  
  return (
    <div>
      <h1>{count}</h1>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

#### Example 2: Keyboard Event Listener

```jsx
function KeyLogger() {
  useEffect(() => {
    const handleKeyPress = (event) => {
      console.log('Key pressed:', event.key);
    };
    
    // Add listener
    window.addEventListener('keydown', handleKeyPress);
    
    // Cleanup: remove listener
    return () => {
      window.removeEventListener('keydown', handleKeyPress);
    };
  }, []); // Run once on mount
  
  return <div>Press any key (check console)</div>;
}
```

#### Example 3: API Data Fetching

```jsx
function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    // Fetch data
    fetch('https://jsonplaceholder.typicode.com/users')
      .then(response => {
        if (!response.ok) throw new Error('Failed to fetch');
        return response.json();
      })
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []); // Run once on mount
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

### React + Tailwind + useEffect Example

**Static Cities Example:**

```jsx
import { useState, useEffect } from 'react';

function CitySelector() {
  const cities = ['Cairo', 'Alexandria', 'Giza', 'Luxor', 'Aswan'];
  const [selectedCity, setSelectedCity] = useState(null);
  
  // Effect: Runs when component mounts
  useEffect(() => {
    console.log('Component mounted - ready to select cities');
  }, []);
  
  // Effect: Runs when selectedCity changes
  useEffect(() => {
    if (selectedCity) {
      console.log('Selected city:', selectedCity);
      document.title = `City: ${selectedCity}`;
    }
  }, [selectedCity]);
  
  return (
    <div className="max-w-md mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Select a City</h1>
      
      <ul className="space-y-2">
        {cities.map((city, index) => (
          <li
            key={index}
            onClick={() => setSelectedCity(city)}
            className={`
              p-3 rounded cursor-pointer transition
              ${selectedCity === city
                ? 'bg-blue-500 text-white'
                : 'bg-gray-100 hover:bg-gray-200'
              }
            `}
          >
            {city}
          </li>
        ))}
      </ul>
      
      {selectedCity && (
        <div className="mt-4 p-4 bg-green-100 rounded">
          You selected: <strong>{selectedCity}</strong>
        </div>
      )}
    </div>
  );
}

export default CitySelector;
```

**API Fetch Version:**

```jsx
import { useState, useEffect } from 'react';

function CitySelector() {
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState(null);
  const [loading, setLoading] = useState(true);
  
  // Fetch cities from API on mount
  useEffect(() => {
    console.log('Fetching cities...');
    
    fetch('https://api.example.com/cities')
      .then(res => res.json())
      .then(data => {
        setCities(data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Error:', err);
        setLoading(false);
      });
  }, []);
  
  // Update title when city selected
  useEffect(() => {
    if (selectedCity) {
      document.title = `City: ${selectedCity.name}`;
    }
  }, [selectedCity]);
  
  if (loading) {
    return <div className="text-center p-8">Loading cities...</div>;
  }
  
  return (
    <div className="max-w-md mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Select a City</h1>
      
      <ul className="space-y-2">
        {cities.map(city => (
          <li
            key={city.id}
            onClick={() => setSelectedCity(city)}
            className={`
              p-3 rounded cursor-pointer transition
              ${selectedCity?.id === city.id
                ? 'bg-blue-500 text-white'
                : 'bg-gray-100 hover:bg-gray-200'
              }
            `}
          >
            {city.name}
          </li>
        ))}
      </ul>
      
      {selectedCity && (
        <div className="mt-4 p-4 bg-green-100 rounded">
          <p>City: <strong>{selectedCity.name}</strong></p>
          <p>Population: {selectedCity.population.toLocaleString()}</p>
        </div>
      )}
    </div>
  );
}

export default CitySelector;
```

### Lifecycle Equivalents

**Class Components vs Hooks:**

| Class Component | Function Component (useEffect) |
|----------------|-------------------------------|
| componentDidMount() | useEffect(() => {}, []) |
| componentDidUpdate() | useEffect(() => {}, [deps]) |
| componentWillUnmount() | useEffect(() => { return cleanup }, []) |

**Combined Example:**

```jsx
// Class Component
class UserProfile extends React.Component {
  componentDidMount() {
    this.fetchUser();
  }
  
  componentDidUpdate(prevProps) {
    if (this.props.userId !== prevProps.userId) {
      this.fetchUser();
    }
  }
  
  componentWillUnmount() {
    this.cancelRequest();
  }
  
  fetchUser() {
    // Fetch logic
  }
}

// Function Component (Equivalent)
function UserProfile({ userId }) {
  useEffect(() => {
    // componentDidMount + componentDidUpdate
    fetchUser(userId);
    
    // componentWillUnmount
    return () => {
      cancelRequest();
    };
  }, [userId]); // Re-run when userId changes
}
```

---

## Section 9: useContext Hook {#section-9}

### What is useContext?

**useContext** lets components access shared data without passing props through every level.

**Problem it Solves:** **Prop Drilling**

### Prop Drilling Problem

**Prop Drilling** = Passing props through multiple components just to reach a deeply nested child.

```jsx
// ❌ PROP DRILLING - Tedious and error-prone
function App() {
  const user = { name: 'Ahmed', role: 'admin' };
  
  return <Layout user={user} />;
}

function Layout({ user }) {
  // Layout doesn't use 'user', just passes it down
  return <Sidebar user={user} />;
}

function Sidebar({ user }) {
  // Sidebar doesn't use 'user', just passes it down
  return <Profile user={user} />;
}

function Profile({ user }) {
  // Finally! The component that actually needs 'user'
  return <div>Hello, {user.name}!</div>;
}
```

**Problems:**
- Layout and Sidebar don't use `user`, just pass it
- Makes components bloated
- Hard to maintain
- Error-prone (easy to forget passing props)

### Context Solution

**Context** creates a "global" data store accessible to any component.

```
App (provides data)
  ↓
Context Provider (makes data available)
  ↓
Any Component (can access data directly)
```

### Using useContext - Three Steps

#### Step 1: Create Context

```jsx
import { createContext } from 'react';

const UserContext = createContext();
```

This creates a "storage box" for shared data.

#### Step 2: Provide Context Value

```jsx
function App() {
  const user = { name: 'Ahmed', role: 'admin' };
  
  return (
    <UserContext.Provider value={user}>
      <Layout />
    </UserContext.Provider>
  );
}
```

Any component inside `<UserContext.Provider>` can access the value.

#### Step 3: Consume Context

```jsx
import { useContext } from 'react';

function Profile() {
  const user = useContext(UserContext);
  
  return <div>Hello, {user.name}!</div>;
}
```

**No prop drilling needed!** Profile directly accesses user data.

### Complete Example

```jsx
import { createContext, useContext, useState } from 'react';

// 1. Create Context
const ThemeContext = createContext();

// App Component
function App() {
  const [theme, setTheme] = useState('light');
  
  const toggleTheme = () => {
    setTheme(theme === 'light' ? 'dark' : 'light');
  };
  
  // 2. Provide Context
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      <div className={`app ${theme}`}>
        <Header />
        <Main />
        <Footer />
      </div>
    </ThemeContext.Provider>
  );
}

// Header Component
function Header() {
  // 3. Consume Context
  const { theme, toggleTheme } = useContext(ThemeContext);
  
  return (
    <header>
      <h1>My App</h1>
      <button onClick={toggleTheme}>
        Switch to {theme === 'light' ? 'dark' : 'light'} mode
      </button>
    </header>
  );
}

// Main Component
function Main() {
  const { theme } = useContext(ThemeContext);
  
  return (
    <main>
      <p>Current theme: {theme}</p>
    </main>
  );
}

// Footer Component
function Footer() {
  const { theme } = useContext(ThemeContext);
  
  return (
    <footer style={{ 
      background: theme === 'light' ? '#f0f0f0' : '#333',
      color: theme === 'light' ? '#000' : '#fff'
    }}>
      Footer content
    </footer>
  );
}

export default App;
```

**What's Happening:**
1. App creates theme state and toggleTheme function
2. App provides both via Context
3. Header, Main, Footer all access theme directly
4. No prop drilling through intermediate components!

### Context with Default Values

```jsx
// Create context with default value
const MyContext = createContext('default value');

function Component() {
  const value = useContext(MyContext);
  // If no Provider found, value = 'default value'
  return <div>{value}</div>;
}
```

**When Default is Used:**
- If component uses context but isn't wrapped in Provider
- Useful for fallback values

### Multiple Contexts

You can use multiple contexts in an app:

```jsx
const UserContext = createContext();
const ThemeContext = createContext();
const LanguageContext = createContext();

function App() {
  return (
    <UserContext.Provider value={user}>
      <ThemeContext.Provider value={theme}>
        <LanguageContext.Provider value={language}>
          <MainApp />
        </LanguageContext.Provider>
      </ThemeContext.Provider>
    </UserContext.Provider>
  );
}

function SomeComponent() {
  const user = useContext(UserContext);
  const theme = useContext(ThemeContext);
  const language = useContext(LanguageContext);
  
  return <div>{/* Use all contexts */}</div>;
}
```

### When to Use useContext

**✅ Good Use Cases:**
- Theme (light/dark mode)
- User authentication data
- Language/locale settings
- Global UI state (modals, notifications)
- Feature flags

**❌ Avoid for:**
- Frequently changing data (use state management library)
- Props that only pass one level
- Non-global data

### Context Best Practices

**1. Create Context in Separate File:**

```jsx
// contexts/ThemeContext.jsx
import { createContext, useContext, useState } from 'react';

const ThemeContext = createContext();

export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState('light');
  
  const toggleTheme = () => {
    setTheme(prev => prev === 'light' ? 'dark' : 'light');
  };
  
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
}

// Custom hook for easy access
export function useTheme() {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error('useTheme must be used within ThemeProvider');
  }
  return context;
}
```

**2. Use in App:**

```jsx
// App.jsx
import { ThemeProvider, useTheme } from './contexts/ThemeContext';

function App() {
  return (
    <ThemeProvider>
      <Header />
      <Main />
    </ThemeProvider>
  );
}

function Header() {
  const { theme, toggleTheme } = useTheme();
  
  return (
    <header>
      <button onClick={toggleTheme}>
        Theme: {theme}
      </button>
    </header>
  );
}
```

**Benefits:**
- Cleaner code
- Reusable provider
- Error handling
- Better organization

---

## Section 10: Advanced Patterns {#section-10}

### Custom Hooks

Create reusable logic by extracting it into custom hooks:

```jsx
// useLocalStorage hook
function useLocalStorage(key, initialValue) {
  const [value, setValue] = useState(() => {
    const stored = localStorage.getItem(key);
    return stored ? JSON.parse(stored) : initialValue;
  });
  
  useEffect(() => {
    localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);
  
  return [value, setValue];
}

// Usage
function App() {
  const [name, setName] = useLocalStorage('name', 'Guest');
  
  return (
    <input 
      value={name} 
      onChange={(e) => setName(e.target.value)} 
    />
  );
}
```

### Compound Components Pattern

```jsx
function Tabs({ children }) {
  const [activeTab, setActiveTab] = useState(0);
  
  return (
    <div>
      {React.Children.map(children, (child, index) =>
        React.cloneElement(child, {
          isActive: index === activeTab,
          onClick: () => setActiveTab(index)
        })
      )}
    </div>
  );
}

function Tab({ label, children, isActive, onClick }) {
  return (
    <div>
      <button onClick={onClick}>{label}</button>
      {isActive && <div>{children}</div>}
    </div>
  );
}

// Usage
<Tabs>
  <Tab label="Tab 1">Content 1</Tab>
  <Tab label="Tab 2">Content 2</Tab>
  <Tab label="Tab 3">Content 3</Tab>
</Tabs>
```

### Summary

**Lecture 10 covered:**

1. **Props** - Passing data and functions between components
2. **Props vs State** - When to use each
3. **Immutability** - Why and how to avoid mutations
4. **Re-rendering** - Understanding React's update mechanism
5. **Lifecycle** - Component phases and methods
6. **React Router** - Navigation in SPAs
7. **Hooks** - useState, useEffect, useContext
8. **Advanced Patterns** - Custom hooks, compound components

**Next Steps:**
- Practice building multi-page apps with React Router
- Experiment with useEffect for data fetching
- Implement global state with useContext
- Build custom hooks for reusable logic

---

**End of Comprehensive Lecture 10 Explanation**