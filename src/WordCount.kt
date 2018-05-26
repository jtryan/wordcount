import java.io.File
import java.io.InputStream


fun main(args: Array<String>) {

    if (args.size == 0) {
        println("You must provide a file name.")
    } else {
        val inputStream: InputStream  = File(args[0]).inputStream()
        val input = inputStream.bufferedReader().use{ it.readText() }
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
        var count = 0
        for (word in sortedList) {
            // only print top 10
            if (count < 10) {
                println("${word.first} = ${word.second}")
            }
            count += 1
        }
    }
}