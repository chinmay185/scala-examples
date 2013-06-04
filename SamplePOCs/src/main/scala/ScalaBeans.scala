import java.util.Date

class Student(id: Int, name: String, courses: List[Course]) {

	// the following statement becomes part of primary constructor
	println("constructing Student with id = " + id + " and name = " + name)

	override def toString() = name+" taking courses:"+courses
}

class Course(id: Int, name: String, startDate: Date, endDate: Date) {
	override def toString() = name
}

object Main {
	def main(args: Array[String]) {
		val c1 = new Course(1, "Introductory Mathematics", new Date(), new Date())
		val c2 = new Course(2, "Introductory Computer Science", new Date(), new Date())
		val c3 = new Course(3, "Introductory Physics", new Date(), new Date())
		val s1 = new Student(1, "John Snow", List(c1, c3))
		val s2 = new Student(2, "Tom Hanks", List())
		val s3 = new Student(3, "James Bond", List(c1, c2, c3))
		val s4 = new Student(4, "Harry Potter", List(c3))

		println(s1)
		println(s2)
		println(s3)
		println(s4)
	}

	def listDemo() = {
		
	}
}