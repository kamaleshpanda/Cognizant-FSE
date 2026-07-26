# ReactJS Hands-On Labs - Answers


---

## Lab 1 & 2: Introduction to Components & JSX

### Basic App Component (`App.js`)
```jsx
import React from 'react';

function App() {
  const heading = "Welcome to React JS Community Portal";
  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>{heading}</h1>
      <p>Building single page applications using React components.</p>
    </div>
  );
}

export default App;
```

---

## Lab 4: Lifecycle Hooks & Error Boundaries

### Posts Component with Lifecycle Methods (`Posts.js`)
```jsx
import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }

  componentDidMount() {
    this.loadPosts();
  }

  loadPosts = () => {
    fetch('https://jsonplaceholder.typicode.com/posts?_limit=5')
      .then(res => res.json())
      .then(data => this.setState({ posts: data }))
      .catch(err => this.setState({ error: err.message }));
  };

  componentDidCatch(error, info) {
    alert("Error caught in component: " + error);
  }

  render() {
    const { posts, error } = this.state;
    if (error) return <div>Error: {error}</div>;

    return (
      <div>
        <h2>Latest Posts</h2>
        {posts.map(post => (
          <div key={post.id} style={{ borderBottom: '1px solid #ccc', margin: '10px 0' }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
```

---

## Lab 5: Styling Components with CSS Modules

### CSS Module (`CohortDetails.module.css`)
```css
.box {
  width: 300px;
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  border: 1px solid black;
  border-radius: 10px;
}

dt {
  font-weight: 500;
}
```

### Cohort Component (`CohortDetails.js`)
```jsx
import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const isOngoing = cohort.status === 'ongoing';
  const headingStyle = {
    color: isOngoing ? 'green' : 'blue'
  };

  return (
    <div className={styles.box}>
      <h3 style={headingStyle}>{cohort.name}</h3>
      <dl>
        <dt>Status:</dt>
        <dd>{cohort.status}</dd>
        <dt>Duration:</dt>
        <dd>{cohort.duration}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
```

---

## Lab 6: React Router DOM Implementation

### Navigation & Routing Setup (`App.js`)
```jsx
import React from 'react';
import { BrowserRouter, Routes, Route, Link, useParams } from 'react-router-dom';

const mockTrainers = [
  { id: '1', name: 'Alice', email: 'alice@example.com', tech: 'React' },
  { id: '2', name: 'Bob', email: 'bob@example.com', tech: 'Java FSE' }
];

function Home() {
  return <h2>Home - Cognizant Trainers Portal</h2>;
}

function TrainersList() {
  return (
    <div>
      <h2>Trainers List</h2>
      <ul>
        {mockTrainers.map(t => (
          <li key={t.id}>
            <Link to={`/trainers/${t.id}`}>{t.name} ({t.tech})</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

function TrainerDetail() {
  const { id } = useParams();
  const trainer = mockTrainers.find(t => t.id === id);

  if (!trainer) return <h3>Trainer not found</h3>;

  return (
    <div>
      <h3>Trainer Details</h3>
      <p>ID: {trainer.id}</p>
      <p>Name: {trainer.name}</p>
      <p>Email: {trainer.email}</p>
      <p>Technology: {trainer.tech}</p>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <nav style={{ margin: '10px' }}>
        <Link to="/" style={{ marginRight: '10px' }}>Home</Link>
        <Link to="/trainers">Trainers</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/trainers" element={<TrainersList />} />
        <Route path="/trainers/:id" element={<TrainerDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

---

## Lab 7: Props & Component Composition

### Cart Component (`Cart.js`)
```jsx
import React from 'react';

function Cart({ itemname, price }) {
  return (
    <div style={{ border: '1px solid #ddd', padding: '10px', margin: '5px' }}>
      <h4>Item: {itemname}</h4>
      <p>Price: ${price}</p>
    </div>
  );
}

export default Cart;
```

### OnlineShopping Component (`OnlineShopping.js`)
```jsx
import React from 'react';
import Cart from './Cart';

function OnlineShopping() {
  const items = [
    { itemname: "Laptop", price: 800 },
    { itemname: "Headphones", price: 50 },
    { itemname: "Keyboard", price: 30 },
    { itemname: "Mouse", price: 20 },
    { itemname: "Monitor", price: 150 }
  ];

  return (
    <div>
      <h2>Online Shopping Cart</h2>
      {items.map((item, index) => (
        <Cart key={index} itemname={item.itemname} price={item.price} />
      ))}
    </div>
  );
}

export default OnlineShopping;
```

---

## Lab 8: React State Management

### CountPeople Component (`CountPeople.js`)
```jsx
import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  UpdateEntry = () => {
    this.setState(prevState => ({ entrycount: prevState.entrycount + 1 }));
  };

  UpdateExit = () => {
    this.setState(prevState => ({ exitcount: prevState.exitcount + 1 }));
  };

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h2>Mall Visitor Counter</h2>
        <p>People Entered: {this.state.entrycount}</p>
        <p>People Exited: {this.state.exitcount}</p>
        <button onClick={this.UpdateEntry} style={{ marginRight: '10px' }}>Login</button>
        <button onClick={this.UpdateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
```

---

## Lab 9: ES6 Map, Arrow Functions & Destructuring

### Cricket App (`CricketApp.js`)
```jsx
import React from 'react';

const players = [
  { name: 'Virat Kohli', score: 95 },
  { name: 'Rohit Sharma', score: 85 },
  { name: 'KL Rahul', score: 65 },
  { name: 'Shreyas Iyer', score: 45 },
  { name: 'Hardik Pandya', score: 72 }
];

// Arrow function filter for scores >= 70
const topPlayers = players.filter(player => player.score >= 70);

function ListofPlayers() {
  return (
    <div>
      <h3>Top Scorers (Score >= 70)</h3>
      <ul>
        {topPlayers.map((player, index) => (
          <li key={index}>{player.name} - Score: {player.score}</li>
        ))}
      </ul>
    </div>
  );
}

function IndianPlayers() {
  const t20 = ['Surya', 'Hardik'];
  const ranji = ['Pujara', 'Rahane'];
  
  // Merge using ES6 Spread operator
  const mergedPlayers = [...t20, ...ranji];

  // Destructuring odd/even teams
  const [player1, player2, player3, player4] = mergedPlayers;

  return (
    <div>
      <h3>Merged Players Team</h3>
      <p>Odd Position Players: {player1}, {player3}</p>
      <p>Even Position Players: {player2}, {player4}</p>
    </div>
  );
}

function CricketApp({ flag }) {
  return (
    <div>
      <h2>Cricket Application</h2>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default CricketApp;
```
