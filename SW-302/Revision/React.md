# React Core Concepts: Complete Guide

## Table of Contents
1. [JavaScript Array Methods (map, filter, reduce, etc.)](#section-1)
2. [Props vs State - Complete Understanding](#section-2)
3. [React Hooks - Deep Dive with Use Cases](#section-3)

---

# Section 1: JavaScript Array Methods {#section-1}

## Overview

Array methods are **essential for React** because they allow you to transform and manipulate data **immutably** (without changing the original array). This is crucial for React's state management.

---

## 1. map() - Transform Every Element

### What It Does
Creates a **new array** by applying a function to every element in the original array.

### Syntax
```javascript
const newArray = array.map((element, index, array) => {
  return transformedElement;
});
```

### Parameters
- **element**: Current item being processed
- **index** (optional): Current item's index
- **array** (optional): The original array

### Basic Examples

**Example 1: Double all numbers**
```javascript
const numbers = [1, 2, 3, 4, 5];
const doubled = numbers.map(num => num * 2);

console.log(doubled); // [2, 4, 6, 8, 10]
console.log(numbers); // [1, 2, 3, 4, 5] - Original unchanged!
```

**Example 2: Extract property from objects**
```javascript
const users = [
  { id: 1, name: 'Ahmed', age: 22 },
  { id: 2, name: 'Sarah', age: 25 },
  { id: 3, name: 'John', age: 30 }
];

const names = users.map(user => user.name);
console.log(names); // ['Ahmed', 'Sarah', 'John']
```

**Example 3: Transform objects**
```javascript
const products = [
  { name: 'Laptop', price: 1000 },
  { name: 'Phone', price: 500 },
  { name: 'Tablet', price: 300 }
];

const discounted = products.map(product => ({
  ...product,
  price: product.price * 0.9, // 10% discount
  discounted: true
}));

console.log(discounted);
// [
//   { name: 'Laptop', price: 900, discounted: true },
//   { name: 'Phone', price: 450, discounted: true },
//   { name: 'Tablet', price: 270, discounted: true }
// ]
```

### React Use Case: Rendering Lists

**Without map (manual, tedious):**
```jsx
function ProductList() {
  const products = ['Laptop', 'Phone', 'Tablet'];
  
  return (
    <ul>
      <li>{products[0]}</li>
      <li>{products[1]}</li>
      <li>{products[2]}</li>
    </ul>
  );
}
```

**With map (dynamic, scalable):**
```jsx
function ProductList() {
  const products = [
    { id: 1, name: 'Laptop', price: 1000 },
    { id: 2, name: 'Phone', price: 500 },
    { id: 3, name: 'Tablet', price: 300 }
  ];
  
  return (
    <ul>
      {products.map(product => (
        <li key={product.id}>
          {product.name} - ${product.price}
        </li>
      ))}
    </ul>
  );
}
```

### Common Patterns in React

**Pattern 1: Rendering components**
```jsx
function UserCards() {
  const users = [
    { id: 1, name: 'Ahmed', email: 'ahmed@example.com' },
    { id: 2, name: 'Sarah', email: 'sarah@example.com' }
  ];
  
  return (
    <div>
      {users.map(user => (
        <UserCard 
          key={user.id} 
          name={user.name} 
          email={user.email} 
        />
      ))}
    </div>
  );
}
```

**Pattern 2: Adding index for numbering**
```jsx
function TodoList() {
  const todos = ['Learn React', 'Build Project', 'Deploy App'];
  
  return (
    <ol>
      {todos.map((todo, index) => (
        <li key={index}>
          {index + 1}. {todo}
        </li>
      ))}
    </ol>
  );
}
```

---

## 2. filter() - Select Specific Elements

### What It Does
Creates a **new array** with only elements that pass a test (return true from the callback function).

### Syntax
```javascript
const filteredArray = array.filter((element, index, array) => {
  return condition; // true or false
});
```

### Basic Examples

**Example 1: Filter by condition**
```javascript
const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

const evenNumbers = numbers.filter(num => num % 2 === 0);
console.log(evenNumbers); // [2, 4, 6, 8, 10]

const greaterThanFive = numbers.filter(num => num > 5);
console.log(greaterThanFive); // [6, 7, 8, 9, 10]
```

**Example 2: Filter objects**
```javascript
const students = [
  { name: 'Ahmed', grade: 85, passed: true },
  { name: 'Sarah', grade: 92, passed: true },
  { name: 'John', grade: 58, passed: false },
  { name: 'Emma', grade: 78, passed: true }
];

const passedStudents = students.filter(student => student.passed);
console.log(passedStudents);
// [
//   { name: 'Ahmed', grade: 85, passed: true },
//   { name: 'Sarah', grade: 92, passed: true },
//   { name: 'Emma', grade: 78, passed: true }
// ]

const topStudents = students.filter(student => student.grade >= 85);
console.log(topStudents);
// [
//   { name: 'Ahmed', grade: 85, passed: true },
//   { name: 'Sarah', grade: 92, passed: true }
// ]
```

**Example 3: Filter by string matching**
```javascript
const fruits = ['Apple', 'Banana', 'Orange', 'Mango', 'Pineapple'];

const containsA = fruits.filter(fruit => fruit.includes('a'));
console.log(containsA); // ['Banana', 'Orange', 'Mango', 'Pineapple']

const startsWithA = fruits.filter(fruit => fruit.startsWith('A'));
console.log(startsWithA); // ['Apple']
```

### React Use Case: Search/Filter Features

**Example: Search functionality**
```jsx
function ProductSearch() {
  const [searchTerm, setSearchTerm] = useState('');
  
  const products = [
    { id: 1, name: 'Laptop', category: 'Electronics' },
    { id: 2, name: 'Phone', category: 'Electronics' },
    { id: 3, name: 'Desk', category: 'Furniture' },
    { id: 4, name: 'Chair', category: 'Furniture' }
  ];
  
  // Filter products based on search
  const filteredProducts = products.filter(product =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  return (
    <div>
      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      
      <ul>
        {filteredProducts.map(product => (
          <li key={product.id}>{product.name}</li>
        ))}
      </ul>
      
      <p>Showing {filteredProducts.length} products</p>
    </div>
  );
}
```

**Example: Category filter**
```jsx
function ProductFilter() {
  const [category, setCategory] = useState('all');
  
  const products = [
    { id: 1, name: 'Laptop', category: 'electronics', price: 1000 },
    { id: 2, name: 'Phone', category: 'electronics', price: 500 },
    { id: 3, name: 'Desk', category: 'furniture', price: 200 },
    { id: 4, name: 'Chair', category: 'furniture', price: 150 }
  ];
  
  const filteredProducts = category === 'all'
    ? products
    : products.filter(product => product.category === category);
  
  return (
    <div>
      <select onChange={(e) => setCategory(e.target.value)}>
        <option value="all">All Categories</option>
        <option value="electronics">Electronics</option>
        <option value="furniture">Furniture</option>
      </select>
      
      <ul>
        {filteredProducts.map(product => (
          <li key={product.id}>
            {product.name} - ${product.price}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

**Example: Remove item from array (immutably)**
```jsx
function TodoList() {
  const [todos, setTodos] = useState([
    { id: 1, text: 'Learn React' },
    { id: 2, text: 'Build App' },
    { id: 3, text: 'Deploy' }
  ]);
  
  const removeTodo = (id) => {
    // Filter out the todo with matching id
    setTodos(todos.filter(todo => todo.id !== id));
  };
  
  return (
    <ul>
      {todos.map(todo => (
        <li key={todo.id}>
          {todo.text}
          <button onClick={() => removeTodo(todo.id)}>Delete</button>
        </li>
      ))}
    </ul>
  );
}
```

---

## 3. reduce() - Combine to Single Value

### What It Does
Reduces an array to a **single value** by applying a function to each element and accumulating the result.

### Syntax
```javascript
const result = array.reduce((accumulator, currentValue, index, array) => {
  return newAccumulatorValue;
}, initialValue);
```

### Parameters
- **accumulator**: The accumulated value (starts with initialValue)
- **currentValue**: Current element being processed
- **index** (optional): Current element's index
- **array** (optional): Original array
- **initialValue**: Starting value for accumulator

### Basic Examples

**Example 1: Sum all numbers**
```javascript
const numbers = [1, 2, 3, 4, 5];

const sum = numbers.reduce((total, num) => total + num, 0);
console.log(sum); // 15

// Step by step:
// Iteration 1: total = 0, num = 1, return 0 + 1 = 1
// Iteration 2: total = 1, num = 2, return 1 + 2 = 3
// Iteration 3: total = 3, num = 3, return 3 + 3 = 6
// Iteration 4: total = 6, num = 4, return 6 + 4 = 10
// Iteration 5: total = 10, num = 5, return 10 + 5 = 15
```

**Example 2: Find maximum**
```javascript
const numbers = [10, 5, 23, 8, 15];

const max = numbers.reduce((maximum, num) => {
  return num > maximum ? num : maximum;
}, numbers[0]);

console.log(max); // 23
```

**Example 3: Count occurrences**
```javascript
const fruits = ['apple', 'banana', 'apple', 'orange', 'banana', 'apple'];

const count = fruits.reduce((acc, fruit) => {
  acc[fruit] = (acc[fruit] || 0) + 1;
  return acc;
}, {});

console.log(count);
// { apple: 3, banana: 2, orange: 1 }
```

**Example 4: Group by property**
```javascript
const students = [
  { name: 'Ahmed', grade: 'A' },
  { name: 'Sarah', grade: 'B' },
  { name: 'John', grade: 'A' },
  { name: 'Emma', grade: 'B' },
  { name: 'Mike', grade: 'A' }
];

const grouped = students.reduce((acc, student) => {
  if (!acc[student.grade]) {
    acc[student.grade] = [];
  }
  acc[student.grade].push(student.name);
  return acc;
}, {});

console.log(grouped);
// {
//   A: ['Ahmed', 'John', 'Mike'],
//   B: ['Sarah', 'Emma']
// }
```

**Example 5: Flatten nested arrays**
```javascript
const nested = [[1, 2], [3, 4], [5, 6]];

const flattened = nested.reduce((acc, arr) => acc.concat(arr), []);
console.log(flattened); // [1, 2, 3, 4, 5, 6]
```

### React Use Case: Calculate Totals

**Example: Shopping cart total**
```jsx
function ShoppingCart() {
  const [cart] = useState([
    { id: 1, name: 'Laptop', price: 1000, quantity: 1 },
    { id: 2, name: 'Phone', price: 500, quantity: 2 },
    { id: 3, name: 'Mouse', price: 25, quantity: 3 }
  ]);
  
  // Calculate total using reduce
  const total = cart.reduce((sum, item) => {
    return sum + (item.price * item.quantity);
  }, 0);
  
  const itemCount = cart.reduce((count, item) => {
    return count + item.quantity;
  }, 0);
  
  return (
    <div>
      <h2>Shopping Cart</h2>
      {cart.map(item => (
        <div key={item.id}>
          {item.name} - ${item.price} x {item.quantity}
        </div>
      ))}
      
      <hr />
      <p>Total Items: {itemCount}</p>
      <p>Total Price: ${total}</p>
    </div>
  );
}
```

---

## 4. find() - Find First Match

### What It Does
Returns the **first element** that passes a test. Returns `undefined` if nothing found.

### Syntax
```javascript
const foundElement = array.find((element, index, array) => {
  return condition; // true or false
});
```

### Examples

**Example 1: Find by condition**
```javascript
const numbers = [5, 12, 8, 130, 44];

const found = numbers.find(num => num > 10);
console.log(found); // 12 (first number > 10)
```

**Example 2: Find object by property**
```javascript
const users = [
  { id: 1, name: 'Ahmed', age: 22 },
  { id: 2, name: 'Sarah', age: 25 },
  { id: 3, name: 'John', age: 30 }
];

const user = users.find(user => user.id === 2);
console.log(user); // { id: 2, name: 'Sarah', age: 25 }

const notFound = users.find(user => user.id === 999);
console.log(notFound); // undefined
```

### React Use Case

**Example: Find and display user details**
```jsx
function UserProfile({ userId }) {
  const users = [
    { id: 1, name: 'Ahmed', email: 'ahmed@example.com' },
    { id: 2, name: 'Sarah', email: 'sarah@example.com' },
    { id: 3, name: 'John', email: 'john@example.com' }
  ];
  
  const user = users.find(u => u.id === userId);
  
  if (!user) {
    return <div>User not found</div>;
  }
  
  return (
    <div>
      <h2>{user.name}</h2>
      <p>Email: {user.email}</p>
    </div>
  );
}
```

---

## 5. findIndex() - Find Index of First Match

### What It Does
Returns the **index** of the first element that passes a test. Returns `-1` if nothing found.

### Syntax
```javascript
const index = array.findIndex((element, index, array) => {
  return condition;
});
```

### Examples

```javascript
const numbers = [5, 12, 8, 130, 44];

const index = numbers.findIndex(num => num > 10);
console.log(index); // 1 (index of 12)

const users = [
  { id: 1, name: 'Ahmed' },
  { id: 2, name: 'Sarah' },
  { id: 3, name: 'John' }
];

const userIndex = users.findIndex(user => user.name === 'Sarah');
console.log(userIndex); // 1
```

### React Use Case: Update specific item

```jsx
function TodoList() {
  const [todos, setTodos] = useState([
    { id: 1, text: 'Learn React', completed: false },
    { id: 2, text: 'Build App', completed: false }
  ]);
  
  const toggleTodo = (id) => {
    const index = todos.findIndex(todo => todo.id === id);
    
    if (index !== -1) {
      const newTodos = [...todos];
      newTodos[index] = {
        ...newTodos[index],
        completed: !newTodos[index].completed
      };
      setTodos(newTodos);
    }
  };
  
  return (
    <ul>
      {todos.map(todo => (
        <li key={todo.id}>
          <input
            type="checkbox"
            checked={todo.completed}
            onChange={() => toggleTodo(todo.id)}
          />
          {todo.text}
        </li>
      ))}
    </ul>
  );
}
```

---

## 6. some() - Test if Any Element Passes

### What It Does
Returns `true` if **at least one** element passes the test.

### Syntax
```javascript
const hasMatch = array.some((element, index, array) => {
  return condition;
});
```

### Examples

```javascript
const numbers = [1, 2, 3, 4, 5];

const hasEven = numbers.some(num => num % 2 === 0);
console.log(hasEven); // true (2 and 4 are even)

const hasLargeNumber = numbers.some(num => num > 10);
console.log(hasLargeNumber); // false

const users = [
  { name: 'Ahmed', age: 22 },
  { name: 'Sarah', age: 17 },
  { name: 'John', age: 30 }
];

const hasMinor = users.some(user => user.age < 18);
console.log(hasMinor); // true (Sarah is 17)
```

### React Use Case

```jsx
function FormValidation() {
  const [fields, setFields] = useState([
    { name: 'email', value: '', required: true },
    { name: 'password', value: '', required: true },
    { name: 'phone', value: '', required: false }
  ]);
  
  // Check if any required field is empty
  const hasEmptyRequired = fields.some(
    field => field.required && field.value === ''
  );
  
  return (
    <div>
      {fields.map(field => (
        <input
          key={field.name}
          type="text"
          placeholder={field.name}
          value={field.value}
          onChange={(e) => {
            setFields(fields.map(f =>
              f.name === field.name
                ? { ...f, value: e.target.value }
                : f
            ));
          }}
        />
      ))}
      
      <button disabled={hasEmptyRequired}>
        Submit
      </button>
      
      {hasEmptyRequired && (
        <p style={{ color: 'red' }}>
          Please fill all required fields
        </p>
      )}
    </div>
  );
}
```

---

## 7. every() - Test if All Elements Pass

### What It Does
Returns `true` if **all** elements pass the test.

### Syntax
```javascript
const allPass = array.every((element, index, array) => {
  return condition;
});
```

### Examples

```javascript
const numbers = [2, 4, 6, 8, 10];

const allEven = numbers.every(num => num % 2 === 0);
console.log(allEven); // true

const allPositive = numbers.every(num => num > 0);
console.log(allPositive); // true

const allLarge = numbers.every(num => num > 5);
console.log(allLarge); // false (2 and 4 are not > 5)
```

### React Use Case

```jsx
function TaskCompletion() {
  const [tasks, setTasks] = useState([
    { id: 1, name: 'Setup', completed: true },
    { id: 2, name: 'Development', completed: true },
    { id: 3, name: 'Testing', completed: false },
    { id: 4, name: 'Deployment', completed: false }
  ]);
  
  const allCompleted = tasks.every(task => task.completed);
  const progress = (tasks.filter(t => t.completed).length / tasks.length) * 100;
  
  return (
    <div>
      <h2>Project Progress</h2>
      
      <div className="progress-bar">
        <div style={{ width: `${progress}%` }}></div>
      </div>
      
      <p>{progress.toFixed(0)}% Complete</p>
      
      {tasks.map(task => (
        <div key={task.id}>
          <input
            type="checkbox"
            checked={task.completed}
            onChange={() => {
              setTasks(tasks.map(t =>
                t.id === task.id
                  ? { ...t, completed: !t.completed }
                  : t
              ));
            }}
          />
          {task.name}
        </div>
      ))}
      
      {allCompleted && (
        <div className="success">
          🎉 All tasks completed!
        </div>
      )}
    </div>
  );
}
```

---

## 8. Chaining Methods

You can **chain** multiple array methods together for powerful transformations.

### Examples

**Example 1: Filter then map**
```javascript
const products = [
  { name: 'Laptop', price: 1000, category: 'electronics' },
  { name: 'Phone', price: 500, category: 'electronics' },
  { name: 'Desk', price: 200, category: 'furniture' },
  { name: 'Chair', price: 150, category: 'furniture' }
];

// Get names of electronics under $600
const result = products
  .filter(p => p.category === 'electronics')
  .filter(p => p.price < 600)
  .map(p => p.name);

console.log(result); // ['Phone']
```

**Example 2: Map then reduce**
```javascript
const orders = [
  { id: 1, items: 3, price: 100 },
  { id: 2, items: 5, price: 200 },
  { id: 3, items: 2, price: 50 }
];

// Calculate total revenue
const totalRevenue = orders
  .map(order => order.price)
  .reduce((sum, price) => sum + price, 0);

console.log(totalRevenue); // 350
```

**Example 3: Complex chain**
```javascript
const students = [
  { name: 'Ahmed', grades: [85, 90, 88] },
  { name: 'Sarah', grades: [92, 95, 93] },
  { name: 'John', grades: [70, 75, 72] }
];

// Get names of students with average > 80
const topStudents = students
  .map(student => ({
    name: student.name,
    average: student.grades.reduce((sum, g) => sum + g, 0) / student.grades.length
  }))
  .filter(student => student.average > 80)
  .map(student => student.name);

console.log(topStudents); // ['Ahmed', 'Sarah']
```

### React Use Case: Complex filtering

```jsx
function ProductDashboard() {
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(1000);
  const [category, setCategory] = useState('all');
  
  const products = [
    { id: 1, name: 'Laptop', price: 1000, category: 'electronics', inStock: true },
    { id: 2, name: 'Phone', price: 500, category: 'electronics', inStock: true },
    { id: 3, name: 'Desk', price: 200, category: 'furniture', inStock: false },
    { id: 4, name: 'Chair', price: 150, category: 'furniture', inStock: true }
  ];
  
  // Chain multiple filters
  const filteredProducts = products
    .filter(p => p.inStock)  // Only in stock
    .filter(p => category === 'all' || p.category === category)
    .filter(p => p.price >= minPrice && p.price <= maxPrice)
    .sort((a, b) => a.price - b.price);  // Sort by price
  
  const totalValue = filteredProducts
    .reduce((sum, p) => sum + p.price, 0);
  
  return (
    <div>
      <div>
        <label>Category:</label>
        <select onChange={(e) => setCategory(e.target.value)}>
          <option value="all">All</option>
          <option value="electronics">Electronics</option>
          <option value="furniture">Furniture</option>
        </select>
      </div>
      
      <div>
        <label>Price Range: ${minPrice} - ${maxPrice}</label>
        <input
          type="range"
          min="0"
          max="1000"
          value={maxPrice}
          onChange={(e) => setMaxPrice(Number(e.target.value))}
        />
      </div>
      
      <p>Showing {filteredProducts.length} products</p>
      <p>Total Value: ${totalValue}</p>
      
      <ul>
        {filteredProducts.map(product => (
          <li key={product.id}>
            {product.name} - ${product.price}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

---

## Quick Reference Table

| Method | Returns | Purpose | Mutates Original? |
|--------|---------|---------|-------------------|
| **map()** | New array | Transform each element | ❌ No |
| **filter()** | New array | Select matching elements | ❌ No |
| **reduce()** | Single value | Accumulate to one value | ❌ No |
| **find()** | Element or undefined | Find first match | ❌ No |
| **findIndex()** | Index or -1 | Find index of first match | ❌ No |
| **some()** | Boolean | Test if any pass | ❌ No |
| **every()** | Boolean | Test if all pass | ❌ No |
| **forEach()** | undefined | Loop through each | ❌ No |
| **sort()** | Same array | Sort elements | ✅ Yes |
| **push()** | New length | Add to end | ✅ Yes |
| **pop()** | Removed element | Remove from end | ✅ Yes |
| **slice()** | New array | Extract portion | ❌ No |
| **splice()** | Removed elements | Add/remove at index | ✅ Yes |
| **concat()** | New array | Combine arrays | ❌ No |

---

## Common React Patterns Summary

### 1. Render List
```jsx
{items.map(item => <Component key={item.id} {...item} />)}
```

### 2. Search/Filter
```jsx
const filtered = items.filter(item => 
  item.name.toLowerCase().includes(search.toLowerCase())
);
```

### 3. Remove Item
```jsx
setItems(items.filter(item => item.id !== idToRemove));
```

### 4. Update Item
```jsx
setItems(items.map(item =>
  item.id === targetId ? { ...item, updated: true } : item
));
```

### 5. Calculate Total
```jsx
const total = items.reduce((sum, item) => sum + item.price, 0);
```

### 6. Check Completion
```jsx
const allDone = items.every(item => item.completed);
const anyDone = items.some(item => item.completed);
```

---

# Section 2: Props vs State - Complete Understanding {#section-2}

## What Are Props?

**Props (Properties)** are arguments passed to React components. They work like **function parameters**.

### Key Characteristics of Props

1. **Passed from parent to child** (unidirectional flow)
2. **Immutable** - cannot be modified by the receiving component
3. **Read-only** - treat them as configuration
4. **Any data type** - strings, numbers, objects, arrays, functions
5. **Make components reusable** - same component, different data

### Props Example

```jsx
// Parent Component
function App() {
  return (
    <div>
      <Greeting name="Ahmed" age={22} />
      <Greeting name="Sarah" age={25} />
    </div>
  );
}

// Child Component
function Greeting(props) {
  // props = { name: "Ahmed", age: 22 }
  return (
    <div>
      <h1>Hello, {props.name}!</h1>
      <p>You are {props.age} years old</p>
    </div>
  );
}

// Or with destructuring (cleaner):
function Greeting({ name, age }) {
  return (
    <div>
      <h1>Hello, {name}!</h1>
      <p>You are {age} years old</p>
    </div>
  );
}
```

### Props are Read-Only

```jsx
function Greeting({ name }) {
  // ❌ WRONG - Cannot modify props!
  name = "Modified"; // Error in development mode
  
  // ✅ CORRECT - Use props as-is
  return <h1>Hello, {name}!</h1>;
}
```

---

## What is State?

**State** is data that a component **owns and manages internally**. When state changes, the component re-renders.

### Key Characteristics of State

1. **Owned by the component** (internal, private)
2. **Mutable** - can be changed using setState
3. **Triggers re-renders** when updated
4. **Asynchronous updates** - React batches state updates
5. **Local to component** - each instance has its own state

### State Example

```jsx
import { useState } from 'react';

function Counter() {
  // Declare state variable 'count' with initial value 0
  const [count, setCount] = useState(0);
  
  // Update state
  const increment = () => {
    setCount(count + 1); // This triggers re-render
  };
  
  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={increment}>Increment</button>
    </div>
  );
}
```

---

## Props vs State: The Difference

| Aspect | Props | State |
|--------|-------|-------|
| **Source** | Passed from parent | Created within component |
| **Ownership** | Parent owns it | Component owns it |
| **Can change?** | ❌ No (immutable) | ✅ Yes (via setState) |
| **Triggers re-render?** | ✅ Yes (when parent updates) | ✅ Yes (when setState called) |
| **Scope** | Received from outside | Local to component |
| **Purpose** | Configure component | Track changing data |
| **Access** | `props.propName` or `{propName}` | Direct variable access |
| **Update** | Parent must update | Use setState function |

---

## Visual Comparison

### Props Flow (Top-Down)

```
Parent Component (owns data)
     ↓
   Props (passes data down)
     ↓
Child Component (receives, cannot modify)
     ↓
   Props (passes further down)
     ↓
Grandchild Component
```

### State Flow (Internal)

```
Component
    ↓
  State (internal data)
    ↓
User Action (button click)
    ↓
setState() called
    ↓
State Updates
    ↓
Component Re-renders
```

---

## When to Use Props vs State

### Use Props When:

1. **Data comes from parent**
   ```jsx
   <UserCard name="Ahmed" email="ahmed@example.com" />
   ```

2. **Component needs configuration**
   ```jsx
   <Button variant="primary" size="large" />
   ```

3. **Passing callbacks to children**
   ```jsx
   <ChildComponent onSave={handleSave} />
   ```

4. **Data doesn't change within component**
   ```jsx
   <ProductCard title="Laptop" price={1000} />
   ```

5. **Making components reusable**
   ```jsx
   <Card title="First Card" content="Content 1" />
   <Card title="Second Card" content="Content 2" />
   ```

### Use State When:

1. **Data changes over time**
   ```jsx
   const [count, setCount] = useState(0); // Counter value
   ```

2. **User input**
   ```jsx
   const [email, setEmail] = useState(''); // Form input
   ```

3. **Toggle visibility**
   ```jsx
   const [isOpen, setIsOpen] = useState(false); // Modal state
   ```

4. **Fetched data**
   ```jsx
   const [users, setUsers] = useState([]); // API response
   ```

5. **Component-specific data**
   ```jsx
   const [selectedTab, setSelectedTab] = useState(0); // Active tab
   ```

---

## Real-World Use Cases

### Use Case 1: Props for Configuration

**Reusable Button Component:**
```jsx
function Button({ text, variant, onClick, disabled }) {
  const getClassName = () => {
    const base = 'btn';
    const variantClass = variant ? `btn-${variant}` : 'btn-default';
    return `${base} ${variantClass}`;
  };
  
  return (
    <button 
      className={getClassName()}
      onClick={onClick}
      disabled={disabled}
    >
      {text}
    </button>
  );
}

// Usage: Same component, different configurations
function App() {
  return (
    <div>
      <Button text="Submit" variant="primary" onClick={handleSubmit} />
      <Button text="Cancel" variant="secondary" onClick={handleCancel} />
      <Button text="Delete" variant="danger" onClick={handleDelete} />
    </div>
  );
}
```

### Use Case 2: State for Interactive Features

**Toggle Component:**
```jsx
function ThemeToggle() {
  const [isDark, setIsDark] = useState(false);
  
  const toggleTheme = () => {
    setIsDark(!isDark);
    // Apply theme to body
    document.body.className = isDark ? 'light' : 'dark';
  };
  
  return (
    <button onClick={toggleTheme}>
      {isDark ? '☀️ Light Mode' : '🌙 Dark Mode'}
    </button>
  );
}
```

### Use Case 3: Props + State Together

**Form Component:**
```jsx
// Parent Component (manages overall form state)
function UserForm() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    age: ''
  });
  
  const handleChange = (field, value) => {
    setFormData({
      ...formData,
      [field]: value
    });
  };
  
  const handleSubmit = () => {
    console.log('Form submitted:', formData);
  };
  
  return (
    <div>
      <InputField
        label="Name"
        value={formData.name}
        onChange={(value) => handleChange('name', value)}
      />
      <InputField
        label="Email"
        type="email"
        value={formData.email}
        onChange={(value) => handleChange('email', value)}
      />
      <InputField
        label="Age"
        type="number"
        value={formData.age}
        onChange={(value) => handleChange('age', value)}
      />
      
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}

// Child Component (receives props, manages internal state)
function InputField({ label, type = 'text', value, onChange }) {
  const [isFocused, setIsFocused] = useState(false); // Internal state
  
  return (
    <div className={`input-group ${isFocused ? 'focused' : ''}`}>
      <label>{label}</label>
      <input
        type={type}
        value={value} // From props
        onChange={(e) => onChange(e.target.value)} // Callback prop
        onFocus={() => setIsFocused(true)} // Internal state
        onBlur={() => setIsFocused(false)} // Internal state
      />
    </div>
  );
}
```

### Use Case 4: Passing Functions via Props

**Parent-Child Communication:**
```jsx
// Parent Component
function TodoApp() {
  const [todos, setTodos] = useState([
    { id: 1, text: 'Learn React', completed: false },
    { id: 2, text: 'Build App', completed: false }
  ]);
  
  // Function to update state
  const toggleTodo = (id) => {
    setTodos(todos.map(todo =>
      todo.id === id
        ? { ...todo, completed: !todo.completed }
        : todo
    ));
  };
  
  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id));
  };
  
  return (
    <div>
      <h1>Todo List</h1>
      {todos.map(todo => (
        <TodoItem
          key={todo.id}
          todo={todo}
          onToggle={toggleTodo}  // Pass function as prop
          onDelete={deleteTodo}  // Pass function as prop
        />
      ))}
    </div>
  );
}

// Child Component
function TodoItem({ todo, onToggle, onDelete }) {
  return (
    <div className="todo-item">
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => onToggle(todo.id)} // Call parent function
      />
      <span style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
        {todo.text}
      </span>
      <button onClick={() => onDelete(todo.id)}>Delete</button>
    </div>
  );
}
```

### Use Case 5: Lifting State Up

When multiple components need to share state, lift it to their common parent.

```jsx
// Parent manages shared state
function TemperatureApp() {
  const [temperature, setTemperature] = useState(0);
  
  return (
    <div>
      <h1>Temperature Converter</h1>
      
      {/* Both components share the same state */}
      <CelsiusInput 
        value={temperature}
        onChange={setTemperature}
      />
      
      <FahrenheitInput 
        value={temperature}
        onChange={setTemperature}
      />
    </div>
  );
}

function CelsiusInput({ value, onChange }) {
  return (
    <div>
      <label>Celsius:</label>
      <input
        type="number"
        value={value}
        onChange={(e) => onChange(Number(e.target.value))}
      />
    </div>
  );
}

function FahrenheitInput({ value, onChange }) {
  const fahrenheit = (value * 9/5) + 32;
  
  const handleChange = (f) => {
    const celsius = (f - 32) * 5/9;
    onChange(celsius);
  };
  
  return (
    <div>
      <label>Fahrenheit:</label>
      <input
        type="number"
        value={fahrenheit}
        onChange={(e) => handleChange(Number(e.target.value))}
      />
    </div>
  );
}
```

---

## Common Patterns Summary

### Pattern 1: Props as Configuration
```jsx
<Component 
  title="My Title"
  color="blue"
  size="large"
/>
```

### Pattern 2: State for UI Interactions
```jsx
const [isOpen, setIsOpen] = useState(false);
<Modal isOpen={isOpen} onClose={() => setIsOpen(false)} />
```

### Pattern 3: Callback Props
```jsx
// Parent
<Child onAction={handleAction} />

// Child
<button onClick={() => props.onAction(data)}>Click</button>
```

### Pattern 4: Derived State from Props
```jsx
function Component({ initialCount }) {
  const [count, setCount] = useState(initialCount);
  // Use initialCount only once to set initial state
}
```

### Pattern 5: Controlled Components
```jsx
// Parent controls state
const [value, setValue] = useState('');
<input value={value} onChange={(e) => setValue(e.target.value)} />
```

---

## Decision Tree: Props or State?

```
Does the data come from parent?
    ├─ YES → Use Props
    └─ NO → Does it change over time?
          ├─ YES → Use State
          └─ NO → Use constant/variable
```

### Examples

| Scenario | Solution | Reason |
|----------|----------|--------|
| User's name from parent | Props | Data comes from parent |
| Current tab selected | State | Changes within component |
| Button text | Props | Configuration from parent |
| Form input value | State | Changes as user types |
| API endpoint URL | Props or Constant | Doesn't change |
| Loading indicator | State | Toggles during fetch |
| Component theme color | Props | Parent decides |
| Modal open/closed | State | Component controls |

---

# Section 3: React Hooks - Deep Dive {#section-3}

## What Are Hooks?

**Hooks** are special functions that let you "hook into" React features from function components. They were introduced in React 16.8 to replace class components.

### Why Hooks?

**Before Hooks (Class Components):**
```jsx
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
    this.increment = this.increment.bind(this);
  }
  
  increment() {
    this.setState({ count: this.state.count + 1 });
  }
  
  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <button onClick={this.increment}>+</button>
      </div>
    );
  }
}
```

**With Hooks (Function Components):**
```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>+</button>
    </div>
  );
}
```

### Benefits of Hooks

1. **Simpler code** - Less boilerplate
2. **Easier to understand** - No `this` keyword confusion
3. **Reusable logic** - Custom hooks
4. **Better testing** - Pure functions
5. **No lifecycle complexity** - useEffect handles everything

---

## Rules of Hooks

**Critical Rules You Must Follow:**

### Rule 1: Only Call at Top Level
```jsx
// ❌ WRONG - Inside condition
function Component({ condition }) {
  if (condition) {
    const [state, setState] = useState(0); // ERROR!
  }
}

// ✅ CORRECT - At top level
function Component({ condition }) {
  const [state, setState] = useState(0);
  
  if (condition) {
    // Use state here
  }
}
```

### Rule 2: Only Call from React Functions
```jsx
// ❌ WRONG - Regular function
function regularFunction() {
  const [state, setState] = useState(0); // ERROR!
}

// ✅ CORRECT - React component
function Component() {
  const [state, setState] = useState(0); // OK
}

// ✅ CORRECT - Custom hook
function useCustomHook() {
  const [state, setState] = useState(0); // OK
}
```

---

## 1. useState - State Management

### What It Does
Adds state to function components.

### Syntax
```jsx
const [stateValue, setStateValue] = useState(initialValue);
```

### Basic Examples

**Example 1: Simple counter**
```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <button onClick={() => setCount(count - 1)}>Decrement</button>
      <button onClick={() => setCount(0)}>Reset</button>
    </div>
  );
}
```

**Example 2: String state**
```jsx
function NameInput() {
  const [name, setName] = useState('');
  
  return (
    <div>
      <input
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Enter your name"
      />
      <p>Hello, {name || 'Guest'}!</p>
    </div>
  );
}
```

**Example 3: Boolean state**
```jsx
function ToggleSwitch() {
  const [isOn, setIsOn] = useState(false);
  
  return (
    <div>
      <button onClick={() => setIsOn(!isOn)}>
        {isOn ? 'ON' : 'OFF'}
      </button>
      
      <div className={isOn ? 'light on' : 'light off'}>
        💡
      </div>
    </div>
  );
}
```

**Example 4: Object state**
```jsx
function UserForm() {
  const [user, setUser] = useState({
    name: '',
    email: '',
    age: ''
  });
  
  const updateField = (field, value) => {
    setUser({
      ...user,
      [field]: value
    });
  };
  
  return (
    <div>
      <input
        value={user.name}
        onChange={(e) => updateField('name', e.target.value)}
        placeholder="Name"
      />
      <input
        value={user.email}
        onChange={(e) => updateField('email', e.target.value)}
        placeholder="Email"
      />
      <input
        value={user.age}
        onChange={(e) => updateField('age', e.target.value)}
        placeholder="Age"
      />
      
      <pre>{JSON.stringify(user, null, 2)}</pre>
    </div>
  );
}
```

**Example 5: Array state**
```jsx
function TodoList() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState('');
  
  const addTodo = () => {
    if (input.trim()) {
      setTodos([...todos, { id: Date.now(), text: input, done: false }]);
      setInput('');
    }
  };
  
  const toggleTodo = (id) => {
    setTodos(todos.map(todo =>
      todo.id === id ? { ...todo, done: !todo.done } : todo
    ));
  };
  
  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id));
  };
  
  return (
    <div>
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyPress={(e) => e.key === 'Enter' && addTodo()}
      />
      <button onClick={addTodo}>Add</button>
      
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            <input
              type="checkbox"
              checked={todo.done}
              onChange={() => toggleTodo(todo.id)}
            />
            <span style={{ textDecoration: todo.done ? 'line-through' : 'none' }}>
              {todo.text}
            </span>
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
```

### Real-World Use Case: Shopping Cart

```jsx
function ShoppingCart() {
  const [cart, setCart] = useState([]);
  
  const products = [
    { id: 1, name: 'Laptop', price: 1000 },
    { id: 2, name: 'Phone', price: 500 },
    { id: 3, name: 'Headphones', price: 100 }
  ];
  
  const addToCart = (product) => {
    const existing = cart.find(item => item.id === product.id);
    
    if (existing) {
      setCart(cart.map(item =>
        item.id === product.id
          ? { ...item, quantity: item.quantity + 1 }
          : item
      ));
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };
  
  const removeFromCart = (id) => {
    setCart(cart.filter(item => item.id !== id));
  };
  
  const updateQuantity = (id, quantity) => {
    if (quantity === 0) {
      removeFromCart(id);
    } else {
      setCart(cart.map(item =>
        item.id === id ? { ...item, quantity } : item
      ));
    }
  };
  
  const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  
  return (
    <div>
      <h2>Products</h2>
      {products.map(product => (
        <div key={product.id}>
          {product.name} - ${product.price}
          <button onClick={() => addToCart(product)}>Add to Cart</button>
        </div>
      ))}
      
      <h2>Cart ({cart.length} items)</h2>
      {cart.map(item => (
        <div key={item.id}>
          {item.name} - ${item.price} x 
          <input
            type="number"
            value={item.quantity}
            onChange={(e) => updateQuantity(item.id, Number(e.target.value))}
            min="0"
          />
          = ${item.price * item.quantity}
          <button onClick={() => removeFromCart(item.id)}>Remove</button>
        </div>
      ))}
      
      <h3>Total: ${total}</h3>
    </div>
  );
}
```

---

## 2. useEffect - Side Effects

### What It Does
Runs code after render (side effects like API calls, subscriptions, timers).

### Syntax
```jsx
useEffect(() => {
  // Effect code
  
  return () => {
    // Cleanup (optional)
  };
}, [dependencies]);
```

### Dependency Patterns

**Pattern 1: Run once on mount**
```jsx
useEffect(() => {
  console.log('Component mounted');
}, []); // Empty array = run once
```

**Pattern 2: Run when specific value changes**
```jsx
useEffect(() => {
  console.log('Count changed:', count);
}, [count]); // Run when count changes
```

**Pattern 3: Run on every render**
```jsx
useEffect(() => {
  console.log('Component rendered');
}); // No array = run always
```

### Real-World Use Cases

**Use Case 1: Fetch data on mount**
```jsx
function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    fetch('https://jsonplaceholder.typicode.com/users')
      .then(res => res.json())
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error:', error);
        setLoading(false);
      });
  }, []); // Run once on mount
  
  if (loading) return <div>Loading...</div>;
  
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

**Use Case 2: Update document title**
```jsx
function PageTitle({ title }) {
  useEffect(() => {
    document.title = title;
  }, [title]); // Run when title changes
  
  return <h1>{title}</h1>;
}
```

**Use Case 3: Event listeners with cleanup**
```jsx
function WindowSize() {
  const [size, setSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight
  });
  
  useEffect(() => {
    const handleResize = () => {
      setSize({
        width: window.innerWidth,
        height: window.innerHeight
      });
    };
    
    // Add listener
    window.addEventListener('resize', handleResize);
    
    // Cleanup: remove listener
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []); // Run once
  
  return (
    <div>
      Window size: {size.width} x {size.height}
    </div>
  );
}
```

**Use Case 4: Timer with cleanup**
```jsx
function Timer() {
  const [seconds, setSeconds] = useState(0);
  const [isRunning, setIsRunning] = useState(false);
  
  useEffect(() => {
    let interval = null;
    
    if (isRunning) {
      interval = setInterval(() => {
        setSeconds(s => s + 1);
      }, 1000);
    }
    
    // Cleanup: clear interval
    return () => {
      if (interval) clearInterval(interval);
    };
  }, [isRunning]); // Run when isRunning changes
  
  return (
    <div>
      <h1>{seconds}s</h1>
      <button onClick={() => setIsRunning(!isRunning)}>
        {isRunning ? 'Stop' : 'Start'}
      </button>
      <button onClick={() => setSeconds(0)}>Reset</button>
    </div>
  );
}
```

**Use Case 5: Fetch when dependency changes**
```jsx
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    setLoading(true);
    
    fetch(`https://jsonplaceholder.typicode.com/users/${userId}`)
      .then(res => res.json())
      .then(data => {
        setUser(data);
        setLoading(false);
      });
  }, [userId]); // Re-fetch when userId changes
  
  if (loading) return <div>Loading...</div>;
  if (!user) return <div>User not found</div>;
  
  return (
    <div>
      <h2>{user.name}</h2>
      <p>Email: {user.email}</p>
      <p>Phone: {user.phone}</p>
    </div>
  );
}
```

**Use Case 6: LocalStorage sync**
```jsx
function NoteApp() {
  const [note, setNote] = useState(() => {
    // Initialize from localStorage
    return localStorage.getItem('note') || '';
  });
  
  useEffect(() => {
    // Save to localStorage whenever note changes
    localStorage.setItem('note', note);
  }, [note]);
  
  return (
    <textarea
      value={note}
      onChange={(e) => setNote(e.target.value)}
      placeholder="Your note..."
    />
  );
}
```

---

## 3. useContext - Global State

### What It Does
Access context values without prop drilling.

### Setup

```jsx
import { createContext, useContext, useState } from 'react';

// 1. Create context
const ThemeContext = createContext();

// 2. Provider component
function App() {
  const [theme, setTheme] = useState('light');
  
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <Header />
      <Content />
    </ThemeContext.Provider>
  );
}

// 3. Consume context
function Header() {
  const { theme, setTheme } = useContext(ThemeContext);
  
  return (
    <header className={theme}>
      <h1>My App</h1>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </header>
  );
}

function Content() {
  const { theme } = useContext(ThemeContext);
  
  return (
    <div className={`content ${theme}`}>
      <p>Current theme: {theme}</p>
    </div>
  );
}
```

### Real-World Use Case: Authentication

```jsx
const AuthContext = createContext();

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    // Check for stored session
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
    setLoading(false);
  }, []);
  
  const login = (email, password) => {
    // API call
    const userData = { email, name: 'User' };
    setUser(userData);
    localStorage.setItem('user', JSON.stringify(userData));
  };
  
  const logout = () => {
    setUser(null);
    localStorage.removeItem('user');
  };
  
  return (
    <AuthContext.Provider value={{ user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
}

// Custom hook for easier access
function useAuth() {
  return useContext(AuthContext);
}

// Usage in components
function LoginButton() {
  const { user, login, logout } = useAuth();
  
  if (user) {
    return (
      <div>
        Welcome, {user.name}!
        <button onClick={logout}>Logout</button>
      </div>
    );
  }
  
  return (
    <button onClick={() => login('user@example.com', 'password')}>
      Login
    </button>
  );
}
```

---

## 4. useRef - DOM Access & Persistent Values

### What It Does
1. Access DOM elements directly
2. Store mutable values that persist across renders (doesn't trigger re-render)

### Use Case 1: Focus input

```jsx
function SearchForm() {
  const inputRef = useRef(null);
  
  useEffect(() => {
    // Focus input on mount
    inputRef.current.focus();
  }, []);
  
  return (
    <input
      ref={inputRef}
      type="text"
      placeholder="Search..."
    />
  );
}
```

### Use Case 2: Store previous value

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  const prevCountRef = useRef();
  
  useEffect(() => {
    prevCountRef.current = count;
  });
  
  const prevCount = prevCountRef.current;
  
  return (
    <div>
      <h1>Now: {count}, Before: {prevCount}</h1>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

### Use Case 3: Store interval ID

```jsx
function Stopwatch() {
  const [time, setTime] = useState(0);
  const [isRunning, setIsRunning] = useState(false);
  const intervalRef = useRef(null);
  
  useEffect(() => {
    if (isRunning) {
      intervalRef.current = setInterval(() => {
        setTime(t => t + 1);
      }, 1000);
    } else {
      clearInterval(intervalRef.current);
    }
    
    return () => clearInterval(intervalRef.current);
  }, [isRunning]);
  
  return (
    <div>
      <h1>{time}s</h1>
      <button onClick={() => setIsRunning(!isRunning)}>
        {isRunning ? 'Stop' : 'Start'}
      </button>
    </div>
  );
}
```

---

## 5. useReducer - Complex State Logic

### What It Does
Alternative to useState for complex state logic.

### When to Use
- Multiple related state values
- Complex state updates
- State depends on previous state
- Similar pattern to Redux

### Syntax

```jsx
const [state, dispatch] = useReducer(reducer, initialState);
```

### Example: Todo App with useReducer

```jsx
const initialState = {
  todos: [],
  filter: 'all'
};

function reducer(state, action) {
  switch (action.type) {
    case 'ADD_TODO':
      return {
        ...state,
        todos: [...state.todos, {
          id: Date.now(),
          text: action.payload,
          completed: false
        }]
      };
    
    case 'TOGGLE_TODO':
      return {
        ...state,
        todos: state.todos.map(todo =>
          todo.id === action.payload
            ? { ...todo, completed: !todo.completed }
            : todo
        )
      };
    
    case 'DELETE_TODO':
      return {
        ...state,
        todos: state.todos.filter(todo => todo.id !== action.payload)
      };
    
    case 'SET_FILTER':
      return {
        ...state,
        filter: action.payload
      };
    
    default:
      return state;
  }
}

function TodoApp() {
  const [state, dispatch] = useReducer(reducer, initialState);
  const [input, setInput] = useState('');
  
  const addTodo = () => {
    if (input.trim()) {
      dispatch({ type: 'ADD_TODO', payload: input });
      setInput('');
    }
  };
  
  const filteredTodos = state.todos.filter(todo => {
    if (state.filter === 'active') return !todo.completed;
    if (state.filter === 'completed') return todo.completed;
    return true;
  });
  
  return (
    <div>
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyPress={(e) => e.key === 'Enter' && addTodo()}
      />
      <button onClick={addTodo}>Add</button>
      
      <div>
        <button onClick={() => dispatch({ type: 'SET_FILTER', payload: 'all' })}>
          All
        </button>
        <button onClick={() => dispatch({ type: 'SET_FILTER', payload: 'active' })}>
          Active
        </button>
        <button onClick={() => dispatch({ type: 'SET_FILTER', payload: 'completed' })}>
          Completed
        </button>
      </div>
      
      <ul>
        {filteredTodos.map(todo => (
          <li key={todo.id}>
            <input
              type="checkbox"
              checked={todo.completed}
              onChange={() => dispatch({ type: 'TOGGLE_TODO', payload: todo.id })}
            />
            <span style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
              {todo.text}
            </span>
            <button onClick={() => dispatch({ type: 'DELETE_TODO', payload: todo.id })}>
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
```

---

## 6. useMemo - Memoize Expensive Calculations

### What It Does
Caches the result of expensive calculations.

### When to Use
- Expensive computations
- Avoid recalculating on every render
- Performance optimization

### Example

```jsx
function ProductList({ products, searchTerm }) {
  // Without useMemo: filters on every render
  // const filtered = products.filter(p => p.name.includes(searchTerm));
  
  // With useMemo: only recalculates when dependencies change
  const filtered = useMemo(() => {
    console.log('Filtering...');
    return products.filter(p =>
      p.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }, [products, searchTerm]);
  
  const total = useMemo(() => {
    console.log('Calculating total...');
    return filtered.reduce((sum, p) => sum + p.price, 0);
  }, [filtered]);
  
  return (
    <div>
      <p>Found {filtered.length} products</p>
      <p>Total: ${total}</p>
      
      <ul>
        {filtered.map(product => (
          <li key={product.id}>
            {product.name} - ${product.price}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

---

## 7. useCallback - Memoize Functions

### What It Does
Returns a memoized version of callback function.

### When to Use
- Pass callbacks to optimized child components
- Prevent unnecessary re-creations

### Example

```jsx
function ParentComponent() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('');
  
  // Without useCallback: new function on every render
  // const handleClick = () => console.log('Clicked');
  
  // With useCallback: same function unless dependencies change
  const handleClick = useCallback(() => {
    console.log('Clicked with count:', count);
  }, [count]);
  
  return (
    <div>
      <input value={name} onChange={(e) => setName(e.target.value)} />
      <MemoizedChild onClick={handleClick} />
    </div>
  );
}

const MemoizedChild = React.memo(({ onClick }) => {
  console.log('Child rendered');
  return <button onClick={onClick}>Click me</button>;
});
```

---

## 8. Custom Hooks - Reusable Logic

### What They Are
Functions that use hooks and can be reused across components.

### Example 1: useFetch

```jsx
function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err);
        setLoading(false);
      });
  }, [url]);
  
  return { data, loading, error };
}

// Usage
function UserList() {
  const { data: users, loading, error } = useFetch('/api/users');
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

### Example 2: useLocalStorage

```jsx
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
function Settings() {
  const [theme, setTheme] = useLocalStorage('theme', 'light');
  
  return (
    <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
      Theme: {theme}
    </button>
  );
}
```

---

## Hooks Comparison Table

| Hook | Purpose | Returns | When to Use |
|------|---------|---------|-------------|
| **useState** | Add state | [value, setter] | Any changing data |
| **useEffect** | Side effects | Cleanup function | API calls, subscriptions, timers |
| **useContext** | Access context | Context value | Global state, theme, auth |
| **useRef** | DOM access | Ref object | Focus, scroll, store mutable values |
| **useReducer** | Complex state | [state, dispatch] | Multiple related states |
| **useMemo** | Memoize value | Cached value | Expensive calculations |
| **useCallback** | Memoize function | Cached function | Prevent function recreation |
| **Custom** | Reusable logic | Any | Share logic between components |

---

## Summary

### Array Methods
- **map()** - Transform arrays (render lists)
- **filter()** - Select elements (search, remove items)
- **reduce()** - Calculate totals
- **find()** - Find single element
- **some()/every()** - Test conditions

### Props vs State
- **Props** - Data from parent, read-only, configuration
- **State** - Internal data, mutable, triggers re-renders

### Hooks
- **useState** - Component state
- **useEffect** - Side effects
- **useContext** - Global data
- **useRef** - DOM access
- **useReducer** - Complex state
- **useMemo** - Cache values
- **useCallback** - Cache functions
- **Custom Hooks** - Reusable logic

**Practice these concepts by building real projects!**