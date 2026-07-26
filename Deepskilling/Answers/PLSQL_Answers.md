# PL/SQL Programming

## Table Setup

```sql
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE,
    IsVIP CHAR(1) DEFAULT 'F'
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

CREATE TABLE AuditLog (
    LogID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    LogDate DATE,
    Action VARCHAR2(100)
);

-- Insert Sample Data
INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 12000, SYSDATE, 'F');
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE, 'F');
INSERT INTO Accounts VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 1500, SYSDATE);
INSERT INTO Loans VALUES (1, 1, 5000, 5, SYSDATE, SYSDATE + 20);
INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
COMMIT;
```

---

## Exercise 1: Control Structures

### Scenario 1: Apply 1% discount to interest rate for customers over 60 years old

```sql
DECLARE
    v_age NUMBER;
BEGIN
    FOR c IN (SELECT CustomerID, DOB FROM Customers) LOOP
        v_age := MONTHS_BETWEEN(SYSDATE, c.DOB) / 12;
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = c.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/
```

### Scenario 2: Promote customer to VIP status if balance > $10,000

```sql
BEGIN
    FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF c.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'T'
            WHERE CustomerID = c.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/
```

### Scenario 3: Print reminder for loans due in next 30 days

```sql
BEGIN
    FOR l IN (
        SELECT l.LoanID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan #' || l.LoanID || ' for ' || l.Name || ' is due on ' || TO_CHAR(l.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
```

---

## Exercise 2: Error Handling

### Scenario 1: SafeTransferFunds procedure with rollback

```sql
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_acc IN NUMBER,
    p_to_acc   IN NUMBER,
    p_amount   IN NUMBER
) IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_from_acc FOR UPDATE;
    IF v_bal < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance!');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
```

### Scenario 2: UpdateSalary procedure for non-existent employee

```sql
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id IN NUMBER,
    p_percent IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_percent / 100))
    WHERE EmployeeID = p_emp_id;

    IF SQL%NOTFOUND THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID does not exist');
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
```

### Scenario 3: AddNewCustomer with duplicate ID check

```sql
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_id || ' already exists!');
END;
/
```

---

## Exercise 3: Stored Procedures

### Scenario 1: Apply 1% monthly interest to savings accounts

```sql
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    COMMIT;
END;
/
```

### Scenario 2: Employee bonus update by department

```sql
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_dept IN VARCHAR2,
    p_bonus_percent IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_bonus_percent / 100))
    WHERE Department = p_dept;
    COMMIT;
END;
/
```

### Scenario 3: Transfer funds between accounts

```sql
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_src IN NUMBER,
    p_dest IN NUMBER,
    p_amt IN NUMBER
) IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_src;
    IF v_bal >= p_amt THEN
        UPDATE Accounts SET Balance = Balance - p_amt WHERE AccountID = p_src;
        UPDATE Accounts SET Balance = Balance + p_amt WHERE AccountID = p_dest;
        COMMIT;
    END IF;
END;
/
```

---

## Exercise 4: Functions

### Scenario 1: CalculateAge function

```sql
CREATE OR REPLACE FUNCTION CalculateAge (p_dob IN DATE) RETURN NUMBER IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/
```

### Scenario 2: CalculateMonthlyInstallment function

```sql
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_amount IN NUMBER,
    p_rate IN NUMBER,
    p_years IN NUMBER
) RETURN NUMBER IS
    v_m_rate NUMBER;
    v_months NUMBER;
BEGIN
    v_m_rate := (p_rate / 100) / 12;
    v_months := p_years * 12;
    RETURN ROUND((p_amount * v_m_rate * POWER(1 + v_m_rate, v_months)) / (POWER(1 + v_m_rate, v_months) - 1), 2);
END;
/
```

### Scenario 3: HasSufficientBalance function

```sql
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_acc_id IN NUMBER,
    p_amt IN NUMBER
) RETURN BOOLEAN IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_acc_id;
    RETURN v_bal >= p_amt;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
```

---

## Exercise 5: Triggers

### Scenario 1: Update LastModified date on customer update

```sql
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/
```

### Scenario 2: Audit log trigger on Transactions table

```sql
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, TransactionID, LogDate, Action)
    VALUES (NVL((SELECT MAX(LogID) FROM AuditLog), 0) + 1, :NEW.TransactionID, SYSDATE, 'Transaction inserted');
END;
/
```

### Scenario 3: Transaction rules check (deposit > 0, withdrawal <= balance)

```sql
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_bal NUMBER;
BEGIN
    IF :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Amount must be positive');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF v_bal < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20004, 'Amount exceeds balance');
        END IF;
    END IF;
END;
/
```

---

## Exercise 6: Cursors

### Scenario 1: Monthly statements cursor

```sql
DECLARE
    CURSOR c_stmt IS
        SELECT TransactionID, AccountID, Amount, TransactionType
        FROM Transactions
        WHERE TRUNC(TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM');
BEGIN
    FOR r IN c_stmt LOOP
        DBMS_OUTPUT.PUT_LINE('Tx #' || r.TransactionID || ' - Acc ' || r.AccountID || ': $' || r.Amount);
    END LOOP;
END;
/
```

### Scenario 2: Annual fee deduction cursor

```sql
DECLARE
    CURSOR c_acc IS SELECT AccountID FROM Accounts FOR UPDATE;
BEGIN
    FOR a IN c_acc LOOP
        UPDATE Accounts SET Balance = Balance - 50 WHERE AccountID = a.AccountID;
    END LOOP;
    COMMIT;
END;
/
```

### Scenario 3: Update loan interest rates cursor

```sql
DECLARE
    CURSOR c_loans IS SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
BEGIN
    FOR l IN c_loans LOOP
        UPDATE Loans SET InterestRate = l.InterestRate + 0.5 WHERE LoanID = l.LoanID;
    END LOOP;
    COMMIT;
END;
/
```

---

## Exercise 7: Packages

### Scenario 1: CustomerManagement Package

```sql
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_bal NUMBER);
    FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_bal NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_bal, SYSDATE);
        COMMIT;
    END AddCustomer;

    FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER IS
        v_bal NUMBER;
    BEGIN
        SELECT Balance INTO v_bal FROM Customers WHERE CustomerID = p_id;
        RETURN v_bal;
    END GetBalance;
END CustomerManagement;
/
```
