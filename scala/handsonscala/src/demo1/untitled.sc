println("x")
val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
m
val x = 1
m.contains("two")

m("two")

m.get("one")
m.get("four")

val myArrayDeque = collection.mutable.ArrayDeque(1,2,3,4,5)
myArrayDeque.removeHead()
myArrayDeque.append(6)
myArrayDeque.foreach(x => println(x))
myArrayDeque.indexOf(2)



case class Point(x: Int, y: Int){
  def z = x+y
}
val p = Point(1,2)
p.toString
val p2 = Point(1,2)
p == p2

val p3 = p.copy(y=10)
val p4 = p.copy(x = 20)

p4.z
