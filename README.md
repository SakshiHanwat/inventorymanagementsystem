# 🏪 Inventory Management System

A **console-based Inventory Management System** for a small retail store, built with **Pure Java + JDBC + MySQL** following clean OOP architecture and layered design patterns.

---

## 🚀 Features

- ✅ Add new products to inventory
- ✅ View all products with real-time stock status
- ✅ Update product quantity
- ✅ Remove products with confirmation
- ✅ Input validation (no negative price/quantity)
- ✅ Duplicate product name check
- ✅ Stock alerts — 🟢 IN STOCK / 🟡 LOW STOCK / 🔴 OUT OF STOCK
- ✅ MySQL persistent storage

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java (Pure OOP) | Core language |
| JDBC | Database connectivity |
| MySQL | Persistent storage |
| IntelliJ IDEA | IDE |

---

## 📁 Project Structure

```
src/com/inventory/
│
├── Main.java                        ← Entry point + CLI Menu
│
├── model/
│   └── Product.java                 ← Data model (Encapsulation)
│
├── config/
│   ├── DBConfig.java                ← DB credentials (gitignored)
│   └── DBConnection.java            ← Singleton DB connection
│
├── repository/
│   ├── ProductRepository.java       ← Interface (Abstraction)
│   └── ProductRepositoryImpl.java   ← JDBC SQL queries (DAO Pattern)
│
├── service/
│   ├── ProductService.java          ← Interface (Business contract)
│   └── ProductServiceImpl.java      ← Business logic + Validation
│
├── controller/
│   └── ProductController.java       ← User input + output display
│
├── exception/
│   ├── ProductNotFoundException.java
│   └── DuplicateProductException.java
│
└── util/
    └── ConsoleHelper.java           ← Safe Scanner wrapper
```

---

## 🎯 Design Patterns Used

| Pattern | File | Description |
|---------|------|-------------|
| **Singleton** | `DBConnection.java` | Single DB connection throughout app |
| **DAO Pattern** | `ProductRepositoryImpl.java` | DB logic separated from business logic |
| **Layered Architecture** | All layers | Controller → Service → Repository → DB |
| **Encapsulation** | `Product.java` | Private fields + getters/setters |
| **Abstraction** | `ProductRepository`, `ProductService` | Interface hides implementation |
| **Custom Exceptions** | `exception/` package | Meaningful, typed error handling |

---

## ⚙️ Setup & Installation

### Prerequisites
- Java JDK 8+
- MySQL Server
- [MySQL Connector/J JAR](https://dev.mysql.com/downloads/connector/j/)

---

### Step 1 — Database Setup

Run `schema.sql` in your MySQL terminal:
```sql
source /path/to/schema.sql;
```
This will create `inventory_db` database and `products` table with sample data.

---

### Step 2 — Configure Database Credentials

Create `src/com/inventory/config/DBConfig.java` (this file is gitignored):
```java
package com.inventory.config;

public class DBConfig {
    public static final String URL      = "jdbc:mysql://localhost:3306/inventory_db?useSSL=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "your_password_here";
    public static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
}
```

---

### Step 3 — Add MySQL Connector JAR in IntelliJ

```
File → Project Structure → Libraries → + → Java
→ Select mysql-connector-j-x.x.x.jar → OK → Apply
```

---

### Step 4 — Run

Click the ▶️ Run button in IntelliJ on `Main.java`

---

## 📸 Output Preview

```
  ╔══════════════════════════════════════╗
  ║   🏪 INVENTORY MANAGEMENT SYSTEM 🏪  ║
  ║        Small Retail Store            ║
  ╚══════════════════════════════════════╝

  ┌─────────────────────────────────────┐
  │              MAIN MENU              │
  ├─────────────────────────────────────┤
  │  1.  View All Products              │
  │  2.  Add New Product                │
  │  3.  Update Product Quantity        │
  │  4.  Remove Product                 │
  │  5.  Exit                           │
  └─────────────────────────────────────┘

╠═══╦════════════════════════╦══════════════╦══════════╦══════════╣
║ ID║ Name                   ║ Category     ║ Price    ║ Qty      ║
╠═══╬════════════════════════╬══════════════╬══════════╬══════════╣
║ 1 ║ Basmati Rice 5kg       ║ Groceries    ║ Rs.320   ║ 50  🟢   ║
║ 2 ║ Colgate Toothpaste     ║ Personal Care║ Rs.89    ║  4  🟡   ║
║ 3 ║ Tata Salt 1kg          ║ Groceries    ║ Rs.22    ║  0  🔴   ║
╚═══╩════════════════════════╩══════════════╩══════════╩══════════╝
```

---

## 📌 Note

`DBConfig.java` is added to `.gitignore` to keep database credentials secure.
Use `src/com/inventory/config/example.java` as a reference to create your own `DBConfig.java`.

---

## 👩‍💻 Author

Made with ❤️ for Java Machine Round Interview
