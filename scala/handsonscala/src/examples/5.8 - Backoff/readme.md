# Example 5.8 - Backoff
A version of our by-name `def retry` method with configurable exponential
backoff

```bash
amm TestRetry.sc
```

## Upstream Example: [5.4 - ByName](https://github.com/handsonscala/handsonscala/tree/v1/examples/5.4%20-%20ByName):
Diff:
```diff
diff --git a/5.4 - ByName/Log.sc b/5.4 - ByName/Log.sc
deleted file mode 100644
index 3575b6a..0000000
--- a/5.4 - ByName/Log.sc	
+++ /dev/null
@@ -1,11 +0,0 @@
-
-var logLevel = 1
-
-def log(level: Int, msg: => String) = {
-  if (level > logLevel) println(msg)
-}
-
-log(2, "Hello " + 123 + " World")
-
-logLevel = 3
-log(2, "Hello " + 123 + " World")
diff --git a/5.4 - ByName/Measure.sc b/5.4 - ByName/Measure.sc
deleted file mode 100644
index cae8ece..0000000
--- a/5.4 - ByName/Measure.sc	
+++ /dev/null
@@ -1,13 +0,0 @@
-
-def measureTime(f: => Unit) = {
-  val start = System.currentTimeMillis()
-  f
-  val end = System.currentTimeMillis()
-  println("Evaluation took " + (end - start) + " milliseconds")
-}
-
-measureTime(new Array[String](10 * 1000 * 1000).hashCode())
-
-measureTime { // methods taking a single arg can also be called with curly brackets
-  new Array[String](100 * 1000 * 1000).hashCode()
-}
diff --git a/5.4 - ByName/Retry.sc b/5.8 - Backoff/Retry.sc
index aee6a04..8e0c23b 100644
--- a/5.4 - ByName/Retry.sc	
+++ b/5.8 - Backoff/Retry.sc	
@@ -1,10 +1,13 @@
 
-def retry[T](max: Int)(f: => T): T = {
+def retry[T](max: Int, delay: Int = 0)(f: => T): T = {
   var tries = 0
   var result: Option[T] = None
+  var currentDelay = delay
   while (result == None) {
     try { result = Some(f) }
     catch {case e: Throwable =>
+      Thread.sleep(currentDelay)
+      currentDelay *= 2
       tries += 1
       if (tries > max) throw e
       else {
diff --git a/5.4 - ByName/TestRetry.sc b/5.8 - Backoff/TestRetry.sc
index 0bd1be9..fb27d73 100644
--- a/5.4 - ByName/TestRetry.sc	
+++ b/5.8 - Backoff/TestRetry.sc	
@@ -1,5 +1,8 @@
 import $file.Retry, Retry._
-retry(max = 50) {
+
+val httpbin = "https://httpbin.org"
+
+retry(max = 50, delay = 50 /*milliseconds*/) {
   // Only succeeds with a 200 response
   // code 1/3 of the time
   requests.get(
```
