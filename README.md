# CampusConnect

**A Java-based Hostel Management System** — a console application for managing students, wardens, rooms, payments, and complaints in a campus hostel environment. Built as a portfolio project to apply core OOP principles (encapsulation, abstraction, inheritance, polymorphism), generics, lambda expressions, exception handling, and object cloning in a single, cohesive system.

---

## Project Structure

```
src/campusconnect/
├── Main.java                 # Menu-driven CLI application (entry point)
├── model/                    # Core domain models
│   ├── Person.java           # Abstract base class (name, id, contact)
│   ├── Student.java          # extends Person, implements Payable, Notifiable, Cloneable
│   ├── Warden.java           # extends Person, implements Notifiable
│   ├── Room.java             # capacity and occupant management
│   ├── Hostel.java           # holds multiple rooms, room lookup
│   ├── Complaint.java        # complaint tracking, deep-clonable
│   └── Notice.java           # notice/announcement data class
├── interfaces/                # Java interfaces
│   ├── Payable.java           # calculateDue(), makePayment()
│   └── Notifiable.java        # sendNotification() (default method)
├── generics/                  # Generic, type-agnostic tools
│   ├── Repository.java        # generic store + search (Predicate-based), backed by CustomArrayList
│   └── CustomArrayList.java   # hand-built dynamic array (no java.util.ArrayList)
├── exceptions/                 # Custom checked exceptions
│   ├── RoomFullException.java
│   ├── InvalidPaymentException.java
│   ├── StudentNotFoundException.java
│   └── ComplaintNotFoundException.java
└── util/
    └── FilePersistence.java    # save/load students to students.txt
```

---

## Features

- **Student & Warden Management** — add students (name, ID, contact, room, admission year) and wardens (name, ID, contact, assigned block)
- **Room Allocation** — assign students to rooms with capacity limits, enforced via `RoomFullException`
- **Payment System** — process dues payments with validation via `InvalidPaymentException`
- **Complaint System** — students raise complaints; wardens resolve them, which updates status and notifies the student
- **Defaulter Detection** — filter students with outstanding dues using a `Predicate` lambda
- **Deep Object Cloning** — `Complaint.clone()` performs a true deep copy (the referenced `Student` is cloned independently, not shared)
- **File Persistence** — save/load student records to `students.txt`
- **Custom Generic Collection** — `CustomArrayList<T>`, a hand-built dynamic array, used throughout instead of `java.util.ArrayList`

---

## How It Works

`Main.java` runs a menu-driven loop:

| Option | Function |
|--------|----------|
| 1 | Add Student |
| 2 | Add Warden |
| 3 | Add Room |
| 4 | Allocate Room to Student |
| 5 | Make Payment |
| 6 | Raise Complaint |
| 7 | Resolve Complaint (Warden action) |
| 8 | View Defaulters |
| 9 | Save Students to File |
| 10 | Load Students from File |
| 0 | Exit |

**Key data flow:**
- `Repository<T>` is a fully generic store — the same class manages `Student`s, `Warden`s, and `Complaint`s, using `Predicate<T>` lambdas (`findOne`, `filter`) so it never needs to know what `T` actually is
- `Student` implements `Payable` (dues/payments) and `Notifiable` (receiving notifications); `Warden` implements `Notifiable` only
- `Warden.resolveComplaint()` updates a complaint's status through a controlled setter and notifies the student via `Notifiable.sendNotification()`
- `Complaint.clone()` demonstrates a deep copy: `Student` also implements `Cloneable`, so cloning a `Complaint` produces an independent copy of its `Student` too, not a shared reference
- `FilePersistence` serializes students to comma-separated lines in a text file and reconstructs them on load

---

## Requirements

- Java JDK 8+
- No external dependencies — 100% standard library

---

## How to Run

**Easiest:** open the project in IntelliJ (or any IDE) and run `Main.java` directly.

**From the command line:**
```bash
# From the project root
javac -d out $(find src -name "*.java")
java -cp out campusconnect.Main
```

---

## Design Notes

- **Generics-first:** `Repository<T>` and `CustomArrayList<T>` are written to be completely type-agnostic — no domain assumptions baked in
- **Interface-driven behavior:** `Payable` and `Notifiable` let `Student` and `Warden` share behavior without inheriting from a common non-`Person` class
- **Checked exceptions model real failure points:** `RoomFullException`, `InvalidPaymentException`, `StudentNotFoundException`, `ComplaintNotFoundException` are all thrown at the exact point a business rule is violated, not as an afterthought
- **Deep vs. shallow cloning:** `Complaint.clone()` explicitly avoids Java's default shallow-copy pitfall by manually re-cloning its `Student` reference

---

## Future Improvements

These are known simplifications made to keep the project scoped and finishable — noted deliberately rather than overlooked:

- **No duplicate complaint detection** — two students reporting the same real-world issue currently create two separate `Complaint` entries
- **Complaints are matched by student ID only** — if a student has multiple open complaints, resolution always targets the first match; a unique complaint ID would allow precise selection
- **`Complaint.student` is currently a public field** rather than exposed only through a getter — a getter-only accessor would better preserve encapsulation, consistent with the rest of the codebase
- **No GUI** — this is a console application by design, to keep focus on the OOP/backend logic rather than UI concerns

---

## License

MIT License — free to use, modify, and reference.