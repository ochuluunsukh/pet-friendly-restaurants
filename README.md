### User Requirements Document: Pet-Friendly Restaurant Finder

#### **Project Overview**
A web-based platform to help users find pet-friendly restaurants. The platform will include restaurant listings, reviews, user profiles, and filtering options. The system will also manage restaurant details and categorize them based on pet-friendliness.

---

### **Entities**

1. **User**
   - Attributes:
     - `user_id`
     - `username`
     - `email`
     - `password`
     - `profile_picture`
     - `Bio` (I have two dogs.)
     - `is_pet_owner`
   - Relationships:
     - A user can write **reviews**.
     - A user can save **favorite restaurants**.

2. **Favorite**
   - Attributes:
     - `favorite_id`
     - `user_id`
     - `restaurant_id`
   - Relationships:
     - A user can have multiple **favorite restaurants**.

3. **Review**
   - Attributes:
     - `review_id`
     - `user_id`
     - `restaurant_id`
     - `rating`
     - `comment`
     - `created_at`
   - Relationships:
     - A review is written by a **user** and belongs to a **restaurant**.

4. **Category** (Admin manage)
   - Attributes:
     - `category_id`
     - `name`
   - Relationships:
     - A category can include multiple **restaurants**, and a restaurant can belong to multiple **categories**.

5. **Restaurant** (Admin manage)
   - Attributes:
     - `restaurant_id`
     - `name`
     - `address`
     - `phone_number`
     - `website`
   - Relationships:
     - A restaurant can have multiple **reviews**.
     - A restaurant can belong to multiple **categories**.

---

### **Key Functionalities**
1. **Search and Filter**
   - Users can search for restaurants by name, location, or category.
   - Users can filter results by pet-friendliness, rating, or amenities.

2. **Restaurant Profiles**
   - Display detailed information about restaurants, including pet-friendliness, reviews, and contact details.

3. **User Accounts**
   - Allow users to create profiles, save favorite restaurants, and write reviews.

4. **Reviews and Ratings**
   - Users can rate restaurants and leave reviews to help others decide.

5. **Admin Management**
   - Admin users can add, edit, or delete restaurants and categories.

---
