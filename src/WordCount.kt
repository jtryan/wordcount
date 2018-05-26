import java.io.File
import java.io.InputStream


fun main(args: Array<String>) {

    val inputStream: InputStream  = File("test.txt").inputStream()

    val input = inputStream.bufferedReader().use{ it.readText() }

//    val input = "Hello there my name is Bob. I like to eat tacos, because I think they are great! bob bob Bob"

    val words = input.replace(",", "")
            .replace(".", "")
            .replace("!", "")
            .replace("?", "")
            .replace("\n", " ")
            .split(" ")
    val wordMap = mutableMapOf<String, Int>()
    for (word in words) {
        if (wordMap[word.capitalize()] == null) {
            wordMap[word.capitalize()] = 1
        } else {
            val wordCount = wordMap[word.capitalize()]!!
            wordMap[word.capitalize()] = wordCount + 1
        }
    }

    val wordList = wordMap.toList()
    val sortedList = wordList.sortedWith(compareByDescending({it.second}))

    for (word in sortedList) {
        println("${word.first} = ${word.second}")
    }
}