package me.nansha
package snippets

@ class Box(var x: Int) {
    def update(f: Int => Int) = x = f(x)
    def printMsg(msg: String) = {
      println(msg + x)
    }
  }
