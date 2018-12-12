package net.println.kotlin.chapter3

/**
 * Created by benny on 3/19/17.
 */
class Student{
    fun isNotClothedProperly(): Boolean{
        return false
    }
}

fun main(args: Array<String>) {
    val students = ArrayList<Student>()
    val you = Student()
    for (student in students){
        if(student == you) continue
        if(student.isNotClothedProperly()){
            break
        }
    }
}