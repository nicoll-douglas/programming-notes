# Intermediate Java

## Table of Contents

1. [Advanced OOP](#1-advanced-oop)
   1. [Interfaces](#11-interfaces)
   2. [Abstract Classes](#12-abstract-classes)
   3. [Enums](#13-enums)

## 1. Advanced OOP

### 1.1 Interfaces

#### Key Points:

- An interface defines a set of methods or behaviours as a **characteristic** that a class can implement
- Achieved with **abstract** methods
- Abstract methods are implicitly `public` and **cannot** be defined otherwise; only static methods may have other modifiers in interfaces
- Classes can implement multiple interfaces (comma separation) allowing Java to overcome limitation of single inheritance (a class can only extend one other class)
- Constants in interfaces are implicitly `public`, `static` and `final`
- The `@FunctionalInterface` directive can be used to indicate a functional interface you define
- Interfaces can inherit from other interfaces like classes with the `extends` keyword

#### Types of Methods:

- **Abstract** - a method with no body; must be given implementations in a class otherwise the class has to be defined with `abstract`
- **Static** - a method that can be called on the interface itself
- **Default** - a method with a default implementation; can be overriden by the implementing class (declared with the `default` keyword)

#### Interfaces & Variables

- You can also declare the type of a variable as an interface
- This means that the variable can refer to any object whose class implements that interface
- Allows for polymorphism since you can change the concrete implementation of the object at runtime without changing the code that uses the interface
- Allows for abstraction since code as such depends on the **behaviours** of the object rather than the specific implementation

### 1.2 Abstract Classes

#### Key Notes:

- Abstract classes are classes that cannot be instantiated and are specified with the `abstract` keyword
- They may have both abstract methods (unimplemented) and concrete methods (implemented)
- Abstract methods should be implemented by an adopting class
- If not all abstract methods implemented, then the class **must** be defined with `abstract`

#### Example:

```java
// abstract class abstracts away a blueprint for implementing classes
abstract class Animal {
  public abstract void makeSound();
}

class Dog extends Animal {
  // method is given concrete implementation
  @Override
  public void makeSound() {
    System.out.println("Dog barks");
  }
}
```

### 1.3 Enums

#### Key Notes:

- Enum is a special type (similar to a class) to denote a group of constants and is denoted with uppercase starting letter
- Enums can have fields, methods, constructors, nested classes, interfaces and enums like other classes
- Type safe so you can only assign enum values to variables of the same enum type
- Enums are immutable, cannot add or remove values once created (implicitly `public`, `static` and `final`, but can be `private` if not at the top level)
- Cannot instantiate new enums, instances are created when the enum is loaded
- Enums inherit methods from the `java.lang.Enum` class
- Java also provides several built in enums (e.g `java.time.Month`)

#### Example:

```java
public enum Day {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}
```

#### Fields, Methods & Constructors in Enums

- Fields in Enums can store additional information about each constant
- Enum constructors are used to initialise these values
- Enum constructors are implicitly `private` and cannot take on any other access modifier
- Enum constructors are called once per constant
- Enums can have instance and static methods to be called on the constants or the enum itself
- For instance members, `this` refers to the current constant

E.g:

```java
public enum Fruit {
  // constants use the Fruit constructor
  APPLE("Red"),
  BANANA("Yellow");

  public final String color; // stores color of fruit constant

  Fruit(String color) {
    this.color = color;
  }
}

Fruit fruit = Fruit.APPLE;
fruit.color; // returns "Red"
```

#### Advanced Enums

- Enums can implement interfaces much like classes
- Enums can also have their own abstract methods
- Each enum constant must provide its own custom implementation of all the abstract methods from their source

##### Example:

```java
public enum Operation {
  // if using a constructor, just utilise the syntax as normal to initiliase the instance fields
  ADD {
    @Override
    public int apply(int x, int y) {
      return x + y;
    }
  },
  SUBTRACT {
    @Override
    public int apply(int x, int y) {
      return x - y;
    }
  }

  public abstract int apply(int x, int y);
}
```

## 2. Collections

### 2.1 Hierarchies

#### Iterable "Types"

<img src="images/collections-iterable.png" width="700" alt="Iterable Hierarchy" />

#### Map "Types"

<img src="images/collections-map.png" width="700" alt="Map Hierarchy" />
