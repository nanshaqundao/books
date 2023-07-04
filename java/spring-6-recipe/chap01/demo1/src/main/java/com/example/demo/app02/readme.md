# 1-2. Create POJOs by Invoking a Constructor
## Problem
You would like to create a POJO instance or bean in the Spring IoC container by invoking its constructor, which is the most common and direct way of creating beans. It is equivalent to using the new operator to create objects in Java.

## Solution
Define a POJO class with a constructor or constructors. Next, create a Java Config class to configure POJO instance values with constructors for the Spring IoC container. Next, instantiate the Spring IoC container to scan for Java classes with annotations. The POJO instances or bean instances become accessible to put together as part of an application.


## How It Works
Suppose you’re going to develop a shop application to sell products online. First of all, you create the Product class, which has several properties, such as the product name and price. As there are many types of products in your shop, you make the Product class abstract to extend it for different product subclasses.