!say_hello.   // initial goal

+!say_hello   // plan to achieve goal say_hello
   <- .send(bob,tell,greeting("hello world")).

// some usual includes for JaCaMo projects:
{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }
{ include("$moise/asl/org-obedient.asl") }