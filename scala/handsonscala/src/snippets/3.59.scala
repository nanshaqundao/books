package me.nansha
package snippets

@ class Baz(x: Int) {
    val bangs = "!" * x
    def printMsg(msg: String) = {
      println(msg + bangs)
    }
  }
