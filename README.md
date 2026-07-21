# Artist Booking System

## Overview

The Artist Booking System is a Java-based backend application that simulates the complete workflow of booking artists for events. 
It models real-world interactions between event organizers and artists by supporting event creation, offer negotiation, slot management, booking confirmation, and payment tracking.

The project was built with a strong focus on Object-Oriented Design (OOD), clean architecture, and business rule modeling rather than simply implementing CRUD operations.

---

## Features

* Create and manage events.
* Manage artist profiles and their available performance slots.
* Send booking offers to artists.
* Accept, reject, cancel, or expire offers.
* Reserve and book artist slots during the booking lifecycle.
* Track booking status from creation to completion.
* Track advance payment status.
* Enforce business rules using domain validations and state transitions.
* In-memory repository implementation for data persistence.

---

## Architecture

The application follows a layered architecture.

                Main
                  │
                  ▼
        Service Layer
                  │
                  ▼
      Repository Layer
                  │
                  ▼
         Domain Entities


### Domain Entities

* **Artist** – Represents an artist available for booking.
* **Event** – Represents an event created by a user.
* **Offer** – Represents a booking proposal sent to an artist.
* **Slot** – Represents an artist's available performance time.
* **Booking** – Represents a confirmed booking after offer acceptance.
* **Money** – Value Object representing monetary values.

---

## Design Principles

This project follows several object-oriented design principles:

* Rich domain entities with encapsulated business behavior.
* Business rules enforced inside domain models.
* Services responsible for coordinating workflows across multiple entities.
* Repository pattern used to abstract persistence.
* Value Objects used for immutable concepts such as monetary values.
* Validation through exceptions to prevent invalid state transitions.

---

## Booking Workflow

User
 │
 ▼
Create Event
 │
 ▼
Select Artist Slot
 │
 ▼
Send Offer
 │
 ▼
Artist Accepts Offer
 │
 ▼
Booking Created
 │
 ▼
Advance Payment Received
 │
 ▼
Slot Booked
 │
 ▼
Booking Completed

---

## Business Rules Implemented

* Event date cannot be in the past.
* Slot timings cannot overlap.
* Invalid state transitions throw exceptions.
* Booked slots cannot be deleted.
* Only valid offers can be accepted.
* Advance payment is required before confirming a booking.
* Business validations are encapsulated within domain entities.

---

## Tech Stack

* Java
* Object-Oriented Programming (OOP)
* Java Collections Framework
* Layered Architecture
* Repository Pattern
* Git
* GitHub

---

## Current Limitations (Version 1)

* Uses in-memory repositories instead of a database.
* No REST APIs.
* No authentication or authorization.
* No payment gateway integration.
* No notification system.
* No scheduler for automatic offer expiry.

---

## Future Enhancements (Version 2)

* Spring Boot REST APIs
* MySQL database integration
* Spring Data JPA
* JWT Authentication
* Role-based access control
* Payment gateway integration
* Email/SMS notifications
* Automatic offer expiry scheduler
* Docker containerization
* Unit and integration testing
* CI/CD pipeline

---

## Learning Outcomes

This project helped strengthen understanding of:

* Object-Oriented Design
* Entity vs Value Object
* Repository Pattern
* Service Layer Design
* State Machine Design
* Business Rule Enforcement
* Exception Handling
* Clean Code Practices

---

## Author

**Adarsh Rajput**

