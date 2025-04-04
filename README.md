# Galaxy Cinema 🎬

## Introduction

Galaxy Cinema is a modern web application that allows users to book movie tickets online. The system provides a smooth and convenient ticket booking experience for customers, from viewing movie listings, selecting showtimes, choosing seats, to payment and receiving e-tickets. A WinForms application developed during university, which is still maintained.

## Technologies Used 🚀

### Frontend

- **Angular**: JavaScript framework for building user interfaces
- **TypeScript**: Primary programming language
- **Angular Material**: UI components library
- **Tailwind CSS**: CSS framework for creating beautiful and responsive interfaces
- **RxJS**: Library for handling asynchronous operations
- **Slick Carousel**: Slider library
- **Stripe JS**: Payment integration

### Backend

- **Spring Boot**: Java framework for building the backend
- **Spring Security**: Authentication and authorization management
- **Spring Data JPA**: Database interaction
- **MySQL**: Main database system
- **JWT**: Authentication with JSON Web Tokens
- **Stripe API**: Payment processing
- **Java Mail**: Sending ticket confirmation emails
- **ZXing**: QR code generation for e-tickets

### Other Tech

- **Docker**: Docker for containerized applications, ensuring consistency across environments.
- **Winform**: The Winform POS system enable staff to efficiently process ticket and food sale at the cinema.
- **Figma**: For UI/UX designs.
- **Postman**: For API testing.

## Main Features 🚀

### For Customers

1. **Movie Browsing**

   - List of current/upcoming movies
   - Detailed movie information (poster, cast, ratings, trailer)
   - Movie search

2. **Ticket Booking**

   - Select showtimes by date and time
   - Choose seats with visual interface
   - Order food and beverages
   - Online payment via Stripe
   - Receive e-tickets with QR code

3. **Movie Reviews**

   - Write reviews after watching movies
   - Rate movies

4. **Account Management**
   - Register, login

### For Administrators

1. **Movie Management**

   - Add, edit, delete movies
   - Update movie status

2. **Showtime Management**

   - Add, edit, delete showtimes
   - Arrange movie schedules

3. **Food Management**

   - Manage food and beverage menu
   - Update prices and availability

4. **Customer Management**

   - View customer list
   - Manage user information

5. **Reports & Statistics**
   - Revenue by movie, showtime
   - Number of tickets sold
   - User data analysis

## Installation and Running Guide ⚡

1. Clone this repository

```
git clone https://github.com/DuanNguyenCoder/galaxy-cinema.git
```

2. Update Stripe for payment and Email in `application.properties` in server folder:

```properties
stripe.api.key= "your api key"
app.email = "your email"
app.password = "your email passwords"
```

3. Build and run Docker containers

```
cd <project-directory>
docker-compose up --build -d
```

4. Access the Application

- Frontend: [http://localhost:4200]()
- Backend: [http://localhost:8083]()

## Running the WinForms Application ⚡

1. Open the Project in Visual Studio

- Open Visual Studio.

- Click Open a project or solution.

- Navigate to the project directory and open the .sln file.

2. Build the Solution

- In Visual Studio, click Build > Build Solution (Ctrl + Shift + B).

- Ensure there are no build errors.

3. Run the Application

- Press F5 to start the application in Debug mode.

- Or press Ctrl + F5 to run without debugging.
