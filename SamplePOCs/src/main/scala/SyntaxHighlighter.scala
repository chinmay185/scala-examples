val input = "If we write a program and compile it, then we can run the program to get output"
val keywords = List("as", "if", "and", "then", "when")
val output = "[blue]If[blue] we write a program [blue]and[blue] compile it, [blue]then[blue] we can run the program to get output"

def highlight(input: String, keywords: List[String]) = {
	var o = input
	for( k <- keywords) {
		o = input.replace(k, "[blue]"+k+"[blue]")		
	}	
}
