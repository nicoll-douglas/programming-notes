# Java Notes

1. [Data Types & Variables](#1-data-types--variables)
   1. [Variables](#11-variables)
   2. [Primitive Types](#12-primitive-types)
   3. [Reference Types (Objects)](#13-reference-types-objects)
2. [Type Casting](#2-type-casting)
   1. [Implicit Casting (Widening)](#21-implicit-casting-widening)
   2. [Explicit Casting (Narrowing)](#22-explicit-casting-narrowing)
   3. [Boolean Contexts](#23-boolean-contexts)
3. [Operators & Booleans](#3-operators--booleans)
   1. [Comparison](#31-comparison)
   2. [null](#32-null)
   3. [switch](#33-switch)
   4. [instanceof](#34-instanceof)
   5. [XOR](#35-xor)
4. [Loops](#4-loops)
   1. [For-each Loop](#41-for-each-loop)
5. [Arrays](#5-arrays)
   1. [About Arrays](#51-about-arrays)
   2. [Creating Arrays](#52-creating-arrays)
6. [Functions](#6-functions)
   1. [Method Overloading](#61-method-overloading)
   2. [Lambda Expressions](#62-lambda-expressions)
   3. [Method References](#63-method-references)
7. [OOP](#7-oop)
   1. [Inheritance](#71-inheritance)
   2. [Encapsulation](#72-encapsulation)
   3. [Polymorphism](#73-polymorphism)
   4. [Abstraction](#74-abstraction)
8. [Access Modifiers](#8-access-modifiers)
   1. [About Access Modifiers](#81-about-access-modifiers)
   2. [Main Modifiers](#82-main-modifiers)
   3. [Summary](#83-summary)

## 1. Data Types & Variables

### 1.1 Variables

- Variables **must** be initiliased before utilisation

```java
int i; // declared
i = 10; // initialised (assigned)

int j = 10; // in one step

int x, y, z = 10; // in one line
```

### 1.2 Primitive Types

- All primitive types passed by value

#### Integers

```java
byte b = 12; // 1 byte
short s = 236; // 2 bytes
int i = 153; // 4 bytes
long l = 100L; // 8 bytes
```

- int and long default to int, specify L to declare as long

#### Floats

```java
float f = 100.23f; // 4 bytes
double d = 100.23; // 8 bytes
```

- float and double default to double, specify f to declare as float

#### Non-numerics

```java
boolean b = true; // 1 bit
char c = 'c'; // 2 bytes (\u0000 to \uFFFF so UTF-16)
```

### 1.3 Reference Types (Objects)

- All reference types passed by reference

#### Strings

```java
String s = "hello world";
```

- When initiliased with a string literal, string object lives in the string pool
- Any other string literals declared return the same reference
- Strings that are instatiated have a separate reference in the heap
- Strings are immutable (but can be reassigned normally)

#### Objects

```java
Object o = new Object();
```

## 2. Type Casting

### 2.1 Implicit Casting (Widening)

- Converting from smaller-size type to larger size-type that results in no data loss and can accomodate it
- When adding characters or numbers with strings, they get upgraded to a string to return a string

#### Valid Casts

1. **byte → short → int → long → float → double**
2. **char → int → long → float → double**

- Cannot convert from byte or short to char as char is unsigned so data loss would occur due to limited range
- Can convert from long or big int to float because precision loss is not critical (float range contains entire range of longs and ints)

### 2.2 Explicit Casting (Narrowing)

- Converting from larger-size type to smaller size-type that results in data loss since it can't accomodate it
- Any data outside the size range gets "chopped off" and then converted by according value

#### Casts

1. **double → float → long → int → short → byte**
2. **double → float → long → int → char**

### 2.3 Boolean Contexts

- In a boolean context such as an `if` conditional or ternary operator, expression **must** evaluate to a boolean
- In such scenarios, automatic conversion **does not** occur for non-boolean types and throws an error (unlike JS)

## 3. Operators & Booleans

### 3.1 Comparison

- `==` and `!=` operators compare primitives by value and objects by reference
- String literals that are equal return the same reference from the string pool
- Comparison can be made with `.equals()` methods that may perform custom implementations
- When doing arithmetic comparison, smaller types are [implicitly casted](#implicit-casting-widening) or "promoted" upwards for accurate comparison

### 3.2 null

- `null` indicates the absence of an object reference
- `null` **can only** be assigned to reference types
- `null == null` evaluates to `true`

### 3.3 switch

- Switch statements are compared by value
- For strings or wrappers, they are compared with `.equals()`
- Case statement values must be **implicitly castable** if primitive otherwise have to be explicitly casted
- Cannot switch on reference types unless `.equals()` method overriden meaningfully

#### Valid expression types that can be switched on:

- **byte, short, int** (and their wrappers)
- **char** (and wrapper)
- **String** (both literal and Object)
- **enum** values

### 3.4 instanceof

- `instanceof` returns `true` if the object is an instance of the specified class
- Returns `true` if the object implements the specified interface
- Returns `false` if the object is `null`

### 3.5 XOR

```java
boolean a = true;
boolean b = false;
a ^ b // returns exclusive or (XOR) value
```

## 4. Loops

### 4.1 For-each Loop

- Used to iterate over an array or a collection that implements the `Iterable` interface
- The loop variable cannot be modified unless it is a mutable object (throws exception otherwise)

#### Syntax:

```java
for (type element : collection) {
  // code
}
```

## 5. Arrays

### 5.1 About Arrays

- Arrays can only store items of the same type
- Array lengths are fixed and cannot be changed (final field `.length` on the array)
- The array class is a dynamically created runtime class and extends `Object`
- Inherites basic methods such as `.getClass()`, `.equals()`
- Arrays implicitly implement the `Cloneable` and `Serializable` interfaces (allows for cloning and serialization)
- Use the `Arrays` class for more useful static methods that operate on arrays

### 5.2 Creating Arrays

#### Method 1

- Array values default to the default value of their type (i.e `0` for int, `false` for boolean and `null` for objects)

```java
int[] numbers = new int[5]; // array of size 5: {0, 0, 0, 0, 0}
```

#### Method 2

```java
int[] numbers = {1, 2, 3, 4, 5};
```

#### Method 3

- `new` keyword must be used to intialise anonymous arrays

```java
someMethod(new int[]{1, 4}); // uses new keyword
```

## 6. Functions

### 6.1 Method Overloading

- Methods have signatures which is the method name and types/number of parameters
- Methods can be "overloaded" to accept different parameters

#### e.g:

```java
// method that gets called depends on the arguments

static int add(int x, int y) {
  return x + y;
}

static double add(double x, double y) {
  return x + y;
}
```

### 6.2 Lambda Expressions

- Anonmyous functions typically passed as arguments to methods (like JS arrow functions)
- Parameter types don't have to be specified if they can be inferred from the interface
- Can be stored in variables if type is a functional interface (interface with exactly one abstract method)
- Override methods do not count towards the abstract method count in an interface

#### Common functional interfaces:

- **Supplier\<T>** (method: get, takes none, returns any)
- **BiFunction\<T, U, R>** (method: apply, takes any 2, returns any)
- **Function\<T, R>** (method: apply, takes any, returns any)
- **Predicate\<T>** (method: test, takes any, returns bool)
- **Consumer\<T>** (method: accept, takes any, returns none)

#### Assigment example:

```java
Predicate<Integer> greaterThan5 = (x) -> x > 5; // param type inferred from interface
greaterThan5.test(3) // test is the abstract method, returns false
```

### 6.3 Method References

- Can be a lambda shorthand since it simply calls a predefined method

#### Types:

1. Ref to static method (`ClassName::staticMethod`)
2. Ref to instance method (`instance::instanceMethod`)
3. Ref to instance method of arbitrary object of particular type (`ClassName::instanceMethod`)
4. Ref to constructor (`ClassName::new`)

#### Examples:

Static method:

```java
Function<Double, Double> sqrtRef = Math::sqrt;
// same as (x) -> Math.sqrt(x)
```

Instance method:

```java
Consumer<String> printlnRef = System.out::println;
// instance method of PrintStream object System.out
// same as (s) -> System.out.println(s)
```

Instance method, arbitrary object:

```java
Function<String, Integer> lengthRef = String::length;
// same as (s) -> s.length()
```

Constructor:

```java
Supplier<Object> constructorRef = Object::new;
// same as () -> new Object()
```

## 7. OOP

### 7.1 Inheritance

- Allows classes to inherit fields and methods from other classes
- The `extends` keyword allows classes to inherit all methods and properties from its parent class (super class)
- Subclasses must use the `super` keyword

e.g:

```java
class Animal {
  public int legCount;

  public Animal(int legCount) {
    this.legCount = legCount;
  }
}

class Dog extends Animal {
  public Dog() {
    super(2);
  }
}

Dog d = new Dog();
d.legCount; // evaluates to 2, Dog inherits legCount field
```

### 7.2 Encapsulation

- Data fields and methods are bundled into a singular class
- Access to fields and methods can be restricted to hide the internal state of the object

e.g:

```java
class Person {
  private String name;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

Person p = new Person("Bob");

p.name; // throws exception, name field can only be used internally
p.getName(); // get method provides access, returns "Bob",
p.setName("John"); // set method allows modification, sets p.name to "John",
```

### 7.3 Polymorphism

- Allows one interface to be used for a general class of actions
- In simpler terms an interface or method can take on many forms on a need-to basis

#### Compile-time Polymorphism (Static)

- Achieved by method overloading

#### Run-time Polymorphism (Dynamic)

- Achieved by method overriding
- Happens during runtime where the object determines which method to invoke

e.g:

```java
class Animal {
  public void makeSound() {
    System.out.println("Animal makes a sound");
  }
}

class Dog extends Animal {
  @Override
  public void makeSound() {
    System.out.println("Dog barks");
  }
}

Animal dog = new Dog();
Animal a = new Animal();

dog.makeSound(); // "Dog barks", object type is Dog
a.makeSound();  // "Animal makes a sound", object type is Animal
```

### 7.4 Abstraction

- Abstraction hides the complex implementation details of the object and only exposes the essential features of the object
- Achieved with **abstract classes** or **interfaces**

#### Abstract Classes & Interfaces

- Abstract classes are classes that cannot be instantiated and are specified with the `abstract` keyword
- They may have both abstract methods (unimplemented) and concrete methods (implemented)
- Interfaces typically only contain abstract methods to be implemented by classes
- Abstract methods should be implemented by an adopting class
- If not all abstract methods implemented, then the class **must** be defined with `abstract`
- A class can use the `implements` keyword to implement an interface (multiple can be implemented with comma separation)

#### Example of abstraction:

```java
abstract class Animal {
  public abstract void makeSound();
}

// class is not abstract since all methods implemented
class Dog extends Animal {
  // method is given concrete implementation
  @Override
  public void makeSound() {
    System.out.println("Dog barks");
  }
}
```

## 8. Access Modifiers

### 8.1 About Access Modifiers

- Used to specify the visibility of methods, variables and constructors (also called members)
- Controls where they can be accessed from
- Top level classes can only specified as public or package-private by default
- Nested classes can be specified with any modifier (since they count as members)

### 8.2 Main Modifiers

#### `Public`

- The member can be accessed from any other class, inside or outside the package

#### `Protected`

- The member is accessible to all classes within the same package and by all subclasses (even if the class is in a different package)

#### `Private`

- The member is only accessible within the same class (or class body)
- Private fields and methods cannot be accessed from any other class including subclasses

#### **Default** (no modifier)

- The member can be accessed from any other class in the package, but not from outside the package
- Is also referred to as "package-private"

### 8.3 Summary

- A summary of the level of the access a program has to members of a class with the specified modifier

| Modifier    | Within Class | Same Package | Subclass (Different Package) | Outside Package |
| ----------- | ------------ | ------------ | ---------------------------- | --------------- |
| `public`    | Yes          | Yes          | Yes                          | Yes             |
| `protected` | Yes          | Yes          | Yes                          | No              |
| default     | Yes          | Yes          | No                           | No              |
| `private`   | Yes          | No           | No                           | No              |
