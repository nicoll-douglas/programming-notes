# Intermediate Java

## Table of Contents

1. [Advanced OOP](#1-advanced-oop)
   1. [Interfaces](#11-interfaces)
   2. [Abstract Classes](#12-abstract-classes)
   3. [Enums](#13-enums)
2. [Generics](#2-generics)
   1. [About Generics](#21-about-generics)
   2. [Generic Methods, Classes & Interfaces](#22-generic-methods-classes--interfaces)
   3. [Bounded Type Parameters](#23-bounded-type-parameters)
   4. [Wildcard Types](#24-wildcard-types)
   5. [Inheritance](#25-inheritance)
   6. [Type Erasure](#26-type-erasure)
   7. [Restrictions](#27-restrictions)
3. [Collections](#3-collections)
   1. [Hierarchies](#31-hierarchies)

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

##### Example:

```java
interface Animal {
  void makeSound();
}

class Dog implements Animal {
  public void makeSound() {
      System.out.println("Bark");
  }
}

class Cat implements Animal {
  public void makeSound() {
      System.out.println("Meow");
  }
}

Animal myAnimal = new Dog(); // myAnimal can hold any object that implements Animal
myAnimal.makeSound(); // "Bark"

myAnimal = new Cat(); // now it's a cat (run-time polymorphism)
myAnimal.makeSound(); // "Meow"

// the makeSound() method call is an example of abstraction
// declaring myAnimal with Animal means we can care more about the behaviours rather than the implementation
```

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

## 2. Generics

### 2.1 About Generics

#### Key Points:

- Generics allow you to parameterize classes, interfaces and methods
- This means you can define a class or method that works with different types without sacrificing type safety

#### Advantages:

- Allows code reusability
- Enforces compile-time type checking reducing the chance of a `ClassCastException` (you can't accidentally insert a value of the wrong type into a collection or object that uses generics
  )
- Generics remove the need for explicit casting

E.g:

```java
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // Explicit cast needed

List<String> list2 = new ArrayList<>(); // String type in generic
list2.add("Hello");
String str = list.get(0); // No cast needed since type is concrete
```

### 2.2 Generic Methods, Classes & Interfaces

#### Example 1 (Generic Methods)

```java
public <T> void method1(T param) {
  // T is the type parameter
}

public <T, U> U method2(T t, U u) {
  // T and U are the type parameters
  return u; // returns type U
}
```

#### Example 2 (Generic Class)

```java
// Class with type T
class Box<T> {
  T item;

  Box(T item) {
    this.item = item;
  }
}
```

#### Example 3 (Generic Interface)

```java
// interface with type T
interface Reusable<T> {
  void methodName(T param); // abstract method that operates on a param of the supplied type T
}
```

### 2.3 Bounded Type Parameters

- Used in scenarios where you want to restrict the types that can be passed as type arguments to a certain class or its subclasses

Example:

```java
// T must be Number or any subclass of Number
class Box<T extends Number> {
  T item;

  Box(T item) {
      this.item = item;
  }
}
```

### 2.4 Wildcard Types

#### Key Notes:

- You cannot add elements to a collection with a wildcard type as the type cannot be guaranteed during compilation

#### Example 1 (Unbounded)

```java
// ? represents the wildcard (unknown type)
public void printList(List<?> list) {
  for (Object obj : list) {
    System.out.println(obj);
  }
}
```

#### Example 2 (Upper Bounded)

```java
// the list can only accept type Number or any subclass of Number
public void display(List<? extends Number> list) {
  for (Number num : list) {
    System.out.println(num);
  }
}
```

#### Example 3 (Lower Bounded)

```java
// the list can only accept type Integer or any superclass of Integer
public void addNumbers(List<? super Integer> list) {
  list.add(100);
}
```

### 2.5 Inheritance

- Generics in Java do not support polymorphism and inheritance in the way regular types do
- For example, `List<Integer>` is not a subclass of `List<Number>` even though `Integer` is a subclass of `Number`
- They are treated as completely separate types

Example:

```java
List<Number> numbers = new ArrayList<>();
List<Integer> integers = new ArrayList<>();
numbers = integers;  // Compilation error

List<? extends Number> numbers2 = new ArrayList<>();
numbers2 = integers; // correct as the wildcard type can accept Number or any subclass of Number (in this case Integer)
```

### 2.6 Type Erasure

#### Key Notes;

- Generics are implemented using type erasure
- Means generic type information is replaced during compilation
- Before compilation: `List<String>`
- After compilation: `List`

#### Example:

```java
List<String> list = new ArrayList<>();
list instanceof List<String>; // cause compile-time error as the generic type information has been replaced
```

### 2.7 Restrictions

- Static fields and methods cannot utilise generic types since they are tied to the instance of the class
- You cannot use primitives as type arguments for generics (can only use wrappers)
- You cannot instantiate objects of parameterized types (e.g `new T[10]` is illegal, type must be concrete)

## 3. Collections

### 3.1 Hierarchies

#### Iterable "Types"

<img src="images/collections-iterable.png" width="700" alt="Iterable Hierarchy" />

#### Map "Types"

<img src="images/collections-map.png" width="700" alt="Map Hierarchy" />
