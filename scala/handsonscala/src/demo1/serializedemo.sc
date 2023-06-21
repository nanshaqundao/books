trait StrWriter[T] {
  def write(t: T): String
}

object StrWriter {
  implicit object WriteInt extends StrWriter[Int] {
    def write(s: Int) = s.toString
  }

  implicit object WriteBoolean extends StrWriter[Boolean] {
    def write(s: Boolean) = s.toString
  }

  implicit object WriteDouble extends StrWriter[Double] {
    def write(s: Double) = s.toString
  }

  implicit def ParseSeq[T](implicit w: StrWriter[T]) = new StrWriter[Seq[T]] {
    def write(t: Seq[T]) = t.map(w.write).mkString("[", ",", "]")
  }

  implicit def WriteTuple[T, V](implicit w1: StrWriter[T], w2: StrWriter[V])
  = new StrWriter[(T, V)] {
    def write(t: (T, V)) = {
      val (left, right) = t
      "[" + w1.write(left) + "," + w2.write(right) + "]"
    }
  }
}

def writeToString[T](t: T)(implicit writer: StrWriter[T]): String = writer.write(t)

def writeToConsole[T](t: T)(implicit writer: StrWriter[T]) = {
  scala.Console.out.println(writer.write(t))
}


val myInt = writeToString[Int](123)
val myBoolean = writeToString(true)
val myDouble = writeToString(7.5)

assert(myInt == "123")
assert(myBoolean == "true")
assert(myDouble == "7.5")

assert(writeToString(Seq(true, false, true)) == "[true,false,true]")
assert(writeToString(Seq(1, 2, 3, 4)) == "[1,2,3,4]")

assert(writeToString[(Int, Boolean)]((123, true)) == "[123,true]")
assert(writeToString[(Boolean, Double)]((true, 1.5)) == "[true,1.5]")

assert(
  writeToString(Seq((1, true), (2, false), (3, true), (4, false))) ==
    "[[1,true],[2,false],[3,true],[4,false]]"
)

assert(
  writeToString((Seq(1, 2, 3, 4, 5), Seq(true, false, true))) ==
    "[[1,2,3,4,5],[true,false,true]]"
)

val nested =
  Seq(
    (Seq(1), Seq(true)),
    (Seq(2, 3), Seq(false, true)),
    (Seq(4, 5, 6), Seq(false, true, false))
  )

writeToString(nested)

