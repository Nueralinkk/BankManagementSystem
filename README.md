# Bank Management System

A robust console-based **Bank Management System** written in Java that interfaces seamlessly with a MySQL database. The application efficiently handles customer record retrieval, database entity mapping, and real-time record configuration validation.

---

## 🚀 Key Features

* **Automated Data Mapping**: Automatically fetches existing database tables and dynamically populates structured data matrices on startup.
* **Modular Codebase**: Clean, decoupled structural patterns optimizing separate logic modules for database indexing (`loadCustomersFromDatabase`), state configuration loop exits (`shouldExit`), and dashboard layout formatting (`printMenuUI`).
* **Multi-Layer Validation**: Prevents data degradation using strong type safety layers and cross-verifies account ownership constraints via structural IDs and customer name flags.
* **Modern Switch Semantics**: Harnesses clean flow control constructs avoiding hazardous switch fall-through logic paths.

---

## 🛠️ Architecture Details

The program splits operational processes out from monolithic blocks into atomic, single-responsibility methods:
1. `loadCustomersFromDatabase()`: Encapsulates standalone connection pooling, prepared statements, cursor navigation via JDBC, and dynamic data collections mapping.
2. `shouldExit()`: Centralized terminal pipeline state evaluator verifying termination flags.
3. `printMenuUI()`: Decoupled design asset isolating structural application dashboard views from terminal logical paths.

---

## 📋 Prerequisites

To run this application, you must install and configure:
* **Java Development Kit (JDK)**: Version 14 or higher.
* **MySQL Database Server**: Active local or remote engine running a `Bank` schema.
* **JDBC Connector**: `com.mysql.cj.jdbc.Driver` included in your environment build classpath dependencies.

---

## ⚙️ Configuration Setup

The source architecture prioritizes identity isolation by reading sensitive database properties via environment variables rather than hardcoded configuration literals. 

Set up these system variables on your host device prior to executing compilation steps:

### Windows (Command Prompt)
```cmd
set DB_USER=your_mysql_username
set DB_PASSWORD=your_mysql_password
```

### macOS / Linux (Terminal)
```bash
export DB_USER="your_mysql_username"
export DB_PASSWORD="your_mysql_password"
```

---

## 🗄️ Database Schema Requirement

Ensure your underlying target MySQL schema aligns with the model mappings below:

```sql
CREATE DATABASE Bank;
USE Bank;

CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_number BIGINT,
    account_number BIGINT NOT NULL,
    account_type VARCHAR(50)
);
```

---

## 🏃 Compilation and Execution

Navigate to your workspace root directory containing your source code modules path context to compile and instantiate:

```bash
# Compile code layout architecture
javac Customer/Main.java

# Execute main class entry point
java Customer.Main
```

---

## 💻 Operations Summary Breakdown

1. **Identity Query**: Input the customer's legal string identifier matching your records.
2. **Account Summary**: Displays structural account metrics mapping relational indexes.
3. **Core Operations Panel**: Select localized numeric actions to execute state mutations:
   * `1`: Modify phone tracking parameters accompanied by multi-tiered verification loops.
   * `2` to `5`: Reserved slots for upcoming microservice plugins.
   * `6`: Safely logs out of current customer session scopes.
