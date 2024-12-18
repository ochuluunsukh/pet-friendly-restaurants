### Project Requirements and Structure for "Pet-Friendly Restaurants Finder"

---

#### **1. User Requirements**
The application should allow users to find, review, and explore pet-friendly restaurants. Key requirements include:

- **User Registration and Authentication**:
  - Users can sign up, log in, and manage their profiles.
  - Users can save their favorite pet-friendly restaurants.

- **Restaurant Management**:
  - Restaurant owners can register their restaurants as pet-friendly.
  - Each restaurant should include details such as name, address, category, rating, and pet policies.

- **Search and Filter**:
  - Users can search for restaurants by location, category, or specific pet policies.
  - Filters should include options like "pet menu available," "pet play area," etc.

- **Reviews and Ratings**:
  - Users can leave reviews and ratings for restaurants.
  - Reviews should include text, ratings, and optional pet-specific notes.

---

#### **2. Entity Definitions**
The system will consist of the following five primary entities and their relationships:

1. **User**:
   - Fields: `id`, `name`, `email`, `password`, `favorites` (many-to-many relationship with `Restaurant`).
   - Relationships:
     - One-to-Many with `Review`.

2. **Favorite**
   - Attributes:
     - `favorite_id`
     - `user_id`
     - `restaurant_id`
   - Relationships:
     - A user can have multiple **favorite restaurants**.

3. **Restaurant**:
   - Fields: `id`, `name`, `address`, `category`, `rating`, `petPolicyDetails`.
   - Relationships:
     - Many-to-Many with `User`.
     - One-to-Many with `Review`.
     - Many-to-One with `Category`.

4. **Review**:
   - Fields: `id`, `userId`, `restaurantId`, `text`, `rating`, `petSpecificNotes`.
   - Relationships:
     - Many-to-One with `User`.
     - Many-to-One with `Restaurant`.

5. **Address**:
   - Fields: `id`, `street`, `city`, `state`, `zipCode`.

---

#### **3. Service Division**
To ensure modularity and scalability, divide the application into two Spring Boot services:

1. **Restaurant Service**:
   - **Responsibilities**:
     - Manage restaurant data (CRUD operations).
     - Handle restaurant search, filtering, and pet-specific policy management.
   - **Entities**:
     - `Restaurant`, `Address`.
   - **Endpoints**:
     - `/restaurants` (GET, POST, PUT, DELETE).
     - `/restaurants/search` (GET with filters).

2. **User Service**:
   - **Responsibilities**:
     - Manage user profiles, favorites, and reviews.
     - Handle authentication and notification subscriptions.
   - **Entities**:
     - `User`, `Favorite`, `Review`.
   - **Endpoints**:
     - `/users` (GET, POST, PUT, DELETE).
     - `/users/favorites` (GET, POST).
     - `/users/reviews` (GET, POST).

---

#### **4. JMS Integration**
Use JMS for asynchronous communication between the two services:

- **Review Synchronization**:
  - When a review is added or updated in the **User Service**, send a message to the **Restaurant Service** to update the restaurant's average rating and review count.

---
