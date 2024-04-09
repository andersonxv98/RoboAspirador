+greeting(M)[source(A)] <-  // plan to react to new beliefs
    .print("I received ",M," from ",A).

// some usual includes for JaCaMo projects:
{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }
{ include("$moise/asl/org-obedient.asl") }