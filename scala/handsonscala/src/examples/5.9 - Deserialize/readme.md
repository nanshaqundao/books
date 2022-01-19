# Example 5.9 - Deserialize
Using recursive typeclass inference to parse and de-serialize arbitrarily-deep
data structures from a JSON-like syntax

```bash
amm TestTypeclassInference.sc
```

## Upstream Example: [5.6 - TypeclassInference](https://github.com/handsonscala/handsonscala/tree/v1/examples/5.6%20-%20TypeclassInference):
Diff:
```diff
diff --git a/5.6 - TypeclassInference/TestTypeclassInference.sc b/5.9 - Deserialize/TestTypeclassInference.sc
index 117ac46..490225b 100644
--- a/5.6 - TypeclassInference/TestTypeclassInference.sc	
+++ b/5.9 - Deserialize/TestTypeclassInference.sc	
@@ -9,18 +9,45 @@ assert(myInt == 123)
 assert(myBoolean == true)
 assert(myDouble == 7.5)
 
-assert(parseFromString[Seq[Boolean]]("true,false,true") == Seq(true, false, true))
-assert(parseFromString[Seq[Int]]("1,2,3,4") == Seq(1, 2, 3, 4))
+assert(parseFromString[Seq[Boolean]]("[true,false,true]") == Seq(true, false, true))
+assert(parseFromString[Seq[Int]]("[1,2,3,4]") == Seq(1, 2, 3, 4))
 
-assert(parseFromString[(Int, Boolean)]("123=true") == (123, true))
-assert(parseFromString[(Boolean, Double)]("true=1.5") == (true, 1.5))
+assert(parseFromString[(Int, Boolean)]("[123,true]") == (123, true))
+assert(parseFromString[(Boolean, Double)]("[true,1.5]") == (true, 1.5))
 
 assert(
-  parseFromString[Seq[(Int, Boolean)]]("1=true,2=false,3=true,4=false") ==
+  parseFromString[Seq[(Int, Boolean)]]("[[1,true],[2,false],[3,true],[4,false]]") ==
   Seq((1, true), (2, false), (3, true), (4, false))
 )
 
 assert(
-  parseFromString[(Seq[Int], Seq[Boolean])]("1,2,3,4,5=true,false,true") ==
+  parseFromString[(Seq[Int], Seq[Boolean])]("[[1,2,3,4,5],[true,false,true]]") ==
   (Seq(1, 2, 3, 4, 5), Seq(true, false, true))
 )
+
+val nested = parseFromString[Seq[(Seq[Int], Seq[Boolean])]](
+  "[[[1],[true]],[[2,3],[false,true]],[[4,5,6],[false,true,false]]]"
+)
+pprint.log(nested)
+assert(
+  nested ==
+  Seq(
+    (Seq(1), Seq(true)),
+    (Seq(2, 3), Seq(false, true)),
+    (Seq(4, 5, 6), Seq(false, true, false))
+  )
+)
+
+val nested2 = parseFromString[Seq[(Seq[Int], Seq[(Boolean, Double)])]](
+  "[[[1],[[true,0.5]]],[[2,3],[[false,1.5],[true,2.5]]]]"
+)
+
+pprint.log(nested2)
+
+assert(
+  nested2 ==
+  Seq(
+    (Seq(1), Seq((true, 0.5))),
+    (Seq(2, 3), Seq((false, 1.5), (true, 2.5)))
+  )
+)
diff --git a/5.6 - TypeclassInference/TypeclassInference.sc b/5.9 - Deserialize/TypeclassInference.sc
index 135d9ab..8ded262 100644
--- a/5.6 - TypeclassInference/TypeclassInference.sc	
+++ b/5.9 - Deserialize/TypeclassInference.sc	
@@ -12,22 +12,39 @@ object StrParser{
   }
 
   implicit def ParseSeq[T](implicit p: StrParser[T]) = new StrParser[Seq[T]]{
-    def parse(s: String) = s.split(',').toSeq.map(p.parse)
+    def parse(s: String) = splitExpressions(s).map(p.parse)
   }
 
   implicit def ParseTuple[T, V](implicit p1: StrParser[T], p2: StrParser[V]) =
     new StrParser[(T, V)]{
       def parse(s: String) = {
-        val Array(left, right) = s.split('=')
+        val Seq(left, right) = splitExpressions(s)
         (p1.parse(left), p2.parse(right))
       }
     }
 }
 
-def parseFromString[T](s: String)(implicit parser: StrParser[T]) = {
-  parser.parse(s)
-}
+def parseFromString[T](s: String)(implicit parser: StrParser[T]) = parser.parse(s)
 
 def parseFromConsole[T](implicit parser: StrParser[T]) = {
   parser.parse(scala.Console.in.readLine())
 }
+
+def splitExpressions(s: String): Seq[String] = {
+  assert(s.head == '[')
+  assert(s.last == ']')
+  val indices = collection.mutable.ArrayDeque.empty[Int]
+  var openBrackets = 0
+  for(i <- Range(1, s.length - 1)){
+    s(i) match{
+      case '[' => openBrackets += 1
+      case ']' => openBrackets -= 1
+      case ',' =>
+        if (openBrackets == 0) indices += i
+      case _ => // do nothing
+    }
+  }
+  val allIndices = Seq(0) ++ indices ++ Seq(s.length - 1)
+  for(i <- Range(1, allIndices.length).toList)
+  yield s.substring(allIndices(i - 1) + 1, allIndices(i))
+}
```
