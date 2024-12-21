### Project Requirements and Structure for "Pet-Friendly Restaurants Finder"

---

#### **1. User Requirements**
The application should allow customers to find, review, and explore pet-friendly restaurants. Key requirements include:

- **Customer Registration and Authentication**:
  - Customers can register.
  - Customers can save their favorite pet-friendly restaurants.

- **Restaurant Management**:
  - Restaurant can register their restaurants as pet-friendly.
  - Each restaurant should include details such as name, address, rating, and pet policies.

- **Search and Filter**:
  - Customers can search for restaurants by address or specific pet policies.
  - Filters should include options like "pet menu available," "pet play area," etc.

- **Reviews and Ratings**:
  - Customers can leave reviews and ratings for restaurants.
  - Reviews should include text, ratings, and optional pet-specific notes.

---

#### **2. Entity Definitions**
The system will consist of the following five primary entities and their relationships:

1. **Customer**:
   - Fields: `id`, `username`, `firstName`, `lastName`,  `email`, `password`, `role`, `reviews`, `favorites` (one-to-many relationship with `Restaurant`).
   - Relationships:
     - One-to-Many with `Review`.
     - One-to-Many with `favorites`

2. **Favorite**
   - Attributes:
     - `favorite_id`
     - `customer_id`
     - `restaurant_id`
   - Relationships:
     - Many-to-One with Customer.
     - Many-to-One with Restaurant.

3. **Review**:
   - Fields: `id`, `customerId`, `restaurantId`, `text`, `rating`, `createdAt`, `updatedAt`, `petSpecificNotes`.
   - Relationships:
     - Many-to-One with `Customer`.
     - Many-to-One with `Restaurant`.

4. **Restaurant**:
   - Fields: `id`, `name`, `rating`, `petPolicyDetails`, `isPetMenuAvailable`, `hasPetPlayArea`.
   - Relationships:
     - One-to-Many with `Review`.

5. **Address (Embeddable table)**:
   - Fields: `country`, `street`, `city`, `state`, `zipCode` .

---

#### **3. Service Division**
To ensure modularity and scalability, divide the application into two Spring Boot services:

1. **Restaurant Service**:
   - **Responsibilities**:
     - Manage restaurant data (CRUD operations).
     - Manage customer data (CRUD operations).
     - Handle restaurant search, filtering, and pet-specific policy management.
     - Manage customer favorites, and reviews.
     - Handle authentication.
   - **Entities**:
     - `Restaurant`, `Address`, `Customer`, `Favorite`, `Review`.
   - **Endpoints**:
     - `/restaurants` (GET, POST, PUT, DELETE).
     - `/restaurants/search` (GET with filters).
     - `/customers` (GET, POST, PUT, DELETE).
     - `/customers/favorites` (GET, POST).
     - `/customers/reviews` (GET, POST).

2. **Ratings Service**:
   - **Responsibilities**:
     - 
   - **Entities**:
     - Restaurant Ratings


---

#### **4. JMS Integration**
Use JMS for asynchronous communication between the two services:

- **Review Synchronization**:
  - When a review is added or updated in the **Restaurant Service**, send a message to the **Rating Service** to update the restaurant's average rating and review count.

---
