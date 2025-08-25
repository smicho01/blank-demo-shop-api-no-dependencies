# Spring Boot REST API - blank-demo-api

A simple Spring Boot REST API demonstrating basic CRUD operations across different domains.

## Overview

This API provides endpoints for managing customers, items, and orders. It includes configurable features and follows RESTful principles.

## Configuration

The application can be configured using the following properties:

- `serviceX.enabled`: Controls whether Service X functionality is enabled (default: `false`)
    - Set via environment variable: `ENABLE_SERVICE_X=true`

## API Endpoints

### Customer API
Base URL: `/customer`

#### Get Customer Information
- **GET** `/customer`
- **Description**: Retrieves customer information
- **Response**: Returns "Customer" string
- **Note**: Behavior varies based on `serviceX.enabled` configuration

**Example Request:**
```
GET /customer
```

**Example Response:**
```
Customer
```

---

### Item API
Base URL: `/item`

#### Get Item Information
- **GET** `/item`
- **Description**: Retrieves item information
- **Response**: Returns "Item" string

**Example Request:**
```
GET /item
```

**Example Response:**
```
Item
```

---

### Order API
Base URL: `/order`

#### Get All Orders
- **GET** `/order`
- **Description**: Retrieves all orders
- **Response**: JSON array of order objects

**Example Request:**
```
GET /order
```

**Example Response:**
```json
[
  {
    "id": 1,
    "description": "Sample order",
    "amount": 100.0
  }
]
```

#### Get Order by ID
- **GET** `/order/{id}`
- **Description**: Retrieves a specific order by ID
- **Parameters**:
    - `id` (path parameter): Order ID (Long)
- **Response**: Order object or null if not found

**Example Request:**
```
GET /order/1
```

**Example Response:**
```json
{
  "id": 1,
  "description": "Sample order",
  "amount": 100.0
}
```

#### Create New Order
- **POST** `/order`
- **Description**: Creates a new order
- **Request Body**: Order object (without ID)
- **Response**: Created order with assigned ID

**Example Request:**
```
POST /order
Content-Type: application/json

{
  "description": "New order",
  "amount": 250.0
}
```

**Example Response:**
```json
{
  "id": 2,
  "description": "New order",
  "amount": 250.0
}
```

#### Update Order
- **PUT** `/order/{id}`
- **Description**: Updates an existing order
- **Parameters**:
    - `id` (path parameter): Order ID (Long)
- **Request Body**: Order object with updated fields
- **Response**: Updated order object or null if not found

**Example Request:**
```
PUT /order/1
Content-Type: application/json

{
  "description": "Updated order description",
  "amount": 150.0
}
```

**Example Response:**
```json
{
  "id": 1,
  "description": "Updated order description",
  "amount": 150.0
}
```

#### Delete Order
- **DELETE** `/order/{id}`
- **Description**: Deletes an order by ID
- **Parameters**:
    - `id` (path parameter): Order ID (Long)
- **Response**: Success or failure message

**Example Request:**
```
DELETE /order/1
```

**Example Response:**
```
Order deleted successfully
```

## Data Models

### Order
```json
{
  "id": "Long",
  "description": "String",
  "amount": "Double"
}
```

## Getting Started

1. Clone the repository
2. Run the application using `mvn spring-boot:run` or your IDE
3. The API will be available at `http://localhost:8080`
4. Configure environment variables as needed (e.g., `ENABLE_SERVICE_X=true`)

## Notes

- The Order API uses in-memory storage, so data will be lost when the application restarts
- The Customer API behavior changes based on the `serviceX.enabled` configuration
- All endpoints return plain text except for Order API endpoints which return JSON

## Technology Stack

- Spring Boot
- Spring Web
- Java

---

*Generated from REST controller analysis*